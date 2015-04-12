package loadPrediction.core.predictor.util;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import loadPrediction.core.predictor.WorkdayPredictorTestDataRepo;
import loadPrediction.domain.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class CommonUtilsTest {
    public CommonUtilsTest() {
        try {
            init();
        }catch (Exception e){
            assertNull(e);
        }

    }

    @Before
    public  void setUp()throws Exception{

    }

    @Test
    public void testGetHistoryWeather() throws Exception {
        ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> weas=commonUtils.getHistoryWeather(mock2DSimpleDates);
        assertEquals(weas.size(),1);
        assertEquals(weas.get(0).size(),7);
    }

    @Test
    public void testGetPredictionWeather() throws Exception {
        ElementPrintableLinkedList<WeatherData> weas=commonUtils.getPredictionWeather(mock1DSimpleDates);
        assertEquals(weas.size(),7);
    }

    @Test
    public void testGetSimilarDaysLoad() throws Exception {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads=commonUtils.getSimilarDaysLoad(mock2DSimpleDates);
        assertEquals(loads.size(),1);
        assertEquals(loads.get(0).size(),7);
    }

    @Test
    public void testGetSimilarDaysLoad_1() throws Exception {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads=commonUtils.getSimilarDaysLoad_1(mock2DDateStrings);
        assertEquals(loads.size(),1);
        assertEquals(loads.get(0).size(),7);

    }

    @Test
    public void testGetActualLoad() throws Exception {
        ElementPrintableLinkedList<LoadData> loads=commonUtils.getActualLoad(mock1DSimpleDates);
        assertEquals(loads.size(),7);
    }

    @Test
    public void testSimpleDate2DateString() throws Exception {
        List<String> strings=commonUtils.simpleDate2DateString(simpleDates);
        assertEquals(strings.size(),1);
        assertEquals(strings.get(0),"2014-04-02");
    }

    private CommonUtils commonUtils;
    private WeatherObtainer mockWeatherObtainer;
    private LoadsObtainer mockLoadObtainer;


    private ElementPrintableLinkedList<SimpleDate> simpleDates=new ElementPrintableLinkedList<SimpleDate>("");
    private PrintableLinkedList<String> strings=new PrintableLinkedList<String>("");
    private ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> mock2DSimpleDates;
    private ElementPrintableLinkedList<PrintableLinkedList<String>> mock2DDateStrings;
    private ElementPrintableLinkedList<SimpleDate> mock1DSimpleDates;
    private PrintableLinkedList<String> mock1DDateStrings;

    private void init()throws Exception{
        mockWeatherObtainer=mock(WeatherObtainer.class);
        mockLoadObtainer=mock(LoadsObtainer.class);

        when(mockWeatherObtainer.tryGetSomeWeathers(any(List.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.predictionWeathers);
        when(mockLoadObtainer.tryGetSomeLoads(any(List.class))).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());

        commonUtils=new CommonUtils();

        commonUtils.setLoadsObtainer(mockLoadObtainer);
        commonUtils.setWeatherObtainer(mockWeatherObtainer);

        mock1DSimpleDates=mock(ElementPrintableLinkedList.class);
        mock2DSimpleDates=mock(ElementPrintableLinkedList.class);
        mock1DDateStrings=mock(PrintableLinkedList.class);
        mock2DDateStrings=mock(ElementPrintableLinkedList.class);


        SimpleDate simpleDate=new SimpleDate("2014-04-02",new SimpleDateType(),new SimpleDateWeatherType());
        simpleDates.add(simpleDate);
        String string="2014-04-02";
        strings.add(string);

        when(mock2DDateStrings.size()).thenReturn(1);
        when(mock2DSimpleDates.size()).thenReturn(1);
        when(mock2DSimpleDates.get(anyInt())).thenReturn(simpleDates);
        when(mock2DDateStrings.get(anyInt())).thenReturn(strings);


    }
}