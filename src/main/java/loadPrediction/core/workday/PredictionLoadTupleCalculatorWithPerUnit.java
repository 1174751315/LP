/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.workday;

import common.MaxAveMinTuple;
import loadPrediction.domain.LoadBase;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.WeatherData;

/**
 * 李倍存 创建于 2015-03-08 22:26。电邮 1174751315@qq.com。
 */
public class PredictionLoadTupleCalculatorWithPerUnit implements IPredictionLoadTupleCalculator {
    public MaxAveMinTuple<Double> calc(WeatherData predictionWeatherData, WeatherData historyWeatherData,
                                       LoadData historyLoadData,
                                       LoadBase predictionLoadBase, LoadBase historyLoadBase) {

        MaxAveMinTuple<Double> tempPrediction;
        MaxAveMinTuple<Double> tempHistory;

        tempPrediction = predictionWeatherData.toMaxAveMin();
        tempHistory = historyWeatherData.toMaxAveMin();

        MaxAveMinTuple<Double> loadHistoryPerUnits = historyLoadData.getPerUnits(historyLoadBase);

        MaxAveMinTuple<Double> loadPredictionPerUnits = new MaxAveMinTuple<Double>("loadPredictionPerUnits");

        loadPredictionPerUnits.max = loadHistoryPerUnits.max + (tempPrediction.max - tempHistory.max) * (0.0016 * ((tempPrediction.max - tempHistory.max) / 2 + tempHistory.max) - 0.0355);
        loadPredictionPerUnits.ave = loadHistoryPerUnits.ave + (tempPrediction.ave - tempHistory.ave) * (0.003 * ((tempPrediction.ave - tempHistory.ave) / 2 + tempHistory.ave) - 0.0601);
        loadPredictionPerUnits.min = loadHistoryPerUnits.min + (tempPrediction.min - tempHistory.min) * (0.0052 * ((tempPrediction.min - tempHistory.min) / 2 + tempHistory.min) - 0.1009);

        MaxAveMinTuple<Double> predictionLoadData = new MaxAveMinTuple<Double>("predictionLoadData");
        predictionLoadData.max = loadPredictionPerUnits.max * predictionLoadBase.toMaxAveMin().max;
        predictionLoadData.ave = loadPredictionPerUnits.ave * predictionLoadBase.toMaxAveMin().ave;
        predictionLoadData.min = loadPredictionPerUnits.min * predictionLoadBase.toMaxAveMin().min;

        return predictionLoadData;
    }
}
