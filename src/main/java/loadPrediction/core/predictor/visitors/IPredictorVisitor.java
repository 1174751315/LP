/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;

/**
 * 李倍存 创建于 2015-03-05 15:42。电邮 1174751315@qq.com。
 */
public interface IPredictorVisitor {
    /**
     * 访问者模式接口；用于为特定的IWorkdayPredictor扩展功能。
     *
     * @param predictor 预测器实例
     * @return 包含特定信息的对象；包含何种信息由实现者决定。
     */
    Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE;

    Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE;

    Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE;
}
