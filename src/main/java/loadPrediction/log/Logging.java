/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.log;

import  loadPrediction.resouce.IOPaths;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

/**
 * 李倍存 创建于 2015-03-25 11:41。电邮 1174751315@qq.com。
 */
public class Logging {
    private static String res = IOPaths.WEB_CONTENT_LOG4J_PROPERTIES_PATH;
    private static URL cfgFileRes = Logging.class.getResource(res);

    private Logging() {
        PropertyConfigurator.configure(res);
    }

    private static Logging instance = new Logging();

    public static Logging instance() {
        return instance;
    }

    public Logger createLogger(String name) {
        return Logger.getLogger(name);
    }

    public Logger createLogger() {
        return createLogger("默认");
    }

}
