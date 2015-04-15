/*
 * 版权�?�? (c) 2015 �? 李�?�存 （iPso）�??
 * �?有�?�对该文件所包含的代码的正确性�?�执行效率等任何方面不作任何保证�?
 * �?有个人和组织均可不受约束地将该文件所包含的代码用于非商业用�?��?�若�?要将其用于商业软件的�?发，请首先联系所有�?�以取得许可�?
 */
package loadPrediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;

import loadPrediction.core.predictor.util.CommonUtils;
import loadPrediction.core.predictor.util.LoadsObtainer;
import loadPrediction.core.predictor.util.WeatherObtainer;

import loadPrediction.dataAccess.DAOFactory;

import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherData;

import loadPrediction.exception.LPE;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private XlLpModelAccessor xlAccessor;

    public void setXlAccessor(XlLpModelAccessor xlAccessor) {
        this.xlAccessor = xlAccessor;
    }

    private PrintableLinkedList<Double> accuracies;
    ElementPrintableLinkedList<LoadData> predictionLoads = new ElementPrintableLinkedList<LoadData>(
            "al");

    public void setDateString(String dateString) {
        this.date=Date.valueOf(dateString);
        this.dateString = dateString;
    }

    public void setDate(Date date) {
        this.dateString=date.toLocalDate().toString();
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
        if (xlAccessor==null)
            xlAccessor=new XlLpModelAccessor();
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

        /*打开模板*/
        xlAccessor.openWorkbook(inPath);

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
        xlAccessor.writeSomeWeatherData2Cells(historyWeatherExcelPositions,historyWeathers);
       /*填充预测气象数据*/
        this.predictionWeatherExcelPosition = doGetPredictionWeatherExcelPosition();
        xlAccessor.writeSomeWeatherData2Cells(predictionWeatherExcelPosition,predictionWeathers);
        this.doAfterInjectWeathers(xlAccessor.getWorkbook(), historyDays, predictionDays);
        /*填充历史日*/
        historyDaysExcelPositions = doGetHistoryDaysExcelPositions();
        xlAccessor.writeSomeDateStrings2Cells(historyDaysExcelPositions,historyDays);
        /*填充预测�?*/
        predictionDaysExcelPosition = doGetPredictionDaysExcelPosition();
        xlAccessor.writeSomeDateStrings2Cells(predictionDaysExcelPosition,predictionDays);

        /*重新打开模板*/
        xlAccessor.writeAndClose(outPath);
        xlAccessor.openWorkbook(outPath);
        outPath += ".xls";

        /*从模板读取相似日计算结果*/
       List<CellPosition> ofSimilarDayStrings = doGetSimilarDaysExcelPositions();
        similarDayStrings=xlAccessor.readSimilarDateStrings(ofSimilarDayStrings,predictionDaysNbr);

        /*获取相似日负�?*/
        similarLoads = commonUtils.getSimilarDaysLoad_1(similarDayStrings);
        /*填充相似日负�?*/
        List<CellPosition> ofSimilarLoads = doGetSimilarLoadsExcelPosition();
        xlAccessor.writeSomeLoadData2Cells(ofSimilarLoads,similarLoads);

        this.doAfterInjectSimilarLoads(xlAccessor.getWorkbook());

        /*获取实际负荷数据：若存在*/
        actualLoads = commonUtils.getActualLoad(predictionDays);
        actualLoads.print(System.err);
        CellPosition t = doGetActualLoadsExcelPosition();
        xlAccessor.writeSomeLoadData2Cells(t,actualLoads);

        /*获取预测负荷*/
        CellPosition ofPredictionLoads = doGetPredictionLoadsExcelPosition();
        predictionLoads= xlAccessor.readSomeLoadDataFromFormulas(ofPredictionLoads,predictionDaysNbr);
        for (int i = 0; i < predictionLoads.size(); i++) {
            predictionLoads.get(i).setDateString(predictionDays.get(i).getDateString());
        }
        predictionLoads.print(System.out);

        /*获取预测精度*/
        CellPosition ofAcc = doGetAccuraciesExcelPosition();
        accuracies=xlAccessor.readSomeAccuracies(ofAcc,predictionDaysNbr);

        xlAccessor.writeAndClose(outPath);
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

    protected abstract Integer doGetPredictionDaysNbr();

    protected abstract List<Integer> doGetHistoryDaysNbrs();

    protected abstract ElementPrintableLinkedList<SimpleDate> doGetPredictionDays()
        throws LPE;

    protected abstract ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays()
        throws LPE;

    protected abstract List<CellPosition> doGetHistoryDaysExcelPositions();

    protected abstract CellPosition doGetPredictionDaysExcelPosition();

    protected abstract CellPosition doGetPredictionWeatherExcelPosition();

    protected abstract List<CellPosition> doGetHistoryWeatherExcelPositions();

    protected abstract List<CellPosition> doGetSimilarDaysExcelPositions();

    protected abstract List<CellPosition> doGetSimilarLoadsExcelPosition();

    protected abstract CellPosition doGetActualLoadsExcelPosition();

    protected abstract CellPosition doGetPredictionLoadsExcelPosition();

    protected abstract CellPosition doGetAccuraciesExcelPosition();

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





    private void writeSomeDateStrings2Cells(Sheet sheet, Integer col, Integer row, List<SimpleDate> dates) {
        for (int j = 0; j < dates.size(); j++) {
            String ds = dates.get(j).getDateString();
            sheet.getRow(row + j).getCell(col).setCellValue(Date.valueOf(ds));
        }
    }



    public ElementPrintableLinkedList<WeatherData> getPredictionWeathers() {
        return predictionWeathers;
    }
}
