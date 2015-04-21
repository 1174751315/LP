package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.exception.LPE;
import loadPrediction.utils.color.MyColor;
import org.jfree.chart.JFreeChart;

import java.awt.*;

/**
 * Created by LBC on 2015-04-08.
 */
public abstract class AbstractChartBuilder {
    public abstract JFreeChart build(IPredictor predictor) throws LPE;

    protected Color foreColor = MyColor.COMMON_FOREGROUND;
    protected Color backColor = MyColor.COMMON_BACKGROUND;
    protected Color gridColor = MyColor.COMMON_GRID_LINE;

    public AbstractChartBuilder(Color foreColor, Color backColor, Color gridColor) {
        this.foreColor = foreColor;
        this.backColor = backColor;
        this.gridColor = gridColor;
    }

    public AbstractChartBuilder() {
    }

    public Color getForeColor() {
        return foreColor;
    }

    public void setForeColor(Color foreColor) {
        this.foreColor = foreColor;
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

}
