package prediction.dataAccess;

import prediction.domain.WeatherCoes;

/**
 * 李倍存 创建于 2015-04-13 21:17。电邮 1174751315@qq.com。
 */
public interface IDAOWeatherCoes {
    WeatherCoes query(String key) throws Exception;
}
