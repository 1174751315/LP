package loadPrediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.WorkdayPredictorTestDataRepo;
import loadPrediction.core.predictor.util.CommonUtils;
import loadPrediction.utils.powerSystemDateQuery.AbstractPowerSystemDayQuery;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class ExcellingWorkdayPredictorTest {

    @Test
    public void testPredict() throws Exception {
        init();
        predictor.predict();

    }


    private CommonUtils mockUtils;
    private ExcellingWorkdayPredictor predictor;
    private AbstractPowerSystemDayQuery mockHistoryQuery,mockPredictionQuery;
    private void init()throws Exception{
        mockUtils=mock(CommonUtils.class);
        mockHistoryQuery=mock(AbstractPowerSystemDayQuery.class);
        mockPredictionQuery=mock(AbstractPowerSystemDayQuery.class);



        when(mockUtils.getHistoryWeather(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.historyWeathers);
        when(mockUtils.getPredictionWeather(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.predictionWeathers);
        when(mockUtils.getSimilarDaysLoad_1(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());
        when(mockUtils.getActualLoad(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());

        when(mockHistoryQuery.list(anyInt(),anyInt())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getHistoryDays());
        when(mockPredictionQuery.list(anyInt(),anyInt())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getPredictionDays());

        predictor=new ExcellingWorkdayPredictor(Date.valueOf("2014-04-21"));
        predictor.setDayQuery4HistoryDays(mockHistoryQuery);
        predictor.setDayQuery4PredictionDays(mockPredictionQuery);
        predictor.setCommonUtils(mockUtils);


    }
}