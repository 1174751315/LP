package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.dataAccess.DAOLoadData;
import loadPrediction.exception.LPE;
import org.jfree.chart.JFreeChart;

import java.awt.*;

/**
 * Created by LBC on 2015-04-08.
 */
public interface IChartBuilder {
    JFreeChart build(IPredictor predictor) throws LPE;
    void setDaoLoadData(DAOLoadData loadData);
}
