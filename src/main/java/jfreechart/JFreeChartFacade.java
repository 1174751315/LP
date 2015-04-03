/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package jfreechart;

import  loadPrediction.utils.FileContentUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-05 17:59。电邮 1174751315@qq.com。
 */
public class JFreeChartFacade {

    public JFreeChart createLineChart3D(String title, String categoryLabel, String valueLabel, CategoryDataset ds) {
        return ChartFactory.createLineChart3D(title, categoryLabel, valueLabel, ds, PlotOrientation.VERTICAL, true, true, true);
    }

    public JFreeChart createLineChart(String title, String categoryLabel, String valueLabel, CategoryDataset ds) {
        return ChartFactory.createLineChart(title, categoryLabel, valueLabel, ds, PlotOrientation.VERTICAL, true, true, true);
    }

    public DefaultCategoryDataset createDataset(Comparable rowKey, List<Comparable> columnKeys, List<Double> values) throws Exception {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        Integer size = columnKeys.size();
        if (size != values.size())
            throw new Exception();
        for (int i = 0; i < size; i++) {
            ds.addValue(values.get(i), rowKey, columnKeys.get(i));
        }
        return ds;
    }

    public DefaultCategoryDataset createDataset() {
        return new DefaultCategoryDataset();
    }

    public String saveAs(JFreeChart chart, String path) {
        FileOutputStream out = null;
        try {

            File outFile = new File(path);
            if (!outFile.getParentFile().exists()) {

                outFile.getParentFile().mkdirs();

            }

            out = new FileOutputStream(path);

            // 保存为PNG

            ChartUtilities.writeChartAsPNG(out, chart, 600, 450);

            // 保存为JPEG

            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);

            out.flush();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
        return path;
    }

    public String saveAs(JFreeChart chart, String dir, String filenamePrefix) {
        String path = dir + FileContentUtils.autoFileName(filenamePrefix, ".jpg");
        return saveAs(chart, path);
    }
}
