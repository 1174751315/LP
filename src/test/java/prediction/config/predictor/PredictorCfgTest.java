package prediction.config.predictor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prediction.core.predictor.excelling.CellPosition;
import prediction.resouce.IOPaths;

import java.util.List;

import static org.junit.Assert.*;

public class PredictorCfgTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAccPosition()throws Exception{

        CellPosition position=predictorCfg.doGetAccuraciesExcelPosition();
//        assertEquals(position.getClass())
    }

    @Test
    public void testNbrs()throws Exception{
        Integer nbr=predictorCfg.doGetPredictionDaysNbr();
        assertEquals(Integer.valueOf(2),nbr);

        List<Integer> nbrs=predictorCfg.doGetHistoryDaysNbrs();
        assertEquals(2,nbrs.size());
        assertEquals(Integer.valueOf(12),nbrs.get(0));
        assertEquals(Integer.valueOf(20),nbrs.get(1));

    }



    PredictorCfg predictorCfg=new PredictorCfg(IOPaths.WEB_CONTENT_WORKDAY_PREDICTOR_CFG_PATH);
    private void init()throws Exception{

    }
}