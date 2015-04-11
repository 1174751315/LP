/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.util;

import  common.ElementPrintableLinkedList;
import  common.PrintableLinkedList;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.domain.WeatherData;
import  loadPrediction.exception.DAE;
import  loadPrediction.exception.LPE;
import  loadPrediction.log.Logging;
import loadPrediction.timerTask.LoadDataCopy;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-22 10:57。电邮 1174751315@qq.com。
 */
public class CommonUtils {
    private DAOFactory generalDaoFactory;

    public CommonUtils() {
    }

    public CommonUtils(DAOFactory generalDaoFactory, DAOFactory backupDaoFactory) {

        this.generalDaoFactory = generalDaoFactory;
        this.backupDaoFactory = backupDaoFactory;
    }

    public DAOFactory getBackupDaoFactory() {

        return backupDaoFactory;
    }

    public void setBackupDaoFactory(DAOFactory backupDaoFactory) {
        this.backupDaoFactory = backupDaoFactory;
    }

    public DAOFactory getGeneralDaoFactory() {
        return generalDaoFactory;
    }

    public void setGeneralDaoFactory(DAOFactory generalDaoFactory) {
        this.generalDaoFactory = generalDaoFactory;
    }

    private DAOFactory backupDaoFactory;


    /*辅助计算函数.*/
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>
    getHistoryWeather(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> weather = new ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>("");
        Integer iSize = historyDays.size();
            for (int i = 0; i < iSize; i++) {
                List<SimpleDate> subdates = historyDays.get(i);
                weather.add(tryGetSomeWeathers(simpleDate2DateString(subdates)));
            }
            return weather;
       }
    public  ElementPrintableLinkedList<WeatherData>
    getPredictionWeather(ElementPrintableLinkedList<SimpleDate> predictionDays) throws LPE {
        return tryGetSomeWeathers(simpleDate2DateString(predictionDays));
    }
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>
    getSimilarDaysLoad(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("loads");
        for (int i = 0; i < similarDays.size(); i++) {
            List<String> date = simpleDate2DateString(similarDays.get(i));
            ElementPrintableLinkedList<LoadData> load =tryGetSomeLoads(date);
            loads.add(load);
        }
        return loads;

    }
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>
    getSimilarDaysLoad_1(ElementPrintableLinkedList<PrintableLinkedList<String>> similarDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("loads");
        for (int i = 0; i < similarDays.size(); i++) {
            PrintableLinkedList<String> date = similarDays.get(i);
            ElementPrintableLinkedList<LoadData> load = tryGetSomeLoads(date);
            loads.add(load);
        }
        return loads;
    }
    public  ElementPrintableLinkedList<LoadData>
    getActualLoad(ElementPrintableLinkedList<SimpleDate> predictionDays) throws LPE {
        return tryGetSomeLoads(simpleDate2DateString(predictionDays));
    }
    private  ElementPrintableLinkedList<LoadData> tryGetSomeLoads(List<String> dateStrings) throws LPE{
        Integer size=dateStrings.size();
        ElementPrintableLinkedList<LoadData> loadDatas=new ElementPrintableLinkedList<LoadData>("");
        for (int i = 0; i <size ; i++) {
            String dateString=dateStrings.get(i);
            try {
                loadDatas.add(generalDaoFactory.createDaoLoadData().query(dateString));
            }catch (DAE e){
                Logging.instance().createLogger().info("负荷数据缺失  "+dateStrings.get(i)+"。尝试重新同步负荷");
                /*处理数据缺失异常：同步负荷*/
                try {
                    loadDatas.add((LoadData) new LoadDataCopy(backupDaoFactory, generalDaoFactory).copy(dateString));
                    /*若在同步负荷的过程中又出现异常，则处理失败，抛出业务异常。*/
                } catch (DAE dae) {
                    Logging.instance().createLogger().info("同步失败");
                    throw new LPE(Date.valueOf(dateString));
                }catch (Exception e1){
                    Logging.instance().createLogger().info("同步失败");
                    throw new LPE(Date.valueOf(dateString));
                }
                Logging.instance().createLogger().info("同步成功");
            }
        }
        return loadDatas;
    }
    private  ElementPrintableLinkedList<WeatherData> tryGetSomeWeathers(List<String> dateStrings)throws LPE{
        Integer size=dateStrings.size();
        ElementPrintableLinkedList<WeatherData> weathers=new ElementPrintableLinkedList<WeatherData>("");
        for (int i = 0; i < size; i++) {
            String dateString=dateStrings.get(i);
            try{
                weathers.add(generalDaoFactory.createDaoWeatherData().query(dateString));
            }catch (DAE e){
                Logging.instance().createLogger().debug("气象数据缺失");
                Logging.instance().createLogger().error("气象数据缺失，算法异常终止");
                throw new LPE("获取气象数据时发生数据访问异常");
            }catch (Exception e){
                Logging.instance().createLogger().error("算法异常终止");
                throw new LPE("获取气象数据时发生未知异常");
            }
        }
        return weathers;
    }
    public  List<String> simpleDate2DateString(List<SimpleDate> simpleDates){
        List<String> dates=new LinkedList<String>();
        for (int i = 0; i <simpleDates.size() ; i++) {
            dates.add(simpleDates.get(i).getDateString());
        }
        return dates;
    }




}
