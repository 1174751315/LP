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
    private LoadsObtainer loadsObtainer;
    private WeatherObtainer weatherObtainer;

    public void setLoadsObtainer(LoadsObtainer loadsObtainer) {
        this.loadsObtainer = loadsObtainer;
    }

    public void setWeatherObtainer(WeatherObtainer weatherObtainer) {
        this.weatherObtainer = weatherObtainer;
    }

    public CommonUtils() {
    }

    public CommonUtils(LoadsObtainer loadsObtainer, WeatherObtainer weatherObtainer) {

        this.loadsObtainer = loadsObtainer;
        this.weatherObtainer = weatherObtainer;
    }

    /*辅助计算函数.*/
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>
    getHistoryWeather(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>> weather = new ElementPrintableLinkedList<ElementPrintableLinkedList<WeatherData>>("");
        Integer iSize = historyDays.size();
            for (int i = 0; i < iSize; i++) {
                List<SimpleDate> subdates = historyDays.get(i);
                weather.add(weatherObtainer.tryGetSomeWeathers(simpleDate2DateString(subdates)));
            }
            return weather;
       }
    public  ElementPrintableLinkedList<WeatherData>
    getPredictionWeather(ElementPrintableLinkedList<SimpleDate> predictionDays) throws LPE {
        return weatherObtainer.tryGetSomeWeathers(simpleDate2DateString(predictionDays));
    }
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>
    getSimilarDaysLoad(ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> similarDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("loads");
        for (int i = 0; i < similarDays.size(); i++) {
            List<String> date = simpleDate2DateString(similarDays.get(i));
            ElementPrintableLinkedList<LoadData> load =loadsObtainer.tryGetSomeLoads(date);
            loads.add(load);
        }
        return loads;

    }
    public  ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>
    getSimilarDaysLoad_1(ElementPrintableLinkedList<PrintableLinkedList<String>> similarDays) throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> loads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("loads");
        for (int i = 0; i < similarDays.size(); i++) {
            PrintableLinkedList<String> date = similarDays.get(i);
            ElementPrintableLinkedList<LoadData> load = loadsObtainer.tryGetSomeLoads(date);
            loads.add(load);
        }
        return loads;
    }
    public  ElementPrintableLinkedList<LoadData>
    getActualLoad(ElementPrintableLinkedList<SimpleDate> predictionDays) throws LPE {
        return loadsObtainer.tryGetSomeLoads(simpleDate2DateString(predictionDays));
    }


    public  List<String> simpleDate2DateString(List<SimpleDate> simpleDates){
        List<String> dates=new LinkedList<String>();
        for (int i = 0; i <simpleDates.size() ; i++) {
            dates.add(simpleDates.get(i).getDateString());
        }
        return dates;
    }




}
