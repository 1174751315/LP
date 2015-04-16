package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AllInformation2ExcelVisitorTest {


    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void setUp() throws Exception{
        workdayPredictor=mock(IWorkdayPredictor.class);
        weekendPredictor=mock(IWeekendPredictor.class);
        qingmingPredictor=mock(IQingmingPredictor.class);
    }


    @Test
    public void testVisitWorkdayPredictor() throws Exception {

    }

    @Test
    public void testVisitQingmingPredictor() throws Exception {

    }

    @Test
    public void testVisitWeekendPredictor() throws Exception {

    }

    private IWorkdayPredictor workdayPredictor;
    private IWeekendPredictor weekendPredictor;
    private IQingmingPredictor qingmingPredictor;
    private String ds="2000-01-01";
}