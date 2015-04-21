package loadPrediction.core.predictor.excelling.xlAccessor;

import loadPrediction.exception.LPE;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 李倍存 创建于 2015-04-16 15:53。电邮 1174751315@qq.com。
 */
public class AbstractXLAccessor {
    public AbstractXLAccessor() {
    }

    public AbstractXLAccessor(String wbPath) throws LPE {
        this.setWorkbook(openWorkbook(wbPath));
    }

    public Workbook getWorkbook() {
        return workbook;
    }


    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
        this.evaluator = workbook.getCreationHelper().createFormulaEvaluator();
    }

    protected Workbook workbook;
    protected FormulaEvaluator evaluator;

    protected void forceCalcAllFormulas() {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            workbook.getSheetAt(i).setForceFormulaRecalculation(true);
        }
    }


    public static void writeAndCloseWorkbook(Workbook workbook, String newPath) throws LPE {
        try {
            workbook.write(new FileOutputStream(newPath));
            workbook.close();
        } catch (FileNotFoundException e) {
            throw new LPE("保存并关闭工作簿时发生异�?", LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE("保存并关闭工作簿时发生异�?", LPE.eScope.USER);
        }
    }

    public static Workbook openWorkbook(String path) throws LPE {
        try {
            openedWorkbook = new HSSFWorkbook(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        } catch (IOException e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
        return openedWorkbook;
    }

    public static Workbook openedWorkbook = null;

    public static Workbook getOpenedWorkbook() {
        return openedWorkbook;
    }
}
