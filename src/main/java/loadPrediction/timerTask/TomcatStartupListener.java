package loadPrediction.timerTask;

import loadPrediction.log.Logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;

/**
 * Created by LBC on 2015/4/3.
 */
public class TomcatStartupListener implements ServletContextListener {
    Timer timer=null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        timer=new Timer();
        Logging.instance().createLogger().info("定时任务开始。\n每隔24小时同步负荷和气象数据");
        int time=1000*60;
        timer.schedule(new TimerTask4FetchingAndCalcingWeatherData(),new Date(),time);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer.cancel();
        Logging.instance().createLogger().info("定时任务结束。");
    }
}
