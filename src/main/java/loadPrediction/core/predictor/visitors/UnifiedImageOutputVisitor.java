package loadPrediction.core.predictor.visitors;

import loadPrediction.config.ui.ChartColorCfg;
import loadPrediction.config.ui.UiCfg;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.exception.LPE;
import loadPrediction.utils.FileContentUtils;

/**
 * Created by LBC on 2015-04-08.
 */
public abstract class UnifiedImageOutputVisitor extends UnifiedFileOutputVisitor {
    public UnifiedImageOutputVisitor(String dir, String dateString) {
        super(dir,dateString);
        UiCfg uiCfg=UiCfg.INSTANCE;
        uiCfg.update();
        imageColorCfg=uiCfg.getOutputChartImageCfg();
    }

    protected ChartColorCfg imageColorCfg;
    @Override
    protected String getFileExtend() {
        return ".jpg";
    }
}
