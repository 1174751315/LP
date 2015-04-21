/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.utils;

import loadPrediction.domain.WeatherData;

import java.sql.Date;

/**
 * Created by LBC on 2015/2/15.
 */
public class SeasonIdentifier {

    /**
     * 获取给定日期所属的电力系统季节。
     *
     * @param date 给定的日期。
     * @return 给定日期所属的电力系统季节。
     */
    public static Season getSeasonByDate(Date date) {
        Integer month = date.toLocalDate().getMonthValue();
        Integer[] summer = {6, 7, 8, 9, 10};
        for (int i = 0; i < summer.length; i++) {
            if (month.equals(summer[i]))
                return Season.SUMMER;
        }
        return Season.WINTER;
    }

    public static Season getSeasonByWeather(WeatherData weatherData) {
        if (weatherData.getMaxTemperature() > 25.)
            return Season.SUMMER;
        else
            return Season.WINTER;
    }
}
