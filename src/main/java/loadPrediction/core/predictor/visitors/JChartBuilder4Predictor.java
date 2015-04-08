package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.exception.LPE;
import org.jfree.chart.JFreeChart;

import java.awt.*;

/**
 * Created by LBC on 2015-04-08.
 */
public interface JChartBuilder4Predictor {
    JFreeChart build(IPredictor predictor) throws LPE;
}
