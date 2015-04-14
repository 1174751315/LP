package loadPrediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import loadPrediction.domain.Accuracy;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherData;
import loadPrediction.domain.visitors.List2LoadDataVisitor;
import loadPrediction.exception.LPE;
import loadPrediction.resouce.WeatherDataMappingKeys;
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
 * 李倍存 创建于 2015-04-14 11:22。电邮 1174751315@qq.com。
 */
public class XlAccess {
    public XlAccess(){
    }

    public XlAccess(String wbPath)throws LPE{
        this.wbPath = wbPath;
        openWorkbook(wbPath);
    }

    public Workbook getWorkbook() {
        return workbook;
    }
    public void writeAndClose(String newPath) throws LPE {
        try {
            workbook.write(new FileOutputStream(newPath));
            workbook.close();
        } catch (FileNotFoundException e) {
            throw new LPE("保存并关闭工作簿时发生异�?", LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE("保存并关闭工作簿时发生异�?", LPE.eScope.USER);
        }
    }

    public String getPath() {
        return wbPath;
    }

    public void openWorkbook(String path)throws LPE{
        try {
            wbPath=path;
            workbook = WorkbookFactory.create(new FileInputStream(path));
            evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        } catch (FileNotFoundException e) {

            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (InvalidFormatException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
    }
    private Workbook workbook;
    private String wbPath;
    private FormulaEvaluator evaluator;

    public void writeSomeDateStrings2Cells(CellPosition position, List<SimpleDate> dates) {
        String sheetName=position.getSheetName();
        Short col=position.getCol();
        Integer row=position.getRow();
        Sheet sheet=workbook.getSheet(sheetName);
        for (int j = 0; j < dates.size(); j++) {
            String ds = dates.get(j).getDateString();
            sheet.getRow(row + j).getCell(col).setCellValue(Date.valueOf(ds));
        }
    }
    public void writeSomeDateStrings2Cells(List<CellPosition> positions, List<ElementPrintableLinkedList<SimpleDate>> dates) {
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            this.writeSomeDateStrings2Cells(pos, dates.get(i));
        }
    }

    public void writeSomeWeatherData2Cells(CellPosition position, List<WeatherData> datas) {
        for (int k = 0; k < datas.size(); k++) {
            WeatherData weatherData = datas.get(k);
            this.writeOneWeatherData2Cells(position.ofRowAfter(k),weatherData);
        }
    }

    private void writeOneWeatherData2Cells(CellPosition position, WeatherData data) {

        String sheetName=position.getSheetName();
        Short col=position.getCol();
        Integer row=position.getRow();
        Sheet sheet=workbook.getSheet(sheetName);
        Map<String,Double> map = data.toMap();
        Row row_ = sheet.getRow(row);
        for (int j = 0; j < WeatherDataMappingKeys.keys.length; j++) {
            try {
                row_.getCell(col + j).setCellValue(map.get(WeatherDataMappingKeys.keys[j]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeSomeWeatherData2Cells(List<CellPosition> positions, List<ElementPrintableLinkedList<WeatherData>> datas) {
    for (int i = 0; i < positions.size(); i++) {
        CellPosition pos = positions.get(i);
        this.writeSomeWeatherData2Cells(pos,datas.get(i));
        }
    }
    public void writeOneLoadData2Cells(CellPosition position,
                                        LoadData data) {
        String sheetName=position.getSheetName();
        Short col=position.getCol();
        Integer row=position.getRow();
        Sheet sheet=workbook.getSheet(sheetName);
        List<Double> loadData = data.toList();

        for (int k = 0; k < 96; k++) {
            Cell cell = sheet.getRow(row + k).getCell(col);
            cell.setCellValue(loadData.get(k));
        }
    }
    public void writeSomeLoadData2Cells(CellPosition positions,
                                       List<LoadData> datas) {
        for (int j = 0; j < datas.size(); j++) {
            this.writeOneLoadData2Cells(positions.ofColAfter(j), datas.get(j));
        }
    }
    public void writeSomeLoadData2Cells(List<CellPosition> positions,
                                        List<ElementPrintableLinkedList<LoadData>> datas) {
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            List<LoadData> loads = datas.get(i);
            for (int j = 0; j < loads.size(); j++) {
                this.writeOneLoadData2Cells(pos, loads.get(j));
            }
        }
    }
    private LoadData readOneLoadDataFromFormulas(String sheetName,Integer col,Integer row) {
        forceCalcAllFormulas();
        List<Double> loadData = new LinkedList<Double>();
        Sheet sheet=workbook.getSheet(sheetName);
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
    public ElementPrintableLinkedList<LoadData> readSomeLoadDataFromFormulas(CellPosition position,Integer nbr){
        ElementPrintableLinkedList<LoadData> loadDatas=new ElementPrintableLinkedList<LoadData>("ld");
        CellPosition ofPredictionLoads = position;
          for (int i = 0; i < nbr; i++) {
            LoadData ld = this.readOneLoadDataFromFormulas(ofPredictionLoads.getSheetName(), ofPredictionLoads.getCol() + i, ofPredictionLoads.getRow());
            loadDatas.add(ld);
        }
        return loadDatas;
    }
    private void forceCalcAllFormulas() {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            workbook.getSheetAt(i).setForceFormulaRecalculation(true);
        }
    }

    public PrintableLinkedList<Double> readSomeAccuracies(CellPosition position,Integer nbr){

        forceCalcAllFormulas();


        CellPosition ofAcc = position;
        Sheet sh = workbook.getSheet(ofAcc.getSheetName());
        PrintableLinkedList<Double> accuracies=new PrintableLinkedList<Double>("acc");
        Integer row=ofAcc.getRow();
        Short col=ofAcc.getCol();
        for (int i = 0; i < nbr; i++) {
            Cell cell = null;
            CellValue cv = null;
            cell = sh.getRow(row).getCell(col + i);
            cv = evaluator.evaluate(cell);
            Double acc = cv.getNumberValue();
            accuracies.add(acc);
        }
        return accuracies;
    }

    PrintableLinkedList<String> readSomeDateStrings(CellPosition position,Integer nbr){
        Sheet sheet=workbook.getSheet(position.getSheetName());
        Integer row=position.getRow();
        Short col=position.getCol();
        PrintableLinkedList<String> strings = new PrintableLinkedList<String>(
                "unnamed");
        for (int j = 0; j < nbr; j++) {
            Cell cell = sheet.getRow(row + j).getCell(col);
            CellValue cv = evaluator.evaluate(cell);
            strings.add(cv.formatAsString().replaceAll("\"", ""));
        }
        return strings;
    }
    ElementPrintableLinkedList<PrintableLinkedList<String>> readSimilarDateStrings(List<CellPosition> positions,Integer nbr){
        ElementPrintableLinkedList<PrintableLinkedList<String>> similar=new ElementPrintableLinkedList<PrintableLinkedList<String>>("similar");
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            PrintableLinkedList<String> dates = readSomeDateStrings(pos, nbr);
            similar.add(dates);
        }
        return similar;
    }

}
