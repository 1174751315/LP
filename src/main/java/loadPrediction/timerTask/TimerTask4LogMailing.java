package loadPrediction.timerTask;



import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;
import loadPrediction.resouce.IOPaths;
import loadPrediction.utils.LogMailingUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by LBC on 2015-04-04.
 */
public class TimerTask4LogMailing extends TimerTask{
    @Override
    public void run() {
        JavaMailSenderImpl sender=LogMailingUtils.getDefaultSender();
        try {
            MimeMessage message= LogMailingUtils.createMimeMessage(sender,"每日日志自动邮件");
            sender.send(message);
            Logging.instance().createLogger("自动邮件").info("成功发送了日志自动邮件");
        } catch (LPE e) {
            e.printStackTrace();
            Logging.instance().createLogger("自动邮件").info("日志自动邮件发送失败");
            Logging.instance().createLogger("自动邮件").error("日志自动邮件发送失败\n"+e.getMessage());
        }
    }
}
