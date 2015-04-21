/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.predictor.visitors;

import prediction.core.predictor.IPredictor;
import prediction.exception.LPE;
import org.jfree.chart.JFreeChart;

/**
 * 李倍存 创建于 2015-03-08 17:58。电邮 1174751315@qq.com。
 */
public class PredictionLoad23LinePictureVisitor extends UnifiedChartImageOutputVisitor {
    @Override
    protected String getFileNamePostfix() {
        return "3_LINE_EXT";
    }

    @Override
    protected JFreeChart doVisitAndGenerateChart(IPredictor predictor) throws LPE {
        return (JFreeChart) predictor.accept(new PredictionLoad2DefaultChartVisitor());
    }

    public PredictionLoad23LinePictureVisitor(String dir, String ds) {
        super(dir, ds);
    }
}
