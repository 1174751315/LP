/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.excelling;

import  common.ElementPrintableLinkedList;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.util.CommonUtils;
import  loadPrediction.core.predictor.util.WeekendUtils;
import  loadPrediction.core.predictor.visitors.IPredictorVisitor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.exception.DAE;
import  loadPrediction.exception.LPE;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.DateUtil;
import loadPrediction.utils.ListUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-24 8:43。电邮 1174751315@qq.com。
 */
public class ExcellingWeekendPredictor extends AbstractTemplateMethodExcellingPredictor implements IWeekendPredictor {
    /*
    * Index 0 工作日
    * Index 1 周末
    * */


    List<Integer> his = ListUtils.unnamed(20, 12);
    Integer pre = 2;

    public ExcellingWeekendPredictor(Date date) {
        super(date);
    }

    @Override
    protected String doGetInputWorkbookPath() {
        return IOPaths.WEB_CONTENT_WEEKEND_TEMPLATE_PATH;
    }

    @Override
    protected String doGetOutputWorkbookPath() {
        return IOPaths.WEB_CONTENT_TEMP + dateString + "WK.xls";
    }

    @Override
    protected Integer doGetPredictionDaysNbr() {
        return pre;
    }

    @Override
    protected List<Integer> doGetHistoryDaysNbrs() {
        return his;
    }

    @Override
    protected ElementPrintableLinkedList<SimpleDate> doGetPredictionDays() throws LPE {
        return WeekendUtils.doGetPredictionDays(date, pre);
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays() throws LPE {
        try {
            return WeekendUtils.doGetHistoryDays(date, his);
        } catch (LPE lpe) {
            if (lpe.getScope() == LPE.eScope.USER)
                throw lpe;
        }
        return null;
    }

    @Override
    protected List<CellPosition> doGetHistoryDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("B2", "周末气象数据"), new CellPosition("B28", "周末气象数据"));
    }

    @Override
    protected CellPosition doGetPredictionDaysExcelPosition() {
        return new CellPosition("B22", "周末气象数据");
    }

    @Override
    protected CellPosition doGetPredictionWeatherExcelPosition() {
        return new CellPosition("D22", "周末气象数据");
    }

    @Override
    protected List<CellPosition> doGetHistoryWeatherExcelPositions() {
        return ListUtils.unnamed(new CellPosition("D2", "周末气象数据"), new CellPosition("D28", "周末气象数据"));
    }

    @Override
    protected List<CellPosition> doGetSimilarDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("C29", "相似日查找-相似日为工作日"), new CellPosition("C21", "相似日查找-相似日为周末"));
    }

    @Override
    protected List<CellPosition> doGetSimilarLoadsExcelPosition() {
        return ListUtils.unnamed(new CellPosition("B20", "周末96节点负荷预测"), new CellPosition("F20", "周末96节点负荷预测"));
    }

    @Override
    protected CellPosition doGetActualLoadsExcelPosition() {
        return new CellPosition("B8", "待预测周末实际负荷数据");
    }

    @Override
    protected Boolean doValidate(Date date) {
        return true;
    }

    @Override
    protected CellPosition doGetPredictionLoadsExcelPosition() {
        return new CellPosition("F106", "待预测周末实际负荷数据");
    }

    @Override
    protected CellPosition doGetAccuraciesExcelPosition() {
        return new CellPosition("B6", "待预测周末实际负荷数据");
    }

    @Override
    public Object accept(IPredictorVisitor visitor) throws LPE{
         return visitor.visitWeekendPredictor(this);
    }

    @Override
    public String getPredictorType() {
        return "EXCELLING 周末";
    }

    @Override
    protected void doAfterInjectSimilarLoads(Workbook activeWorkbook) throws LPE {
        Date thursday = DateUtil.getDateBefore(date, 2);
        CellPosition pos = new CellPosition("J19", "周末96节点负荷预测");
        try {
            SimpleDate thurs = DAOFactory.getDefault().createDaoSimpleDate().query(thursday.toLocalDate().toString());
            Sheet sh = activeWorkbook.getSheet(pos.getSheetName());
            List<Double> loads = DAOFactory.getDefault().createDaoLoadData().query(thurs.getDateString()).toList();
            for (int k = 0; k < 96; k++) {
                Cell cell = sh.getRow(pos.getRow() + 1 + k).getCell(pos.getCol());
                cell.setCellValue(loads.get(k));
            }
        } catch (DAE e) {
            throw new LPE("数据访问异常", LPE.eScope.USER);
        }

    }
}
