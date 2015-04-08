package loadPrediction.core.predictor.visitors;

import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;
import loadPrediction.utils.FileContentUtils;

/**
 * Created by LBC on 2015-04-08.
 */
public abstract class ImageFileOutputVisitor implements IPredictorVisitor {

    abstract protected Object doVisitAndOutput(IPredictor predictor,String fileAbsPath) throws LPE;
    abstract protected String getFileNamePostfix();
    public ImageFileOutputVisitor(String dir,String dateString) {
        this.dir = dir;
        this.dateString=dateString;
    }
    private String dir;
    private String dateString;

    public String getDir() {
        return dir;
    }
    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,generateFileName("WD"));
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,generateFileName("WK"));
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor,generateFileName("QM"));
    }

    private String generateFileName(String prefix){
        return dir+prefix+FileContentUtils.autoFileName(dateString.replaceAll("-",""),"",4)+getFileNamePostfix()+".jpg";
    }
}
