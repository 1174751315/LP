/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.utils;

import  jfreechart.JFreeChartFacade;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/17 0:06。电邮 1174751315@qq.com。
 */
public class LoadData2ChartUtils {
    public LoadData2ChartUtils() {
    }

    public JFreeChart loadData2Chart(List<LoadData> loadDatas, List<String> labels, String title) {
        if (title == null) {
            title = "未命名";
        }
        DefaultCategoryDataset ds = new JFreeChartFacade().createDataset();
        for (int i = 0; i < loadDatas.size(); i++) {
            ds = (DefaultCategoryDataset) loadDatas.get(i).accept(new LoadDataAppend2DatasetVisitor(ds, labels.get(i)));
        }

        JFreeChart chart = ChartFactory.createLineChart(title, "时刻", "全网耗电功率/MW", ds, PlotOrientation.VERTICAL, true, true, true);
        chart.setBorderVisible(true);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        for (int i = 0; i < loadDatas.size(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(2));
        }


        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }

    public JFreeChart loadData2Chart(LoadData loadData, String label, String title) {
        DefaultCategoryDataset ds = new JFreeChartFacade().createDataset();

        ds = (DefaultCategoryDataset) loadData.accept(new LoadDataAppend2DatasetVisitor(ds, label));
        if (title == null) {
            title = "未命名";
        }
        JFreeChart chart = ChartFactory.createLineChart(title, "时刻", "全网耗电功率/MW", ds, PlotOrientation.VERTICAL, true, true, true);
        chart.setBorderVisible(true);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(2));
        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }
}
