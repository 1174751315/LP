/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  jfreechart.JFreeChartFacade;
import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor;
import  loadPrediction.utils.AccuracyUtils;
import  loadPrediction.utils.FileContentUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad24LinePictureVisitor implements IPredictorVisitor {

    private String dir;

    public PredictionLoad24LinePictureVisitor(String dir) {
        this.dir = dir;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) {
        return unnamed(predictor, "WORKDAY_4_LINE");
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) {
        return unnamed(predictor, "WEEKEND_4_LINE");
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) {
        return null;
    }


    public String unnamed(IPredictor predictor, String prefix) {
        String fileName = FileContentUtils.autoFileName(prefix + predictor.getDateString(), ".JPG");

        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        LoadData actual = predictor.getActual96PointLoads().get(0);
        LoadData prediction = predictor.getPrediction96PointLoads().get(0);


        ds = (DefaultCategoryDataset) prediction.accept(new LoadDataAppend2DatasetVisitor(ds, "预测负荷"));
        ds = (DefaultCategoryDataset) prediction.multiple(1.06382978723404).accept(new LoadDataAppend2DatasetVisitor(ds, "上包络线"));
        ds = (DefaultCategoryDataset) prediction.multiple(0.943396226415094).accept(new LoadDataAppend2DatasetVisitor(ds, "下包络线"));
        if (actual != null) {
            ds = (DefaultCategoryDataset) actual.accept(new LoadDataAppend2DatasetVisitor(ds, "实际负荷"));
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

        new JFreeChartFacade().saveAs(chart, dir + fileName);

        return dir + fileName;
    }
}

