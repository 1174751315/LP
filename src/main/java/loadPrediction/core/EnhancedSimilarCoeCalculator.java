/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.core;

import  loadPrediction.domain.WeatherCoesPackage;
import  loadPrediction.domain.WeatherData;

/**
 * 李倍存 创建于 2015/2/12 21:58。电邮 1174751315@qq.com。
 */
public class EnhancedSimilarCoeCalculator extends AbstractCalculatorUseWeatherCoes {
    public EnhancedSimilarCoeCalculator(WeatherCoesPackage weatherCoesPackage) {
        super(weatherCoesPackage);
        similarCoeCalculator=new SimilarCoeCalculatorImpl1(weatherCoesPackage);
        weatherFactorCalculator=new WeatherFactorCalculatorImpl1(weatherCoesPackage);
    }
    private ISimilarCoeCalculator similarCoeCalculator;
    private IWeatherFactorCalculator weatherFactorCalculator ;

    public ISimilarCoeCalculator getSimilarCoeCalculator() {
        return similarCoeCalculator;
    }

    public void setSimilarCoeCalculator(ISimilarCoeCalculator similarCoeCalculator) {
        this.similarCoeCalculator = similarCoeCalculator;
    }

    public IWeatherFactorCalculator getWeatherFactorCalculator() {
        return weatherFactorCalculator;
    }

    public void setWeatherFactorCalculator(IWeatherFactorCalculator weatherFactorCalculator) {
        this.weatherFactorCalculator = weatherFactorCalculator;
    }

    public Double calcSimilarCoe(WeatherData weatherData1, WeatherData weatherData2) {
        WeatherCoesPackage weatherCoesPackage =super.getWeatherCoes();
         WeatherData weatherDataFactor1 = weatherFactorCalculator.calcWeatherFactor(weatherData1);
        WeatherData weatherDataFactor2 = weatherFactorCalculator.calcWeatherFactor(weatherData2);
        return similarCoeCalculator.calcSimilarCoe(weatherDataFactor1,weatherDataFactor2);

    }

}
