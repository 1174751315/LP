package prediction.core.predictor.visitors;

import common.ElementPrintableLinkedList;
import prediction.core.predictor.IWorkdayPredictor;
import prediction.dataAccess.DAOLoadData;
import prediction.domain.LoadData;
import prediction.domain.visitors.List2LoadDataVisitor;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PredictionLoad24LinePictureVisitor_1Test {
    public   void setUp()throws Exception {
        load1.setDateString("TEST-1900-01-01");
        load2.setDateString("1900-01-02");
        for (int i = 0; i <96 ; i++) {
            loads_list1.add(i*100.);
            loads_list2.add(loads_list1.get(i)*0.7);
        }
        load1.accept(new List2LoadDataVisitor(loads_list1));
        load2.accept(new List2LoadDataVisitor(loads_list2));
        for (int i = 0; i <7 ; i++) {
            if(i%2==0)
                loads_7day.add(load1.clone());
            else
                loads_7day.add(load2.clone());
        }
        workdayPredictor=mock(IWorkdayPredictor.class);
        when(workdayPredictor.getActual96PointLoads()).thenReturn(loads_7day);
        when(workdayPredictor.getPrediction96PointLoads()).thenReturn(loads_7day);
        when(workdayPredictor.getDateString()).thenReturn("1900-01-10");
        daoLoadData=mock(DAOLoadData.class);
        builder.setDaoLoadData(daoLoadData);
        when(daoLoadData.query(anyString())).thenReturn(load2.clone());
    }

    @Test
    public void testDoVisitAndOutput() throws Exception {
        setUp();
        UnifiedImageOutputVisitor visitor=new PredictionLoad24LinePictureVisitor_1("f:\\",workdayPredictor.getDateString(),builder);
        String path=(String) visitor.visitWorkdayPredictor(workdayPredictor);
        assertTrue(new File(path).exists());
    }

    ChartBuilderImpl1 builder=new ChartBuilderImpl1();
    DAOLoadData daoLoadData;
    IWorkdayPredictor workdayPredictor;
    private List<Double> loads_list1=new LinkedList<Double>(),loads_list2=new LinkedList<Double>();
    private ElementPrintableLinkedList<LoadData> loads_7day=new ElementPrintableLinkedList<LoadData>("用于单元测试");
    private LoadData load1=new LoadData(),load2=new LoadData();

}