/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.shape.TriangleMesh;
import jfreechart.JFreeChartFacade;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor;
import loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor_1;
import loadPrediction.exception.LPE;
import loadPrediction.utils.AccuracyUtils;
import loadPrediction.utils.FileContentUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.CategoryTableXYDataset;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad24LinePictureVisitor_1 implements IPredictorVisitor {

    private String dir;

    public PredictionLoad24LinePictureVisitor_1(String dir) {
        this.dir = dir;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return unnamed(predictor, "WORKDAY_4_LINE");
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return unnamed(predictor, "WEEKEND_4_LINE");
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        throw new LPE("方法未实现");
    }


    public String unnamed(IPredictor predictor, String prefix) {
        String fileName = FileContentUtils.autoFileName(prefix + predictor.getDateString(), ".JPG");

        CategoryTableXYDataset ds = new CategoryTableXYDataset();


        LoadData actual = predictor.getActual96PointLoads().get(0);
        LoadData prediction = predictor.getPrediction96PointLoads().get(0);
        LoadData lwr=prediction.multiple(0.943396226415094);
        LoadData upr=prediction.multiple(1.06382978723404);

        List<LoadData> list=new LinkedList<LoadData>();
        list.add(prediction);
        list.add(lwr);
        list.add(upr);

        ds = (CategoryTableXYDataset) prediction.accept(new LoadDataAppend2DatasetVisitor_1(ds, "预测负荷"));
        ds = (CategoryTableXYDataset) upr.accept(new LoadDataAppend2DatasetVisitor_1(ds, "上包络线"));
        ds = (CategoryTableXYDataset) lwr.accept(new LoadDataAppend2DatasetVisitor_1(ds, "下包络线"));
        if (actual != null) {
            ds = (CategoryTableXYDataset) actual.accept(new LoadDataAppend2DatasetVisitor_1(ds, "实际负荷"));
            list.add(actual);
        }
        Double acc = 0.;
        if (actual != null) {
            acc = AccuracyUtils.calcOneAccuracy(actual, prediction);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(predictor.getDateString(),"时刻","功率/MW",ds, PlotOrientation.VERTICAL,true,true,true);// new JFreeChartFacade().createLineChart(predictor.getDateString(),"时刻","功率/MW",ds);

        XYPlot xyPlot=chart.getXYPlot();
//        XYItemRenderer renderer=xyPlot.getRenderer();
        XYLineAndShapeRenderer renderer=new XYLineAndShapeRenderer();

        BasicStroke dotLine=new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0F, new float[] {3.F, 3.F}, 1.0F);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(1, dotLine);
        renderer.setSeriesPaint(1, Color.pink);
        renderer.setSeriesStroke(2, dotLine);
        renderer.setSeriesPaint(2, Color.orange);
        renderer.setBasePaint(Color.BLACK);
        renderer.setSeriesShape(1,new Rectangle());
        renderer.setSeriesShape(2,new Rectangle());
        renderer.setSeriesShapesVisible(1,true);
        renderer.setSeriesShapesVisible(2,true);

        if (actual != null) {
            renderer.setSeriesStroke(3, new BasicStroke(2));
            renderer.setSeriesPaint(3, Color.GREEN);
        }

//        CategoryPlot plot=chart.getCategoryPlot();

        xyPlot.setRenderer(renderer);

        /*图形区域背景颜色*/
        xyPlot.setBackgroundPaint(Color.BLACK);

        /*图片背景颜色*/
        chart.setBackgroundPaint(Color.GRAY);
        chart.setBorderPaint(Color.BLACK);

        xyPlot.setRangeGridlineStroke(new BasicStroke(1));
        xyPlot.setDomainGridlinesVisible(true);
        xyPlot.setRangeGridlinesVisible(true);
        xyPlot.setRangeGridlinePaint(Color.lightGray);
        xyPlot.setDomainGridlinePaint(Color.lightGray);


        MaxAveMinTuple<Double> t=unnamed(list);

        ValueAxis valueAxis=xyPlot.getRangeAxis();
        valueAxis.setLowerBound(t.min*0.99);
        valueAxis.setUpperBound(t.max*1.01);


        new JFreeChartFacade().saveAs(chart, dir + fileName);
        return dir + fileName;
    }


    MaxAveMinTuple<Double> unnamed(List<LoadData> list ){
        List<MaxAveMinTuple<Double>> list1=new LinkedList<MaxAveMinTuple<Double>>();
        for (int i = 0; i <list.size() ; i++) {
            list1.add(list.get(i).toMaxAveMin());
        }

        MaxAveMinTuple<Double> t=new MaxAveMinTuple<Double>(0.,0.,100000.);

        for (int i = 0; i <list1.size() ; i++) {
            if (t.max<list1.get(i).getMax())
                t.max=list1.get(i).getMax();
            if (t.min>list1.get(i).getMin())
                t.min=list1.get(i).getMin();
        }
        return t;
    }
}

