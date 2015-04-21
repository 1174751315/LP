/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.utils.weather;

import common.ElementPrintableLinkedList;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.domain.CityPackage;
import loadPrediction.domain.RawWeatherData;
import loadPrediction.domain.WeatherData;
import loadPrediction.resouce.IOPaths;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/11 20:48。电邮 1174751315@qq.com。
 */
public class WeatherCalcAndSyncUtil {
    public WeatherCalcAndSyncUtil() {
    }

    public static void syncWeatherData(String pythonScriptFilePath) {
        try {
            Process pr = Runtime.getRuntime().exec("python " + IOPaths.PYTHON_SCRIPT_SYNC_WEATHER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static WeatherData calcWeatherData(RawWeatherData raw) {
        WeatherData weatherData = new WeatherData();

        Double
                maxTemp = raw.getMaxTemperature(),
                aveTemp = raw.getAveTemperature(),
                minTemp = raw.getMinTemperature(),
                maxHumid = raw.getMaxHumid(),
                aveHumid = raw.getAveHumid(),
                minHumid = raw.getMinHumid(),
                wind = raw.getAveWindSpeed(),
                rain = raw.getRainFall();


        weatherData.setMaxTemperature(maxTemp);
        weatherData.setAveTemperature(aveTemp);
        weatherData.setMinTemperature(minTemp);
        weatherData.setMaxHumid(maxHumid);
        weatherData.setAveHumid(aveHumid);
        weatherData.setMinHumid(minHumid);
        weatherData.setWindSpeed(wind);
        weatherData.setRainFall(rain);

        weatherData.setMaxTHI(1.8 * maxTemp + 32. - 0.55 * (1 - maxHumid / 100.) * (1.8 * maxTemp - 26.));
        weatherData.setAveTHI(1.8 * aveTemp + 32. - 0.55 * (1 - aveHumid / 100.) * (1.8 * aveTemp - 26.));
        weatherData.setMinTHI(1.8 * minTemp + 32. - 0.55 * (1 - minHumid / 100.) * (1.8 * minTemp - 26.));

        weatherData.setMaxRTT(37 - (37 - maxTemp) / (0.68 - 0.14 * maxHumid / 100 + 1 / (1.76 + 1.4 * Math.pow(wind, 0.75))) - 0.29 * maxTemp * (1 - maxHumid / 100));
        weatherData.setMinRTT(37 - (37 - minTemp) / (0.68 - 0.14 * minHumid / 100 + 1 / (1.76 + 1.4 * Math.pow(wind, 0.75))) - 0.29 * minTemp * (1 - minHumid / 100));
        weatherData.setAveRTT(37 - (37 - aveTemp) / (0.68 - 0.14 * aveHumid / 100 + 1 / (1.76 + 1.4 * Math.pow(wind, 0.75))) - 0.29 * aveTemp * (1 - aveHumid / 100));


        weatherData.setMaxCI(1.8 * maxTemp - 0.55 * (1.8 * maxTemp - 26) * (1 - maxHumid / 100) - 3.2 * Math.pow(wind, 0.5) + 3.2);
        weatherData.setMinCI(1.8 * minTemp - 0.55 * (1.8 * minTemp - 26) * (1 - minHumid / 100) - 3.2 * Math.pow(wind, 0.5) + 3.2);
        weatherData.setAveCI(1.8 * aveTemp - 0.55 * (1.8 * aveTemp - 26) * (1 - aveHumid / 100) - 3.2 * Math.pow(wind, 0.5) + 3.2);

        weatherData.setMaxCHI((33 - maxTemp) * (3.3 * Math.pow(wind, 0.5) - wind / 3 + 20) * Math.exp(0.005 * Math.abs(maxHumid / 100. - 40. / 100)));
        weatherData.setAveCHI((33 - aveTemp) * (3.3 * Math.pow(wind, 0.5) - wind / 3 + 20) * Math.exp(0.005 * Math.abs(aveHumid / 100. - 40. / 100)));
        weatherData.setMinCHI((33 - minTemp) * (3.3 * Math.pow(wind, 0.5) - wind / 3 + 20) * Math.exp(0.005 * Math.abs(minHumid / 100. - 40. / 100)));

        weatherData.setDateString(raw.getDateString());


        return weatherData;
    }


    /**
     * 从数据库获取所有可用的原始气象数据，并对每一个日期（包含14市的数据）分别计算综合气象数据；随后删除所有原始气象数据，保存所有综合气象数据。
     *
     * @return 所有的综合气象数据
     * @throws Exception 当某日原始气象数据不完整或数据访问异常
     */
    public static ElementPrintableLinkedList<WeatherData> calcWeatherDataFromRawWeatherDataInDbThenWriteDb() throws Exception {
        ElementPrintableLinkedList<WeatherData> list = new ElementPrintableLinkedList<WeatherData>("");
        List<RawWeatherData> raws = DAOFactory.getDefault().createDaoRawWeatherData().query();

        List<String> contains = new LinkedList<String>();
        for (int i = 0; i < raws.size(); i++) {
            String s = raws.get(i).getDateString();
            if (!contains.contains(s))
                contains.add(s);
        }

        for (int i = 0; i < contains.size(); i++) {
            try {
                list.add(calcWeatherDataFromRawWeatherDataInDbThenWriteDb(contains.get(i)));
            } catch (Exception e) {
                DAOFactory.getDefault().createDaoRawWeatherData().delete(contains.get(i));
                e.printStackTrace();
            }

        }
        return list;

    }

    /**
     * 从原始气象数据库取出dateString所指日期的14市原始气象数据，并计算当天的综合气象数据；计算完成后，从数据库删除当天14市原始气象数据，将综合气象数据存入数据库。
     *
     * @param dateString 日期字符串
     * @return 当天的综合气象数据
     * @throws Exception 当原始数据不完整或数据访问异常
     */
    public static WeatherData calcWeatherDataFromRawWeatherDataInDbThenWriteDb(String dateString) throws Exception {
        ElementPrintableLinkedList<RawWeatherData> raws = new ElementPrintableLinkedList<RawWeatherData>("");
        ElementPrintableLinkedList<WeatherData> weatherDatas = new ElementPrintableLinkedList<WeatherData>("");
        for (int i = 0; i < 14; i++) {
            RawWeatherData raw = DAOFactory.getDefault().createDaoRawWeatherData().query(dateString, i + 1);
            if (raw == null) {
                throw new Exception("预测气象原始数据" + " [ " + dateString + " ] " + "不完整。");
            }

            raw.print(System.out);
            raws.add(raw);
            WeatherData w = calcWeatherData(raw);
            w.print(System.err);
            weatherDatas.add(w);
        }
        CityPackage cityPackage = new CityPackage();

        for (int i = 0; i < 14; i++) {
            weatherDatas.set(i, weatherDatas.get(i).multiple(cityPackage.getCityById(i + 1).getWeight()));
        }

        WeatherData r = new WeatherData();
        for (int i = 0; i < 14; i++) {
            r.add(weatherDatas.get(i));
        }

        r.setDateString(dateString);
        DAOFactory.getDefault().createDaoWeatherData().insertOrUpdate(r);
        DAOFactory.getDefault().createDaoRawWeatherData().delete(dateString);
        return r;
    }
}
