package loadPrediction.exception;

import loadPrediction.log.Logging;
import loadPrediction.utils.LogMailingUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

/**
 * Created by LBC on 2015-04-05.
 */
public class ExceptionMailler extends ExceptionHandlerDecorator {

    public ExceptionMailler(ExceptionHandler handler){
        this(handler, "");
    }
    private String prefix;
    public ExceptionMailler(ExceptionHandler handler,String subjectPrefix){
        super(handler);
        this.prefix=subjectPrefix;

    }

    @Override
    public void handle(Exception exception) {
        handleWithMailing(exception,prefix);
        super.handle(exception);
    }

    private void handleWithMailing(Exception ex,String prefix){
        String text="负荷预测过程发生异常。\n";
        text+=prefix+"\n";
        text+=ex.getMessage()+"\n";
        JavaMailSenderImpl sender= LogMailingUtils.getDefaultSender();
        try {
            MimeMessage message=LogMailingUtils.createMimeMessage(sender,"异常报告");
            message.setText(text);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
