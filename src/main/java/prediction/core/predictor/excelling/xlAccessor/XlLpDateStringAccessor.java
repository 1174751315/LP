package prediction.core.predictor.excelling.xlAccessor;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import prediction.core.predictor.excelling.CellPosition;
import prediction.domain.SimpleDate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-16 15:53。电邮 1174751315@qq.com。
 */
public class XlLpDateStringAccessor extends AbstractXLAccessor {
    PrintableLinkedList<String> readSomeDateStrings(CellPosition position, Integer nbr) {
        forceCalcAllFormulas();
        Sheet sheet = workbook.getSheet(position.getSheetName());
        Integer row = position.getRow();
        Short col = position.getCol();
        PrintableLinkedList<String> strings = new PrintableLinkedList<String>(
                "unnamed");
        for (int j = 0; j < nbr; j++) {
            Cell cell = sheet.getRow(row + j).getCell(col);
            CellValue cv = evaluator.evaluate(cell);
            strings.add(cv.formatAsString().replaceAll("\"", ""));
        }
        return strings;
    }

    public ElementPrintableLinkedList<PrintableLinkedList<String>> readSimilarDateStrings(List<CellPosition> positions, Integer nbr) {

        ElementPrintableLinkedList<PrintableLinkedList<String>> similar = new ElementPrintableLinkedList<PrintableLinkedList<String>>("similar");
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            PrintableLinkedList<String> dates = readSomeDateStrings(pos, nbr);
            similar.add(dates);
        }
        return similar;
    }

    public void writeSomeDateStrings2Cells(CellPosition position, List<SimpleDate> dates) {
        String sheetName = position.getSheetName();
        Short col = position.getCol();
        Integer row = position.getRow();
        Sheet sheet = workbook.getSheet(sheetName);
        for (int j = 0; j < dates.size(); j++) {
            String ds = dates.get(j).getDateString();
            Cell cell = sheet.getRow(row + j).getCell(col.intValue());
            Date date = Date.valueOf(ds);
            cell.setCellValue(date);
            System.out.println(cell.getDateCellValue().toString());
        }
        forceCalcAllFormulas();
    }

    public void writeSomeDateStrings2Cells(List<CellPosition> positions, List<ElementPrintableLinkedList<SimpleDate>> dates) {
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            this.writeSomeDateStrings2Cells(pos, dates.get(i));
        }
    }

}
