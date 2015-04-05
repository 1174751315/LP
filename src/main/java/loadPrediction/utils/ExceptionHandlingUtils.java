package loadPrediction.utils;

import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 李倍存 创建于 2015-04-05 21:19。电邮 1174751315@qq.com。
 */
public class ExceptionHandlingUtils {
    public void handle(Exception ex,Logger logger,String prefix){
        String text="负荷预测过程发生异常。\n";
        text+=ex.getMessage()+"\n";
        JavaMailSenderImpl sender=LogMailingUtils.getDefaultSender();
        try {
            MimeMessage message=LogMailingUtils.createMimeMessage(sender,"异常报告");
            message.setText(text);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("在异常处理过程中再次发生异常报告邮件无法发送的异常。\n" + e.getMessage());
            logger.info("在异常处理过程中再次发生异常报告邮件无法发送的异常。\n");
        }
    }
    public void handle(Exception ex,Logger logger){
        handle(ex,logger,"");
    }
    public void handle(Exception ex){
        handle(ex, Logging.instance().createLogger(),"");
    }
    public void handle(Exception ex,String prefix){
        handle(ex,Logging.instance().createLogger(),prefix);
    }
    public void handleWithoutMailing(Exception ex,Logger logger,String prefix){
        logger.info(prefix);
        logger.error(prefix+"\n"+ex.getMessage());
    }
    public void handleWithoutMailing(Exception ex){
        handleWithoutMailing(ex,Logging.instance().createLogger(),"");
    }
    public void handleWithoutMailing(Exception ex,String prefix){
        handleWithoutMailing(ex,Logging.instance().createLogger(),prefix);
    }
}
