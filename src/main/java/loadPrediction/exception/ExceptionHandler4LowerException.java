package loadPrediction.exception;

import loadPrediction.log.Logging;
import org.apache.log4j.Logger;

/**
 * Created by LBC on 2015-04-06.
 */
public class ExceptionHandler4LowerException implements IExceptionHandler {

    private String loggerName;
    private String msgPrefix;

    public ExceptionHandler4LowerException(String loggerName, String msgPrefix) {
        this.loggerName = loggerName;
        this.msgPrefix = msgPrefix;
    }

    public ExceptionHandler4LowerException() {
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
    public void setMsgPrefix(String msgPrefix) {
        this.msgPrefix = msgPrefix;
    }


    @Override
    public void handle(Exception e) {
        defaultHandling(e,Logging.instance().createLogger(loggerName),msgPrefix);
    }



    public static void defaultHandling(Exception exception,Logger logger,String msgPrefix){
        new ExceptionLogger(new ExceptionMailler(new ExceptionHandler()),logger,msgPrefix).handle(exception);
    }
    public static void defaultHandling(Exception exception,String msgPrefix){
        defaultHandling(exception, Logging.instance().createLogger(),msgPrefix);
    }
    public static void defaultHandling(Exception exception,Logger logger){
        defaultHandling(exception,logger,"【未指定异常提示】");
    }
    public static void defaultHandling(Exception e){
        defaultHandling(e,Logging.instance().createLogger(),"【未指定异常提示】");
    }
}
