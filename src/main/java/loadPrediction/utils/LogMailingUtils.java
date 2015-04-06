package loadPrediction.utils;

import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;
import loadPrediction.resouce.IOPaths;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 李倍存 创建于 2015-04-04 16:10。电邮 1174751315@qq.com。
 */
public class LogMailingUtils {

    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender,String from,String to,String subjectPrefix) throws LPE{
        MimeMessage message=sender.createMimeMessage();
        MimeMessageHelper helper=null;
        try {
            helper=new MimeMessageHelper(message,true,"UTF-8");
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subjectPrefix+"  "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            FileSystemResource err=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"error.log"));
            FileSystemResource inf=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"info.log"));
            FileSystemResource dbg=new FileSystemResource(new File(IOPaths.WEB_CONTENT_LOG_ROOT+"debug.log"));


            helper.addAttachment("调试.txt",dbg);
            helper.addAttachment("信息.txt",inf);
            helper.addAttachment("错误.txt",err);


        } catch (MessagingException e) {
            e.printStackTrace();
            throw new LPE("创建邮件时发生异常"+e.getMessage());
        }
        return message;
    }
    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender)throws LPE{
        return createMimeMessage(sender,"2229184705@qq.com","2229184705@qq.com","负荷预测日志");
    }
    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender,String subjectPrefix)throws LPE{
        return createMimeMessage(sender,"2229184705@qq.com","2229184705@qq.com",subjectPrefix);
    }

    public static JavaMailSenderImpl getDefaultSender(){
        ApplicationContext actx=new ClassPathXmlApplicationContext("spring-mail-appcontext.xml");
        return (JavaMailSenderImpl)actx.getBean("mailSender");
    }
}