/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.core;

import  loadPrediction.domain.WeatherCoes4Workday;
import  loadPrediction.domain.WeatherCoesPackage;
import  loadPrediction.domain.WeatherData;
import  loadPrediction.resouce.WeatherDataMappingKeys;

import java.util.Map;

/**
 * 李倍存 创建于 2015/2/12 21:58。电邮 1174751315@qq.com。
 */
public abstract class AbstractEnhancedSimilarCoeCalculator {

    private static String[] keys = WeatherDataMappingKeys.keys;

    public Double getSimilarCoe(WeatherData weatherData1, WeatherData weatherData2) {
        WeatherCoesPackage weatherCoesPackage = this.doGetCoes();
        weatherCoesPackage.print(System.err);

        Double coe = 0.0;
        WeatherData weatherDataFactor1 = calcWeatherFactor(weatherData1, weatherCoesPackage);
        WeatherData weatherDataFactor2 = calcWeatherFactor(weatherData2, weatherCoesPackage);
        weatherDataFactor1.print(System.out);
        weatherDataFactor2.print(System.err);

        Map<String, Double> map1 = weatherDataFactor1.toMap(), map2 = weatherDataFactor2.toMap();

        Double weight, d1, d2;
        for (int i = 0; i < keys.length; i++) {
            String s = keys[i];
            weight = weatherCoesPackage.toMap().get(s).getWeight();
            d1 = map1.get(s);
            d2 = map2.get(s);
            coe += weight * (d1 - d2) * (d1 - d2);
        }
        return coe;
    }

    protected abstract WeatherCoesPackage doGetCoes();


    private WeatherData calcWeatherFactor(WeatherData weatherData, WeatherCoesPackage weatherCoesPackage) {
        WeatherData weatherDataFactor = new WeatherData();

        Map<String, Double> mapWeatherData = weatherData.toMap();
        Map<String, WeatherCoes4Workday> mapCoes = weatherCoesPackage.toMap();

        weatherDataFactor.setDateString(weatherData.getDateString());
        for (int j = 0; j < keys.length; j++) {
            String s = keys[j];
            Double minBase = mapCoes.get(s).getMinBase(), maxBase = mapCoes.get(s).getMaxBase();
            weatherDataFactor.putMap(s, (mapWeatherData.get(s) - minBase) / (maxBase - minBase));

        }
        weatherDataFactor.unMap();
        return weatherDataFactor;
    }
}
