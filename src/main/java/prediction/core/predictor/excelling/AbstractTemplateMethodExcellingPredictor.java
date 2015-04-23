/*
 * 版权�?�? (c) 2015 �? 李�?�存 （iPso）�??
 * �?有�?�对该文件所包含的代码的正确性�?�执行效率等任何方面不作任何保证�?
 * �?有个人和组织均可不受约束地将该文件所包含的代码用于非商业用�?��?�若�?要将其用于商业软件的�?发，请首先联系所有�?�以取得许可�?
 */
package prediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import prediction.config.predictor.PredictorCfg;
import prediction.core.predictor.excelling.xlAccessor.*;
import prediction.core.predictor.util.CommonUtils;
import prediction.core.predictor.util.LoadsObtainer;
import prediction.core.predictor.util.WeatherObtainer;
import prediction.dataAccess.DAOFactory;
import prediction.domain.LoadData;
import prediction.domain.SimpleDate;
import prediction.domain.WeatherData;
import prediction.exception.LPE;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.sql.Date;
import java.util.List;


/**
 * 李�?�存 创建�? 2015-03-22 9:19。电�? 1174751315@qq.com�?
 */
public abstract class AbstractTemplateMethodExcellingPredictor {
    protected Date date;
    protected String dateString;
    protected CommonUtils commonUtils;
    protected DAOFactory defaultDaoFactory;
    private PredictorCfg cfg;

    public void setAccuracyAccessor(XlLpAccuracyAccessor accuracyAccessor) {
        this.accuracyAccessor = accuracyAccessor;
    }

    public void setDateStringAccessor(XlLpDateStringAccessor dateStringAccessor) {
        this.dateStringAccessor = dateStringAccessor;
    }

    public void setDataAccessor(XlLpLoadDataAccessor loadDataAccessor) {
        this.loadDataAccessor = loadDataAccessor;
    }

    public void setWeatherDataAccessor(XlLpWeatherDataAccessor weatherDataAccessor) {
        this.weatherDataAccessor = weatherDataAccessor;
    }

    private XlLpAccuracyAccessor accuracyAccessor = new XlLpAccuracyAccessor();
    private XlLpDateStringAccessor dateStringAccessor = new XlLpDateStringAccessor();
    private XlLpLoadDataAccessor loadDataAccessor = new XlLpLoadDataAccessor();
    private XlLpWeatherDataAccessor weatherDataAccessor = new XlLpWeatherDataAccessor();

    private PrintableLinkedList<Double> accuracies;
    ElementPrintableLinkedList<LoadData> predictionLoads = new ElementPrintableLinkedList<LoadData>(
            "al");

    public void setDateString(String dateString) {
        this.date = Date.valueOf(dateString);
        this.dateString = dateString;
    }

    public void setDate(Date date) {
        this.dateString = date.toLocalDate().toString();
        this.date = date;
    }

    private Integer predictionDaysNbr;
    private List<Integer> historyDaysNbrs;
    private List<CellPosition> historyDaysExcelPositions;
    private CellPosition predictionDaysExcelPosition;
    private CellPosition predictionWeatherExcelPosition;
    private List<CellPosition> historyWeatherExcelPositions;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays;
    private ElementPrintableLinkedList<SimpleDate> predictionDays;
    private ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers;
    private ElementPrintableLinkedList<WeatherData> predictionWeathers;
    private ElementPrintableLinkedList<PrintableLinkedList<String>> similarDayStrings =
            new ElementPrintableLinkedList<PrintableLinkedList<String>>(
                    "similar-day-string");
    private ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads;
    private ElementPrintableLinkedList<LoadData> actualLoads;

    public AbstractTemplateMethodExcellingPredictor(Date date,
                                                    CommonUtils commonUtils) {
        this.date = date;
        dateString = date.toLocalDate().toString();
        this.commonUtils = commonUtils;
    }

    public AbstractTemplateMethodExcellingPredictor(Date date,
                                                    DAOFactory defaultDaoFactory) {
        this(date);
        this.defaultDaoFactory = defaultDaoFactory;
    }

    public AbstractTemplateMethodExcellingPredictor(Date date) {
        this.date = date;
        dateString = date.toLocalDate().toString();
    }

    public AbstractTemplateMethodExcellingPredictor() {
    }

    public void setCommonUtils(CommonUtils commonUtils) {
        this.commonUtils = commonUtils;
    }

