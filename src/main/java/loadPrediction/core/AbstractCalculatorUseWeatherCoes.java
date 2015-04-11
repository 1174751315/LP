package loadPrediction.core;

import loadPrediction.domain.WeatherCoesPackage;
import loadPrediction.resouce.WeatherDataMappingKeys;

/**
 * 李倍存 创建于 2015-04-11 10:32。电邮 1174751315@qq.com。
 */
public abstract class AbstractCalculatorUseWeatherCoes {
    public AbstractCalculatorUseWeatherCoes(WeatherCoesPackage weatherCoesPackage) {

        this.weatherCoesPackage = weatherCoesPackage;
    }

    public    void  setWeatherCoes(WeatherCoesPackage coes){
       this.weatherCoesPackage=coes;
   }
   public  WeatherCoesPackage getWeatherCoes(){
       return weatherCoesPackage;
   }
    public static String[] MAPPING_KEYS = WeatherDataMappingKeys.keys;
    private WeatherCoesPackage weatherCoesPackage;
}
