package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor_1;
import loadPrediction.exception.LPE;
import loadPrediction.utils.AccuracyUtils;
import loadPrediction.utils.DateUtil;
import loadPrediction.utils.MyColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.CategoryTableXYDataset;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by LBC on 2015-04-08.
 */
public class ChartBuilderImpl2 implements JChartBuilder4Predictor {
    Color foreColor;
    Color backColor;
    Color gridColor;

    public ChartBuilderImpl2(Color foreColor, Color backColor, Color gridColor) {
        this.foreColor = foreColor;
        this.backColor = backColor;
        this.gridColor = gridColor;
    }
    public ChartBuilderImpl2(){
        this(MyColor.white,MyColor.c4,MyColor.lightGray);
    }
    @Override
    public JFreeChart build(IPredictor predictor) throws LPE {
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
        renderer.setSeriesPaint(0, Color.yellow);
        renderer.setSeriesStroke(1, dotLine);
        renderer.setSeriesPaint(1, Color.white);
        renderer.setSeriesStroke(2, dotLine);
        renderer.setSeriesPaint(2, Color.yellow);
        renderer.setBasePaint(Color.BLACK);
        renderer.setSeriesShape(1, new Rectangle());
        renderer.setSeriesShape(2, new Rectangle());
        renderer.setSeriesShapesVisible(1,true);
        renderer.setSeriesShapesVisible(2,true);

        if (actual != null) {
            renderer.setSeriesStroke(3, new BasicStroke(2));
            renderer.setSeriesPaint(3, Color.GREEN);
        }

//        CategoryPlot plot=chart.getCategoryPlot();

        xyPlot.setRenderer(renderer);


        /*图形区域背景颜色*/
        xyPlot.setBackgroundPaint(backColor);
        chart.setBackgroundPaint(backColor);



        chart.setBorderPaint(Color.BLACK);

        xyPlot.setRangeGridlineStroke(new BasicStroke(1));
        xyPlot.setDomainGridlinesVisible(true);
        xyPlot.setRangeGridlinesVisible(true);
        xyPlot.setRangeGridlinePaint(MyColor.white);
        xyPlot.setDomainGridlinePaint(Color.white);


        ValueAxis valueAxis=xyPlot.getRangeAxis();
        valueAxis.setTickLabelPaint(Color.white);
        valueAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 16));
        valueAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 20));
        valueAxis.setLabelPaint(Color.white);
        ValueAxis domainAxis=xyPlot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial",Font.BOLD,16));
        domainAxis.setTickLabelPaint(Color.white);
        domainAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,20));
        domainAxis.setLabelPaint(Color.white);


        chart.getTitle().setFont(new Font("Arial",Font.BOLD,20));
        chart.getTitle().setPaint(Color.white);
        MaxAveMinTuple<Double> t=PredictionLoad24LinePictureVisitor_1.unnamed(list);

        valueAxis.setLowerBound(t.min*0.99);
        valueAxis.setUpperBound(t.max * 1.01);

        return chart;
    }
}
