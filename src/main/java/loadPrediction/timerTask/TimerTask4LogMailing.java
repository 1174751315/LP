package loadPrediction.timerTask;



import loadPrediction.log.Logging;
import loadPrediction.resouce.IOPaths;
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
        ApplicationContext actx=new ClassPathXmlApplicationContext("spring-mail-appcontext.xml");
        JavaMailSenderImpl sender=(JavaMailSenderImpl)actx.getBean("mailSender");
        MimeMessage message=sender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true,"UTF-8");
            helper.setTo("1174751315@qq.com");
            helper.setFrom("1174751315@qq.com");
            helper.setSubject("负荷预测日志"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            FileSystemResource err=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"error.log"));
            FileSystemResource inf=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"info.log"));
            FileSystemResource dbg=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"debug.log"));
            helper.addAttachment("调试.txt",dbg);
            helper.addAttachment("信息.txt",inf);
            helper.addAttachment("错误.txt",err);
            sender.send(message);
            Logging.instance().createLogger("自动邮件").info("成功发送了日志自动邮件");
        } catch (MessagingException e) {
            e.printStackTrace();
            Logging.instance().createLogger("自动邮件").error("日志自动邮件发送失败\n"+e.getMessage());
        }
    }
}
