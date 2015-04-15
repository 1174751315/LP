import loadPrediction.core.predictor.excelling.CellPosition;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;

/**
 * Created by LBC on 2015-04-14.
 */
public class PoiTest {
    @Test
    public void testPoi()throws Exception{
        Workbook workbook=new HSSFWorkbook(new FileInputStream("f:\\test.xls"));
        CellPosition position=new CellPosition("B2","工作日气象数据");
        String sheetName=position.getSheetName();
        Short col=position.getCol();
        Integer row=position.getRow();
        Sheet sheet=workbook.getSheet(sheetName);
        for (int j = 0; j < 7; j++) {
            String ds = "2014-01-0"+(j+1);
            Cell cell=sheet.getRow(row+j).getCell(col.intValue());
            Date date=Date.valueOf(ds);
            cell.setCellValue(date);
            System.out.println(cell.getDateCellValue().toString());
        }


        workbook.write(new FileOutputStream("f:\\test_output.xls"));
        workbook.close();
        assertTrue(new File("f:\\test_output.xls").exists());

    }
}
