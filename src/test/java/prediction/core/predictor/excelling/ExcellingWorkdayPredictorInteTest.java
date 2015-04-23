package prediction.core.predictor.excelling;

import prediction.core.predictor.excelling.xlAccessor.AbstractXLAccessor;
import prediction.core.predictor.excelling.xlAccessor.XlLpAccuracyAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ExcellingWorkdayPredictorInteTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        File xl=new File(xlPath);
        xl.deleteOnExit();
        xl.delete();
    }

    private String xlPath;
    @Test
    public void testPredict() throws Exception {

        ExcellingWorkdayPredictor predictor =new ExcellingWorkdayPredictor();

        predictor.setDateString("2014-04-21");
        xlPath=(String)predictor.predict();

        /*确认预测器已输出报表*/
        assertTrue(new File(xlPath).exists());

        XlLpAccuracyAccessor accuracyAccessor=new XlLpAccuracyAccessor();
        accuracyAccessor.setWorkbook(AbstractXLAccessor.openWorkbook(xlPath));

        List<Double> accuracies=accuracyAccessor.readSomeAccuracies(new CellPosition("B7","待预测日实际负荷数据"),predictor.getPredictionDaysNumber());

        Double delta=0.001;
        /*确认预测精度值符合预期*/
        assertEquals(accuracies.size(),7);
        assertEquals(accuracies.get(0),0.9609,delta);
        assertEquals(accuracies.get(1),0.9543,delta);
        assertEquals(accuracies.get(2),0.9670,delta);
        assertEquals(accuracies.get(3),0.9648,delta);
        assertEquals(accuracies.get(4),0.9663,delta);
        assertEquals(accuracies.get(5),0.9837,delta);
        assertEquals(accuracies.get(6),0.9819,delta);

    }
}