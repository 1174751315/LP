package loadPrediction.core.predictor.util;

import common.ElementPrintableLinkedList;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOWeatherData;
import loadPrediction.domain.WeatherData;
import loadPrediction.exception.DAE;
import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;

import java.util.List;

/**
 * 李倍存 创建于 2015-04-12 9:55。电邮 1174751315@qq.com。
 */
public class WeatherObtainer{
    private DAOWeatherData general,backup;
    public WeatherObtainer(DAOWeatherData general, DAOWeatherData backup) {
        this.general = general;
        this.backup = backup;
    }

    public void setGeneralDao(DAOWeatherData general) {
        this.general = general;
    }

    public void setBackupDao(DAOWeatherData backup) {
        this.backup = backup;
    }

    public WeatherObtainer() {

    }
    public ElementPrintableLinkedList<WeatherData> tryGetSomeWeathers(List<String> dateStrings)throws LPE {
        Integer size=dateStrings.size();
        ElementPrintableLinkedList<WeatherData> weathers=new ElementPrintableLinkedList<WeatherData>("");
        for (int i = 0; i < size; i++) {
            String dateString=dateStrings.get(i);
            try{
                weathers.add(general.query(dateString));
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
}
