package loadPrediction.log;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by LBC on 2015-04-12.
 */
public abstract class AbsLogging implements ThrowsAdvice,AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        doAfterReturn(o,method,objects,o1);
    }
    protected abstract void doAfterReturn(Object o, Method method, Object[] objects, Object o1)throws Throwable;
    public void afterThrowing(Method m,Object[] os,Object target,Throwable throwable){
        doAfterThrow(m,os,target,throwable);
    }

    protected abstract void doAfterThrow(Method m,Object[] os,Object target,Throwable throwable);


}
