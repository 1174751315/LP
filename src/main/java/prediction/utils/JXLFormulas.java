/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.utils;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.CellReferenceHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 李倍存 创建于 2015-03-23 9:26。电邮 1174751315@qq.com。
 */
public class JXLFormulas {
    public JXLFormulas() {
        String encoding = "UTF8";
        try {
            Workbook w = jxl.Workbook.getWorkbook(new File("F:\\test.xls"));
            OutputStreamWriter e = new OutputStreamWriter(new FileOutputStream(new File("F:\\test.txt")), encoding);

            BufferedWriter bw = new BufferedWriter(e);
            ArrayList parseErrors = new ArrayList();

            for (int i = 0; i < w.getNumberOfSheets(); ++i) {
                Sheet s = w.getSheet(i);
                bw.write(s.getName());
                bw.newLine();
                Cell[] row = null;
                Cell c = null;

                for (int i1 = 0; i1 < s.getRows(); ++i1) {
                    row = s.getRow(i1);

                    for (int j = 0; j < row.length; ++j) {
                        c = row[j];
                        if (c.getType() == CellType.DATE_FORMULA || c.getType() == CellType.NUMBER_FORMULA) {
//                            FormulaCell nfc = (FormulaCell)c;
                            StringBuffer sb = new StringBuffer();
                            CellReferenceHelper.getCellReference(c.getColumn(), c.getRow(), sb);

                            try {
                                bw.write(sb.toString() + " value:  " + c.getContents());
                                bw.flush();
//                                bw.write(" formula: " + nfc.getFormula());
                                bw.flush();
                                bw.newLine();
                            } catch (Exception var16) {
                                bw.newLine();
                                parseErrors.add(s.getName() + '!' + sb.toString() + ": " + var16.getMessage());
                            }
                        } else if (c.getType() == CellType.FORMULA_ERROR) {
//                            bw.write("包含错误公式！！！"+((FormulaCell)c).getFormula());
//                            bw.flush();
//                            bw.flush();
                            bw.newLine();
                        }

                    }
                }
            }

            bw.flush();
            bw.close();
            if (parseErrors.size() > 0) {
                System.err.println();
                System.err.println("There were " + parseErrors.size() + " errors");
                Iterator var18 = parseErrors.iterator();

                while (var18.hasNext()) {
                    System.err.println(var18.next());
                }
            }
        } catch (Exception var17) {
            System.err.println(var17.toString());
        }

    }
}
