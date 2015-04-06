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
import  loadPrediction.domain.LoadData;
import loadPrediction.exception.LPE;
import  loadPrediction.utils.FileContentUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * 李倍存 创建于 2015-03-21 8:50。电邮 1174751315@qq.com。
 */
public class PredictionLoad21LinePictureVisitor implements IPredictorVisitor {
    private String dir;

    public PredictionLoad21LinePictureVisitor(String dir) {
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
        String fileName = FileContentUtils.autoFileName(("WORKDAY_2_LINE" + predictor.getDateString().replaceAll("-", "")), ".JPG");
        JFreeChart chart = unnamed(predictor.getPrediction96PointLoads().get(0), predictor.getActual96PointLoads().get(0));
        new JFreeChartFacade().saveAs(chart, dir + fileName);
        return dir + fileName;
    }

    private JFreeChart unnamed(LoadData prediction, LoadData actual) {
        DefaultCategoryDataset ds = new JFreeChartFacade().createDataset();
        String col = "";
//        ds.addValue(actual.getData00,"00:00:00"()(),col);
//        ds.addValue(actual.getData01,""()(),col);
//        ds.addValue(actual.getData02,""()(),col);
//        ds.addValue(actual.getData03,"00:45:00"()(),col);
//        ds.addValue(actual.getData04,"01:00:00"()(),col);
//        ds.addValue(actual.getData05,""()(),col);
//        ds.addValue(actual.getData06,""()(),col);
//        ds.addValue(actual.getData07,"01:45:00"()(),col);
//        ds.addValue(actual.getData08,"02:00:00"()(),col);
//        ds.addValue(actual.getData09,""()(),col);
//        ds.addValue(actual.getData10,""()(),col);
//        ds.addValue(actual.getData11,""()(),col);
//        ds.addValue(actual.getData12,"03:00:00"()(),col);
//        ds.addValue(actual.getData13,""()(),col);
//        ds.addValue(actual.getData14,""()(),col);
//        ds.addValue(actual.getData15,""()(),col);
//        ds.addValue(actual.getData16,"04:00:00"()(),col);
//        ds.addValue(actual.getData17,""()(),col);
//        ds.addValue(actual.getData18,""()(),col);
//        ds.addValue(actual.getData19,""()(),col);
//        ds.addValue(actual.getData20,"05:00:00"()(),col);
//        ds.addValue(actual.getData21,""()(),col);
//        ds.addValue(actual.getData22,""()(),col);
//        ds.addValue(actual.getData23,""()(),col);
//        ds.addValue(actual.getData24,"06:00:00"()(),col);
//        ds.addValue(actual.getData25,""()(),col);
//        ds.addValue(actual.getData26,""()(),col);
//        ds.addValue(actual.getData27,""()(),col);
//        ds.addValue(actual.getData28,"07:00:00"()(),col);
//        ds.addValue(actual.getData29,""()(),col);
//        ds.addValue(actual.getData30,""()(),col);
//        ds.addValue(actual.getData31,""()(),col);
//        ds.addValue(actual.getData32,"08:00:00"()(),col);
//        ds.addValue(actual.getData33,""()(),col);
//        ds.addValue(actual.getData34,""()(),col);
//        ds.addValue(actual.getData35,""()(),col);
//        ds.addValue(actual.getData36,"09:00:00"()(),col);
//        ds.addValue(actual.getData37,""()(),col);
//        ds.addValue(actual.getData38,""()(),col);
//        ds.addValue(actual.getData39,""()(),col);
//        ds.addValue(actual.getData40,"10:00:00"()(),col);
//        ds.addValue(actual.getData41,""()(),col);
//        ds.addValue(actual.getData42,""()(),col);
//        ds.addValue(actual.getData43,""()(),col);
//        ds.addValue(actual.getData44,"11:00:00"()(),col);
//        ds.addValue(actual.getData45,""()(),col);
//        ds.addValue(actual.getData46,""()(),col);
//        ds.addValue(actual.getData47,""()(),col);
//        ds.addValue(actual.getData48,"12:00:00"()(),col);
//        ds.addValue(actual.getData49,""()(),col);
//        ds.addValue(actual.getData50,""()(),col);
//        ds.addValue(actual.getData51,""()(),col);
//        ds.addValue(actual.getData52,"13:00:00"()(),col);
//        ds.addValue(actual.getData53,""(),col);
//        ds.addValue(actual.getData54,""(),col);
//        ds.addValue(actual.getData55,""(),col);
//        ds.addValue(actual.getData56,"14:00:00"(),col);
//        ds.addValue(actual.getData57,""(),col);
//        ds.addValue(actual.getData58,""(),col);
//        ds.addValue(actual.getData59,""(),col);
//        ds.addValue(actual.getData60,"15:00:00"(),col);
//        ds.addValue(actual.getData61,""(),col);
//        ds.addValue(actual.getData62,""(),col);
//        ds.addValue(actual.getData63,""(),col);
//        ds.addValue(actual.getData64,"16:00:00"(),col);
//        ds.addValue(actual.getData65,""(),col);
//        ds.addValue(actual.getData66,""(),col);
//        ds.addValue(actual.getData67,""(),col);
//        ds.addValue(actual.getData68,"17:00:00"(),col);
//        ds.addValue(actual.getData69,""(),col);
//        ds.addValue(actual.getData70,""(),col);
//        ds.addValue(actual.getData71,""(),col);
//        ds.addValue(actual.getData72,"18:00:00"(),col);
//        ds.addValue(actual.getData73,""(),col);
//        ds.addValue(actual.getData74,""(),col);
//        ds.addValue(actual.getData75,""(),col);
//        ds.addValue(actual.getData76,"19:00:00"(),col);
//        ds.addValue(actual.getData77,""(),col);
//        ds.addValue(actual.getData78,""(),col);
//        ds.addValue(actual.getData79,""(),col);
//        ds.addValue(actual.getData80,"20:00:00"(),col);
//        ds.addValue(actual.getData81,""(),col);
//        ds.addValue(actual.getData82,""(),col);
//        ds.addValue(actual.getData83,""(),col);
//        ds.addValue(actual.getData84,"21:00:00"(),col);
//        ds.addValue(actual.getData85,""(),col);
//        ds.addValue(actual.getData86,""(),col);
//        ds.addValue(actual.getData87,""(),col);
//        ds.addValue(actual.getData88,"22:00:00"(),col);
//        ds.addValue(actual.getData89,""(),col);
//        ds.addValue(actual.getData90,""(),col);
//        ds.addValue(actual.getData91,"22:45:00"(),col);
//        ds.addValue(actual.getData92,"23:00:00"(),col);
//        ds.addValue(actual.getData93,""(),col);
//        ds.addValue(actual.getData94,""(),col);
//        ds.addValue(actual.getData95,""(),col);

//        col = new String("预测负荷");
//        ds.addValue(prediction.getData00(), col, "00:00:00");
//        ds.addValue(prediction.getData01(), col, "");
//        ds.addValue(prediction.getData02(), col, "");
//        ds.addValue(prediction.getData03(), col, "");
//        ds.addValue(prediction.getData04(), col, "01:00:00");
//        ds.addValue(prediction.getData05(), col, "");
//        ds.addValue(prediction.getData06(), col, "");
//        ds.addValue(prediction.getData07(), col, "");
//        ds.addValue(prediction.getData08(), col, "02:00:00");
//        ds.addValue(prediction.getData09(), col, "");
//        ds.addValue(prediction.getData10(), col, "");
//        ds.addValue(prediction.getData11(), col, "");
//        ds.addValue(prediction.getData12(), col, "03:00:00");
//        ds.addValue(prediction.getData13(), col, "");
//        ds.addValue(prediction.getData14(), col, "");
//        ds.addValue(prediction.getData15(), col, "");
//        ds.addValue(prediction.getData16(), col, "04:00:00");
//        ds.addValue(prediction.getData17(), col, "");
//        ds.addValue(prediction.getData18(), col, "");
//        ds.addValue(prediction.getData19(), col, "");
//        ds.addValue(prediction.getData20(), col, "05:00:00");
//        ds.addValue(prediction.getData21(), col, "");
//        ds.addValue(prediction.getData22(), col, "");
//        ds.addValue(prediction.getData23(), col, "");
//        ds.addValue(prediction.getData24(), col, "06:00:00");
//        ds.addValue(prediction.getData25(), col, "");
//        ds.addValue(prediction.getData26(), col, "");
//        ds.addValue(prediction.getData27(), col, "");
//        ds.addValue(prediction.getData28(), col, "07:00:00");
//        ds.addValue(prediction.getData29(), col, "");
//        ds.addValue(prediction.getData30(), col, "");
//        ds.addValue(prediction.getData31(), col, "");
//        ds.addValue(prediction.getData32(), col, "08:00:00");
//        ds.addValue(prediction.getData33(), col, "");
//        ds.addValue(prediction.getData34(), col, "");
//        ds.addValue(prediction.getData35(), col, "");
//        ds.addValue(prediction.getData36(), col, "09:00:00");
//        ds.addValue(prediction.getData37(), col, "");
//        ds.addValue(prediction.getData38(), col, "");
//        ds.addValue(prediction.getData39(), col, "");
//        ds.addValue(prediction.getData40(), col, "10:00:00");
//        ds.addValue(prediction.getData41(), col, "");
//        ds.addValue(prediction.getData42(), col, "");
//        ds.addValue(prediction.getData43(), col, "");
//        ds.addValue(prediction.getData44(), col, "11:00:00");
//        ds.addValue(prediction.getData45(), col, "");
//        ds.addValue(prediction.getData46(), col, "");
//        ds.addValue(prediction.getData47(), col, "");
//        ds.addValue(prediction.getData48(), col, "12:00:00");
//        ds.addValue(prediction.getData49(), col, "");
//        ds.addValue(prediction.getData50(), col, "");
//        ds.addValue(prediction.getData51(), col, "");
//        ds.addValue(prediction.getData52(), col, "13:00:00");
//        ds.addValue(prediction.getData53(), col, "");
//        ds.addValue(prediction.getData54(), col, "");
//        ds.addValue(prediction.getData55(), col, "");
//        ds.addValue(prediction.getData56(), col, "14:00:00");
//        ds.addValue(prediction.getData57(), col, "");
//        ds.addValue(prediction.getData58(), col, "");
//        ds.addValue(prediction.getData59(), col, "");
//        ds.addValue(prediction.getData60(), col, "15:00:00");
//        ds.addValue(prediction.getData61(), col, "");
//        ds.addValue(prediction.getData62(), col, "");
//        ds.addValue(prediction.getData63(), col, "");
//        ds.addValue(prediction.getData64(), col, "16:00:00");
//        ds.addValue(prediction.getData65(), col, "");
//        ds.addValue(prediction.getData66(), col, "");
//        ds.addValue(prediction.getData67(), col, "");
//        ds.addValue(prediction.getData68(), col, "17:00:00");
//        ds.addValue(prediction.getData69(), col, "");
//        ds.addValue(prediction.getData70(), col, "");
//        ds.addValue(prediction.getData71(), col, "");
//        ds.addValue(prediction.getData72(), col, "18:00:00");
//        ds.addValue(prediction.getData73(), col, "");
//        ds.addValue(prediction.getData74(), col, "");
//        ds.addValue(prediction.getData75(), col, "");
//        ds.addValue(prediction.getData76(), col, "19:00:00");
//        ds.addValue(prediction.getData77(), col, "");
//        ds.addValue(prediction.getData78(), col, "");
//        ds.addValue(prediction.getData79(), col, "");
//        ds.addValue(prediction.getData80(), col, "20:00:00");
//        ds.addValue(prediction.getData81(), col, "");
//        ds.addValue(prediction.getData82(), col, "");
//        ds.addValue(prediction.getData83(), col, "");
//        ds.addValue(prediction.getData84(), col, "21:00:00");
//        ds.addValue(prediction.getData85(), col, "");
//        ds.addValue(prediction.getData86(), col, "");
//        ds.addValue(prediction.getData87(), col, "");
//        ds.addValue(prediction.getData88(), col, "22:00:00");
//        ds.addValue(prediction.getData89(), col, "");
//        ds.addValue(prediction.getData90(), col, "");
//        ds.addValue(prediction.getData91(), col, "");
//        ds.addValue(prediction.getData92(), col, "23:00:00");
//        ds.addValue(prediction.getData93(), col, "");
//        ds.addValue(prediction.getData94(), col, "");
//        ds.addValue(prediction.getData95(), col, "");
//
//
//        col = new String("实际负荷");
//        ds.addValue(actual.getData00(), col, "00:00:00");
//        ds.addValue(actual.getData01(), col, "");
//        ds.addValue(actual.getData02(), col, "");
//        ds.addValue(actual.getData03(), col, "");
//        ds.addValue(actual.getData04(), col, "01:00:00");
//        ds.addValue(actual.getData05(), col, "");
//        ds.addValue(actual.getData06(), col, "");
//        ds.addValue(actual.getData07(), col, "");
//        ds.addValue(actual.getData08(), col, "02:00:00");
//        ds.addValue(actual.getData09(), col, "");
//        ds.addValue(actual.getData10(), col, "");
//        ds.addValue(actual.getData11(), col, "");
//        ds.addValue(actual.getData12(), col, "03:00:00");
//        ds.addValue(actual.getData13(), col, "");
//        ds.addValue(actual.getData14(), col, "");
//        ds.addValue(actual.getData15(), col, "");
//        ds.addValue(actual.getData16(), col, "04:00:00");
        ds.addValue(actual.getData17(), col, "");
        ds.addValue(actual.getData18(), col, "");
        ds.addValue(actual.getData19(), col, "");
        ds.addValue(actual.getData20(), col, "05:00:00");
        ds.addValue(actual.getData21(), col, "");
        ds.addValue(actual.getData22(), col, "");
        ds.addValue(actual.getData23(), col, "");
        ds.addValue(actual.getData24(), col, "06:00:00");
        ds.addValue(actual.getData25(), col, "");
        ds.addValue(actual.getData26(), col, "");
        ds.addValue(actual.getData27(), col, "");
        ds.addValue(actual.getData28(), col, "07:00:00");
        ds.addValue(actual.getData29(), col, "");
        ds.addValue(actual.getData30(), col, "");
        ds.addValue(actual.getData31(), col, "");
        ds.addValue(actual.getData32(), col, "08:00:00");
        ds.addValue(actual.getData33(), col, "");
        ds.addValue(actual.getData34(), col, "");
        ds.addValue(actual.getData35(), col, "");
        ds.addValue(actual.getData36(), col, "09:00:00");
        ds.addValue(actual.getData37(), col, "");
        ds.addValue(actual.getData38(), col, "");
        ds.addValue(actual.getData39(), col, "");
        ds.addValue(actual.getData40(), col, "10:00:00");
        ds.addValue(actual.getData41(), col, "");
        ds.addValue(actual.getData42(), col, "");
        ds.addValue(actual.getData43(), col, "");
        ds.addValue(actual.getData44(), col, "11:00:00");
        ds.addValue(actual.getData45(), col, "");
        ds.addValue(actual.getData46(), col, "");
        ds.addValue(actual.getData47(), col, "");
        ds.addValue(actual.getData48(), col, "12:00:00");
        ds.addValue(actual.getData49(), col, "");
        ds.addValue(actual.getData50(), col, "");
        ds.addValue(actual.getData51(), col, "");
        ds.addValue(actual.getData52(), col, "13:00:00");
        ds.addValue(actual.getData53(), col, "");
        ds.addValue(actual.getData54(), col, "");
        ds.addValue(actual.getData55(), col, "");
        ds.addValue(actual.getData56(), col, "14:00:00");
        ds.addValue(actual.getData57(), col, "");
        ds.addValue(actual.getData58(), col, "");
        ds.addValue(actual.getData59(), col, "");
        ds.addValue(actual.getData60(), col, "15:00:00");
        ds.addValue(actual.getData61(), col, "");
        ds.addValue(actual.getData62(), col, "");
        ds.addValue(actual.getData63(), col, "");
        ds.addValue(actual.getData64(), col, "16:00:00");
        ds.addValue(actual.getData65(), col, "");
        ds.addValue(actual.getData66(), col, "");
        ds.addValue(actual.getData67(), col, "");
        ds.addValue(actual.getData68(), col, "17:00:00");
        ds.addValue(actual.getData69(), col, "");
        ds.addValue(actual.getData70(), col, "");
        ds.addValue(actual.getData71(), col, "");
        ds.addValue(actual.getData72(), col, "18:00:00");
        ds.addValue(actual.getData73(), col, "");
        ds.addValue(actual.getData74(), col, "");
        ds.addValue(actual.getData75(), col, "");
        ds.addValue(actual.getData76(), col, "19:00:00");
        ds.addValue(actual.getData77(), col, "");
        ds.addValue(actual.getData78(), col, "");
        ds.addValue(actual.getData79(), col, "");
        ds.addValue(actual.getData80(), col, "20:00:00");
        ds.addValue(actual.getData81(), col, "");
        ds.addValue(actual.getData82(), col, "");
        ds.addValue(actual.getData83(), col, "");
        ds.addValue(actual.getData84(), col, "21:00:00");
        ds.addValue(actual.getData85(), col, "");
        ds.addValue(actual.getData86(), col, "");
        ds.addValue(actual.getData87(), col, "");
        ds.addValue(actual.getData88(), col, "22:00:00");
        ds.addValue(actual.getData89(), col, "");
        ds.addValue(actual.getData90(), col, "");
        ds.addValue(actual.getData91(), col, "");
        ds.addValue(actual.getData92(), col, "23:00:00");
        ds.addValue(actual.getData93(), col, "");
        ds.addValue(actual.getData94(), col, "");
        ds.addValue(actual.getData95(), col, "");
        JFreeChart chart = ChartFactory.createLineChart("负荷预测", "时刻", "全网耗电功率/MW", ds, PlotOrientation.VERTICAL, true, true, true);
        chart.setBorderVisible(true);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        String fileName = FileContentUtils.autoFileName(("WEEKEND_2_LINE" + predictor.getDateString().replaceAll("-", "")), ".JPG");
        JFreeChart chart = unnamed(predictor.getPrediction96PointLoads().get(0), predictor.getActual96PointLoads().get(0));
        new JFreeChartFacade().saveAs(chart, dir + fileName);
        return dir + fileName;
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        throw new LPE("方法未实现");
    }

}
