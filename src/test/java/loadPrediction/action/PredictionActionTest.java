package loadPrediction.action;

import junit.framework.TestCase;
import loadPrediction.log.Logging;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

public class PredictionActionTest extends TestCase {

    public void testIntelli() throws Exception {

            PredictionAction action = new PredictionAction();
            action.setUseCaches(false);
            action.setDateString("2014-04-22");
            action.intelli();


//        Logging.instance().createLogger("INFO").info("INFO");
//        Logging.instance().createLogger("ERR").error("ERROR");
//        Logging.instance().createLogger("DEBUG").debug("DBG");

    }
}
