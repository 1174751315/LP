package prediction.config.predictor;

import common.ElementPrintableLinkedList;
import dom4j.Dom4jFacade;
import org.dom4j.Document;
import org.dom4j.Element;
import prediction.core.predictor.excelling.CellPosition;
import prediction.core.predictor.util.WeekendUtils;
import prediction.core.predictor.visitors.IPredictorVisitor;
import prediction.domain.SimpleDate;
import prediction.exception.LPE;
import prediction.utils.ListUtils;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-22 21:18。电邮 1174751315@qq.com。
 */
public class PredictorCfg {
    public PredictorCfg(String configFilePath) {
        Document document= Dom4jFacade.readDocument(configFilePath);
        Element root=document.getRootElement();

        Element predictor=root.element("predictor");
        System.out.println(root.getText());




    }

    Integer predictionDaysNbr;
    public Integer doGetPredictionDaysNbr() {
        return 2;
    }

    public List<Integer> doGetHistoryDaysNbrs() {
        return ListUtils.unnamed(12,20);
    }


    public List<CellPosition> doGetHistoryDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("B2", "周末气象数据"), new CellPosition("B28", "周末气象数据"));
    }

    public CellPosition doGetPredictionDaysExcelPosition() {
        return new CellPosition("B22", "周末气象数据");
    }

    public CellPosition doGetPredictionWeatherExcelPosition() {
        return new CellPosition("D22", "周末气象数据");
    }

    public List<CellPosition> doGetHistoryWeatherExcelPositions() {
        return ListUtils.unnamed(new CellPosition("D2", "周末气象数据"), new CellPosition("D28", "周末气象数据"));
    }

    public List<CellPosition> doGetSimilarDaysExcelPositions() {
        return ListUtils.unnamed(new CellPosition("C29", "相似日查找-相似日为工作日"), new CellPosition("C21", "相似日查找-相似日为周末"));
    }

    public List<CellPosition> doGetSimilarLoadsExcelPosition() {
        return ListUtils.unnamed(new CellPosition("B20", "周末96节点负荷预测"), new CellPosition("F20", "周末96节点负荷预测"));
    }

    public CellPosition doGetActualLoadsExcelPosition() {
        return new CellPosition("B8", "待预测周末实际负荷数据");
    }

    public Boolean doValidate(Date date) {
        return true;
    }

    public CellPosition doGetPredictionLoadsExcelPosition() {
        return new CellPosition("F106", "待预测周末实际负荷数据");
    }

    public CellPosition doGetAccuraciesExcelPosition() {
        return new CellPosition("B6", "待预测周末实际负荷数据");
    }


}
