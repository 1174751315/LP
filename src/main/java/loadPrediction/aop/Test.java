package loadPrediction.aop;

import loadPrediction.exception.LPE;

/**
 * 李倍存 创建于 2015-04-13 12:08。电邮 1174751315@qq.com。
 */
public class Test {
    public Test() {
    }
    public void test()throws LPE{
        BeanFactory beanFactory=BeanFactory.INSTANCE;
//        try {
            throw new LPE("TEST");
//        }catch (Exception e){
//
//        }
//        try {
//            throw new DAE("TEST");
//        }catch (Exception e){
//
//        }
//        try {
//            throw new Throwable("TEST");
//        }catch (Throwable e){
//
//        }
//        try {
//            throw new Exception("TEST");
//        }catch (Exception e){
//
//        }
    }
}
