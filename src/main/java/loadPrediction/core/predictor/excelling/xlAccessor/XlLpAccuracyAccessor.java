package loadPrediction.core.predictor.excelling.xlAccessor;

import common.PrintableLinkedList;
import loadPrediction.core.predictor.excelling.CellPosition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 李倍存 创建于 2015-04-16 16:06。电邮 1174751315@qq.com。
 */
public class XlLpAccuracyAccessor extends AbstractXLAccessor {

    public PrintableLinkedList<Double> readSomeAccuracies(CellPosition position, Integer nbr) {

        forceCalcAllFormulas();

        CellPosition ofAcc = position;
        Sheet sh = workbook.getSheet(ofAcc.getSheetName());
        PrintableLinkedList<Double> accuracies = new PrintableLinkedList<Double>("acc");
        Integer row = ofAcc.getRow();
        Short col = ofAcc.getCol();
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
}
