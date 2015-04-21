/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.hardCoding;

import common.ConvertUtils;
import common.ElementPrintableLinkedList;
import common.EnhancedLinkedList;
import common.MaxAveMinTuple;
import loadPrediction.config.ConfigureFactory;
import loadPrediction.config.WorkdayPredictorCfg;
import loadPrediction.config.predictionCalculator.XmlCalculatorCfg;
import loadPrediction.core.EnhancedSimilarCoeCalculator;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.core.predictor.visitors.IPredictorVisitor;
import loadPrediction.core.workday.IPredictionLoadTupleCalculator;
import loadPrediction.core.workday.PredictionLoadTupleCalculatorWithPerUnit;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOLoadBase;
import loadPrediction.domain.*;
import loadPrediction.exception.LPE;
import loadPrediction.resouce.IOPaths;
import loadPrediction.utils.Date2StringAdapter;
import loadPrediction.utils.PowerSystemDateUtil;
import loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 创建：21:06
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class HardCodingWorkdayPredictor extends AbstractTemplateMethodForHardCodingPredictor implements IWorkdayPredictor {

    private static DAOLoadBase daoLoadBase = DAOFactory.getDefault().createDaoLoadBase();
    private static WorkdayPredictorCfg workdayPredictorCfg = ConfigureFactory.getInstance().getXmlConfigure().getAllConfiguration().getWorkdayPredictorCfg();

    public HardCodingWorkdayPredictor(Date date, Boolean dbg) {
        super(date, dbg);

    }

    @Override
    public Object accept(IPredictorVisitor visitor) throws LPE {
        return visitor.visitWorkdayPredictor(this);

    }

    @Override
    public String getPredictorType() {
        return "HARD-CODING 工作日";
    }

    @Override
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcCorrectCoes(ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuples, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad) {

        ElementPrintableLinkedList<MaxAveMinTuple<Double>> coes = new ElementPrintableLinkedList<MaxAveMinTuple<Double>>("");
        Integer predictionDays = predictionLoadTuples.size();
        for (int i = 0; i < predictionDays; i++) {
            MaxAveMinTuple<Double> coe = new MaxAveMinTuple<Double>("load_correct_coes");
            coe.max = predictionLoadTuples.get(i).max - similarLoad.get(0).get(i).toMaxAveMin().max;
            coe.min = predictionLoadTuples.get(i).min - similarLoad.get(0).get(i).toMaxAveMin().min;
            coes.add(coe);
        }
        return coes;
    }

    @Override
    protected List<Integer>
    doGetHistoryDaysNumbers() {
        List<Integer> numbers = new LinkedList<Integer>();
        numbers.add(workdayPredictorCfg.getHistoryWorkdayNumber());
        return numbers;
    }

    @Override
    protected Integer
    doGetPredictionDaysNumber() {
        return workdayPredictorCfg.getPredictionWorkdayNumber();
    }

    @Override
    protected Boolean
    doValidate(Date date) {
        return ((new PowerSystemDateUtil().isPowerSystemWorkday(date)) && workdayPredictorCfg.getIsEnabled());
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    doGetHistoryDays(Date date, List<Integer> numbers) throws LPE {
        PowerSystemWorkdayQuery dq = null;
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("unnamed");
        try {
            dq = new PowerSystemWorkdayQuery(date);
            history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("historyDays");
            ElementPrintableLinkedList<SimpleDate> list = ConvertUtils.toElementPrintableLinkedList(dq.list(numbers.get(0) + 1, numbers.get(0) + 1), "workday");
            list.removeLast();
            history.add(list);
        } catch (Exception e) {
        }


        if (workdayPredictorCfg.getHistoryWorkdayNumber() != history.get(0).size()) {
            throw new LPE();
        }

        return history;
    }

    @Override
    protected ElementPrintableLinkedList<SimpleDate>
    doGetPredictionDays(Date date, Integer number) throws LPE {
        try {
            List<SimpleDate> list = new PowerSystemWorkdayQuery(date).list(1, number);
            if (list.size() != number) {
                throw new LPE("");
            }
            return ConvertUtils.toElementPrintableLinkedList(list, "prediction Days");
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>>
    doCalcSimilarCoes(ElementPrintableLinkedList<WeatherData> predictionWeather, ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> coes = new ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>>("");

        ElementPrintableLinkedList<EnhancedLinkedList<Double>> subCoes = new ElementPrintableLinkedList<EnhancedLinkedList<Double>>("");

        Integer predictionDays = predictionWeather.size();
        Integer historyDays = historyWeather.get(0).size();

        List<WeatherData> listAllWeatherData = new LinkedList<WeatherData>();
        for (int i = 0; i < historyDays; i++) {
            listAllWeatherData.add(historyWeather.get(0).get(i));
        }
        for (int i = 0; i < predictionDays; i++) {
            listAllWeatherData.add(predictionWeather.get(i));
        }

        EnhancedSimilarCoeCalculator similarCoeCalculator = new EnhancedSimilarCoeCalculator(new WeatherCoesPackage(DAOFactory.getDefault().createDaoWeatherCoes4Workday()));

        for (int i = 0; i < predictionDays; i++) {
            WeatherData wdNow, wdBefore;
            wdNow = predictionWeather.get(i);
            EnhancedLinkedList<Double> coe = new EnhancedLinkedList<Double>("");
            for (int j = 1; j <= historyDays; j++) {
                wdBefore = listAllWeatherData.get(historyDays + i - j);
                Double subcoe = similarCoeCalculator.calcSimilarCoe(wdNow, wdBefore);
                coe.add(subcoe);
            }
            subCoes.add(coe);
        }
        coes.add(subCoes);
        return coes;
    }

    //TODO
    @Override
    protected String doCheckAccuracy(Double threshold) {

        return null;
    }


    /**
     * @param predictionDays 预测日链表。
     * @param similarLoads   相似日的实际96点负荷数据。
     * @param correctCoes    各预测日的最大、最小修正量链表。
     * @return XXX
     */
    @Override
    protected ElementPrintableLinkedList<LoadData> doCalcPredictionLoad(ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads, ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes) {

        Integer predictionDaysNbr = predictionDays.size();
        ElementPrintableLinkedList<LoadData> loadDatas = new ElementPrintableLinkedList<LoadData>("prediction_load_datas");
        for (int i = 0; i < predictionDaysNbr; i++) {

            loadDatas.add(getOnePredictionLoad(predictionDays.get(i).getDateString(), similarLoads.get(0).get(i), correctCoes.get(i)));
        }
//        for (int i = 0; i <predictionDaysNbr-1 ; i++) {
//            listPredictionLoadData.get(i).setNext(listPredictionLoadData.get(i+1));
//        }
        return loadDatas;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    doCalcSimilarDays(Integer predictionDaysNumber, ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes) {
        Integer similarOffset;
        Double min;
        Integer predictionDaysNbr = predictionDaysNumber;
        Integer historyDaysNbr = historyDays.get(0).size();
        ElementPrintableLinkedList<SimpleDate> listAllDate = new ElementPrintableLinkedList<SimpleDate>("");
        ElementPrintableLinkedList<SimpleDate> listSimilarDate = new ElementPrintableLinkedList<SimpleDate>("");
        for (int i = 0; i < historyDaysNbr; i++) {
            listAllDate.add(historyDays.get(0).get(i));
        }
        for (int i = 0; i < predictionDaysNbr; i++) {
            listAllDate.add(predictionDays.get(i));
        }


        for (int i = 0; i < predictionDaysNbr; i++) {
            List<Double> coes = similarCoes.get(0).get(i);
            /*[初步计算相似日日期]*/
            min = coes.get(0);
            similarOffset = 1;
            for (int j = 0; j < historyDaysNbr; j++) {
                if (coes.get(j) < min) {
                    min = coes.get(j);
                    similarOffset = 1 + j;
                }
            }
            Date dateBefore = listAllDate.get(i + historyDaysNbr - similarOffset).getDate();
            Date dateAfter = predictionDays.get(i).getDate();
            if (similarOffset == 1 && (dateAfter.toLocalDate().getDayOfMonth() - dateBefore.toLocalDate().getDayOfMonth() == 1)) {//若日期差1，重新计算相似日:[实际相似日日期]
                min = coes.get(1);
                similarOffset = 2;
                for (int j = 1; j < historyDaysNbr; j++) {
                    if (coes.get(j) < min) {
                        min = coes.get(j);
                        similarOffset = 1 + j;
                    }
                }
            }
            listSimilarDate.add(listAllDate.get(i + historyDaysNbr - similarOffset));
        }
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> ret = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("similar_days");
        ret.add(listSimilarDate);
        return ret;
    }


    private static List<Map<String, Double>> calculator;

    {
        try {
            calculator = new XmlCalculatorCfg(IOPaths.WORKDAY_PREDICTION_CALCULATOR_CONFIGURATION).getConfiguration();
        } catch (LPE e) {
        }

    }

    ;

    private LoadData getOnePredictionLoad(String date, LoadData similarLoadData, MaxAveMinTuple<Double> correctCoe) {
        LoadData loadData = new LoadData();

        loadData.setDateString((date));
        loadData.setName("prediction_load_data_of_" + loadData.getDateString());
        loadData.setData00(similarLoadData.getData00());
        loadData.setData01(similarLoadData.getData01());
        loadData.setData02(similarLoadData.getData02());
        loadData.setData03(similarLoadData.getData03());
        loadData.setData04(similarLoadData.getData04());
        loadData.setData05(similarLoadData.getData05());
        loadData.setData06(similarLoadData.getData06());
        loadData.setData07(similarLoadData.getData07());
        loadData.setData08(similarLoadData.getData08());
        loadData.setData09(similarLoadData.getData09());
        loadData.setData10(similarLoadData.getData10());
        loadData.setData11(similarLoadData.getData11());
        loadData.setData12(similarLoadData.getData12());
        loadData.setData13(similarLoadData.getData13());
        loadData.setData14(similarLoadData.getData14());
        loadData.setData15(similarLoadData.getData15());
        loadData.setData16(similarLoadData.getData16());
        loadData.setData17(similarLoadData.getData17());
        loadData.setData18(similarLoadData.getData18());
        loadData.setData19(similarLoadData.getData19());
        loadData.setData20((Math.abs(similarLoadData.getData20() + correctCoe.max - loadData.getData19())) > 300 ? similarLoadData.getData20() + 0.2 * correctCoe.max : similarLoadData.getData20() + correctCoe.max);
        loadData.setData21((Math.abs(similarLoadData.getData20() + correctCoe.max - loadData.getData19())) > 300 ? similarLoadData.getData21() + 0.25 * correctCoe.max : similarLoadData.getData21() + correctCoe.max);
        loadData.setData22((Math.abs(similarLoadData.getData20() + correctCoe.max - loadData.getData19())) > 300 ? similarLoadData.getData22() + 0.3333333 * correctCoe.max : similarLoadData.getData22() + correctCoe.max);
        loadData.setData23((Math.abs(similarLoadData.getData20() + correctCoe.max - loadData.getData19())) > 300 ? similarLoadData.getData23() + 0.5 * correctCoe.max : similarLoadData.getData23() + correctCoe.max);
        loadData.setData24((Math.abs(similarLoadData.getData20() + correctCoe.max - loadData.getData19())) > 300 ? similarLoadData.getData24() + 1. * correctCoe.max : similarLoadData.getData24() + correctCoe.max);
        loadData.setData25(similarLoadData.getData25() + correctCoe.max);
        loadData.setData26(similarLoadData.getData26() + correctCoe.max);
        loadData.setData27(similarLoadData.getData27() + correctCoe.max);
        loadData.setData28(similarLoadData.getData28() + correctCoe.max);
        loadData.setData29(similarLoadData.getData29() + correctCoe.max);
        loadData.setData30(similarLoadData.getData30() + correctCoe.max);
        loadData.setData31(similarLoadData.getData31() + correctCoe.max);
        loadData.setData32(similarLoadData.getData32() + correctCoe.max);
        loadData.setData33(similarLoadData.getData33() + correctCoe.max);
        loadData.setData34(similarLoadData.getData34() + correctCoe.max);
        loadData.setData35(similarLoadData.getData35() + correctCoe.max);
        loadData.setData36(similarLoadData.getData36() + correctCoe.max);
        loadData.setData37(similarLoadData.getData37() + correctCoe.max);
        loadData.setData38(similarLoadData.getData38() + correctCoe.max);
        loadData.setData39(similarLoadData.getData39() + correctCoe.max);
        loadData.setData40(similarLoadData.getData40() + correctCoe.max);
        loadData.setData41(similarLoadData.getData41() + correctCoe.max);
        loadData.setData42(similarLoadData.getData42() + correctCoe.max);
        loadData.setData43(similarLoadData.getData43() + correctCoe.max);
        loadData.setData44(similarLoadData.getData44() + correctCoe.max);
        loadData.setData45(similarLoadData.getData45() + correctCoe.max);
        loadData.setData46(similarLoadData.getData46() + correctCoe.max);
        loadData.setData47(similarLoadData.getData47() + correctCoe.max);
        loadData.setData48(similarLoadData.getData48() + correctCoe.max);
        loadData.setData49(similarLoadData.getData49() + correctCoe.max);
        loadData.setData50(similarLoadData.getData50() + correctCoe.max);
        loadData.setData51(similarLoadData.getData51() + correctCoe.max);
        loadData.setData52(similarLoadData.getData52() + correctCoe.max);
        loadData.setData53(similarLoadData.getData53() + correctCoe.max);
        loadData.setData54(similarLoadData.getData54() + correctCoe.max);
        loadData.setData55(similarLoadData.getData55() + correctCoe.max);
        loadData.setData56(similarLoadData.getData56() + correctCoe.max);
        loadData.setData57(similarLoadData.getData57() + correctCoe.max);
        loadData.setData58(similarLoadData.getData58() + correctCoe.max);
        loadData.setData59(similarLoadData.getData59() + correctCoe.max);
        loadData.setData60(similarLoadData.getData60() + correctCoe.max);
        loadData.setData61(similarLoadData.getData61() + correctCoe.max);
        loadData.setData62(similarLoadData.getData62() + correctCoe.max);
        loadData.setData63(similarLoadData.getData63() + correctCoe.max);
        loadData.setData64(similarLoadData.getData64() + correctCoe.max);
        loadData.setData65(similarLoadData.getData65() + correctCoe.max);
        loadData.setData66(similarLoadData.getData66() + correctCoe.max);
        loadData.setData67(similarLoadData.getData67() + correctCoe.max);
        loadData.setData68(similarLoadData.getData68() + correctCoe.max);
        loadData.setData69(similarLoadData.getData69() + correctCoe.max);
        loadData.setData70(similarLoadData.getData70() + correctCoe.max);
        loadData.setData71(similarLoadData.getData71() + correctCoe.max);
        loadData.setData72(similarLoadData.getData72() + correctCoe.max);
        loadData.setData73(similarLoadData.getData73() + correctCoe.max);
        loadData.setData74(similarLoadData.getData74() + correctCoe.max);
        loadData.setData75(similarLoadData.getData75() + correctCoe.max);
        loadData.setData76(similarLoadData.getData76() + correctCoe.max);
        loadData.setData77(similarLoadData.getData77() + correctCoe.max);
        loadData.setData78(similarLoadData.getData78() + correctCoe.max);
        loadData.setData79(similarLoadData.getData79() + correctCoe.max);
        loadData.setData80(similarLoadData.getData80() + correctCoe.max);
        loadData.setData81(similarLoadData.getData81() + correctCoe.max);
        loadData.setData82(similarLoadData.getData82() + correctCoe.max);
        loadData.setData83(similarLoadData.getData83() + correctCoe.max);
        loadData.setData84(similarLoadData.getData84() + correctCoe.max);
        loadData.setData85(similarLoadData.getData85() + correctCoe.max);
        loadData.setData86(similarLoadData.getData86() + correctCoe.max);
        loadData.setData87(similarLoadData.getData87() + correctCoe.max);
        loadData.setData88(similarLoadData.getData88() + correctCoe.max);
        loadData.setData89(similarLoadData.getData89() + correctCoe.max);
        loadData.setData90(similarLoadData.getData90() + correctCoe.max);
        loadData.setData91(similarLoadData.getData91() + correctCoe.max);
        loadData.setData92(similarLoadData.getData92() + correctCoe.max);
        loadData.setData93(similarLoadData.getData93() + correctCoe.max);
        loadData.setData94(similarLoadData.getData94() + correctCoe.max);
        loadData.setData95(similarLoadData.getData95() + correctCoe.max);
        return loadData;
    }

    private LoadData getOnePredictionLoadUseConfiguration(Date date, LoadData similarLoadData, MaxAveMinTuple<Double> correctCoe) {
        LoadData loadData = new LoadData();
        List<Double> datas = new LinkedList<Double>();
        List<Double> similarDatas = similarLoadData.toList();
        for (int i = 0; i < 96; i++) {
            Map<String, Double> m = calculator.get(i);
            datas.add(m.get("similar-load-point") * similarDatas.get(i) + m.get("correct-coe-max") * correctCoe.max + m.get("correct-coe-min") * correctCoe.min);
        }
        loadData.setData(datas);
        loadData.setDateString(Date2StringAdapter.toString(date));
        loadData.setName("prediction_load_data_of_" + loadData.getDateString());

        return loadData;
    }


    private IPredictionLoadTupleCalculator tupleCalculator = new PredictionLoadTupleCalculatorWithPerUnit();

    @Override
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcPredictionLoadTuple1(
            ElementPrintableLinkedList<WeatherData> predictionWeather,
            ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather,
            ElementPrintableLinkedList<SimpleDate> predictionDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays) {

        Integer predictionDaysNbr = predictionDays.size();
        Integer historyDaysNbr = historyDays.get(0).size();

        LoadBase
                loadBase0 = null;
        LoadBase
                loadBase1 = null;
        try {
            LocalDate localDate0 = historyDays.get(0).get(historyDaysNbr - 1).getDate().toLocalDate();
            loadBase0 = daoLoadBase.query(localDate0.getYear(), localDate0.getMonthValue());

            LocalDate localDate1 = predictionDays.get(0).getDate().toLocalDate();
            loadBase1 = daoLoadBase.query(localDate1.getYear(), localDate1.getMonthValue());
        } catch (Exception e) {
        }

        ElementPrintableLinkedList<MaxAveMinTuple<Double>> tuples = new ElementPrintableLinkedList<MaxAveMinTuple<Double>>("tuples");


        ElementPrintableLinkedList<LoadData> historyLoad = new ElementPrintableLinkedList<LoadData>("");
        for (int i = 0; i < historyDaysNbr; i++) {
            try {
                historyLoad.add(DAOFactory.getDefault().createDaoLoadData().query(historyDays.get(0).get(i).getDateString()));
            } catch (Exception e) {
            }
        }

        Integer index;
        for (int i = 0; i < predictionDaysNbr; i++) {
            index = i == 0 ? historyDaysNbr - 2 : historyDaysNbr - 1;
            MaxAveMinTuple<Double> loadPrediction = tupleCalculator.calc(predictionWeather.get(i), historyWeather.get(0).get(index), historyLoad.get(index), loadBase1, loadBase0);
            tuples.add(loadPrediction);
        }
        return tuples;
    }

    private MaxAveMinTuple<Double> getOnePredictionLoadTuple(WeatherData predictionWeatherData, WeatherData historyWeatherData,
                                                             LoadData historyLoadData,
                                                             LoadBase predictionLoadBase, LoadBase historyLoadBase) {

        MaxAveMinTuple<Double> tempPrediction;
        MaxAveMinTuple<Double> tempHistory;

        tempPrediction = predictionWeatherData.toMaxAveMin();
        tempHistory = historyWeatherData.toMaxAveMin();

        MaxAveMinTuple<Double> loadHistoryPerUnits = historyLoadData.getPerUnits(historyLoadBase);

        MaxAveMinTuple<Double> loadPredictionPerUnits = new MaxAveMinTuple<Double>("loadPredictionPerUnits");

        loadPredictionPerUnits.max = loadHistoryPerUnits.max + (tempPrediction.max - tempHistory.max) * (0.0016 * ((tempPrediction.max - tempHistory.max) / 2 + tempHistory.max) - 0.0355);
        loadPredictionPerUnits.ave = loadHistoryPerUnits.ave + (tempPrediction.ave - tempHistory.ave) * (0.003 * ((tempPrediction.ave - tempHistory.ave) / 2 + tempHistory.ave) - 0.0601);
        loadPredictionPerUnits.min = loadHistoryPerUnits.min + (tempPrediction.min - tempHistory.min) * (0.0052 * ((tempPrediction.min - tempHistory.min) / 2 + tempHistory.min) - 0.1009);

        MaxAveMinTuple<Double> predictionLoadData = new MaxAveMinTuple<Double>("predictionLoadData");
        predictionLoadData.max = loadPredictionPerUnits.max * predictionLoadBase.toMaxAveMin().max;
        predictionLoadData.ave = loadPredictionPerUnits.ave * predictionLoadBase.toMaxAveMin().ave;
        predictionLoadData.min = loadPredictionPerUnits.min * predictionLoadBase.toMaxAveMin().min;

        return predictionLoadData;
    }

    @Override
    protected void doAfterBasicPrediction(
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays,
            ElementPrintableLinkedList<SimpleDate> predictionDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers,
            ElementPrintableLinkedList<WeatherData> predictionWeathers,
            ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes,
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarDaysLoads,
            ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadsTuples,
            ElementPrintableLinkedList<LoadData> prediction96PointLoads
    ) throws LPE {
        System.out.printf("完成。\n");
//        try {
//
//            Integer count = 0;
//            for (int i = 0; i < accuracy.size(); i++) {
//                if (accuracy.get(i) < 0.9500)
//                    count++;
//            }
//            String status = count == 0 ? "成功" : count + "个预测精度低于95%";
//            WorkdayTestRecord record = new WorkdayTestRecord(predictionDays, similarDays.get(0), accuracy, status);
//            TestCommands.getInstance().add(new SaveWorkdayTestRecordCommand(record));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        new OutputWriter2Excel().write(this);

//        LoadPredictionException et=new LoadPredictionException("数据库异常。",LoadPredictionException.PREFIX_DEFAULT,LoadPredictionException.ERR_DEFAULT);


    }

    protected void doBeforeWholePrediction(Date date) {


        System.out.printf("[日期 " + date + "]  已启动工作日预测算法。正在执行...\n");
    }
}