    public void setDefaultDaoFactory(DAOFactory defaultDaoFactory) {
        this.defaultDaoFactory = defaultDaoFactory;
    }

    public Object predict() throws LPE {
        if (commonUtils == null) {
            commonUtils = new CommonUtils(new LoadsObtainer(
                    DAOFactory.getDefault().createDaoLoadData(), DAOFactory.getAlter().createDaoLoadData()),
                    new WeatherObtainer(DAOFactory.getDefault().createDaoWeatherData(),
                            DAOFactory.getAlter().createDaoWeatherData()));
        }

        if (!doValidate(date)) {
            throw new LPE("预测器执行前验证失败。\n预测算法被终止�??");
        }

        String inPath = doGetInputWorkbookPath();
        String outPath = doGetOutputWorkbookPath();
        String cfgPath=doGetXmlConfigFilePath();

        cfg=new PredictorCfg(cfgPath);

        /*打开模板*/
        AbstractXLAccessor.openWorkbook(inPath);
        accuracyAccessor.setWorkbook(AbstractXLAccessor.getOpenedWorkbook());
        loadDataAccessor.setWorkbook(AbstractXLAccessor.getOpenedWorkbook());
        weatherDataAccessor.setWorkbook(AbstractXLAccessor.getOpenedWorkbook());
        dateStringAccessor.setWorkbook(AbstractXLAccessor.getOpenedWorkbook());


        /*获取历史日和预测日*/
        historyDaysNbrs = doGetHistoryDaysNbrs();
        predictionDaysNbr = doGetPredictionDaysNbr();
        historyDays = doGetHistoryDays();
        predictionDays = doGetPredictionDays();

        /*获取历史气象和预测气象*/
        historyWeathers = commonUtils.getHistoryWeather(this.historyDays);
        predictionWeathers = commonUtils.getPredictionWeather(predictionDays);

        /*填充历史气象数据*/
        historyWeatherExcelPositions = doGetHistoryWeatherExcelPositions();
        weatherDataAccessor.writeSomeWeatherData2Cells(historyWeatherExcelPositions, historyWeathers);
       /*填充预测气象数据*/
        this.predictionWeatherExcelPosition = doGetPredictionWeatherExcelPosition();
        weatherDataAccessor.writeSomeWeatherData2Cells(predictionWeatherExcelPosition, predictionWeathers);
        this.doAfterInjectWeathers(AbstractXLAccessor.getOpenedWorkbook(), historyDays, predictionDays);
        /*填充历史日*/
        historyDaysExcelPositions = doGetHistoryDaysExcelPositions();
        dateStringAccessor.writeSomeDateStrings2Cells(historyDaysExcelPositions, historyDays);
        /*填充预测�?*/
        predictionDaysExcelPosition = doGetPredictionDaysExcelPosition();
        dateStringAccessor.writeSomeDateStrings2Cells(predictionDaysExcelPosition, predictionDays);

//        /*重新打开模板*/
//        AbstractXLAccessor.writeAndCloseWorkbook(AbstractXLAccessor.getOpenedWorkbook(), outPath);
//        xlAccessor.openWorkbook(outPath);
//        outPath += ".xls";

        /*从模板读取相似日计算结果*/
        List<CellPosition> ofSimilarDayStrings = doGetSimilarDaysExcelPositions();
        similarDayStrings = dateStringAccessor.readSimilarDateStrings(ofSimilarDayStrings, predictionDaysNbr);

        /*获取相似日负�?*/
        similarLoads = commonUtils.getSimilarDaysLoad_1(similarDayStrings);
        /*填充相似日负�?*/
        List<CellPosition> ofSimilarLoads = doGetSimilarLoadsExcelPosition();
        loadDataAccessor.writeSomeLoadData2Cells(ofSimilarLoads, similarLoads);

        this.doAfterInjectSimilarLoads(AbstractXLAccessor.getOpenedWorkbook());

        /*获取实际负荷数据：若存在*/
        actualLoads = commonUtils.getActualLoad(predictionDays);
        actualLoads.print(System.err);
        CellPosition t = doGetActualLoadsExcelPosition();
        loadDataAccessor.writeSomeLoadData2Cells(t, actualLoads);

        /*获取预测负荷*/
        CellPosition ofPredictionLoads = doGetPredictionLoadsExcelPosition();
        predictionLoads = loadDataAccessor.readSomeLoadDataFromFormulas(ofPredictionLoads, predictionDaysNbr);
        for (int i = 0; i < predictionLoads.size(); i++) {
            predictionLoads.get(i).setDateString(predictionDays.get(i).getDateString());
        }
        predictionLoads.print(System.out);

        /*获取预测精度*/
        CellPosition ofAcc = doGetAccuraciesExcelPosition();
        accuracies = accuracyAccessor.readSomeAccuracies(ofAcc, predictionDaysNbr);

        AbstractXLAccessor.writeAndCloseWorkbook(AbstractXLAccessor.getOpenedWorkbook(), outPath);
        return outPath;
    }

