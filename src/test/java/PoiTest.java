import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

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
        Sheet sheet=workbook.getSheetAt(0);
        sheet.getRow(0).getCell(0).setCellValue(Date.valueOf("2010-10-10"));
        workbook.write(new FileOutputStream("f:\\test_output.xls"));
        workbook.close();
    }
}
