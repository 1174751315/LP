package prediction.exception;

import prediction.utils.LogMailingUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

/**
 * Created by LBC on 2015-04-05.
 */
public class ExceptionMailer extends ExceptionHandlerDecorator {

    public ExceptionMailer(ExceptionHandler handler) {
        this(handler, "");
    }

    private String prefix;

    public ExceptionMailer(ExceptionHandler handler, String subjectPrefix) {
        super(handler);
        this.prefix = subjectPrefix;

    }

    @Override
    public void handle(Throwable exception) {
        handleWithMailing(exception, prefix);
        super.handle(exception);
    }

    private void handleWithMailing(Throwable ex, String prefix) {
        StackTraceElement[] traceElements = ex.getStackTrace();

        String text = "负荷预测过程发生异常。\n";
        text += prefix + "\n";
        text += ex.getMessage() + "\n";
        for (int i = 0; i < traceElements.length; i++) {
            text += traceElements[i].toString() + "\n";
        }
        JavaMailSenderImpl sender = LogMailingUtils.getDefaultSender();
        try {
            MimeMessage message = LogMailingUtils.createMimeMessage(sender, "异常报告");
            message.setText(text);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
