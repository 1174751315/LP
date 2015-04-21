/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.hardCoding;

import common.ElementPrintableLinkedList;
import common.EnhancedLinkedList;
import common.MaxAveMinTuple;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherData;
import loadPrediction.exception.LPE;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/10 20:13。电邮 1174751315@qq.com。
 */
public class HardCodingSpringFestivalPredictor extends AbstractTemplateMethodForHardCodingPredictor {
    public HardCodingSpringFestivalPredictor(Date date) {
        super(date, true);
    }

    @Override
    protected ElementPrintableLinkedList<MaxAveMinTuple<Double>> doCalcCorrectCoes(ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadTuples, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoad) {
        return null;
    }

    @Override
    protected List<Integer> doGetHistoryDaysNumbers() {
        return null;
    }

    @Override
    protected Integer doGetPredictionDaysNumber() {
        return null;
    }

    @Override
    protected Boolean doValidate(Date date) {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays(Date date, List<Integer> numbers) throws LPE {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<SimpleDate> doGetPredictionDays(Date date, Integer number) throws LPE {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> doCalcSimilarCoes(ElementPrintableLinkedList<WeatherData> predictionWeather, ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeather) throws LPE {
        return null;
    }

    @Override
    protected String doCheckAccuracy(Double threshold) {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<LoadData> doCalcPredictionLoad(ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarLoads, ElementPrintableLinkedList<MaxAveMinTuple<Double>> correctCoes) {
        return null;
    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doCalcSimilarDays(Integer predictionDaysNumber, ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes) {
        return null;
    }

    @Override
    protected void doAfterBasicPrediction(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<SimpleDate> predictionDays, ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> historyWeathers, ElementPrintableLinkedList<WeatherData> predictionWeathers, ElementPrintableLinkedList<ElementPrintableLinkedList<EnhancedLinkedList<Double>>> similarCoes, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays, ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> similarDaysLoads, ElementPrintableLinkedList<MaxAveMinTuple<Double>> predictionLoadsTuples, ElementPrintableLinkedList<LoadData> prediction96PointLoads) throws LPE {

    }
}
