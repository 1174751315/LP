package loadPrediction.exception;

import jdk.nashorn.internal.runtime.Logging;
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
        this(handler, loadPrediction.log.Logging.instance().createLogger(),msgPrefix);
    }
    public ExceptionLogger(ExceptionHandler handler){
        this(handler, loadPrediction.log.Logging.instance().createLogger(),"【未指定异常提示】]");
    }

    @Override
    public void handle(Exception exception) {
        logger.error(msgPrefix+"\n"+exception.getMessage());
        logger.info(msgPrefix+"\n");
        super.handle(exception);
    }
}
