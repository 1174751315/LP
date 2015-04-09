package common;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FilterUtilsTest {

    @Test
    public void testMedFilter() throws Exception {
        Double[] test={1.,2.,1.,2.,11.,1.,2.,1.,2.};
        List<Double > ans=FilterUtils.medFilter(test,3,300.);
    }
}