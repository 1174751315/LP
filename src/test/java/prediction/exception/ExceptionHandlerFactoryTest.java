package prediction.exception;

import junit.framework.TestCase;

public class ExceptionHandlerFactoryTest extends TestCase {

    public void testGetLowerHandler() throws Exception {

    }

    public void testGetUpperHandler() throws Exception {
        try {
            throw new LPE("测试异常处理器工厂");
        }catch (LPE e){
            ExceptionHandlerFactory.INSTANCE.getUpperHandler().handle(e,"");
        }
    }
}