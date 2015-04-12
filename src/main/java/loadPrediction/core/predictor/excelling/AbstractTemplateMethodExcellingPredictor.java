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

import loadPrediction.domain.Accuracy;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherData;

import loadPrediction.domain.visitors.List2LoadDataVisitor;

import loadPrediction.exception.LPE;

import loadPrediction.log.Logging;

import loadPrediction.resouce.WeatherDataMappingKeys;

import org.apache.log4j.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 李�?�存 创建�? 2015-03-22 9:19。电�? 1174751315@qq.com�?
 */
public abstract class AbstractTemplateMethodExcellingPredictor {
    protected Date date;
    protected String dateString;
    Workbook template = null;
    FormulaEvaluator evaluator = null;
    protected CommonUtils commonUtils;
    protected DAOFactory defaultDaoFactory;
    private ElementPrintableLinkedList<Accuracy> accuracies = new ElementPrintableLinkedList<Accuracy>(
            "acc");
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
    private List<CellPosition> similarDaysExcelPositions;
    private List<CellPosition> similarLoadsExcelPosition;
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
new Test().test();
        String inPath = doGetInputWorkbookPath();
        String outPath = doGetOutputWorkbookPath();

        Logger log = Logging.instance().createLogger("EXCELLING预测");
        log.debug("打开模板文件");

        openTemplate(inPath);

        historyDaysNbrs = doGetHistoryDaysNbrs();
        predictionDaysNbr = doGetPredictionDaysNbr();

        log.debug("获取历史�?");
        historyDays = doGetHistoryDays();
        log.debug("完成");

        //        historyDays.print(System.err);
        log.debug("获取预测�?");
        predictionDays = doGetPredictionDays();
        log.debug("完成");

        //        predictionDays.print(System.out);

        //        closeTemplate(outPath);
        //        openTemplate(outPath);
        log.debug("获取历史气象数据");
        historyWeathers = commonUtils.getHistoryWeather(this.historyDays);

        //        historyWeathers.print(System.err);
        log.debug("完成");
        log.debug("获取预测气象数据");
        predictionWeathers = commonUtils.getPredictionWeather(predictionDays);

        //        predictionWeathers.print(System.out);
        log.debug("完成");

        //        closeTemplate(outPath);
        //        openTemplate(outPath);
        log.debug("填充历史�?");
        historyDaysExcelPositions = doGetHistoryDaysExcelPositions();

        for (int i = 0; i < historyDaysExcelPositions.size(); i++) {
            CellPosition pos = historyDaysExcelPositions.get(i);
            Sheet ws0 = template.getSheet(pos.getSheetName());
            writeSomeDateStrings2Cells(ws0, pos.getCol().intValue(),
                pos.getRow(), historyDays.get(i));
        }

        log.debug("完成");

        //        historyDays.print(System.err);
        /*填充预测�?*/
        log.debug("填充预测�?");
        predictionDaysExcelPosition = doGetPredictionDaysExcelPosition();

        Sheet ws0 = template.getSheet(predictionDaysExcelPosition.getSheetName());
        writeSomeDateStrings2Cells(ws0,
            predictionDaysExcelPosition.getCol().intValue(),
            predictionDaysExcelPosition.getRow(), predictionDays);

        //        predictionDays.print(System.out);
        log.debug("完成");

        /*填充历史气象数据*/
        log.debug("填充历史气象数据");
        historyWeatherExcelPositions = doGetHistoryWeatherExcelPositions();

        for (int i = 0; i < historyWeatherExcelPositions.size(); i++) {
            CellPosition pos = historyWeatherExcelPositions.get(i);
            Sheet ws1 = template.getSheet(pos.getSheetName());

            for (int k = 0; k < historyWeathers.get(i).size(); k++) {
                WeatherData weatherData = historyWeathers.get(i).get(k);
                writeOneWeatherData2Cells(ws1,
                    historyWeatherExcelPositions.get(i).getCol().intValue(),
                    historyWeatherExcelPositions.get(i).getRow() + k,
                    weatherData);
            }
        }

        log.debug("完成");

