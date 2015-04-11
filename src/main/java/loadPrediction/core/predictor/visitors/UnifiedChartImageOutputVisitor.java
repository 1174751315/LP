package loadPrediction.core.predictor.visitors;

import jfreechart.JFreeChartFacade;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.exception.LPE;
import org.jfree.chart.JFreeChart;

/**
 * 李倍存 创建于 2015-04-11 11:27。电邮 1174751315@qq.com。
 */
public abstract class UnifiedChartImageOutputVisitor extends UnifiedImageOutputVisitor {
    public UnifiedChartImageOutputVisitor(String dir, String ds) {
        super(dir,ds);
    }

    @Override
    protected Object doVisitAndOutput(IPredictor predictor, String fileAbsPath) throws LPE {
        JFreeChart chart=doVisitAndGenerateChart(predictor);
        new JFreeChartFacade().saveAs(chart,fileAbsPath);
        return fileAbsPath;
    }
    protected abstract JFreeChart doVisitAndGenerateChart(IPredictor predictor) throws LPE;
}
