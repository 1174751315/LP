/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.jfreechart.JFreeChartFacade;
import main.java.loadPrediction.core.predictor.PredictorFactory;
import main.java.loadPrediction.core.predictor.IPredictor;
import main.java.loadPrediction.core.predictor.visitors.PredictionLoad2ChartVisitor;
import org.jfree.chart.JFreeChart;
import org.junit.Test;

public class JFreeChartFacadeTest {

    @Test
    public void testSaveAs() throws Exception {
        IPredictor predictor = PredictorFactory.getInstance().getProperPredictor("2014-02-07");
        predictor.predict();
        JFreeChart chart = (JFreeChart) predictor.accept(new PredictionLoad2ChartVisitor());
        new JFreeChartFacade().saveAs(chart, "f:\\lbc\\TEST.jpg");
    }
}