        /*填充预测气象数据*/
        log.debug("填充预测气象数据");
        this.predictionWeatherExcelPosition = doGetPredictionWeatherExcelPosition();
        ws0 = template.getSheet(predictionWeatherExcelPosition.getSheetName());

        for (int i = 0; i < predictionWeathers.size(); i++) {
            WeatherData weatherData = predictionWeathers.get(i);
            writeOneWeatherData2Cells(ws0,
                predictionWeatherExcelPosition.getCol().intValue(),
                predictionWeatherExcelPosition.getRow() + i, weatherData);
        }
new Test().test();
        log.debug("完成");
        forceCalcAllFormulas(template);

        this.doAfterInjectWeathers(template, historyDays, predictionDays);

        /*打开工作�?*/
        //        com.jniwrapper.win32.jexcel.Workbook wb=null;
        //        Application app=null;
        //        try {
        //            app=new Application();
        //            wb=app.openWorkbook(new File(outPath));
        //        }catch (Exception e){
        //            e.printStackTrace();
        //            wb.close(true);
        //            app.close();
        //        }
        closeTemplate(outPath);
        openTemplate(outPath);
        outPath += ".xls";


        /////////////////

        //POI__________________________________________________________________________________________________

        //        try {
        //            wb=new HSSFWorkbook(new FileInputStream(outPath));
        List<CellPosition> ofSimilarDayStrings = doGetSimilarDaysExcelPositions();

        for (int i = 0; i < ofSimilarDayStrings.size(); i++) {
            CellPosition pos = ofSimilarDayStrings.get(i);
            org.apache.poi.ss.usermodel.Sheet sh = template.getSheet(pos.getSheetName());
            sh.setForceFormulaRecalculation(true);

            PrintableLinkedList<String> dates = new PrintableLinkedList<String>(
                    "unnamed");

            for (int j = 0; j < predictionDaysNbr; j++) {
                org.apache.poi.ss.usermodel.Cell cell = sh.getRow(pos.getRow() +
                        j).getCell(pos.getCol()); //sh.getRow(pos.row+j).getCell(pos.getCol()+1);
                CellValue cv = evaluator.evaluate(cell);
                dates.add(cv.formatAsString().replaceAll("\"", ""));
            }

            similarDayStrings.add(dates);
        }

        similarDayStrings.print(System.err);

        /*获取相似日负�?*/
        similarLoads = commonUtils.getSimilarDaysLoad_1(similarDayStrings);


        /*填充相似日负�?*/
        List<CellPosition> ofSimilarLoads = doGetSimilarLoadsExcelPosition();

        for (int i = 0; i < ofSimilarLoads.size(); i++) {
            CellPosition pos = ofSimilarLoads.get(i);
            Sheet ws1 = template.getSheet(pos.getSheetName());
            List<LoadData> loads = similarLoads.get(i);

            for (int j = 0; j < loads.size(); j++) {
                writeOneLoadData2Cells(ws1, pos.getCol().intValue() + j,
                    pos.getRow(), loads.get(j));
            }

            ws1.setForceFormulaRecalculation(true);
        }

        this.doAfterInjectSimilarLoads(template);
        forceCalcAllFormulas(template);

        /*获取实际负荷数据：若存在*/
        actualLoads = commonUtils.getActualLoad(predictionDays);
        actualLoads.print(System.err);

        CellPosition t = doGetActualLoadsExcelPosition();
        Sheet ws2 = template.getSheet(t.getSheetName());
        List<LoadData> loads = actualLoads;

        for (int j = 0; j < loads.size(); j++) {
            if (loads.get(j) != null) {
                writeOneLoadData2Cells(ws2, t.getCol() + j, t.getRow(),
                    loads.get(j));
            }
        }

        /*�?始从EXCEL工作簿提取预测计算结�?*/

        /*打开工作�?*/

        //        try {
        //            app=new Application();
        //            wb=app.openWorkbook(new File(outPath));
        //        }catch (Exception e){
        //            e.printStackTrace();
        //        }
        for (int i = 0; i < template.getNumberOfSheets(); i++) {
            template.getSheetAt(i).setForceFormulaRecalculation(true);
        }

