/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.domain.SimpleDate;

/**
 * 李倍存 创建于 2015/3/12 23:11。电邮 1174751315@qq.com。
 */
public class GetDateTypeAction extends ActionSupport {
    public GetDateTypeAction() {
    }

    public String execute() throws Exception {
        try {
            dateType = null;
            warning = "OK";
            SimpleDate date = DAOFactory.getDefault().createDaoSimpleDate().query((dateString));
            if (date == null) {
                warning = "数据库中不存在您选择的日期的信息，请检查输入。";
            } else {
                dateType = date.getDateType().getName();
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            warning = e.getMessage();
        }
        return SUCCESS;
    }

    private String dateType;

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    private String dateString;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    private String warning;

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
