package loadPrediction.action;

import junit.framework.TestCase;

public class ProblemReportingActionTest extends TestCase {

    public void testSendEMail() throws Exception {
        ProblemReportingAction action= new ProblemReportingAction();
        action.setDetails("当我进行2014-04-21预测时，程序崩溃了。");
        action.sendEMail();
    }
}