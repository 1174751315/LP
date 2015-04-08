package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;

/**
 * Created by LBC on 2015-04-08.
 */
public abstract class FileOutputVisitor implements IPredictorVisitor {
    abstract protected Object doVisitAndOutput(IPredictor predictor,String dir);

    private String dir;

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,dir);
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,dir);
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,dir);
    }
}
