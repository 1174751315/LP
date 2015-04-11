package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.TestUtils;
import loadPrediction.dataAccess.DAOAccuracy;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOLoadData;
import loadPrediction.domain.Accuracy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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


        when(mockPredictor.getPredictionDays()).thenReturn(TestUtils.INSTANCE.getSimpleDates());
        when(mockPredictor.getActual96PointLoads()).thenReturn(TestUtils.INSTANCE.getLoadDatas());
        when(mockPredictor.getPrediction96PointLoads()).thenReturn(TestUtils.INSTANCE.getLoadDatas());
        when(mockDaoFactory.createDAOAccuracy()).thenReturn(mockDaoAcc);
        when(mockDaoFactory.createDaoLoadData()).thenReturn(mockDaoLoadData);
        when(mockDaoLoadData.query(anyString())).thenReturn(TestUtils.INSTANCE.getLoadData(0));
        when(mockDaoAcc.insertOrUpdate(any(Accuracy.class))).thenReturn(null);


    }

    private Accuracy acc=new Accuracy("2000-01-01",0.95);
    private DAOFactory mockDaoFactory;
    private DAOLoadData mockDaoLoadData;
    private DAOAccuracy mockDaoAcc;
    private IPredictor mockPredictor;
}