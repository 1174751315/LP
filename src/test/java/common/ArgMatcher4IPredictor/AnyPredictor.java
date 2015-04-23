package common.ArgMatcher4IPredictor;

import prediction.core.predictor.IPredictor;
import org.mockito.ArgumentMatcher;

/**
 * 李倍存 创建于 2015-04-10 9:24。电邮 1174751315@qq.com。
 */
public class AnyPredictor extends ArgumentMatcher<IPredictor> {
    public AnyPredictor() {
    }

    @Override
    public boolean matches(Object argument) {

        return true;
    }
}
