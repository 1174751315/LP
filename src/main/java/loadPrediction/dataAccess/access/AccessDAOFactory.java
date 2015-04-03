/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess.access;

import  db.eDbType;
import  loadPrediction.dataAccess.*;
import  loadPrediction.resouce.DomainNames;

import java.util.HashMap;
import java.util.Map;

/**
 * 李倍存 创建于 2015-02-25 17:58。电邮 1174751315@qq.com。
 */
public class AccessDAOFactory extends DAOFactory {
    private static Map<String, Object> pool;
    public AccessDAOFactory() {
        if (pool == null) {
            pool = new HashMap<String, Object>();
            pool.put(DomainNames.ofSimpleDate, new DAOSimpleDate(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("loaddata", new DAOLoadData(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("loadbase", new DAOLoadBase(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("weatherdata", new DAOWeatherData(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofWeatherCoes4Workday, new DAOWeatherCoes4Workday(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofWeatherCoes4Weekend, new DAOWeatherCoes4Weekend(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("workdaytestrecord", new DAOWorkdayTestRecord(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("user", new DAOUser(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("weekendtestrecord", new DAOWeekendTestRecord(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofPredictionLoadData, new DAOPredictionLoadData(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofAccuracy, new DAOAccuracy(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofRawWeatherData, new DAORawWeatherData(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put(DomainNames.ofCity, new DAOCity(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("oneday_accuracy_cache", new DAOOnedayAccuracyCheckingCacheEntity(SuperDAO.getInstanceOf(eDbType.ACCESS)));
            pool.put("prediction_cache", new DAOPredictionCacheEntity(SuperDAO.getInstanceOf(eDbType.ACCESS)));
        }

    }

    @Override
    protected Object doGetDAO(String name) {
        return pool.get(name);
    }
}
