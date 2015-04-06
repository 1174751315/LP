package loadPrediction.exception;

/**
 * Created by LBC on 2015-04-06.
 */
public class ExceptionHandlerFactory {
    public IExceptionHandler getLowerHandler(){
        return new ExceptionHandler4LowerException("未命名的日志记录仪");

    }
    public IExceptionHandler getUpperHandler(){

        return new ExceptionHandler4UpperException("未命名的日志记录仪");
    }

    public static final ExceptionHandlerFactory INSTANCE=new ExceptionHandlerFactory();
}
