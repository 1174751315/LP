/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.dataAccess;

import  loadPrediction.domain.WeatherCoes4Workday;

import java.util.List;

/**
 * Created by LBC on 2015/2/9.
 */
public class DAOWeatherCoes4Workday extends AbstractDAO {

    public DAOWeatherCoes4Workday(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public WeatherCoes4Workday query(String key) throws Exception {
        return (WeatherCoes4Workday) superDAO.query(WeatherCoes4Workday.class, key);
    }

    public List query() throws Exception {
        return superDAO.query(WeatherCoes4Workday.class);
    }

    public void update(WeatherCoes4Workday o) throws Exception {
        superDAO.update(o);
    }

    public void delete(String key) throws Exception {
        superDAO.delete(WeatherCoes4Workday.class, key);
    }
}
