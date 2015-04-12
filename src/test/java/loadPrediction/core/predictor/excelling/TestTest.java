package loadPrediction.core.predictor.excelling;

import loadPrediction.BeanFactory;
import org.junit.*;

import static org.junit.Assert.*;

public class TestTest {

    @org.junit.Test
    public void testTest1() throws Exception {
        Test test=(Test) BeanFactory.INSTANCE.getBean("test");
        test.test();
    }
}