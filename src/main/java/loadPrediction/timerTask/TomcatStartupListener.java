package loadPrediction.timerTask;

import loadPrediction.aop.Logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by LBC on 2015/4/3.
 */
public class TomcatStartupListener implements ServletContextListener {
    Timer timer1 = null, timer2 = null, timer3 = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        timer1 = new Timer(true);
        timer2 = new Timer(true);
        timer3 = new Timer(true);


        Logging.instance().createLogger().info("定时任务开始。\n每隔12小时同步负荷和气象数据\n每隔12小时发送日志自动邮件\n");
        int second = 1000;
        int min = second * 60;
        int hour = min * 60;
//        timer1.scheduleAtFixedRate(new TimerTask4FetchingAndCalcingWeatherData(),1*hour,12*hour);
//        timer2.scheduleAtFixedRate(new TimerTask4LogMailing(),1*hour,24*hour);
//        timer3.scheduleAtFixedRate(new TimerTask4LoadPrediction(),1*hour,24*hour);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer1.cancel();
        timer2.cancel();
        timer3.cancel();
        Logging.instance().createLogger().info("定时任务结束。");
    }
}
