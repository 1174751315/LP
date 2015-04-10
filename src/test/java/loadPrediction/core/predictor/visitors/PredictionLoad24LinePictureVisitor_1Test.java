package loadPrediction.core.predictor.visitors;

import static org.mockito.Mockito.*;

import common.ArgMatcher4IPredictor.AnyPredictor;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.resouce.IOPaths;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PredictionLoad24LinePictureVisitor_1Test {
    @Test
    public void testDoVisitAndOutput() throws Exception {

        JChartBuilder4Predictor mockBuilder=mock(JChartBuilder4Predictor.class);
        IPredictor predictor=mock(IPredictor.class);
        when(mockBuilder.build(argThat(new AnyPredictor()))).thenReturn(new JFreeChart("TEST",new CategoryPlot()));


        PredictionLoad24LinePictureVisitor_1 visitor=new PredictionLoad24LinePictureVisitor_1("f:\\","1900-01-01");
        visitor.setChartBuilder(mockBuilder);




        visitor.doVisitAndOutput(predictor,"f:\\test\\test.jpg");
//        verify(mockBuilder).build(predictor).getTitle().getText().equals("TEST");

        assertTrue(new File("f:\\test\\test.jpg").exists());
    }
}