        /*获取预测负荷*/
        CellPosition ofPredictionLoads = doGetPredictionLoadsExcelPosition();
        Sheet sh = template.getSheet(ofPredictionLoads.getSheetName());
        forceCalcAllFormulas(template);

        for (int i = 0; i < predictionDaysNbr; i++) {
            LoadData ld = readOneLoadDataFromFormulas(sh, evaluator,
                    ofPredictionLoads.getCol() + i, ofPredictionLoads.getRow());
            ld.setDateString(predictionDays.get(i).getDateString());
            predictionLoads.add(ld);
        }

        predictionLoads.print(System.out);

        /*获取预测精度*/
        CellPosition ofAcc = doGetAccuraciesExcelPosition();
        sh = template.getSheet(ofAcc.getSheetName());

        for (int i = 0; i < predictionDaysNbr; i++) {
            Cell cell = null;
            CellValue cv = null;
            cell = sh.getRow(ofAcc.getRow()).getCell(ofAcc.getCol() + i);
            cv = evaluator.evaluate(cell);

            Double acc = cv.getNumberValue();

            if ((acc >= 0.001) && (dateString != null) &&
                    !dateString.equals("")) {
                accuracies.add(new Accuracy(predictionDays.get(i).getDateString(),
                        acc));
            }
        }

        accuracies.print(System.err);
new Test().test();
        /*关闭工作�?*/
        try {
            template.write(new FileOutputStream(outPath));
            template.close();
        } catch (IOException e) {
            throw new LPE("保存并关闭工作簿时发生异�?");
        }

        log.debug("完成�?次预�?");

        //        wb.close(true);
        //        app.close(true);
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

    public ElementPrintableLinkedList<Accuracy> getAccuracy() {
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

    private void forceCalcAllFormulas(Workbook workbook) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            workbook.getSheetAt(i).setForceFormulaRecalculation(true);
        }
    }

    private void writeOneLoadData2Cells(Sheet sheet, Integer col, Integer row,
        LoadData data) {
        List<Double> loadData = data.toList();

        for (int k = 0; k < 96; k++) {
            Cell cell = sheet.getRow(row + k).getCell(col);
            cell.setCellValue(loadData.get(k));
        }
    }

    private void writeOneWeatherData2Cells(Sheet sheet, Integer col,
        Integer row, WeatherData data) {
        Map<String,Double> map = data.toMap();
        Row row_ = sheet.getRow(row);

        for (int j = 0; j < WeatherDataMappingKeys.keys.length; j++) {
            try {
                row_.getCell(col + j)
                    .setCellValue(map.get(WeatherDataMappingKeys.keys[j]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private LoadData readOneLoadDataFromFormulas(Sheet sheet,
        FormulaEvaluator evaluator, Integer col, Integer row) {
        List<Double> loadData = new LinkedList<Double>();

        for (int k = 0; k < 96; k++) {
            Cell cell = sheet.getRow(row + k).getCell(col);
            CellValue cv = evaluator.evaluate(cell);
            loadData.add(cv.getNumberValue());
        }

        LoadData l = new LoadData();
        l.setName("From Calc");
        l.accept(new List2LoadDataVisitor(loadData));

        return l;
    }

    private void writeSomeDateStrings2Cells(Sheet sheet, Integer col,
        Integer row, List<SimpleDate> dates) {
        for (int j = 0; j < dates.size(); j++) {
            String ds = dates.get(j).getDateString();
            sheet.getRow(row + j).getCell(col).setCellValue(Date.valueOf(ds));
        }
    }

    private void closeTemplate(String path) throws LPE {
        try {
            template.write(new FileOutputStream(path));
            template.close();
        } catch (FileNotFoundException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
    }

    private void openTemplate(String path) throws LPE {
        try {
            template = WorkbookFactory.create(new FileInputStream(path));
            evaluator = template.getCreationHelper().createFormulaEvaluator();
        } catch (FileNotFoundException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (InvalidFormatException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
    }

    public ElementPrintableLinkedList<WeatherData> getPredictionWeathers() {
        return predictionWeathers;
    }
}
