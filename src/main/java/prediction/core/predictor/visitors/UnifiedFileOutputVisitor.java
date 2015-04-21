package prediction.core.predictor.visitors;

import prediction.core.predictor.IPredictor;
import prediction.core.predictor.IQingmingPredictor;
import prediction.core.predictor.IWeekendPredictor;
import prediction.core.predictor.IWorkdayPredictor;
import prediction.exception.LPE;
import prediction.utils.FileContentUtils;

/**
 * 李倍存 创建于 2015-04-10 15:41。电邮 1174751315@qq.com。
 */
public abstract class UnifiedFileOutputVisitor implements IPredictorVisitor {
    abstract protected Object doVisitAndOutput(IPredictor predictor, String fileAbsPath) throws LPE;

    abstract protected String getFileNamePostfix();

    abstract protected String getFileExtend();

    public UnifiedFileOutputVisitor(String dir, String dateString) {
        this.dir = dir;
        this.dateString = dateString;
    }

    private String dir;
    private String dateString;

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDir() {
        return dir;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor, generateFileAbsPath("WD"));
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor, generateFileAbsPath("WK"));
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        return doVisitAndOutput(predictor, generateFileAbsPath("QM"));
    }

    private String generateFileAbsPath(String prefix) {
        return dir + prefix + FileContentUtils.autoFileName(dateString.replaceAll("-", ""), "", 4) + getFileNamePostfix() + getFileExtend();
    }
}
