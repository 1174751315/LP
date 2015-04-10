package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOLoadData;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor_1;
import loadPrediction.domain.visitors.MedFiltVisitor;
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
import java.util.*;

/**
 * Created by LBC on 2015-04-08.
 */
public class ChartBuilderImpl1 implements JChartBuilder4Predictor {

    Color foreColor;Color backColor;Color gridColor;

    public ChartBuilderImpl1(Color foreColor, Color backColor, Color gridColor) {
        this.foreColor = foreColor;
        this.backColor = backColor;
        this.gridColor = gridColor;
    }
    public ChartBuilderImpl1(){
        this(MyColor.COMMON_FOREGROUND,MyColor.COMMON_BACKGROUND,MyColor.COMMON_GRID_LINE);
    }
    private DAOLoadData daoLoadData=DAOFactory.getDefault().createDaoLoadData();
@Override
    public void setDaoLoadData(DAOLoadData daoLoadData) {
        this.daoLoadData = daoLoadData;
    }

    @Override
    public JFreeChart build(IPredictor predictor) throws LPE {
        CategoryTableXYDataset ds = new CategoryTableXYDataset();

        LoadData actual = predictor.getActual96PointLoads().get(0);
        java.util.List<LoadData> predictions=predictor.getPrediction96PointLoads();
        LoadData prediction = predictions.get(0);
        LoadData lwr=prediction.multiple(0.943396226415094);
        LoadData upr=prediction.multiple(1.06382978723404);

        java.util.List<LoadData> list=new LinkedList<LoadData>();
        list.add(prediction);
        list.add(lwr);
        list.add(upr);

        ds = (CategoryTableXYDataset) prediction.accept(new LoadDataAppend2DatasetVisitor_1(ds, "今日预测负荷"));
        ds=(CategoryTableXYDataset)predictions.get(1).accept(new LoadDataAppend2DatasetVisitor_1(ds,"明日预测负荷"));
        ds=(CategoryTableXYDataset)
                daoLoadData.query(DateUtil.getDateBefore(java.sql.Date.valueOf(predictor.getDateString()), 1).toLocalDate().toString())
                .accept(new LoadDataAppend2DatasetVisitor_1(ds,"昨日实际负荷"));

//        ds = (CategoryTableXYDataset) upr.accept(new LoadDataAppend2DatasetVisitor_1(ds, "上包络线"));
//        ds = (CategoryTableXYDataset) lwr.accept(new LoadDataAppend2DatasetVisitor_1(ds, "下包络线"));
        if (actual != null) {
            ds = (CategoryTableXYDataset) actual.accept(new LoadDataAppend2DatasetVisitor_1(ds, "今日实际负荷"));
            list.add(actual);
        }
        Double acc = 0.;
        if (actual != null) {
            acc = AccuracyUtils.calcOneAccuracy(actual, prediction);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(predictor.getDateString(), "时刻", "功率/MW", ds, PlotOrientation.VERTICAL, true, true, true);// new JFreeChartFacade().createLineChart(predictor.getDateString(),"时刻","功率/MW",ds);

        XYPlot xyPlot=chart.getXYPlot();
//        XYItemRenderer renderer=xyPlot.getRenderer();
        XYLineAndShapeRenderer renderer=new XYLineAndShapeRenderer();

        BasicStroke dotLine=new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0F, new float[] {3.F, 3.F}, 1.0F);
        BasicStroke line=new BasicStroke(2);


        renderer.setSeriesStroke(0, line);
        renderer.setSeriesPaint(0, MyColor.COMMON_SERIES_1);
        renderer.setSeriesStroke(1, line);
        renderer.setSeriesPaint(1, MyColor.COMMON_SERIES_2);
        renderer.setSeriesStroke(2, dotLine);
        renderer.setSeriesPaint(2, MyColor.COMMON_SERIES_3);
        renderer.setBasePaint(Color.BLACK);


//        renderer.setSeriesShape(0,new Rectangle());
//        renderer.setSeriesShape(1,new Rectangle());
        renderer.setSeriesShape(2,new Rectangle());
        renderer.setSeriesShape(3,new Rectangle());
        renderer.setSeriesShapesVisible(0,true);
        renderer.setSeriesShapesVisible(1,true);
        renderer.setSeriesShapesVisible(2,true);
        renderer.setSeriesShapesVisible(3,true);

        if (actual != null) {
            renderer.setSeriesStroke(3, dotLine);
            renderer.setSeriesPaint(3, MyColor.COMMON_SERIES_4);

            LoadData l1=((LoadData)prediction.accept(new MedFiltVisitor(MedFiltVisitor.AVE)));
            ds=(CategoryTableXYDataset)  (l1).accept(new LoadDataAppend2DatasetVisitor_1(ds,"均值滤波"));
            renderer.setSeriesStroke(4, line);
            renderer.setSeriesPaint(4, MyColor.magenta);
            renderer.setSeriesShapesVisible(4,true);

            LoadData l2=(LoadData)prediction.accept(new MedFiltVisitor(MedFiltVisitor.MED));
            ds=(CategoryTableXYDataset)  (l2).accept(new LoadDataAppend2DatasetVisitor_1(ds,"中值滤波"));

            renderer.setSeriesStroke(5, line);
            renderer.setSeriesPaint(5, MyColor.c4);
            renderer.setSeriesShapesVisible(5,true);

            list.add(l1);
            list.add(l2);
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
        xyPlot.setRangeGridlinePaint(gridColor);
        xyPlot.setDomainGridlinePaint(gridColor);


        ValueAxis valueAxis=xyPlot.getRangeAxis();
        valueAxis.setTickLabelPaint(foreColor);
        valueAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 16));
        valueAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 20));
        valueAxis.setLabelPaint(foreColor);
        ValueAxis domainAxis=xyPlot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial",Font.BOLD,16));
        domainAxis.setTickLabelPaint(foreColor);
        domainAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,20));
        domainAxis.setLabelPaint(foreColor);


        chart.getTitle().setFont(new Font("Arial",Font.BOLD,20));
        chart.getTitle().setPaint(foreColor);
        MaxAveMinTuple<Double> t=PredictionLoad24LinePictureVisitor_1.unnamed(list);

        valueAxis.setLowerBound(t.min*0.99);
        valueAxis.setUpperBound(t.max * 1.01);
//        valueAxis.setLowerBound(0.);
//        valueAxis.setUpperBound(15000.);
        return chart;
    }
}
