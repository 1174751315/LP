package prediction.core.predictor.excelling;

import common.ElementPrintableLinkedList;
import prediction.WorkdayPredictorTestDataRepo;
import prediction.core.predictor.util.CommonUtils;
import prediction.dataAccess.DAOFactory;
import prediction.dataAccess.DAOWeatherData;
import prediction.domain.LoadData;
import prediction.utils.powerSystemDateQuery.AbstractPowerSystemDayQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExcellingWorkdayPredictorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testPredict() throws Exception {
        init();
        String s=(String)predictor.predict();
        assertTrue(new File(s).exists());
    }


    private CommonUtils mockUtils;
    private ExcellingWorkdayPredictor predictor;
    private AbstractPowerSystemDayQuery mockHistoryQuery,mockPredictionQuery;
    private DAOFactory mockDaoFactory;
    private DAOWeatherData mockDaoWeatherData;
    private void init()throws Exception{
        mockUtils=mock(CommonUtils.class);
        mockHistoryQuery=mock(AbstractPowerSystemDayQuery.class);
        mockPredictionQuery=mock(AbstractPowerSystemDayQuery.class);
        mockDaoFactory=mock(DAOFactory.class);
        mockDaoWeatherData=mock(DAOWeatherData.class);

        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad=new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("");
        similarLoad.add(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());

        when(mockUtils.getHistoryWeather(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.historyWeathers);
        when(mockUtils.getPredictionWeather(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.predictionWeathers);
        when(mockUtils.getSimilarDaysLoad_1(any(ElementPrintableLinkedList.class))).thenReturn(similarLoad);
        when(mockUtils.getActualLoad(any(ElementPrintableLinkedList.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());


        when(mockUtils.getSimilarDaysLoad(any(ElementPrintableLinkedList.class))).thenReturn(similarLoad);
        when(mockHistoryQuery.list(anyInt(), anyInt())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getHistoryDays());
        when(mockPredictionQuery.list(anyInt(),anyInt())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getPredictionDays());

        when(mockDaoFactory.createDaoWeatherData()).thenReturn(mockDaoWeatherData);
        when(mockDaoWeatherData.query(anyString())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.predictionWeathers.get(1));
        predictor=new ExcellingWorkdayPredictor(Date.valueOf("2014-04-21"),mockDaoFactory);
        predictor.setDayQuery4HistoryDays(mockHistoryQuery);
        predictor.setDayQuery4PredictionDays(mockPredictionQuery);
        predictor.setCommonUtils(mockUtils);

    }
}