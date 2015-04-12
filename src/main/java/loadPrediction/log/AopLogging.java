package loadPrediction.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import java.lang.reflect.Method;

/**
 * 李倍存 创建于 2015-04-12 14:19。电邮 1174751315@qq.com。
 */

@Aspect
public class AopLogging implements AfterReturningAdvice{

//    @AfterReturning(pointcut = "execution(* loadPrediction.core.predictor.excelling.*.*(..))")


    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        Logging.instance().createLogger(o.getClass().toString()+method.getName()).info(method.getName()+"执行完毕");
        System.out.println("RETURN!!!!!!!!");
    }
    @AfterReturning(returning = "r",pointcut = "execution(* loadPrediction.core.predictor.excelling.*.*(..))")
    public void doAfterEveryMethods(Object r){
        logger.error("TEST");
        logger.info("TEST");
        logger.debug("TEST");
        System.out.println("AOP!");

    }
    private static Logger logger=Logging.instance().createLogger();
}
