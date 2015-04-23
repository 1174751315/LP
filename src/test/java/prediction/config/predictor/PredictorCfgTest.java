package prediction.config.predictor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prediction.core.predictor.excelling.CellPosition;
import prediction.resouce.IOPaths;

import java.util.LinkedList;
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
    public void testDaysNbrs()throws Exception{


        Integer nbr=predictorCfg.doGetPredictionDaysNbr();
        assertEquals(Integer.valueOf(7),nbr);

        List<Integer> nbrs=predictorCfg.doGetHistoryDaysNbrs();
        assertEquals(1,nbrs.size());
        assertEquals(Integer.valueOf(14),nbrs.get(0));

    }

    @Test
    public void testDaysPositions()throws Exception{
        List<CellPosition> historyDaysPositions=new LinkedList<CellPosition>();
        assertEquals(historyDaysPositions.size(),1);
        assertEquals(historyDaysPositions.get(0).getSheetName(),"工作日气象数据");
        assertEquals(historyDaysPositions.get(0).getRef(),"B2");

    }



    PredictorCfg predictorCfg=new PredictorCfg(IOPaths.WEB_CONTENT_WORKDAY_PREDICTOR_CFG_PATH);

    private void init()throws Exception{

    }
}