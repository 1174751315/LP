package loadPrediction.timerTask;

import junit.framework.TestCase;
import loadPrediction.timerTask.TimerTask4LogMailing;

public class TimerTask4LogMailingTest extends TestCase {

    public void testRun() throws Exception {
        new TimerTask4LogMailing().run();
    }
}