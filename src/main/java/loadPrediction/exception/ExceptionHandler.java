package loadPrediction.exception;

import loadPrediction.log.Logging;
import loadPrediction.utils.LogMailingUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

/**
 * 李倍存 创建于 2015-04-05 21:19。电邮 1174751315@qq.com。
 */
public   class ExceptionHandler {

//    public void handleWithoutMailing(Exception ex,Logger logger,String prefix){
//        logger.info(prefix);
//        logger.error(prefix+"\n"+ex.getMessage());
//    }
//    public void handleWithoutMailing(Exception ex){
//        handleWithoutMailing(ex,Logging.instance().createLogger(),"");
//    }
//    public void handleWithoutMailing(Exception ex,String prefix){
//        handleWithoutMailing(ex,Logging.instance().createLogger(),prefix);
//    }
//
//    public void handle(Boolean mail,Boolean log){
//
//    }
//


    public ExceptionHandler(){
    }
    public  void handle(Throwable exception){
        exception.printStackTrace();
    }






}
