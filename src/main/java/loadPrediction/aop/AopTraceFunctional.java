package loadPrediction.aop;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.exception.ExceptionHandlerFactory;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by LBC on 2015-04-12.
 */
public class AopTraceFunctional implements AfterReturningAdvice,ThrowsAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        success.info(((IPredictor)o1).getDateString() );
    }
    public void afterThrowing(Method m, Object[] os, Object target, Throwable throwable) {
        fail.error(throwable.getMessage());
    }

    private static Logger success=Logging.instance().createLogger("【预测成功】");
    private static Logger fail=Logging.instance().createLogger("【预测失败】");
}
