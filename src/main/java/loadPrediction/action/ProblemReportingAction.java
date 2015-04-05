package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;
import loadPrediction.utils.LogMailingUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
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
        try {
            JavaMailSenderImpl sender= LogMailingUtils.getDefaultSender();
            MimeMessage message=LogMailingUtils.createMimeMessage(sender);
            String text="用户报告了一个问题。\n【描述】\n"+details+"\n";
            text+="【时间】"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n";

            HttpServletRequest request= ServletActionContext.getRequest();
            text+="【用户信息】"+request.getRemoteAddr()+"\n";

            message.setText(text);
            sender.send(message);
        } catch (LPE lpe) {
            lpe.printStackTrace();
            return failed(lpe.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return failed(e.getMessage());
        } catch (MailException e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }

        return SUCCESS;
    }
    private String failed(String msg){
        Logger log=Logging.instance().createLogger("问题报告");
        String inf="用户在发送问题报告时出现问题。\n";
        log.error(inf + msg);
        log.info(inf);
        return ERROR;
    }
}
