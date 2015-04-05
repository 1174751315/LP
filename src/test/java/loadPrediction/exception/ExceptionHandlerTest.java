package loadPrediction.exception;

import junit.framework.TestCase;
import loadPrediction.log.Logging;

public class ExceptionHandlerTest extends TestCase {

    public void testHandle() throws Exception {
        try {
            throw new Exception("测试异常处理器");
        }catch (Exception e){
            ExceptionHandler.defaultHandling(e, Logging.instance().createLogger("TEST EXCEPTION HANDLING"),"TEST");
        }

    }
}