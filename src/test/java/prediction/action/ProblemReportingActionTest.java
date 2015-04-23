package prediction.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProblemReportingActionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSendEMail() throws Exception {
        ProblemReportingAction action= new ProblemReportingAction();
        action.setDetails("当我进行2014-04-21预测时，程序崩溃了。");
//        action.sendEMail();
    }
}