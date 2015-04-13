package loadPrediction.aop;

import org.junit.Test;

public class AopTraceMethodsAndThrowsTest {

    @Test
    public void testBefore() throws Exception {

    }

    @Test
    public void testAfterReturning() throws Exception {

    }

    @Test
    public void testAfterThrowing() throws Exception {

        try {
            new loadPrediction.aop.Test().test();
        } catch (Exception e) {
        }
    }
}