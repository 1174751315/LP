package prediction.core.predictor.visitors;

import prediction.core.predictor.IPredictor;
import prediction.dataAccess.DAOAccuracy;
import prediction.dataAccess.DAOFactory;
import prediction.dataAccess.DAOLoadData;
import prediction.domain.Accuracy;
import prediction.WorkdayPredictorTestDataRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Accuracy2DBVisitorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testVisitWorkdayPredictor() throws Exception {
        init();
        Accuracy2DBVisitor visitor=new Accuracy2DBVisitor();
        visitor.setDaoFactory(mockDaoFactory);
        Accuracy accuracy=(Accuracy) (visitor.doAccessDB(mockPredictor));
        assertNotNull(accuracy);
        assertNotNull(accuracy.getAccuracy());
        assertNotNull(accuracy.getDateString());
        assertNotEquals(accuracy.getAccuracy(),0.0,0.1);
    }
    private void init() throws Exception{
        mockDaoAcc=mock(DAOAccuracy.class);
        mockDaoLoadData=mock(DAOLoadData.class);
        mockDaoFactory=mock(DAOFactory.class);
        mockPredictor=mock(IPredictor.class);


        when(mockPredictor.getPredictionDays()).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getSimpleDates());
        when(mockPredictor.getActual96PointLoads()).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());
        when(mockPredictor.getPrediction96PointLoads()).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadDatas());
        when(mockDaoFactory.createDAOAccuracy()).thenReturn(mockDaoAcc);
        when(mockDaoFactory.createDaoLoadData()).thenReturn(mockDaoLoadData);
        when(mockDaoLoadData.query(anyString())).thenReturn(WorkdayPredictorTestDataRepo.INSTANCE.getLoadData(0));
        when(mockDaoAcc.insertOrUpdate(any(Accuracy.class))).thenReturn(null);


    }

    private Accuracy acc=new Accuracy("2000-01-01",0.95);
    private DAOFactory mockDaoFactory;
    private DAOLoadData mockDaoLoadData;
    private DAOAccuracy mockDaoAcc;
    private IPredictor mockPredictor;

}