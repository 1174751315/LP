/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  jfreechart.JFreeChartFacade;
import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.AppendCategoryDatasetVisitor;
import loadPrediction.exception.LPE;
import  loadPrediction.utils.AccuracyUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad24LinePictureVisitor extends UnifiedChartImageOutputVisitor {
    @Override
    protected String getFileNamePostfix() {
        return "-4LINE";
    }

    public PredictionLoad24LinePictureVisitor(String dir,String ds) {
        super(dir,ds);
    }

    @Override
    protected JFreeChart doVisitAndGenerateChart(IPredictor predictor) throws LPE {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        LoadData actual = predictor.getActual96PointLoads().get(0);
        LoadData prediction = predictor.getPrediction96PointLoads().get(0);
        ds = (DefaultCategoryDataset) prediction.accept(new AppendCategoryDatasetVisitor(ds, "预测负荷"));
        ds = (DefaultCategoryDataset) prediction.multiple(1.06382978723404).accept(new AppendCategoryDatasetVisitor(ds, "上包络线"));
        ds = (DefaultCategoryDataset) prediction.multiple(0.943396226415094).accept(new AppendCategoryDatasetVisitor(ds, "下包络线"));
        if (actual != null) {
            ds = (DefaultCategoryDataset) actual.accept(new AppendCategoryDatasetVisitor(ds, "实际负荷"));
        }
        Double acc = 0.;
        if (actual != null) {
            acc = AccuracyUtils.calcOneAccuracy(actual, prediction);
        }
        JFreeChart chart = new JFreeChartFacade().createLineChart(prediction.getDateString() + "  准确度:" + (actual == null ? "不可用" : acc.toString()), "时刻", "全网耗电功率/MW", ds);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(1, new BasicStroke(1));
        renderer.setSeriesStroke(2, new BasicStroke(1));
        if (actual != null) {
            renderer.setSeriesStroke(3, new BasicStroke(2));
            renderer.setSeriesPaint(3, Color.GREEN);
        }
        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }
}

