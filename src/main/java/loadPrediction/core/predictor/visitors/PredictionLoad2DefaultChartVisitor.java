/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.AppendCategoryDatasetVisitor;
import loadPrediction.exception.LPE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * 李倍存 创建于 2015-03-05 21:40。电邮 1174751315@qq.com。
 */
public class PredictionLoad2DefaultChartVisitor implements IPredictorVisitor {
    public PredictionLoad2DefaultChartVisitor() {
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return this.visit(predictor, 1.06382978723404, 0.943396226415094);
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return this.visit(predictor, 1., 1.);
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        throw new LPE("尚未实现将清明节预测数据输出到CHART的功能。");
    }


    Object visit(IPredictor predictor, Double upr, Double lwr) {
        LoadData real = predictor.getPrediction96PointLoads().get(0);
        LoadData u = real.multiple(upr);
        LoadData l = real.multiple(lwr);
        DefaultCategoryDataset ds = new DefaultCategoryDataset();


        ds = (DefaultCategoryDataset) l.accept(new AppendCategoryDatasetVisitor(ds, "下包络线"));
        ds = (DefaultCategoryDataset) real.accept(new AppendCategoryDatasetVisitor(ds, "中间线"));
        ds = (DefaultCategoryDataset) u.accept(new AppendCategoryDatasetVisitor(ds, "上包络线"));

        JFreeChart chart = ChartFactory.createLineChart("负荷预测", "时刻", "全网耗电功率/MW", ds, PlotOrientation.VERTICAL, true, true, true);
        chart.setBorderVisible(true);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(1));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(1));
        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }
}
