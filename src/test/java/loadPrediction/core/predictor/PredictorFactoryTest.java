package loadPrediction.core.predictor;

import loadPrediction.dataAccess.DAOSimpleDate;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.SimpleDateType;
import loadPrediction.domain.SimpleDateWeatherType;
import loadPrediction.utils.PowerSystemDateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PredictorFactoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


        @Test
    public void testGetProperPredictor() throws Exception {

        DAOSimpleDate mockDao=mock(DAOSimpleDate.class);
        Date date=Date.valueOf("2014-04-21");
        PowerSystemDateUtil dateUtil=mock(PowerSystemDateUtil.class);

        when(dateUtil.isPowerSystemWorkday(date)).thenReturn(true);
        when(mockDao.query("2014-04-21")).thenReturn(new SimpleDate("2014-04-21",new SimpleDateType(0,"工作日"),new SimpleDateWeatherType(0,"正常")));
        PredictorFactory predictorFactory=PredictorFactory.getInstance();
        predictorFactory.setDaoSimpleDate(mockDao);
        predictorFactory.setPowerSystemDateUtil(dateUtil);

        IPredictor predictor= PredictorFactory.getInstance().getProperPredictor(date);
        predictor.predict();
        assertNotNull(predictor);
//        assertEquals(predictor.getClass().getSimpleName(),"ExcellingWorkdayPredictor");
    }
}