    protected abstract Boolean doValidate(Date date);

    /**/
    public String getDateString() {
        return date.toLocalDate().toString();
    }

    /**/
    public List<Integer> getHistoryDaysNumbers() {
        return historyDaysNbrs;
    }

    /**/
    public Integer getPredictionDaysNumber() {
        return predictionDaysNbr;
    }

    /**/
    public ElementPrintableLinkedList<SimpleDate> getPredictionDays() {
        return predictionDays;
    }

    /**/
    public ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getHistoryDays() {
        return historyDays;
    }

    /**/
    public ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getSimilarDays() {
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> days = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>(
                "");

        for (int i = 0; i < similarDayStrings.size(); i++) {
            ElementPrintableLinkedList<SimpleDate> ds = new ElementPrintableLinkedList<SimpleDate>(
                    "");

            for (int j = 0; j < similarDayStrings.get(i).size(); j++) {
                try {
                    ds.add(DAOFactory.getDefault().createDaoSimpleDate()
                            .query(similarDayStrings.get(i).get(j)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            days.add(ds);
        }

        return days;
    }

    /**/
    public ElementPrintableLinkedList<LoadData> getActual96PointLoads() {
        return actualLoads;
    }

    /**/
    public ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> getSimilarLoad() {
        return similarLoads;
    }

    public PrintableLinkedList<Double> getAccuracy() {
        return accuracies;
    }

    public ElementPrintableLinkedList<LoadData> getPrediction96PointLoads() {
        return predictionLoads;
    }

    protected abstract String doGetInputWorkbookPath();

    protected abstract String doGetOutputWorkbookPath();
    protected abstract String doGetXmlConfigFilePath();



    protected abstract ElementPrintableLinkedList<SimpleDate> doGetPredictionDays()
            throws LPE;

    protected abstract ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays()
            throws LPE;


    protected List<CellPosition> doGetSimilarDaysExcelPositions() {
        return cfg.getSimilarDaysPositions();
    }
    protected List<CellPosition> doGetSimilarLoadsExcelPosition() {
        return cfg.getSimilarLoadsPosition();
    }
    protected CellPosition doGetActualLoadsExcelPosition() {
        return cfg.getActualLoadsPosition();
    }
    protected List<CellPosition> doGetHistoryDaysExcelPositions() {
        return cfg.getHistoryDaysPositions();
    }
    protected CellPosition doGetPredictionDaysExcelPosition() {
        return cfg.getPredictionDaysPosition();
    }
    protected CellPosition doGetPredictionWeatherExcelPosition() {
        return cfg.getPredictionWeathersPosition();
    }
    protected List<CellPosition> doGetHistoryWeatherExcelPositions() {
        return cfg.getHistoryWeathersPositions();
    }
    protected CellPosition doGetPredictionLoadsExcelPosition() {
        return cfg.getPredictionLoadsPosition();
    }
    protected  CellPosition doGetAccuraciesExcelPosition(){return cfg.getAccuraciesPosition();}
    protected Integer doGetPredictionDaysNbr() {
        return cfg.getPredictionDaysNbr();
    }
    protected List<Integer> doGetHistoryDaysNbrs() {
        return cfg.getHistoryDaysNbrs();
    }


    protected void doAfterInjectSimilarLoads(Workbook activeWorkbook)
            throws LPE {
        //DO NOTHING
    }
    protected void doAfterInjectWeathers(Workbook activeWorkbook,
                                         ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays,
                                         ElementPrintableLinkedList<SimpleDate> predictionDays)
            throws LPE {
        //DO NOTHING
    }

    public ElementPrintableLinkedList<WeatherData> getPredictionWeathers() {
        return predictionWeathers;
    }
}
