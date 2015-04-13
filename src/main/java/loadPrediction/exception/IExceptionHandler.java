package loadPrediction.exception;

/**
 * Created by LBC on 2015-04-06.
 */
public interface IExceptionHandler {
    String handle(Throwable throwable,String prefix);
}
