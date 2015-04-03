package loadPrediction.action;

import junit.framework.TestCase;
import loadPrediction.log.Logging;
import org.junit.Test;

public class PredictionActionTest extends TestCase {

    public void testIntelli() throws Exception {

            PredictionAction action = new PredictionAction();
            action.setUseCaches(false);
            action.setDateString("2014-04-22");
            action.intelli();
//        Logging.instance().createLogger("INFO").info("INFO");
//        Logging.instance().createLogger("ERR").error("ERROR");
//        Logging.instance().createLogger("DEBUG").debug("DBG");

    }
}
