package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.exception.LPE;

/**
 * 李倍存 创建于 2015-04-08 20:44。电邮 1174751315@qq.com。
 */
public abstract class AbstractPredictionAccessDBVisitor implements IPredictorVisitor {
    public AbstractPredictionAccessDBVisitor() {
    }

    public AbstractPredictionAccessDBVisitor(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public DAOFactory getDaoFactory() {

        return daoFactory;
    }

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    protected DAOFactory daoFactory = DAOFactory.getDefault();

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

    abstract protected Object doAccessDB(IPredictor predictor) throws LPE;
}
