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
 * Created by LBC on 2015/2/12.
 */
public class WeatherFactorCalculator {
    private static WeatherFactorCalculator instance;
    private String[] keys = WeatherDataMappingKeys.keys;

    private WeatherFactorCalculator() {

    }

    public static WeatherFactorCalculator getInstance() {
        if (instance == null) {
            instance = new WeatherFactorCalculator();
        }
        return instance;
    }

    /**
     * 计算中间值（【相似日查找-工作日】右部）。
     */
    public WeatherData getWeatherFactor(WeatherData weatherData, WeatherCoesPackage weatherCoesPackage) {
        WeatherData weatherDataFactor = new WeatherData();

        Map<String, Double> mapWeatherData = weatherData.toMap();
        Map<String, WeatherCoes4Workday> mapCoes = weatherCoesPackage.toMap();

        weatherDataFactor.setDateString(weatherData.getDateString());
        for (int j = 0; j < keys.length; j++) {
            String s = keys[j];
            Double minBase = mapCoes.get(s).getMinBase(), maxBase = mapCoes.get(s).getMaxBase();
            weatherDataFactor.putMap(s, (mapWeatherData.get(s) - minBase) / (maxBase - minBase));

        }
//            weatherData.print(System.out);
        weatherDataFactor.unMap();
        return weatherDataFactor;
    }
}
