package loadPrediction.exception;

import loadPrediction.aop.Logging;
import org.apache.log4j.Logger;

/**
 * Created by LBC on 2015-04-06.
 */
public class ExceptionHandler4LowerException implements IExceptionHandler {

    private String loggerName;

    public ExceptionHandler4LowerException(String loggerName) {
        this.loggerName = loggerName;
    }

    public ExceptionHandler4LowerException() {
    }

    @Override
    public String handle(Throwable throwable, String prefix) {
        defaultHandling(throwable, Logging.instance().createLogger(loggerName), prefix);
        return prefix + throwable.getMessage();
    }


    public static void defaultHandling(Throwable exception, Logger logger, String msgPrefix) {
        new ExceptionLogger(new ExceptionMailer(new ExceptionHandler()), logger, msgPrefix).handle(exception);
    }

    public static void defaultHandling(Throwable exception, String msgPrefix) {
        defaultHandling(exception, Logging.instance().createLogger(), msgPrefix);
    }

    public static void defaultHandling(Throwable exception, Logger logger) {
        defaultHandling(exception, logger, "【未指定异常提示】");
    }

    public static void defaultHandling(Throwable e) {
        defaultHandling(e, Logging.instance().createLogger(), "【未指定异常提示】");
    }
}
