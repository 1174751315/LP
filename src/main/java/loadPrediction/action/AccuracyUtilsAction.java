/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import  io.BarChartRendererWithThreshold;
import  jfreechart.JFreeChartFacade;
import  loadPrediction.dataAccess.DAOAccuracy;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.domain.LoadData;
import loadPrediction.exception.DAE;
import loadPrediction.exception.ExceptionHandlerFactory;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.AccuracyUtils;
import  loadPrediction.utils.LoadData2ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 李倍存 创建于 2015/3/16 23:59。电邮 1174751315@qq.com。
 */
public class AccuracyUtilsAction extends ActionSupport {
    public AccuracyUtilsAction() {
    }


    private Double accuracy;
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

    public Double getAccuracy() {
        return accuracy;
    }

    public String getWarning() {
        return warning;
    }

    public String getOneAccuracy() throws Exception {
        warning = "OK";
        try {
            if (dateString == null) {
                warning = "未选择相应日期。";
                return SUCCESS;
            }
            LoadData actual = DAOFactory.getDefault().createDaoLoadData().query(dateString);
            LoadData prediction = DAOFactory.getDefault().createDaoPredictionLoadData().query((dateString));
            if (actual == null && prediction != null) {
                warning = "尚未取得实际负荷数据。原因可能是当前日期的负荷数据尚未生成或尚未同步至本地数据库。";
            } else if (actual == null && prediction == null) {
                warning = "实际负荷数据和预测负荷数据均不存在。请首先执行一次预测并等待实际负荷数据可用之后再执行此功能。";
            } else if (actual != null && prediction != null) {
                filename = IOPaths.WEB_CONTENT_TEMP + "A" + new Random().nextInt() + ".jpg";
                List<LoadData> loadDatas = new LinkedList<LoadData>();
                loadDatas.add(prediction);
                loadDatas.add(actual);
                List<String> labels = new LinkedList<String>();
                labels.add("预测负荷曲线");
                labels.add("实际负荷曲线");
                new JFreeChartFacade().saveAs(new LoadData2ChartUtils().loadDatas2Chart(loadDatas, labels, ""), filename);
                Integer i = filename.indexOf("TEMP");
                filename = filename.substring(i + 5);

                accuracy = AccuracyUtils.calcOneAccuracy(actual, prediction);
            } else if (actual != null && prediction == null) {
                warning = "您并未执行针对此日期的负荷预测，因此无法查看准确度。";
            }
        } catch (Exception e) {
            e.printStackTrace();
            warning = e.getMessage();
        }
        return SUCCESS;
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
        try {
            threshold = 0.95;
            DAOAccuracy dao = DAOFactory.getDefault().createDAOAccuracy();
            List<Accuracy> list = new LinkedList<Accuracy>();
            List list1 = dao.query();
            if (list1 == null) {
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
            JFreeChart chart = ChartFactory.createBarChart("准确度：共" + list.size() + "项,其中" + count + "项低于指定的阈值" + threshold + ",占" + Double.valueOf(unsatisfiedPercent * 100.).toString().substring(0, 1) + "%。", "日期", "值", ds, PlotOrientation.VERTICAL, true, true, true);

            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = new BarChartRendererWithThreshold(threshold);
            plot.setRenderer(renderer);

            filename = new JFreeChartFacade().saveAs(chart, IOPaths.WEB_CONTENT_TEMP, "AS");
            Integer i = filename.indexOf("TEMP");
            filename = filename.substring(i + 5);
        } catch (DAE dae) {
            ExceptionHandlerFactory.INSTANCE.getUpperHandler().handle(dae,"执行准确度统计时发生异常。");
        }


        return SUCCESS;

    }
}
