package loadPrediction.core;

import loadPrediction.domain.WeatherData;

/**
 * 李倍存 创建于 2015-04-11 10:31。电邮 1174751315@qq.com。
 */
public interface IWeatherFactorCalculator{
     WeatherData calcWeatherFactor(WeatherData weatherData);
}
