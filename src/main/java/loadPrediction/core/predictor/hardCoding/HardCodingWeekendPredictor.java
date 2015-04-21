/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.core.predictor.hardCoding;

import common.*;
import loadPrediction.config.ConfigureFactory;
import loadPrediction.config.WeekendPredictorCfg;
import loadPrediction.core.EnhancedSimilarCoeCalculator;
import loadPrediction.core.noneWorkday.SimilarDayFinder;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.visitors.IPredictorVisitor;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherCoesPackage;
import loadPrediction.domain.WeatherData;
import loadPrediction.exception.LPE;
import loadPrediction.utils.Date2StringAdapter;
import loadPrediction.utils.DateUtil;
import loadPrediction.utils.Season;
import loadPrediction.utils.SeasonIdentifier;
import loadPrediction.utils.powerSystemDateQuery.PowerSystemWeekendQuery;
import loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建：2015/2/13 21:01
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class HardCodingWeekendPredictor extends AbstractTemplateMethodForHardCodingPredictor implements IWeekendPredictor {
    private static WeekendPredictorCfg weekendPredictorCfg = ConfigureFactory.getInstance().getXmlConfigure().getAllConfiguration().getWeekendPredictorCfg();

    public HardCodingWeekendPredictor(Date date) {
        super(date, true);
    }

    @Override
    public Object accept(IPredictorVisitor visitor) throws LPE {
        return visitor.visitWeekendPredictor(this);

    }

    @Override
    public String getPredictorType() {
        return "HARD-CODING 周末";
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays(Date date, List<Integer> numbers) throws LPE {

        if (numbers.size() != 2) {
            return null;
        }

        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("historyDays of " + date.toLocalDate().toString());
        try {

            Date thursday = DateUtil.getDateBefore(date, 2);
            PowerSystemWorkdayQuery wq1 = new PowerSystemWorkdayQuery(thursday);
            ElementPrintableLinkedList<SimpleDate> workdays = ConvertUtils.toElementPrintableLinkedList(wq1.list(numbers.get(0), numbers.get(0)), "workdays");
            historyDays.add(workdays);

            PowerSystemWeekendQuery wq2 = new PowerSystemWeekendQuery(date);
            ElementPrintableLinkedList<SimpleDate> weekends = ConvertUtils.toElementPrintableLinkedList(wq2.list(numbers.get(1) + 1, numbers.get(1)), "weekends");
            historyDays.add(weekends);
        } catch (Exception e) {
        }

        return historyDays;
    }

    @Override
    protected ElementPrintableLinkedList<SimpleDate>
    doGetPredictionDays(Date date, Integer number) throws LPE {
        try {
            PowerSystemWeekendQuery wq = new PowerSystemWeekendQuery(date);
            return ConvertUtils.toElementPrintableLinkedList(wq.list(1, 2), "predictionDays of " + date.toLocalDate().toString());

        } catch (Exception e) {
        }
        return null;
    }


    @Override
    protected List<Integer> doGetHistoryDaysNumbers() {
        List<Integer> numbers = new LinkedList<Integer>();
        numbers.add(this.weekendPredictorCfg.getHistoryWorkdayNumber());
        numbers.add(this.weekendPredictorCfg.getHistoryWeekendNumber());
        return numbers;
    }

    @Override
    protected Integer
    doGetPredictionDaysNumber() {
        return this.weekendPredictorCfg.getPredictionWeekendNumber();
    }


    @Override
    protected Boolean
    doValidate(Date date) {
        return (DateUtil.getISOWeekday(date) == 6) && weekendPredictorCfg.getIsEnabled();

    }

//    @Override
//    protected   void
//    toExcelWorkbook(String path){}


    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>>
    doCalcSimilarCoes(ElementPrintableLinkedList<WeatherData> predictionWeather, ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather) throws LPE {

        ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> coes = new ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>>("similar_coes");


        ElementPrintableLinkedList<EnhancedLinkedList<Double>> workdayCoes = new ElementPrintableLinkedList<EnhancedLinkedList<Double>>("of_workday");
        ElementPrintableLinkedList<EnhancedLinkedList<Double>> weekendCoes = new ElementPrintableLinkedList<EnhancedLinkedList<Double>>("of_weekend");


        Integer historyWorkdays = historyWeather.get(0).size();
        Integer historyWeekends = historyWeather.get(1).size();

        EnhancedSimilarCoeCalculator calculator = new EnhancedSimilarCoeCalculator(new WeatherCoesPackage(DAOFactory.getDefault().createDaoWeatherCoes4Weekend()));

        for (int i = 0; i < 1; i++) {
            EnhancedLinkedList<Double> coes1 = new EnhancedLinkedList<Double>("coes1");
            for (int j = historyWorkdays - 1; j >= 0; j--) {
                coes1.add(calculator.calcSimilarCoe(predictionWeather.get(i), historyWeather.get(0).get(j)));
            }
            workdayCoes.add(coes1);
        }
        for (int i = 1; i < 2; i++) {
            EnhancedLinkedList<Double> coes2 = new EnhancedLinkedList<Double>("coes2");
            for (int j = historyWorkdays - 1; j >= 1; j--) {
                coes2.add(calculator.calcSimilarCoe(predictionWeather.get(i), historyWeather.get(0).get(j)));
            }
            workdayCoes.add(coes2);
        }


        for (int i = 0; i < 1; i++) {
            EnhancedLinkedList<Double> coes1 = new EnhancedLinkedList<Double>("coes1");
//            coes1.add(1000.);
            for (int j = historyWeekends - 1; j >= 0; j--) {
                coes1.add(calculator.calcSimilarCoe(predictionWeather.get(i), historyWeather.get(1).get(j)));
            }
            weekendCoes.add(coes1);
        }
        for (int i = 1; i < 2; i++) {
            EnhancedLinkedList<Double> coes2 = new EnhancedLinkedList<Double>("coes2");
//            coes2.add(1000.);
            for (int j = historyWeekends - 1; j >= 1; j--) {
                coes2.add(calculator.calcSimilarCoe(predictionWeather.get(i), historyWeather.get(1).get(j)));
            }
            weekendCoes.add(coes2);
        }

        coes.add(workdayCoes);
        coes.add(weekendCoes);

        return coes;
    }


    @Override
    protected String doCheckAccuracy(Double threshold) {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcPredictionLoadTuple2(ElementPrintableLinkedList<SimpleDate> predictionDays,
                               ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes,
                               ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads) {
        Season season = SeasonIdentifier.getSeasonByDate(predictionDays.get(0).getDate());
        Double a0ave, a1ave, a2ave, a0min, a1min, a2min;
        switch (season) {
            case WINTER: {
                a0ave = 0.8122;
                a1ave = -1.6591;
                a2ave = 24.826;
                a0min = 0.6359;
                a1min = -1.9364;
                a2min = 32.106;

                break;
            }
            case SUMMER:
            default: {
                a0ave = 0.8671;
                a1ave = -5.3602;
                a2ave = 135.5;
                a0min = 0.7172;
                a1min = -8.0481;
                a2min = 203.77;
                break;
            }
        }
        Integer size = predictionDays.size();

        ElementPrintableLinkedList<EnhancedLinkedList<Double>> workdaysSimilarCoes = ConvertUtils.toElementPrintableLinkedList(similarCoes.get(0), "listMinWorkdaySimilarCoes");

        PrintableLinkedList<Double> min = new PrintableLinkedList<Double>("min_similar_coes");

        for (int i = 0; i < workdaysSimilarCoes.size(); i++) {
            min.add(workdaysSimilarCoes.get(i).getMinValue());
        }


        ElementPrintableLinkedList<MaxAveMinTuple<Double>> tuples = new ElementPrintableLinkedList<MaxAveMinTuple<Double>>("tuples");
        for (int i = 0; i < size; i++) {
            Double coe = min.get(i);

            MaxAveMinTuple<Double> load = new MaxAveMinTuple<Double>();

            load.max = (-0.4399 * coe + 0.9917) * (similarLoads.get(0).get(i).toMaxAveMin().max);
            load.ave = (a2ave * coe * coe + a1ave * coe + a0ave) * load.max;
            load.min = (a2min * coe * coe + a1min * coe + a0min) * load.max;

            tuples.add(load);
        }
        return tuples;
    }

    @Override
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcCorrectCoes(ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuples, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad) {
        ElementPrintableLinkedList<LoadData> loadDatas = similarLoad.get(1);
        Integer size = loadDatas.size();

        ElementPrintableLinkedList<MaxAveMinTuple<Double>> coes = new ElementPrintableLinkedList<MaxAveMinTuple<Double>>("load_prediction_correct_coes");
        for (int i = 0; i < size; i++) {
            MaxAveMinTuple<Double> coe = new MaxAveMinTuple<Double>();
            coe.max = predictionLoadTuples.get(i).max - loadDatas.get(i).toMaxAveMin().max;
            coe.min = predictionLoadTuples.get(i).min - loadDatas.get(i).toMaxAveMin().min;
            coes.add(coe);
        }
        return coes;
    }

    @Override
    protected ElementPrintableLinkedList<LoadData>
    doCalcPredictionLoad(ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads, ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes) {

        ElementPrintableLinkedList<LoadData> loadDatas = new ElementPrintableLinkedList<LoadData>("loadDatas");
//                try {
//            nearThursdayLoad = daoLoadData.query(workdayIdentifier.getDateBefore(date, 2), "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Integer size = predictionDays.size();
        for (int i = 0; i < size; i++) {
            loadDatas.add(calcOnePredictionLoad(predictionDays.get(i).getDate(), similarLoads.get(1).get(i), correctCoes.get(i)));
//            listPreidctionLoadAveCorrect.add(listPredictionLoadInitial.get(i).multiple(listMaxAveMinPredictionLoad.get(i).ave / listPredictionLoadInitial.get(i).toMaxAveMin().ave));
//            listPredictionLoadTHURCorrect.add(nearThursdayLoad);
        }
//        listPredictionLoadTHURCorrect.get(0).setNext(listPredictionLoadTHURCorrect.get(1));
//        listPredictionLoadTHURCorrect.print(System.err);
//        listPredictionLoadInitial.get(0).setNext(listPredictionLoadInitial.get(1));
//
//        listPredictionLoadInitial.print(System.out);
        return loadDatas;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    doCalcSimilarDays(Integer predictionDaysNumber, ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes) {
        Integer size = historyDays.size();
        try {
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("similar_dates");

            for (int i = 0; i < size; i++) {
                SimpleDate predictionDay = predictionDays.get(i);
                ElementPrintableLinkedList<EnhancedLinkedList<Double>> similarCoe = similarCoes.get(i);
                ElementPrintableLinkedList<SimpleDate> similarDay = new ElementPrintableLinkedList<SimpleDate>("default");
                ElementPrintableLinkedList<SimpleDate> daysToFindFrom = historyDays.get(i);
                for (int j = 0; j < predictionDaysNumber; j++) {
                    similarDay.add(SimilarDayFinder.getInstance().getSimilarDay(predictionDay, daysToFindFrom, similarCoe.get(j)));
                }
                similarDays.add(similarDay);
            }

            return similarDays;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取一组96点预测负荷数据。
     *
     * @param date            要进行负荷预测的日期。
     * @param similarLoadData 相似日的实际96点负荷数据。
     * @param correctCoe      修正系数，包括最大、最小修正值。
     * @return 96点预测负荷数据。
     */
    private LoadData calcOnePredictionLoad(Date date, LoadData similarLoadData, MaxAveMinTuple<Double> correctCoe) {
        LoadData loadData = new LoadData();

        loadData.setDateString(Date2StringAdapter.toString(date));
        loadData.setName("prediction_load_data_of_" + loadData.getDateString());

        loadData.setData00(similarLoadData.getData00() + correctCoe.min);
        loadData.setData01(similarLoadData.getData01() + correctCoe.min);
        loadData.setData02(similarLoadData.getData02() + correctCoe.min);
        loadData.setData03(similarLoadData.getData03() + correctCoe.min);
        loadData.setData04(similarLoadData.getData04() + correctCoe.min);
        loadData.setData05(similarLoadData.getData05() + correctCoe.min);
        loadData.setData06(similarLoadData.getData06() + correctCoe.min);
        loadData.setData07(similarLoadData.getData07() + correctCoe.min);
        loadData.setData08(similarLoadData.getData08() + correctCoe.min);
        loadData.setData09(similarLoadData.getData09() + correctCoe.min);
        loadData.setData10(similarLoadData.getData10() + correctCoe.min);
        loadData.setData11(similarLoadData.getData11() + correctCoe.min);
        loadData.setData12(similarLoadData.getData12() + correctCoe.min);
        loadData.setData13(similarLoadData.getData13() + correctCoe.min);
        loadData.setData14(similarLoadData.getData14() + correctCoe.min);
        loadData.setData15(similarLoadData.getData15() + correctCoe.min);
        loadData.setData16(similarLoadData.getData16() + correctCoe.min);
        loadData.setData17(similarLoadData.getData17() + correctCoe.min);
        loadData.setData18(similarLoadData.getData18() + correctCoe.min);
        loadData.setData19(similarLoadData.getData19() + correctCoe.min);
        loadData.setData20(similarLoadData.getData20() + correctCoe.min);
        loadData.setData21(similarLoadData.getData21() + correctCoe.min);
        loadData.setData22(similarLoadData.getData22() + correctCoe.min);
        loadData.setData23(similarLoadData.getData23() + correctCoe.min);
        loadData.setData24(similarLoadData.getData24() + correctCoe.min);
        loadData.setData25(similarLoadData.getData25() + correctCoe.min);
        loadData.setData26(similarLoadData.getData26() + correctCoe.min);
        loadData.setData27(similarLoadData.getData27() + correctCoe.min);
        loadData.setData28(similarLoadData.getData28() + correctCoe.min);
        loadData.setData29(similarLoadData.getData29() + correctCoe.min);
        loadData.setData30(similarLoadData.getData30() + correctCoe.min);
        loadData.setData31(similarLoadData.getData31() + correctCoe.min);
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
        Integer size = prediction96PointLoads.size();
        for (int i = 0; i < size; i++) {
            Double ave = predictionLoadsTuples.get(i).getAve();
            Double ave_perunit = ave / prediction96PointLoads.get(i).getAve();
            prediction96PointLoads.set(i, prediction96PointLoads.get(i).multiple(ave_perunit));
        }


//        try {
//
//            Integer count = 0;
//            for (int i = 0; i < accuracy.size(); i++) {
//                if (accuracy.get(i) < 0.9500)
//                    count++;
//            }
//            String status = count == 0 ? "成功" : count + "个预测精度低于95%";
//
//            OracleDAOFactory.getInstance().createDaoWeekendTestRecord().insert(new WeekendTestRecord(predictionDays, similarDays.get(0), similarDays.get(1), accuracy, status));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    protected void doBeforeWholePrediction(Date date) {
        System.out.printf("[日期 " + date + "]  已启动周末预测算法。正在执行...\n");
    }
}