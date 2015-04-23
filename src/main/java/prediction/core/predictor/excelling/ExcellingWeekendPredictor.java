/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import prediction.core.predictor.IWeekendPredictor;
import prediction.core.predictor.util.WeekendUtils;
import prediction.core.predictor.visitors.IPredictorVisitor;
import prediction.dataAccess.DAOFactory;
import prediction.domain.SimpleDate;
import prediction.exception.DAE;
import prediction.exception.LPE;
import prediction.resouce.IOPaths;
import prediction.utils.DateUtil;
import prediction.utils.ListUtils;
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

    public ExcellingWeekendPredictor() {
    }

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
    protected String doGetXmlConfigFilePath() {
        return IOPaths.WEB_CONTENT_WEEKEND_PREDICTOR_CFG_PATH;
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
    protected Boolean doValidate(Date date) {
        return true;
    }


    @Override
    public Object accept(IPredictorVisitor visitor) throws LPE {
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
