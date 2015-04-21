package loadPrediction.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LBC on 2015-04-12.
 */
public class BeanFactory {
    public static final String[] configFileNames = {
            "spring-config.xml",
            "spring-predictors.xml",
            "spring-mail-appcontext.xml",
            "spring-aop.xml",
            "spring-jobs.xml"
//            "spring-aop-exception-handling.xml"
    };
    private ApplicationContext context;

    private BeanFactory() {
        context = new ClassPathXmlApplicationContext(configFileNames, true);
    }

    public static BeanFactory INSTANCE = new BeanFactory();

    public Object getBean(String id) {
        return context.getBean(id);
    }
}
