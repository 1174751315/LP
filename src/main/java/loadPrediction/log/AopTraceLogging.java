package loadPrediction.log;

import loadPrediction.exception.DAE;
import loadPrediction.exception.LPE;
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
public class AopTraceLogging extends AbsLogging implements MethodBeforeAdvice{

    private static Logger methodTracingLogger=Logging.instance().createLogger("方法调用跟踪：退出");
    private static Logger methodenterTracingLogger=Logging.instance().createLogger("方法调用跟踪：进入");

    private static Logger busExceptionLogger=Logging.instance().createLogger("负荷预测：业务异常");
    private static Logger extExceptionLogger=Logging.instance().createLogger("系统异常");
    private static Logger daeExceptionLogger=Logging.instance().createLogger("负荷预测：数据访问异常");

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        methodenterTracingLogger.debug("进入  "+o.getClass().getSimpleName()+"."+method.getName());
    }

    @Override
    protected void doAfterReturn(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        String text="通过  "+o1.toString()+"\r调用  "+method.toString()+"\r返回  "+o+"\r";
        methodTracingLogger.debug(text);
    }

    @Override
    protected void doAfterThrow(Method m, Object[] os, Object target, Throwable throwable) {
        String text="";
        StackTraceElement[] elements=throwable.getStackTrace();
        text+=target.hashCode()+"  调用  "+m.getName()+"  时发生异常  "+throwable.getClass().getSimpleName()+"\n";

        Logger logger=null;
        String exType=throwable.getClass().getSimpleName();

        if (exType.equals("LPE"))
            logger=busExceptionLogger;
        else if (exType.equals("DAE"))
            logger=daeExceptionLogger;
        else{
            text+="类型："+exType+"\n";
            logger=extExceptionLogger;
        }





        text+=throwable.getMessage()+"\n";
        text+="起因："+throwable.getCause()+"\n";
        text+=("栈追踪\n");
        for (int e = 0; e < elements.length; e++) {
            text+=elements[e].toString()+"\n";
        }

        logger.error(text);
    }


}
