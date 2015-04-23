/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.core;

import prediction.domain.WeatherCoesPackage;
import prediction.domain.WeatherData;

import java.util.Map;

/**
 * Created by LBC on 2015/2/12.
 */
public class SimilarCoeCalculatorImpl1 extends AbstractCalculatorUseWeatherCoes implements ISimilarCoeCalculator {

    public SimilarCoeCalculatorImpl1(WeatherCoesPackage weatherCoesPackage) {
        super(weatherCoesPackage);
    }

    @Override
    public Double calcSimilarCoe(WeatherData weatherDataFactor1, WeatherData weatherDataFactor2) {
        Double coe = 0.;
        Map<String, Double> map1 = weatherDataFactor1.toMap(), map2 = weatherDataFactor2.toMap();
        Double weight, d1, d2;
        String[] keys = AbstractCalculatorUseWeatherCoes.MAPPING_KEYS;
        WeatherCoesPackage weatherCoesPackage = super.getWeatherCoes();
        for (int i = 0; i < keys.length; i++) {
            String s = keys[i];
            weight = weatherCoesPackage.toMap().get(s).getWeight();
            d1 = map1.get(s);
            d2 = map2.get(s);
            coe += weight * (d1 - d2) * (d1 - d2);
        }
        return coe;
    }
}
