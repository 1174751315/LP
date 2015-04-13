package loadPrediction.exception;

import org.apache.log4j.Logger;

/**
 * Created by LBC on 2015-04-05.
 */
public class ExceptionLogger extends ExceptionHandlerDecorator {
    private Logger logger;
    private String msgPrefix;
    public ExceptionLogger(ExceptionHandler handler,Logger logger,String msgPrefix){
        super(handler);
        this.logger=logger;
        this.msgPrefix=msgPrefix;
    }
    public ExceptionLogger(ExceptionHandler handler,String msgPrefix){
        this(handler, loadPrediction.aop.Logging.instance().createLogger(),msgPrefix);
    }
    public ExceptionLogger(ExceptionHandler handler){
        this(handler, loadPrediction.aop.Logging.instance().createLogger(),"【未指定异常提示】]");
    }

    @Override
    public void handle(Throwable exception) {
        String info="";
        info+=msgPrefix+"\n"+exception.getMessage()+"\n";
        StackTraceElement[] traceElements=exception.getStackTrace();
        for (int i = 0; i <traceElements.length; i++) {
            info+=traceElements[i].toString()+"\n";
        }
        logger.error(info);
        logger.info(msgPrefix+"\n");
        super.handle(exception);
    }
}
