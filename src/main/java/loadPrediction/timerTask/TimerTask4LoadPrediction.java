/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.timerTask;

import loadPrediction.action.PredictionAction;
import loadPrediction.exception.ExceptionHandlerFactory;
import loadPrediction.log.Logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * 李倍存 创建于 2015-03-06 15:01。电邮 1174751315@qq.com。
 */
public class TimerTask4LoadPrediction extends TimerTask {
    public TimerTask4LoadPrediction() {
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        Logging.instance().createLogger("每日定时任务").info("开始执行自动负荷预测并缓存");
        PredictionAction action=new PredictionAction();
        action.setDateString(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        action.setUseCaches(true);

        try {
            action.intelli();
        } catch (Exception e) {
            ExceptionHandlerFactory.INSTANCE.getUpperHandler().handle(e,"在执行每日定时任务的自动负荷预测时出现问题");
        }
        Logging.instance().createLogger("每日定时任务").info("成功");
    }
}
