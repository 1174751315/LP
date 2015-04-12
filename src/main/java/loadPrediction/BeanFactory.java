package loadPrediction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 李倍存 创建于 2015-04-12 14:08。电邮 1174751315@qq.com。
 */
public class BeanFactory {
    private ApplicationContext context;
    private BeanFactory() {
        context=new ClassPathXmlApplicationContext(ConfigPaths.configFileNames,true);
    }

    public static BeanFactory INSTANCE=new BeanFactory();
    public Object getBean(String id){
        return context.getBean(id);
    }
}
