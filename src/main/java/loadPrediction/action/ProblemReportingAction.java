package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import loadPrediction.utils.LogMailingUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 李倍存 创建于 2015-04-04 22:19。电邮 1174751315@qq.com。
 */
public class ProblemReportingAction extends ActionSupport {
    public ProblemReportingAction() {
    }
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String sendEMail() throws Exception {
        JavaMailSenderImpl sender= LogMailingUtils.getDefaultSender();
        MimeMessage message=LogMailingUtils.createMimeMessage(sender);
        String text="用户报告了一个问题。\n【描述】\n"+details+"\n";
        text+="【时间】"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n";
        message.setText(text);
        sender.send(message);
        return SUCCESS;
    }
}
