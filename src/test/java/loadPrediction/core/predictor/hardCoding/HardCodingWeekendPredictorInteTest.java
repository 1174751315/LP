package loadPrediction.core.predictor.hardCoding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HardCodingWeekendPredictorInteTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPredict() throws Exception {
        HardCodingWeekendPredictor predictor=new HardCodingWeekendPredictor(Date.valueOf("2014-04-26"));
        predictor.predict();

        assertEquals(predictor.getAccuracy().size(),2);
        assertEquals(predictor.getPrediction96PointLoads().size(),2);
        assertEquals(2,predictor.getPredictionDaysNumber().intValue());

        Double d=0.001;
        assertEquals(0.9487,predictor.getAccuracy().get(0),d);
        assertEquals(0.9321,predictor.getAccuracy().get(1),d);
    }
}