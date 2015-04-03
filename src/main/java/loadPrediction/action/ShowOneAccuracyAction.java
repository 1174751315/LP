/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import  jfreechart.JFreeChartFacade;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.LoadData;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.AccuracyUtils;
import  loadPrediction.utils.FileContentUtils;
import  loadPrediction.utils.LoadData2ChartUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/18 7:59。电邮 1174751315@qq.com。
 */
public class ShowOneAccuracyAction extends ActionSupport {
    public ShowOneAccuracyAction() {
    }

    private String warning;
    private String dateString;
    private String filename;
    private Double accuracy;

    public String getWarning() {
        return warning;
    }


    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getFilename() {
        return filename;
    }


    public Double getAccuracy() {
        return accuracy;
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

                String path = IOPaths.WEB_CONTENT_ROOT;
                String fileName = FileContentUtils.autoFileName("A" + dateString + "-", ".JPG", 4);

                List<LoadData> loadDatas = new LinkedList<LoadData>();
                loadDatas.add(prediction);
                loadDatas.add(actual);
                List<String> labels = new LinkedList<String>();
                labels.add("预测负荷曲线");
                labels.add("实际负荷曲线");
                accuracy = AccuracyUtils.calcOneAccuracy(actual, prediction);

                new JFreeChartFacade().saveAs(new LoadData2ChartUtils().loadData2Chart(loadDatas, labels, dateString + "：准确度为  " + accuracy.toString()), path + fileName);

                filename = fileName;

            } else if (actual != null && prediction == null) {
                warning = "您并未执行针对此日期的负荷预测，因此无法查看准确度。";
            }
        } catch (Exception e) {
            e.printStackTrace();
            warning = e.getMessage();
        }
        return SUCCESS;
    }

}
