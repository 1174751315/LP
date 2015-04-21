package prediction.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetDateTypeActionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        GetDateTypeAction action=new GetDateTypeAction();
        action.setDateString("2014-04-21");
        action.execute();
        assertEquals(action.getWarning(),"OK");
    }
}