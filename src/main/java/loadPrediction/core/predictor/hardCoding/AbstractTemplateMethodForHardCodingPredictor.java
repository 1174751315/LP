/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.hardCoding;

import  common.ElementPrintableLinkedList;
import  common.EnhancedLinkedList;
import  common.MaxAveMinTuple;
import  loadPrediction.config.ConfigureFactory;
import  loadPrediction.config.OutputCfg;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.dataAccess.DAOWeatherData;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.domain.WeatherData;
import  loadPrediction.exception.LPE;
import  loadPrediction.utils.AccuracyUtils;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建：2015/2/6 21:04
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public abstract class AbstractTemplateMethodForHardCodingPredictor {
    public void setDate(Date date){
        this.date=date;
    }
    public String getDateString() {
        return date.toLocalDate().toString();
    }

    protected ElementPrintableLinkedList<OutputCfg> outputCfgs = ConfigureFactory.getInstance().getXmlConfigure().getAllConfiguration().getOutputCfgs();
    private Date date;


    private List<Integer> historyDaysNumbers;
    private Integer predictionDaysNumber;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays;
    private ElementPrintableLinkedList<SimpleDate> predictionDays;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers;
    private ElementPrintableLinkedList<WeatherData> predictionWeathers;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad;
    private ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuple;
    private ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes;
    private ElementPrintableLinkedList<LoadData> prediction96PointLoads;
    private ElementPrintableLinkedList<LoadData> actual96PointLoads;
    private ElementPrintableLinkedList<Accuracy> accuracy;

    public ElementPrintableLinkedList<Accuracy> getAccuracy() {
        return accuracy;
    }

    private Double accuracyOfFirstPredictionDay;
    private LoadData actual96PointLoadOfFirstPredictionDay;

    public Double getAccuracyOfFirstPredictionDay() {
        return accuracyOfFirstPredictionDay;
    }

    public LoadData getActual96PointLoadOfFirstPredictionDay() {
        return actual96PointLoadOfFirstPredictionDay;
    }

    public ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> getSimilarLoad() {
        return similarLoad;
    }

    public ElementPrintableLinkedList<LoadData> getActual96PointLoads() {
        return actual96PointLoads;
    }

    public ElementPrintableLinkedList<MaxAveMinTuple<Double>> getCorrectCoes() {
        return correctCoes;
    }

    public Date getDate() {
        return date;
    }

    public ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getHistoryDays() {
        return historyDays;
    }

    public List<Integer> getHistoryDaysNumbers() {
        return historyDaysNumbers;
    }

    public ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> getHistoryWeathers() {
        return historyWeathers;
    }

    public ElementPrintableLinkedList<OutputCfg> getOutputCfgs() {
        return outputCfgs;
    }

    public ElementPrintableLinkedList<SimpleDate> getPredictionDays() {
        return predictionDays;
    }

    public ElementPrintableLinkedList<LoadData> getPrediction96PointLoads() {
        return prediction96PointLoads;
    }

    public Integer getPredictionDaysNumber() {
        return predictionDaysNumber;
    }

    public ElementPrintableLinkedList<MaxAveMinTuple<Double>> getPredictionLoadTuple() {
        return predictionLoadTuple;
    }

    public ElementPrintableLinkedList<WeatherData> getPredictionWeathers() {
        return predictionWeathers;
    }

    public ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> getSimilarCoes() {
        return similarCoes;
    }

    public ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getSimilarDays() {
        return similarDays;
    }

    private Boolean calcAcc = false;

    public Boolean hasCalculatedAccuracy() {
        return calcAcc;
    }

    /**
     * 进行一次负荷预测。负荷预测的输入、输出方式，由实现者决定。
     *
     * @throws Exception 当预测异常终止或者检测到其它异常时抛出。
     */
    public Object predict() throws LPE {
        calcAcc = false;
        /*子类可扩展插入点。*/
        this.doBeforeWholePrediction(this.date);

        /*验证参数是否合法。*/
        if (!this.doValidate(this.date))
            throw new LPE("预测算法未能启动，请检查参数或配置。");

        /*获取历史日数量链表。*/
        historyDaysNumbers = this.doGetHistoryDaysNumbers();
        /*获取预测日数量。*/
        predictionDaysNumber = this.doGetPredictionDaysNumber();



        /*获取历史日期链表。*/
        historyDays = this.doGetHistoryDays(this.date, historyDaysNumbers);
        for (int i = 0; i < historyDays.size(); i++) {
            if (historyDays.get(i).hasNull())
                throw new LPE("历史日链表包含空值。");
        }
        /*子类可扩展插入点。*/
        this.doAfterGetHistoryDays(historyDays);



        /*获取预测日期链表。*/
        predictionDays = this.doGetPredictionDays(this.date, predictionDaysNumber);
        if (predictionDays.hasNull())
            throw new LPE("预测日链表包含空值。");
        /*子类可扩展插入点。*/
        this.doAfterGetPredictionDays(predictionDays);



        /*获取历史气象数据链表。*/
        historyWeathers = this.getHistoryWeather();
        if (historyWeathers.hasNull())
            throw new LPE("历史气象数据链表包含空值。");
        /*子类可扩展插入点。*/
        this.doAfterGetHistoryWeathers(historyWeathers);



        /*获取预测气象数据链表。*/
        predictionWeathers = this.getPredictionWeather();
        if (predictionWeathers.hasNull())
            throw new LPE("预测气象数据链表包含空值。");
        /*子类可扩展插入点。*/
        this.doAfterGetPredictionWeathers(predictionWeathers);



        /*计算相似度。*/
        similarCoes = this.doCalcSimilarCoes(predictionWeathers, historyWeathers);
        /*子类可扩展插入点。*/
        this.doAfterCalcSimilarCoes(similarCoes);


        /*计算相似日。*/
        similarDays = this.doCalcSimilarDays(predictionDaysNumber, predictionDays, historyDays, similarCoes);
        /*子类可扩展插入点。*/
        this.doAfterCalcSimilarDays(similarDays);

        similarLoad = this.getSimilarDaysLoad();
        /*子类可扩展插入点。*/
        this.doAfterGetSimilarDaysLoad(similarLoad);


        /*最大、平均、最小负荷预测。*/

            predictionLoadTuple = this.doCalcPredictionLoadTuple1(predictionWeathers, historyWeathers, predictionDays, historyDays);
        if (predictionLoadTuple == null)
            predictionLoadTuple = this.doCalcPredictionLoadTuple2(predictionDays, similarCoes, similarLoad);
        if (predictionLoadTuple == null)
            predictionLoadTuple = this.doCalcPredictionLoadTuple3();
        if (predictionLoadTuple == null)
            throw new LPE();
        /*子类可扩展插入点。*/
        this.doAfterCalcPredictionLoadTuples(predictionLoadTuple);


        /*计算修正系数。*/
        correctCoes = this.doCalcCorrectCoes(predictionLoadTuple, similarLoad);
        /*子类可扩展插入点。*/
        this.doAfterCalcCorrectCoes(correctCoes);


        /*计算96点负荷预测初始值。*/
        prediction96PointLoads = this.doCalcPredictionLoad(predictionDays, similarLoad, correctCoes);
        /*子类可扩展插入点。*/
        this.doAfterCalcPredictionLoad(prediction96PointLoads);

        /*进行后处理。*/
        this.doAfterBasicPrediction(historyDays, predictionDays, historyWeathers, predictionWeathers, similarCoes, similarDays, similarLoad, predictionLoadTuple, prediction96PointLoads);


        /*若最后一个预测日的实际负荷数据存在，则表明往前的预测日的实际负荷数据均存在。*/
//        if(DAOFactory.getDefault().createDaoLoadData().query(predictionDays.get(predictionDaysNumber-1).getDateString())!=null){
//            /*获取96点负荷实际值*/
//            try {
//                actual96PointLoads = this.getActualLoad(predictionDays);
//             /*子类可扩展插入点。*/
//                this.doAfterGetActualLoad(actual96PointLoads);
//            /*计算预测精度。*/
//                accuracy = calcAccuracy(actual96PointLoads, prediction96PointLoads);
//            /*子类可扩展插入点。*/
//            } catch (Exception e) {
//                throw e;
//            }
//
//        }else{
//            accuracy=new PrintableLinkedList<Double>("accuracy");
//            for (int i = 0; i < predictionDaysNumber; i++) {
//                accuracy.add(0.);
//            }
//        }
//        this.doAfterCalcAccuracy(accuracy);
//        this.getActualLoadOfFirstPredictionDayIfExistAndCalcAccuracy();

        actual96PointLoads = this.getActualLoad(predictionDays);
        this.doAfterGetActualLoad(actual96PointLoads);

        accuracy = this.calcAccuracy(actual96PointLoads, prediction96PointLoads);
        this.doAfterCalcAccuracy(accuracy);












/*
        getWeatherData();//检索   所有历史日期的气象数据和待预测日的预报气象数据                所有历史日期的气象数据和待预测日的预报气象数据
        calcSimilarCoes();//计算             待预测日与所有候选相似日的相似度                            待预测日与所有候选相似日的相似度以及各最小值
        calcSimilarDays();//计算             所有待预测日的相似日                                      所有待预测日的工作日相似日和周末相似日
        getSimilarDaysLoad();//检索          所有相似日的负荷数据                                      所有相似日的负荷数据
        calcMaxAveMinPrediction();//计算     待预测日的最大、平均和最小预测值                            待预测日的最大、平均和最小预测值
        calcCorrectCoes();//计算             各待预测日的修正系数                                      各待预测日的修正系数
        calcLoadPrediction();//计算          各待预测日的预测负荷                                      各待预测日的预测负荷
        getActualLoad();//检索               各待预测日的实际负荷                                      各待预测日的实际负荷
        calcAccuracy();//计算                准确度                                                  准确度
        toExcelWorkbook("");
*/
        return null;
    }

    private Boolean debug;

    public AbstractTemplateMethodForHardCodingPredictor(Date date, Boolean dbg) {
        this.date = date;
        this.debug = dbg;
    }

    /*辅助计算函数.*/
    private ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>
    getHistoryWeather() {
        ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> weather = new ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>("");

        Integer iSize = this.historyDays.size();

        try {
            for (int i = 0; i < iSize; i++) {

                List<SimpleDate> subdates = this.historyDays.get(i);
                Integer jSize = subdates.size();

                ElementPrintableLinkedList<WeatherData> subweather = new ElementPrintableLinkedList<WeatherData>(subdates.toString());
                for (int j = 0; j < jSize; j++) {
                    subweather.add(DAOFactory.getDefault().createDaoWeatherData().query(subdates.get(j).getDateString()));
                }
                weather.add(subweather);
            }
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ElementPrintableLinkedList<WeatherData>
    getPredictionWeather() throws LPE {
        ElementPrintableLinkedList<WeatherData> weather = new ElementPrintableLinkedList<WeatherData>("prediction_weather");
        Integer size = this.predictionDays.size();

        DAOWeatherData dao = DAOFactory.getDefault().createDaoWeatherData();
        try {
            for (int i = 0; i < size; i++) {
                WeatherData weatherData = dao.query(this.predictionDays.get(i).getDateString());
                if (weatherData == null)
                    throw new LPE("气象预测数据不完整。至少缺少日期" + this.predictionDays.get(i).getDateString() + "的数据。");
                weather.add(weatherData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    private ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>
    getSimilarDaysLoad() {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("loads");
        try {
            for (int i = 0; i < this.similarDays.size(); i++) {
                ElementPrintableLinkedList<SimpleDate> date = this.similarDays.get(i);
                ElementPrintableLinkedList<LoadData> load = new ElementPrintableLinkedList<LoadData>(date.getName());
                for (int j = 0; j < date.size(); j++) {
                    load.add(DAOFactory.getDefault().createDaoLoadData().query(date.get(j).getDateString()));
                }
                loads.add(load);
            }
            return loads;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ElementPrintableLinkedList<LoadData>
    getActualLoad(ElementPrintableLinkedList<SimpleDate> predictionDays) {
        try {
            ElementPrintableLinkedList<LoadData> actualLoad = new ElementPrintableLinkedList<LoadData>("actual_loads");
            Integer size = predictionDays.size();
            for (int i = 0; i < size; i++) {
                LoadData ld = DAOFactory.getDefault().createDaoLoadData().query(predictionDays.get(i).getDateString());
                if (ld != null) {
                    actualLoad.add(ld);
                }
//                    throw new Exception("实际负荷数据  [  "+predictionDays.get(i).getDateString()+"  ]  缺失。");
            }
            return actualLoad;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ElementPrintableLinkedList<Accuracy>
    calcAccuracy(ElementPrintableLinkedList<LoadData> actualLoads, ElementPrintableLinkedList<LoadData> predictionLoads) throws LPE {
        if (predictionLoads.size() < actualLoads.size())
            throw new LPE("预测负荷数据链表项个数竟小于实际负荷数据链表，联系开发者。", LPE.eScope.USER);
        Map<String, LoadData> predictionLoadsMap = new HashMap<String, LoadData>();
        for (int i = 0; i < predictionLoads.size(); i++) {
            predictionLoadsMap.put(predictionLoads.get(i).getDateString(), predictionLoads.get(i));
        }

        ElementPrintableLinkedList<Accuracy> accuracy = new ElementPrintableLinkedList<Accuracy>("accuracy");
        for (int i = 0; i < actualLoads.size(); i++) {
            Accuracy acc = new Accuracy();
            acc.setDateString(actualLoads.get(i).getDateString());
            acc.setAccuracy(AccuracyUtils.calcOneAccuracy(actualLoads.get(i), predictionLoadsMap.get(actualLoads.get(i).getDateString())));
            accuracy.add(acc);
        }
        return accuracy;
    }


    private void getActualLoadOfFirstPredictionDayIfExistAndCalcAccuracy() {
        try {
            LoadData ld = DAOFactory.getDefault().createDaoLoadData().query(predictionDays.get(0).getDateString());
            Double acc = 0.;
            if (ld != null) {
                this.actual96PointLoadOfFirstPredictionDay = ld;
                acc = AccuracyUtils.calcOneAccuracy(ld, prediction96PointLoads.get(0));
            }
            this.accuracyOfFirstPredictionDay = acc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*子类必须实现的函数。*/

    /**
     * @param predictionLoadTuples 负荷预测的最大、平均、最小值组。其元素个数等于待预测日个数，每个元素均包含相应待预测日的最大、平均、最小负荷预测值。
     * @param similarLoad          相似日的负荷。其每一个元素分别是相应待预测日的相似日的96点负荷数据。
     * @return 最大、最小修正量链表。链表的每一项分别是相应待预测日的最大、最小修正量。
     */
    protected abstract ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcCorrectCoes(ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuples, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad);


    /**
     * @return 历史日的数量链表。对于周末预测，历史日数量链表包含两项，分别代表历史工作日数目和历史周末数目。对于工作日预测，历史日数量包含一项，代表历史工作日数目。
     */
    protected abstract List<Integer>
    doGetHistoryDaysNumbers();

    /**
     * @return 预测日的数量。
     */
    protected abstract Integer
    doGetPredictionDaysNumber();

    /**
     * @param date 执行一次验证，以确定算法是否可以继续执行。若返回false，负荷预测算法将异常终止。
     *             * @return true：预测算法可以继续；false：预测算法终止。
     */
    protected abstract Boolean
    doValidate(Date date);

    /**
     * @param date    预测基准日。
     * @param numbers 历史日数量链表。
     * @return 历史日链表。
     */
    protected abstract ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    doGetHistoryDays(Date date, List<Integer> numbers) throws LPE;

    /**
     * @param date   预测基准日。
     * @param number 预测日数量。
     * @return 预测日链表。
     */
    protected abstract ElementPrintableLinkedList<SimpleDate>
    doGetPredictionDays(Date date, Integer number) throws LPE;

    /**
     * @param predictionWeather 预测日气象数据链表。
     * @param historyWeather    历史日气象数据链表。
     * @return 预测日与各历史日的相似系数。
     */
    protected abstract ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>>
    doCalcSimilarCoes(ElementPrintableLinkedList<WeatherData> predictionWeather, ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather);

    /**
     * @param threshold 精度的阈值。
     * @return 精度检查的警告或错误信息字符串。
     */
    protected abstract String doCheckAccuracy(Double threshold);

    /**
     * @param predictionDays 预测日链表。
     * @param similarLoads   相似日的实际96点负荷数据。
     * @param correctCoes    各预测日的最大、最小修正量链表。
     * @return 对应于各预测日的预测负荷。
     */
    protected abstract ElementPrintableLinkedList<LoadData>
    doCalcPredictionLoad(ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads, ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes);

    protected abstract ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    doCalcSimilarDays(Integer predictionDaysNumber, ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes);


    /**
     * 预测后处理。子类可在其中进行基本预测负荷数据的修正、输出、规格化、验证等后处理。
     *
     * @param historyDays
     * @param predictionDays
     * @param historyWeathers
     * @param predictionWeathers
     * @param similarCoes
     * @param similarDays
     * @param similarDaysLoads
     * @param predictionLoadsTuples
     * @param prediction96PointLoads
     * @throws main.java.loadPrediction.exception.LPE
     */
    protected abstract void doAfterBasicPrediction(
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays,
            ElementPrintableLinkedList<SimpleDate> predictionDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers,
            ElementPrintableLinkedList<WeatherData> predictionWeathers,
            ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes,
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarDaysLoads,
            ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadsTuples,
            ElementPrintableLinkedList<LoadData> prediction96PointLoads
    ) throws LPE;

    /*子类必须覆写其中一个的函数。*/
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcPredictionLoadTuple1(
            ElementPrintableLinkedList<WeatherData> predictionWeather,
            ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather,
            ElementPrintableLinkedList<SimpleDate> predictionDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays) {
        return null;
    }

    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcPredictionLoadTuple2(
            ElementPrintableLinkedList<SimpleDate> predictionDays,
            ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes,
            ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads) {
        return null;
    }

    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>>
    doCalcPredictionLoadTuple3() {

        return null;
    }





    /*子类可选扩展点。*/

    protected void doAfterGetHistoryDays(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays) {
        if (debug)
            historyDays.print(System.err);
    }

    protected void doAfterGetPredictionDays(ElementPrintableLinkedList<SimpleDate> predictionDays) {
        if (debug)
            predictionDays.print(System.out);
    }

    protected void doAfterGetHistoryWeathers(ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers) {
        if (debug)
            historyWeathers.print(System.out);
    }

    protected void doAfterGetPredictionWeathers(ElementPrintableLinkedList<WeatherData> predictionWeather) {
        if (debug)
            predictionWeather.print(System.out);
    }

    protected void doAfterCalcSimilarCoes(ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes) {

        if (debug)
            similarCoes.print(System.err);
    }

    protected void doAfterCalcSimilarDays(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays) {
        if (debug)
            similarDays.print(System.err);
    }

    protected void doAfterGetSimilarDaysLoad(ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarDaysLoad) {
        if (debug)
            similarDaysLoad.print(System.out);
    }

    protected void doAfterCalcPredictionLoadTuples(ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuples) {
        if (debug)
            predictionLoadTuples.print(System.err);
    }

    protected void doAfterCalcCorrectCoes(ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes) {
        if (debug)
            correctCoes.print(System.out);
    }

    protected void doAfterCalcPredictionLoad(ElementPrintableLinkedList<LoadData> prediction96PointLoads) {
        if (debug)
            prediction96PointLoads.print(System.err);
    }

    protected void doAfterGetActualLoad(ElementPrintableLinkedList<LoadData> loadDatas) {
        if (debug)
            loadDatas.print(System.out);
    }

    protected void doAfterCalcAccuracy(ElementPrintableLinkedList<Accuracy> accuracy) {
        if (debug)
            accuracy.print(System.out);
    }


    protected void doBeforeWholePrediction(Date date) {

    }


    protected Boolean isFirstPredictionDateExistInLoadDataDB() {
        try {
            return DAOFactory.getDefault().createDaoLoadData().query(this.predictionDays.get(0).getDateString()) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
