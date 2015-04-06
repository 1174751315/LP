package loadPrediction.exception;

/**
 * Created by LBC on 2015-04-06.
 */
public class ExceptionHandler4UpperException extends ExceptionHandler4LowerException {
    public ExceptionHandler4UpperException(String loggerName, String msgPrefix) {
        super(loggerName, msgPrefix);
    }

    public ExceptionHandler4UpperException() {
    }
}
