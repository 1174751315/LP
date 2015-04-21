/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.dataAccess;

import prediction.domain.WeatherData;
import prediction.exception.DAE;

/**
 * Created by LBC on 2015/2/4.
 */
public class DAOWeatherData extends AbstractDAO {
    public DAOWeatherData(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public WeatherData query(String key) throws DAE {
        WeatherData o = (WeatherData) superDAO.query(WeatherData.class, (key));
        if (o == null) {
            throw new DAE(WeatherData.class.getSimpleName());
        }
        return o;
    }

    public void insert(WeatherData o) {
        superDAO.insert(o);
    }

    public void insertOrUpdate(WeatherData o) {
        superDAO.insertOrUpdate(o);
    }

}
