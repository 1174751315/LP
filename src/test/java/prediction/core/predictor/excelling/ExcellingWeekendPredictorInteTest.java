package prediction.core.predictor.excelling;

import prediction.core.predictor.excelling.xlAccessor.AbstractXLAccessor;
import prediction.core.predictor.excelling.xlAccessor.XlLpAccuracyAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class ExcellingWeekendPredictorInteTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        File xl=new File(xlPath);
        xl.deleteOnExit();
        xl.delete();
    }

    private String xlPath=null;
    @Test
    public void testPredict() throws Exception {
        ExcellingWeekendPredictor predictor=new ExcellingWeekendPredictor();
        predictor.setDateString("2014-04-26");

        xlPath=(String)predictor.predict();
        assertTrue(new File(xlPath).exists());

        XlLpAccuracyAccessor xlLpAccuracyAccessor=new XlLpAccuracyAccessor();
        xlLpAccuracyAccessor.setWorkbook(AbstractXLAccessor.openWorkbook(xlPath));

        List<Double> accuracies=xlLpAccuracyAccessor.readSomeAccuracies(new CellPosition("B6","待预测周末实际负荷数据"),predictor.getPredictionDaysNumber());

        Double delta=0.001;
        assertEquals(accuracies.size(),2);
        assertEquals(accuracies.get(0),0.9621,delta);
        assertEquals(accuracies.get(1),0.9403,delta);

    }


}