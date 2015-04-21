package learning.mockito;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.exception.LPE;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 李倍存 创建于 2015-04-09 20:54。电邮 1174751315@qq.com。
 */
public class TestMockito {
    public TestMockito() {
    }

    @Test
    public void test() throws Exception {
        IPredictor predictor = mock(IPredictor.class);

        when(predictor.getDateString()).thenReturn("2014-04-21");
        when(predictor.predict()).thenThrow(new LPE());

        predictor.predict();
    }

}
