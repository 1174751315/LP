package spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 李倍存 创建于 2015-04-12 18:43。电邮 1174751315@qq.com。
 */
public class MyBeanFactory {
    private ApplicationContext context;
    private MyBeanFactory() {
        context=new ClassPathXmlApplicationContext(ConfigPaths.configFileNames,true);
    }

    public static MyBeanFactory INSTANCE=new MyBeanFactory();
    public Object getBean(String id){
        return context.getBean(id);
    }
}
