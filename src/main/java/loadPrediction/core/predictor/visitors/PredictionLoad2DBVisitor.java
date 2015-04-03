/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.PredictionLoadData;

/**
 * 李倍存 创建于 2015-03-05 15:47。电邮 1174751315@qq.com。
 */
public class PredictionLoad2DBVisitor implements IPredictorVisitor {
    public PredictionLoad2DBVisitor(DAOFactory factory) {
        this.factory = factory;
    }

    public PredictionLoad2DBVisitor() {
        this(DAOFactory.getDefault());
    }

    private static DAOFactory factory;

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) {
        /*保存预测日1负荷数据*/
        try {
            PredictionLoadData d = predictor.getPrediction96PointLoads().get(0).convertLower();
            d.setName("工作日预测-预测日1");
            d.setDateString(predictor.getPredictionDays().get(0).getDateString());
            factory.createDaoPredictionLoadData().insertOrUpdate(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictor;
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) {
        return null;
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) {
        return null;
    }

}
