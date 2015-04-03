/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.PredictionLoadData;

/**
 * 李倍存 创建于 2015-03-24 21:45。电邮 1174751315@qq.com。
 */
public class FirstPredictionLoadData2DBVisitor implements IPredictorVisitor {
    public FirstPredictionLoadData2DBVisitor() {
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) {
        return unnamed(predictor);
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) {
        return unnamed(predictor);
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) {
        return null;
    }

    private Object unnamed(IPredictor predictor) {
        PredictionLoadData loadData = null;
        try {
            loadData = predictor.getPrediction96PointLoads().get(0).convertLower();
            DAOFactory.getDefault().createDaoPredictionLoadData().insertOrUpdate(loadData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadData;
    }
}
