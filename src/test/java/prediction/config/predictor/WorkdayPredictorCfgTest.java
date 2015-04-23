package prediction.config.predictor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prediction.core.predictor.excelling.CellPosition;
import prediction.resouce.IOPaths;

import java.util.List;

import static org.junit.Assert.*;

public class WorkdayPredictorCfgTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAccPosition()throws Exception{

        CellPosition position=predictorCfg.getAccuraciesPosition();
//        assertEquals(position.getClass())
    }

    @Test
    public void testDaysNbrs()throws Exception{


        Integer nbr=predictorCfg.getPredictionDaysNbr();
        assertEquals(Integer.valueOf(7),nbr);

        List<Integer> nbrs=predictorCfg.getHistoryDaysNbrs();
        assertEquals(1,nbrs.size());
        assertEquals(Integer.valueOf(14),nbrs.get(0));

    }

    @Test
    public void testDaysPositions()throws Exception{

        List<CellPosition> historyDaysPositions=predictorCfg.getHistoryDaysPositions();
        assertEquals(1,historyDaysPositions.size());
        assertEquals(historyDaysPositions.get(0).getSheetName(),"工作日气象数据");
        assertEquals(historyDaysPositions.get(0).getRef(),"B2");

        CellPosition predictionDaysPosition=predictorCfg.getPredictionDaysPosition();
        assertEquals(predictionDaysPosition.getRef(),"B16");
        assertEquals(predictionDaysPosition.getSheetName(),"工作日气象数据");

        List<CellPosition> similarDaysPositions=predictorCfg.getSimilarDaysPositions();
        assertEquals(1,similarDaysPositions.size());
        assertEquals("D28",similarDaysPositions.get(0).getRef());
        assertEquals("相似日查找-工作日",similarDaysPositions.get(0).getSheetName());

    }

    @Test
    public void testWeatherPositions()throws Exception{
        List<CellPosition> historyWeatherPositions=predictorCfg.getHistoryWeathersPositions();

        assertEquals(1,historyWeatherPositions.size());
        assertEquals("N2",historyWeatherPositions.get(0).getRef());
        assertEquals("工作日气象数据",historyWeatherPositions.get(0).getSheetName());

        CellPosition predictionWeatherPosition=predictorCfg.getPredictionWeathersPosition();
        assertEquals("N16",predictionWeatherPosition.getRef());
        assertEquals("工作日气象数据",predictionWeatherPosition.getSheetName());



    }
    @Test
    public void testOtherPositions()throws Exception{
        List<CellPosition> similarLoad=predictorCfg.getSimilarLoadsPosition();

        assertEquals(1,similarLoad.size());
        assertEquals("工作日96节点负荷预测",similarLoad.get(0).getSheetName());
        assertEquals("B17",similarLoad.get(0).getRef());

        CellPosition predictionLoad=predictorCfg.getPredictionLoadsPosition();
        assertEquals("B115",predictionLoad.getRef());
        assertEquals("工作日96节点负荷预测",predictionLoad.getSheetName());

        CellPosition acc=predictorCfg.getAccuraciesPosition();
        CellPosition actualLoad=predictorCfg.getActualLoadsPosition();
        assertEquals("待预测日实际负荷数据",actualLoad.getSheetName());
        assertEquals("待预测日实际负荷数据",acc.getSheetName());
        assertEquals("B9",actualLoad.getRef());
        assertEquals("B7",acc.getRef());

    }



    PredictorCfg predictorCfg=new PredictorCfg(IOPaths.WEB_CONTENT_WORKDAY_PREDICTOR_CFG_PATH);

    private void init()throws Exception{

    }
}