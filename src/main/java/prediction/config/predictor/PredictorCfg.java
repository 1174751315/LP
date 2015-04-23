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
import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-22 21:18。电邮 1174751315@qq.com。
 */
public class PredictorCfg {
    public PredictorCfg(String configFilePath) {

        Document document= Dom4jFacade.readDocument(configFilePath);
        Element root=document.getRootElement();

        Element predictor=root.element("predictor");

        predictionDaysNbr=parseOneNbr(predictor.element("prediction-days-nbr").element("nbr"));
        historyDaysNbrs=parseSomeNbr(predictor.element("history-days-nbrs").elements());

        historyDaysCellPositions = parseSomePosition(predictor.element("history-days-positions").element("positions").elements());
        predictionDaysCellPosition=parseOnePosition(predictor.element("prediction-days-position").element("position"));
        similarDaysCellPositions=parseSomePosition(predictor.element("similar-days-positions").element("positions").elements());
        historyWeatherCellPositions=parseSomePosition(predictor.element("history-weathers-positions").element("positions").elements());
        predictionWeatherCellPosition=parseOnePosition(predictor.element("prediction-weathers-position").element("position"));
        similarLoadsPositions=parseSomePosition(predictor.element("similar-load-positions").element("positions").elements());
        predictionLoadsPosition=parseOnePosition(predictor.element("prediction-load-position").element("position"));

        actualLoadPosition=parseOnePosition(predictor.element("actual-load-position").element("position"));
        accuraciesPosition=parseOnePosition(predictor.element("accuracies-position").element("position"));
    }

    Integer predictionDaysNbr;
    List<Integer> historyDaysNbrs=new LinkedList<Integer>();


    public Integer getPredictionDaysNbr() {
        return predictionDaysNbr;
    }

    public List<Integer> getHistoryDaysNbrs() {
        return historyDaysNbrs;
    }


    List<CellPosition> historyDaysCellPositions;
    public List<CellPosition> getHistoryDaysPositions() {
        return historyDaysCellPositions;
    }

    CellPosition predictionDaysCellPosition;
    public CellPosition getPredictionDaysPosition() {
        return predictionDaysCellPosition;
    }


    CellPosition predictionWeatherCellPosition;
    public CellPosition getPredictionWeathersPosition() {
        return predictionWeatherCellPosition;
    }


    private List<CellPosition> historyWeatherCellPositions;
    public List<CellPosition> getHistoryWeathersPositions() {
        return historyWeatherCellPositions;
    }
    List<CellPosition> similarDaysCellPositions;
    public List<CellPosition> getSimilarDaysPositions() {
        return similarDaysCellPositions;
    }


    List<CellPosition> similarLoadsPositions;
    public List<CellPosition> getSimilarLoadsPosition() {
        return similarLoadsPositions ;
    }

    CellPosition actualLoadPosition;
    public CellPosition getActualLoadsPosition() {
        return actualLoadPosition;
    }


    CellPosition predictionLoadsPosition;
    public CellPosition getPredictionLoadsPosition() {
        return predictionLoadsPosition;
    }

    CellPosition accuraciesPosition;
    public CellPosition getAccuraciesPosition() {
        return accuraciesPosition;
    }







    private CellPosition parseOnePosition(Element element){
        String ref=element.attribute("cell").getValue();
        String sheet=element.attribute("sheet").getValue();
        return new CellPosition(ref,sheet);
    }
    private List<CellPosition> parseSomePosition(List elements){
        List<CellPosition> positions=new LinkedList<CellPosition>();
        for (int i = 0; i < elements.size(); i++) {
            positions.add(parseOnePosition((Element) elements.get(i)));
        }
        return positions;
    }
    private Integer parseOneNbr(Element element){
        return Integer.valueOf(element.attribute("value").getValue());
    }
    private List<Integer> parseSomeNbr(List elements){
        List<Integer> list=new LinkedList<Integer>();
        for (int i = 0; i < elements.size(); i++) {
            list.add(parseOneNbr((Element)elements.get(i)));
        }
        return list;
    }

}
