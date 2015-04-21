package loadPrediction.aop;

import loadPrediction.exception.ExceptionHandler;
import loadPrediction.exception.ExceptionMailer;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 李倍存 创建于 2015-04-12 14:19。电邮 1174751315@qq.com。
 */

@Aspect
public class AopTraceMethodsAndThrows implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private static Logger methodTracingLogger = Logging.instance().createLogger("【调用跟踪】【返回】");
    private static Logger methodenterTracingLogger = Logging.instance().createLogger("【调用跟踪】【进入】");

    private static Logger busExceptionLogger = Logging.instance().createLogger("【业务异常】");
    private static Logger extExceptionLogger = Logging.instance().createLogger("【系统异常】");
    private static Logger daeExceptionLogger = Logging.instance().createLogger("【数据访问异常】");

    private static ExceptionMailer exceptionMailer = new ExceptionMailer(new ExceptionHandler());

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        String paras = "";
        for (int i = 0; i < objects.length; i++) {
            paras += objects[i].hashCode() + "\n";
        }
        String text = o1.getClass().getName() + "." + method.getName() + "参数" + paras + "\r返回  " + o + "\r";
        methodTracingLogger.debug(text);
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        methodenterTracingLogger.debug("进入  " + o.getClass().getName() + "." + method.getName());
    }

    public void afterThrowing(Method m, Object[] os, Object target, Throwable throwable) {
        String text = "";
        StackTraceElement[] elements = throwable.getStackTrace();
        text += target.hashCode() + "  调用  " + m.getName() + "  时发生异常  " + throwable.getClass().getSimpleName() + "\n";

        Logger logger = null;
        String exType = throwable.getClass().getSimpleName();

        if (exType.equals("LPE")) {
            logger = busExceptionLogger;
            exceptionMailer.handle(throwable);
        } else if (exType.equals("DAE")) {
            logger = daeExceptionLogger;
        } else {
            text += "类型：" + exType + "\n";
            logger = extExceptionLogger;
        }

        text += throwable.getMessage() + "\n";
        text += "起因：" + throwable.getCause() + "\n";
        text += ("栈追踪\n");
        for (int e = 0; e < elements.length; e++) {
            text += elements[e].toString() + "\n";
        }

        logger.error(text);
    }


}
