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
public class AopTraceLogging extends AbsLogging{

    private static Logger methodTracingLogger=Logging.instance().createLogger("方法调用跟踪");
    private static Logger exceptionLogger=Logging.instance().createLogger("异常跟踪");


    @Override
    protected void doAfterReturn(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        methodTracingLogger.debug("通过"+o1.toString()+"\n调用"+method.toString()+"\n返回 "+o+"\n");

    }

    @Override
    protected void doAfterThrow(Method m, Object[] os, Object target, Throwable throwable) {
        String text="";
        StackTraceElement[] elements=throwable.getStackTrace();
        text+=target.hashCode()+"  调用  "+m.getName()+"  时发生异常  "+throwable.getClass().getSimpleName()+"\n";
        text+=throwable.getMessage()+"\n";
        text+="起因："+throwable.getCause()+"\n";
        text+=("栈追踪\n");
        for (int e = 0; e < elements.length; e++) {
            text+=elements[e].toString()+"\n";
        }
        exceptionLogger.error(text);
    }
}
