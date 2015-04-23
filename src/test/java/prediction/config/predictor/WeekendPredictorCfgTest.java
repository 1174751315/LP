package prediction.config.predictor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prediction.core.predictor.excelling.CellPosition;
import prediction.resouce.IOPaths;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeekendPredictorCfgTest {

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
        assertEquals(Integer.valueOf(2),nbr);

        List<Integer> nbrs=predictorCfg.getHistoryDaysNbrs();
        assertEquals(2,nbrs.size());
        assertEquals(Integer.valueOf(20),nbrs.get(0));
        assertEquals(Integer.valueOf(12),nbrs.get(1));

    }

    @Test
    public void testDaysPositions()throws Exception{

        List<CellPosition> historyDaysPositions=predictorCfg.getHistoryDaysPositions();
        assertEquals(2,historyDaysPositions.size());
        assertEquals(historyDaysPositions.get(0).getSheetName(),"周末气象数据");
        assertEquals(historyDaysPositions.get(0).getRef(),"B2");
        assertEquals(historyDaysPositions.get(1).getSheetName(),"周末气象数据");
        assertEquals(historyDaysPositions.get(1).getRef(),"B28");

        CellPosition predictionDaysPosition=predictorCfg.getPredictionDaysPosition();
        assertEquals(predictionDaysPosition.getRef(),"B22");
        assertEquals(predictionDaysPosition.getSheetName(),"周末气象数据");

        List<CellPosition> similarDaysPositions=predictorCfg.getSimilarDaysPositions();
        assertEquals(2,similarDaysPositions.size());
        assertEquals("C29",similarDaysPositions.get(0).getRef());
        assertEquals("相似日查找-相似日为工作日",similarDaysPositions.get(0).getSheetName());
        assertEquals("C21",similarDaysPositions.get(1).getRef());
        assertEquals("相似日查找-相似日为周末",similarDaysPositions.get(1).getSheetName());

    }

    @Test
    public void testWeatherPositions()throws Exception{
        List<CellPosition> historyWeatherPositions=predictorCfg.getHistoryWeathersPositions();

        assertEquals(2,historyWeatherPositions.size());
        assertEquals("D2",historyWeatherPositions.get(0).getRef());
        assertEquals("周末气象数据",historyWeatherPositions.get(0).getSheetName());
        assertEquals("D28",historyWeatherPositions.get(1).getRef());
        assertEquals("周末气象数据",historyWeatherPositions.get(1).getSheetName());

        CellPosition predictionWeatherPosition=predictorCfg.getPredictionWeathersPosition();
        assertEquals("D22",predictionWeatherPosition.getRef());
        assertEquals("周末气象数据",predictionWeatherPosition.getSheetName());



    }
    @Test
    public void testOtherPositions()throws Exception{
        List<CellPosition> similarLoad=predictorCfg.getSimilarLoadsPosition();

        assertEquals(2,similarLoad.size());
        assertEquals("周末96节点负荷预测",similarLoad.get(0).getSheetName());
        assertEquals("B20",similarLoad.get(0).getRef());
        assertEquals("周末96节点负荷预测",similarLoad.get(1).getSheetName());
        assertEquals("F20",similarLoad.get(1).getRef());

        CellPosition predictionLoad=predictorCfg.getPredictionLoadsPosition();
        assertEquals("F106",predictionLoad.getRef());
        assertEquals("待预测周末实际负荷数据",predictionLoad.getSheetName());

        CellPosition acc=predictorCfg.getAccuraciesPosition();
        CellPosition actualLoad=predictorCfg.getActualLoadsPosition();
        assertEquals("待预测周末实际负荷数据",actualLoad.getSheetName());
        assertEquals("待预测周末实际负荷数据",acc.getSheetName());
        assertEquals("B8",actualLoad.getRef());
        assertEquals("B6",acc.getRef());

    }



    PredictorCfg predictorCfg=new PredictorCfg(IOPaths.WEB_CONTENT_WEEKEND_PREDICTOR_CFG_PATH);

    private void init()throws Exception{

    }
}