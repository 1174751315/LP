/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.excelling;

import  common.ConvertUtils;
import  common.ElementPrintableLinkedList;
import  loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.util.CommonUtils;
import  loadPrediction.core.predictor.visitors.IPredictorVisitor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.exception.LPE;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.*;
import  loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;
import  loadPrediction.utils.powerSystemDateQuery.QingmingQuery;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-26 21:42。电邮 1174751315@qq.com。
 */
public class ExcellingQingmingPredictor extends AbstractTemplateMethodExcellingPredictor implements IQingmingPredictor {

    public ExcellingQingmingPredictor(Date date) {
        super(date);
    }

    @Override
    protected Boolean doValidate(Date date) {
        return true;
    }

    @Override
    protected String doGetInputWorkbookPath() {
        return IOPaths.WEB_CONTENT_CONFIG+"template_qingming.xls";
    }

    @Override
    protected String doGetOutputWorkbookPath() {
        return IOPaths.WEB_CONTENT_TEMP+dateString+"QM.xls";
    }

    @Override
    protected Integer doGetPredictionDaysNbr() {
        return 3;
    }

    @Override
    protected List<Integer> doGetHistoryDaysNbrs() {
        return ListUtils.unnamed(20,12);
    }

    @Override
    protected ElementPrintableLinkedList<SimpleDate> doGetPredictionDays() throws LPE {
        try {
            return ConvertUtils.toElementPrintableLinkedList((new QingmingQuery(date)).list(1, 3), "清明");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays() throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> lists=new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("");

        try {
            Date pw=DateUtil.getDateBefore(date,1);
            while (DAOFactory.getDefault().createDaoSimpleDate().query(pw.toLocalDate().toString()).getDateType().getCode()!=0){
                pw=DateUtil.getDateBefore(pw,1);
            }

            ElementPrintableLinkedList<SimpleDate> w=ConvertUtils.toElementPrintableLinkedList((new PowerSystemWorkdayQuery(pw)).list(20, 20),"");
            lists.add(ConvertUtils.toElementPrintableLinkedList(w,""));

            ElementPrintableLinkedList<SimpleDate> q=ConvertUtils.toElementPrintableLinkedList( (new QingmingQuery(date)).list(13,13),"");
            q.removeLast();
            lists.add(q);
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected List<CellPosition> doGetHistoryDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("B2","3天节假日气象数据"),new CellPosition("B29","3天节假日气象数据"));
    }

    @Override
    protected CellPosition doGetPredictionDaysExcelPosition() {
        return new CellPosition("B22","3天节假日气象数据");
    }

    @Override
    protected CellPosition doGetPredictionWeatherExcelPosition() {
        return new CellPosition("D22","3天节假日气象数据");
    }

    @Override
    protected List<CellPosition> doGetHistoryWeatherExcelPositions() {
        return ListUtils.unnamed(new CellPosition("D2","3天节假日气象数据"),new CellPosition("D29","3天节假日气象数据"));
    }

    @Override
    protected List<CellPosition> doGetSimilarDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("C31","相似日查找-相似日为工作日"),new CellPosition("C23","相似日查找-相似日为同类型日"));
    }

    @Override
    protected List<CellPosition> doGetSimilarLoadsExcelPosition() {
        return ListUtils.unnamed(new CellPosition("B20","3天假期96节点负荷预测"),new CellPosition("G20","3天假期96节点负荷预测"));
    }

    @Override
    protected CellPosition doGetActualLoadsExcelPosition() {
        return new CellPosition("B8","待测节假日实际负荷数据");
    }


    @Override
    protected CellPosition doGetPredictionLoadsExcelPosition() {
        return new CellPosition("B8","输出");
    }

    @Override
    protected CellPosition doGetAccuraciesExcelPosition() {
        return new CellPosition("B6","待测节假日实际负荷数据");
    }

    @Override
    public Object accept(IPredictorVisitor visitor)throws LPE{
        return visitor.visitQingmingPredictor(this);
    }

    @Override
    public String getPredictorType() {
        return "清明节";
    }
}
