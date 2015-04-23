package prediction.core.predictor.hardCoding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HardCodingWorkdayPredictorInteTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPredict() throws Exception {
        HardCodingWorkdayPredictor predictor=new HardCodingWorkdayPredictor(Date.valueOf("2014-04-21"),false);

        predictor.predict();

        assertEquals(predictor.getAccuracy().size(),7);
        assertEquals(predictor.getPrediction96PointLoads().size(),7);
        assertEquals(predictor.getPredictionDaysNumber().intValue(),7);

        Double d=0.001;
        assertEquals(0.9200,predictor.getAccuracy().get(0),d);
        assertEquals(0.9079,predictor.getAccuracy().get(1),d);
        assertEquals(0.9222,predictor.getAccuracy().get(2),d);
        assertEquals(0.9353,predictor.getAccuracy().get(3),d);
        assertEquals(0.9303,predictor.getAccuracy().get(4),d);
        assertEquals(0.9623,predictor.getAccuracy().get(5),d);
        assertEquals(0.9597,predictor.getAccuracy().get(6),d);
//        assertEquals();
    }
}