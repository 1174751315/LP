/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import jfreechart.BarChartRendererWithThreshold;
import  jfreechart.JFreeChartFacade;
import  loadPrediction.dataAccess.DAOAccuracy;
import  loadPrediction.dataAccess.oracle.OracleDAOFactory;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.FileContentUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/16 23:59。电邮 1174751315@qq.com。
 */
public class ShowAccuraciesAction extends ActionSupport {
    public ShowAccuraciesAction() {
    }


    private String warning;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    private String dateString;

    public String getFilename() {
        return filename;
    }

    private String filename;

    public String getWarning() {
        return warning;
    }


    private Double threshold;
    private Integer number;
    private Integer unsatisfiedNumber;
    private Double unsatisfiedPercent;

    public Double getUnsatisfiedPercent() {
        return unsatisfiedPercent;
    }

    public void setUnsatisfiedPercent(Double unsatisfiedPercent) {
        this.unsatisfiedPercent = unsatisfiedPercent;
    }

    public Integer getUnsatisfiedNumber() {
        return unsatisfiedNumber;
    }

    public void setUnsatisfiedNumber(Integer unsatisfiedNumber) {
        this.unsatisfiedNumber = unsatisfiedNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public String getAccuracyStatics() throws Exception {
        warning = "OK";
        DAOAccuracy dao = new OracleDAOFactory().createDAOAccuracy();
        List<Accuracy> list = new LinkedList<Accuracy>();
        List list1 = dao.query();
        if (list1 == null) {
            warning = "尚未执行任何预测或数据损坏/缺失。/n无数据";
            return SUCCESS;
        }
        for (int i = 0; i < list1.size(); i++) {
            list.add((Accuracy) list1.get(i));
        }

        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        Integer count = 0;
        for (int i = 0; i < list.size(); i++) {
            Double acc = list.get(i).getAccuracy();
            if (acc < threshold)
                count++;
            ds.addValue(acc, "准确度", list.get(i).getDateString());
        }
        number = (list.size());
        unsatisfiedNumber = count;
        unsatisfiedPercent = unsatisfiedNumber.doubleValue() / number.doubleValue();
        JFreeChart chart = ChartFactory.createBarChart("准确度：共" + list.size() + "项,其中" + count + "项低于指定的阈值" + threshold + ",占" + Double.valueOf(unsatisfiedPercent * 100.).toString().substring(0) + "%。", "日期", "值", ds, PlotOrientation.VERTICAL, true, true, true);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new BarChartRendererWithThreshold(threshold);
        plot.setRenderer(renderer);

        String path = IOPaths.WEB_CONTENT_ROOT;
        filename = FileContentUtils.autoFileName("AS" + dateString + "-", ".JPG");
        new JFreeChartFacade().saveAs(chart, path + filename);
        filename = FileContentUtils.getFileNameFromPath(filename);

        return SUCCESS;
    }
}
