package loadPrediction.action;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class PredictionActionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    public void testIntelli() throws Exception {

        PredictionAction action = new PredictionAction();
        action.setUseCaches(false);
        action.setDateString("2014-04-21");
        action.intelli();
        action.intelli();

        action.setDateString("2014-04-26");
        action.intelli();
//            action.setDateString("2014-04-26");
//            action.intelli();


//        Logging.instance().createLogger("INFO").info("INFO");
//        Logging.instance().createLogger("ERR").error("ERROR");
//        Logging.instance().createLogger("DEBUG").debug("DBG");

    }
}