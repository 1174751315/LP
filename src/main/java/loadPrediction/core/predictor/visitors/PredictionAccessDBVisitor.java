package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;

/**
 * 李倍存 创建于 2015-04-08 20:44。电邮 1174751315@qq.com。
 */
public abstract class PredictionAccessDBVisitor implements IPredictorVisitor {
    public PredictionAccessDBVisitor() {
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return this.doAccessDB(predictor);
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return this.doAccessDB(predictor);
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        return this.doAccessDB(predictor);
    }

    abstract protected Object doAccessDB(IPredictor predictor)throws LPE;
}
