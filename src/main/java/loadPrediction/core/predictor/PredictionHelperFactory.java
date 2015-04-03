/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor;

import  loadPrediction.core.predictor.hardCoding.AbstractTemplateMethodForHardCodingPredictor;
import  loadPrediction.core.predictor.hardCoding.HardCodingWeekendPredictor;
import  loadPrediction.core.predictor.hardCoding.HardCodingWorkdayPredictor;
import  loadPrediction.utils.PowerSystemDateUtil;

import java.sql.Date;

/**
 * 李倍存 创建于 2015/2/19 20:05。电邮 1174751315@qq.com。i love you
 */
public class PredictionHelperFactory {
    private static PredictionHelperFactory instance = new PredictionHelperFactory();

    public static PredictionHelperFactory getInstance() {
        return instance;
    }

    private PredictionHelperFactory() {

    }

    public AbstractTemplateMethodForHardCodingPredictor getHelper(Date date) {
        if (PowerSystemDateUtil.isPowerSystemWorkday(date))
            return new HardCodingWorkdayPredictor(date, true);
        if (PowerSystemDateUtil.isPowerSystemNoneWorkday(date))
            return new HardCodingWeekendPredictor(date);
        return new HardCodingWorkdayPredictor(date, true);
    }


}
