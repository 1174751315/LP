package loadPrediction.core.predictor.excelling.xlAccessor;

import common.ElementPrintableLinkedList;
import loadPrediction.core.predictor.excelling.CellPosition;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.List2LoadDataVisitor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-16 15:54。电邮 1174751315@qq.com。
 */
public class XlLpLoadDataAccessor extends AbstractXLAccessor {
    private LoadData readOneLoadDataFromFormulas(String sheetName, Integer col, Integer row) {
        forceCalcAllFormulas();
        List<Double> loadData = new LinkedList<Double>();
        Sheet sheet = workbook.getSheet(sheetName);
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

    public ElementPrintableLinkedList<LoadData> readSomeLoadDataFromFormulas(CellPosition position, Integer nbr) {
        ElementPrintableLinkedList<LoadData> loadDatas = new ElementPrintableLinkedList<LoadData>("ld");
        CellPosition ofPredictionLoads = position;
        for (int i = 0; i < nbr; i++) {
            LoadData ld = this.readOneLoadDataFromFormulas(ofPredictionLoads.getSheetName(), ofPredictionLoads.getCol() + i, ofPredictionLoads.getRow());
            loadDatas.add(ld);
        }
        return loadDatas;
    }

    public void writeOneLoadData2Cells(CellPosition position,
                                       LoadData data) {
        String sheetName = position.getSheetName();
        Short col = position.getCol();
        Integer row = position.getRow();
        Sheet sheet = workbook.getSheet(sheetName);
        List<Double> loadData = data.toList();

        for (int k = 0; k < 96; k++) {
            Cell cell = sheet.getRow(row + k).getCell(col);
            cell.setCellValue(loadData.get(k));
        }
        forceCalcAllFormulas();
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
                this.writeOneLoadData2Cells(pos.ofColAfter(j), loads.get(j));
            }
        }
    }
}
