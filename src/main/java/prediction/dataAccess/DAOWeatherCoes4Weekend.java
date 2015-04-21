/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.dataAccess;

import prediction.domain.WeatherCoes;
import prediction.domain.WeatherCoes4Weekend;

/**
 * 李倍存 创建于 2015-03-02 19:12。电邮 1174751315@qq.com。
 */
public class DAOWeatherCoes4Weekend extends AbstractDAO implements IDAOWeatherCoes {
    public DAOWeatherCoes4Weekend(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public WeatherCoes query(String key) throws Exception {
        return (WeatherCoes4Weekend) superDAO.query(WeatherCoes4Weekend.class, key);
    }
}
