package loadPrediction.exception;

/**
 * Created by LBC on 2015-04-05.
 */
public class ExceptionHandlerDecorator extends ExceptionHandler {

    private ExceptionHandler handler;
    public ExceptionHandlerDecorator(ExceptionHandler handler){
        this.handler=handler;
    }

    @Override
    public void handle(Exception exception) {
        handler.handle(exception);
    }
}
