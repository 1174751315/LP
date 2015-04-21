package loadPrediction.timerTask;


import loadPrediction.aop.Logging;
import loadPrediction.exception.LPE;
import loadPrediction.utils.LogMailingUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.TimerTask;

/**
 * Created by LBC on 2015-04-04.
 */
public class TimerTask4LogMailing extends TimerTask {
    @Override
    public void run() {
        JavaMailSenderImpl sender = LogMailingUtils.getDefaultSender();
        try {
            Logging.instance().createLogger("自动邮件").info("开始发送自动邮件");
            MimeMessage message = LogMailingUtils.createMimeMessage(sender, "每日日志自动邮件");
            sender.send(message);
            Logging.instance().createLogger("自动邮件").info("成功发送了日志自动邮件");
        } catch (LPE e) {
            e.printStackTrace();
            failed(e.getMessage());

        } catch (Exception e) {
            failed(e.getMessage());
        }
    }

    private void failed(String msg) {
        Logging.instance().createLogger("自动邮件").info("日志自动邮件发送失败");
        Logging.instance().createLogger("自动邮件").error("日志自动邮件发送失败\n" + msg);
    }
}
