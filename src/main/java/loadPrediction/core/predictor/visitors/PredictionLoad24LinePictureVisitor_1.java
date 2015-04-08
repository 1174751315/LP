/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import jfreechart.JFreeChartFacade;
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
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad24LinePictureVisitor_1 extends ImageFileOutputVisitor{
    @Override
    protected Object doVisitAndOutput(IPredictor predictor, String absPath) throws LPE {
        CategoryTableXYDataset ds = new CategoryTableXYDataset();

        LoadData actual = predictor.getActual96PointLoads().get(0);
        List<LoadData> predictions=predictor.getPrediction96PointLoads();
        LoadData prediction = predictions.get(0);
        LoadData lwr=prediction.multiple(0.943396226415094);
        LoadData upr=prediction.multiple(1.06382978723404);

        List<LoadData> list=new LinkedList<LoadData>();
        list.add(prediction);
        list.add(lwr);
        list.add(upr);

        ds = (CategoryTableXYDataset) prediction.accept(new LoadDataAppend2DatasetVisitor_1(ds, "今日预测负荷"));
        ds=(CategoryTableXYDataset)predictions.get(1).accept(new LoadDataAppend2DatasetVisitor_1(ds,"明日预测负荷"));
        ds=(CategoryTableXYDataset) DAOFactory.getDefault().createDaoLoadData().query(DateUtil.getDateBefore(Date.valueOf(predictor.getDateString()),1).toLocalDate().toString()).accept(new LoadDataAppend2DatasetVisitor_1(ds,"昨日实际负荷"));

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
        JFreeChart chart = ChartFactory.createXYLineChart(predictor.getDateString(),"时刻","功率/MW",ds, PlotOrientation.VERTICAL,true,true,true);// new JFreeChartFacade().createLineChart(predictor.getDateString(),"时刻","功率/MW",ds);

        XYPlot xyPlot=chart.getXYPlot();
//        XYItemRenderer renderer=xyPlot.getRenderer();
        XYLineAndShapeRenderer renderer=new XYLineAndShapeRenderer();

        BasicStroke dotLine=new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0F, new float[] {3.F, 3.F}, 1.0F);
        BasicStroke line=new BasicStroke(2);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesPaint(0, MyColor.COMMON_SERIES_1);
        renderer.setSeriesStroke(1, line);
        renderer.setSeriesPaint(1, MyColor.COMMON_SERIES_3);
        renderer.setSeriesStroke(2, line);
        renderer.setSeriesPaint(2, MyColor.COMMON_SERIES_2);
        renderer.setBasePaint(Color.BLACK);


//        renderer.setSeriesShape(0,new Rectangle());
//        renderer.setSeriesShape(1,new Rectangle());
//        renderer.setSeriesShape(2,new Rectangle());
//        renderer.setSeriesShape(3,new Rectangle());
        renderer.setSeriesShapesVisible(0,true);
        renderer.setSeriesShapesVisible(1,true);
        renderer.setSeriesShapesVisible(2,true);
        renderer.setSeriesShapesVisible(3,true);

        if (actual != null) {
            renderer.setSeriesStroke(3, new BasicStroke(2));
            renderer.setSeriesPaint(3, MyColor.COMMON_SERIES_4);
        }

//        CategoryPlot plot=chart.getCategoryPlot();

        xyPlot.setRenderer(renderer);


        /*图形区域背景颜色*/
        xyPlot.setBackgroundPaint(MyColor.COMMON_BACKGROUND);
        chart.setBackgroundPaint(MyColor.COMMON_BACKGROUND);



        chart.setBorderPaint(Color.BLACK);

        xyPlot.setRangeGridlineStroke(new BasicStroke(1));
        xyPlot.setDomainGridlinesVisible(true);
        xyPlot.setRangeGridlinesVisible(true);
        xyPlot.setRangeGridlinePaint(MyColor.COMMON_GRID_LINE);
        xyPlot.setDomainGridlinePaint(MyColor.COMMON_GRID_LINE);


        ValueAxis valueAxis=xyPlot.getRangeAxis();
        valueAxis.setTickLabelPaint(MyColor.COMMON_FOREGROUND);
        valueAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 16));
        valueAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 20));
        valueAxis.setLabelPaint(MyColor.COMMON_FOREGROUND);
        ValueAxis domainAxis=xyPlot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial",Font.BOLD,16));
        domainAxis.setTickLabelPaint(MyColor.COMMON_FOREGROUND);
        domainAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,20));
        domainAxis.setLabelPaint(MyColor.COMMON_FOREGROUND);


        chart.getTitle().setFont(new Font("Arial",Font.BOLD,20));
        chart.getTitle().setPaint(MyColor.COMMON_FOREGROUND);
        MaxAveMinTuple<Double> t=unnamed(list);

        valueAxis.setLowerBound(t.min*0.99);
        valueAxis.setUpperBound(t.max * 1.01);
        new JFreeChartFacade().saveAs(chart,absPath);        return absPath;
    }

    public PredictionLoad24LinePictureVisitor_1(String dir,String ds) {
        super(dir,ds);
    }

    @Override
    protected String getFileNamePostfix() {
        return "-4LINE_EXT";
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

