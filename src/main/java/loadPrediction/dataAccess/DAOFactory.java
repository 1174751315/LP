/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import  loadPrediction.dataAccess.access.AccessDAOFactory;
import  loadPrediction.dataAccess.access.AccessDAOFactory_Backup;
import  loadPrediction.resouce.DomainNames;

/**
 * 李倍存 创建于 2015-02-25 17:05。电邮 1174751315@qq.com。
 */
public abstract class DAOFactory {
    public DAOSimpleDate createDaoSimpleDate() {
        return (DAOSimpleDate) doGetDAO(DomainNames.ofSimpleDate);
    }

    public DAOLoadData createDaoLoadData() {
        return (DAOLoadData) doGetDAO(DomainNames.ofLoadData);
    }

    public DAOLoadBase createDaoLoadBase() {
        return (DAOLoadBase) doGetDAO(DomainNames.ofLoadBase);
    }

    public DAOWeatherData createDaoWeatherData() {
        return (DAOWeatherData) doGetDAO(DomainNames.ofWeatherData);
    }

    public DAOWeatherCoes4Workday createDaoWeatherCoes4Workday() {
        return (DAOWeatherCoes4Workday) doGetDAO(DomainNames.ofWeatherCoes4Workday);
    }

    public DAOWeatherCoes4Weekend createDaoWeatherCoes4Weekend() {
        return (DAOWeatherCoes4Weekend) doGetDAO(DomainNames.ofWeatherCoes4Weekend);
    }

    public DAOWorkdayTestRecord createDaoWorkdayTestRecord() {
        return (DAOWorkdayTestRecord) doGetDAO(DomainNames.ofWorkdayTestRecord);
    }

    public DAOUser createDaoUser() {
        return (DAOUser) doGetDAO(DomainNames.ofUser);
    }

    public DAOWeekendTestRecord createDaoWeekendTestRecord() {
        return (DAOWeekendTestRecord) doGetDAO(DomainNames.ofWeekendTestRecord);
    }

    public DAOPredictionLoadData createDaoPredictionLoadData() {
        return (DAOPredictionLoadData) doGetDAO(DomainNames.ofPredictionLoadData);
    }

    public DAORawWeatherData createDaoRawWeatherData() {
        return (DAORawWeatherData) doGetDAO(DomainNames.ofRawWeatherData);
    }

    public DAOCity createDaoCity() {
        return (DAOCity) doGetDAO(DomainNames.ofCity);
    }

    public DAOAccuracy createDAOAccuracy() {
        return (DAOAccuracy) doGetDAO(DomainNames.ofAccuracy);
    }

    public DAOOnedayAccuracyCheckingCacheEntity createDAOOnedayAccuracyCheckingCacheEntity() {
        return (DAOOnedayAccuracyCheckingCacheEntity) doGetDAO("oneday_accuracy_cache");
    }

    public DAOPredictionCacheEntity createDAOPredictionCacheEntity() {
        return (DAOPredictionCacheEntity) doGetDAO("prediction_cache");
    }
    public static DAOFactory getDefault() {
        return new AccessDAOFactory();
    }
    public static DAOFactory getAlter(){return new AccessDAOFactory_Backup();}

    protected abstract Object doGetDAO(String name);


}
