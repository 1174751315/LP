/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.core;

import prediction.domain.WeatherCoes;
import prediction.domain.WeatherCoesPackage;
import prediction.domain.WeatherData;

import java.util.Map;

/**
 * Created by LBC on 2015/2/12.
 */
public class WeatherFactorCalculatorImpl1 extends AbstractCalculatorUseWeatherCoes implements IWeatherFactorCalculator {
    public WeatherFactorCalculatorImpl1(WeatherCoesPackage weatherCoesPackage) {
        super(weatherCoesPackage);
    }

    /**
     * 计算中间值（【相似日查找-工作日】右部）。
     */
    public WeatherData calcWeatherFactor(WeatherData weatherData) {
        WeatherData weatherDataFactor = new WeatherData();
        WeatherCoesPackage weatherCoesPackage = super.getWeatherCoes();
        Map<String, Double> mapWeatherData = weatherData.toMap();
        Map<String, WeatherCoes> mapCoes = weatherCoesPackage.toMap();
        String[] keys = AbstractCalculatorUseWeatherCoes.MAPPING_KEYS;
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
