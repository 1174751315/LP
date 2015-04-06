/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  jfreechart.JFreeChartFacade;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;
import  loadPrediction.utils.FileContentUtils;
import org.jfree.chart.JFreeChart;

/**
 * 李倍存 创建于 2015-03-08 17:58。电邮 1174751315@qq.com。
 */
public class PredictionLoad23LinePictureVisitor implements IPredictorVisitor {
    private String dir;

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        return null;
    }

    public PredictionLoad23LinePictureVisitor(String dir) {
        this.dir = dir;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
//        JFreeChart chart;
//        chart=(JFreeChart)predictor.accept(new PredictionLoad2ChartVisitor());
//        this.saveAsFile(chart,predictor.getDate().toLocalDate().toString()+"-预测.png");
//        chart =(JFreeChart)predictor.getActual96PointLoadOfFirstPredictionDay().accept(new LoadData2JFreeChartVisitor());
//        this.saveAsFile(chart,predictor.getDate().toLocalDate().toString()+"-实际.png");
//        chart=(JFreeChart)this.unnamed(predictor.getPrediction96PointLoads().get(0),predictor.getActual96PointLoadOfFirstPredictionDay());
//        this.saveAsFile(chart,predictor.getDate().toLocalDate().toString()+"combine.png");
        JFreeChart chart = (JFreeChart) predictor.accept(new PredictionLoad2ChartVisitor());
        String fileName = FileContentUtils.autoFileName(("WORKDAY_3_LINE" + predictor.getDateString().replaceAll("-", "")), ".JPG");
        new JFreeChartFacade().saveAs(chart, dir + fileName);
        return dir + fileName;
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        JFreeChart chart = (JFreeChart) predictor.accept(new PredictionLoad2ChartVisitor());
        String fileName = FileContentUtils.autoFileName(("WEEKEND_3_LINE" + predictor.getDateString().replaceAll("-", "")), ".JPG");
        new JFreeChartFacade().saveAs(chart, dir + fileName);
        return dir + fileName;
    }


}
