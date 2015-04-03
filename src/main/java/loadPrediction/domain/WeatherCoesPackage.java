/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;


import  common.IMapable;
import  common.IPrintable;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.dataAccess.DAOWeatherCoes4Weekend;
import  loadPrediction.dataAccess.DAOWeatherCoes4Workday;
import  loadPrediction.domain.visitors.IDomainVisitor;
import  loadPrediction.resouce.WeatherDataMappingKeys;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/2/9.
 */
public class WeatherCoesPackage implements IDomain, IMapable<String, WeatherCoes4Workday>, IPrintable {
    private WeatherCoes4Workday ofMaxTemp;

    public WeatherCoes4Workday getOfAveCHI() {
        return ofAveCHI;
    }

    public WeatherCoes4Workday getOfAveCI() {
        return ofAveCI;
    }

    public WeatherCoes4Workday getOfAveHum() {
        return ofAveHum;
    }

    public WeatherCoes4Workday getOfAveRTT() {
        return ofAveRTT;
    }

    public WeatherCoes4Workday getOfAveTemp() {
        return ofAveTemp;
    }

    public WeatherCoes4Workday getOfAveTHI() {
        return ofAveTHI;
    }

    public WeatherCoes4Workday getOfMaxCHI() {
        return ofMaxCHI;
    }

    public WeatherCoes4Workday getOfMaxCI() {
        return ofMaxCI;
    }

    public WeatherCoes4Workday getOfMaxHum() {
        return ofMaxHum;
    }

    public WeatherCoes4Workday getOfMaxRTT() {
        return ofMaxRTT;
    }

    public WeatherCoes4Workday getOfMaxTemp() {
        return ofMaxTemp;
    }

    public WeatherCoes4Workday getOfMaxTHI() {
        return ofMaxTHI;
    }

    public WeatherCoes4Workday getOfMinCHI() {
        return ofMinCHI;
    }

    public WeatherCoes4Workday getOfMinCI() {
        return ofMinCI;
    }

    public WeatherCoes4Workday getOfMinHum() {
        return ofMinHum;
    }

    public WeatherCoes4Workday getOfMinRTT() {
        return ofMinRTT;
    }

    public WeatherCoes4Workday getOfMinTemp() {
        return ofMinTemp;
    }

    public WeatherCoes4Workday getOfMinTHI() {
        return ofMinTHI;
    }

    public WeatherCoes4Workday getOfRainFall() {
        return ofRainFall;
    }

    public WeatherCoes4Workday getOfWindSpeed() {
        return ofWindSpeed;
    }

    private WeatherCoes4Workday ofMinTemp;
    private WeatherCoes4Workday ofAveTemp;
    private WeatherCoes4Workday ofMaxHum;
    private WeatherCoes4Workday ofMinHum;
    private WeatherCoes4Workday ofAveHum;
    private WeatherCoes4Workday ofWindSpeed;
    private WeatherCoes4Workday ofRainFall;
    private WeatherCoes4Workday ofMaxTHI;
    private WeatherCoes4Workday ofMinTHI;
    private WeatherCoes4Workday ofAveTHI;
    private WeatherCoes4Workday ofMaxRTT;
    private WeatherCoes4Workday ofMinRTT;
    private WeatherCoes4Workday ofAveRTT;
    private WeatherCoes4Workday ofMaxCI;
    private WeatherCoes4Workday ofMinCI;
    private WeatherCoes4Workday ofAveCI;
    private WeatherCoes4Workday ofMaxCHI;
    private WeatherCoes4Workday ofMinCHI;
    private WeatherCoes4Workday ofAveCHI;


    public WeatherCoesPackage(String type) {

        try {
            if (type.toUpperCase().equals("WORKDAY")) {
                DAOWeatherCoes4Workday daoWeatherCoes4Workday = DAOFactory.getDefault().createDaoWeatherCoes4Workday();

                ofMaxTemp = daoWeatherCoes4Workday.query("最大温度");
                ofAveTemp = daoWeatherCoes4Workday.query("平均温度");
                ofMinTemp = daoWeatherCoes4Workday.query("最低温度");
                ofMaxHum = daoWeatherCoes4Workday.query("最大湿度");
                ofAveHum = daoWeatherCoes4Workday.query("平均湿度");
                ofMinHum = daoWeatherCoes4Workday.query("最低湿度");
                ofWindSpeed = daoWeatherCoes4Workday.query("风速");
                ofRainFall = daoWeatherCoes4Workday.query("降雨量");
                ofMaxTHI = daoWeatherCoes4Workday.query("最大温湿指数");
                ofAveTHI = daoWeatherCoes4Workday.query("平均温湿指数");
                ofMinTHI = daoWeatherCoes4Workday.query("最低温湿指数");
                ofMaxRTT = daoWeatherCoes4Workday.query("最高实感温度");
                ofAveRTT = daoWeatherCoes4Workday.query("平均实感温度");
                ofMinRTT = daoWeatherCoes4Workday.query("最低实感温度");
                ofMaxCI = daoWeatherCoes4Workday.query("最高舒适度");
                ofAveCI = daoWeatherCoes4Workday.query("平均舒适度");
                ofMinCI = daoWeatherCoes4Workday.query("最低舒适度");
                ofMaxCHI = daoWeatherCoes4Workday.query("最高寒湿指数");
                ofAveCHI = daoWeatherCoes4Workday.query("平均寒湿指数");
                ofMinCHI = daoWeatherCoes4Workday.query("最低寒湿指数");
            } else if (type.toUpperCase().equals("WEEKEND")) {
                DAOWeatherCoes4Weekend daoWeatherCoes4Weekend = DAOFactory.getDefault().createDaoWeatherCoes4Weekend();

                ofMaxTemp = daoWeatherCoes4Weekend.query("最大温度");
                ofAveTemp = daoWeatherCoes4Weekend.query("平均温度");
                ofMinTemp = daoWeatherCoes4Weekend.query("最低温度");
                ofMaxHum = daoWeatherCoes4Weekend.query("最大湿度");
                ofAveHum = daoWeatherCoes4Weekend.query("平均湿度");
                ofMinHum = daoWeatherCoes4Weekend.query("最低湿度");
                ofWindSpeed = daoWeatherCoes4Weekend.query("风速");
                ofRainFall = daoWeatherCoes4Weekend.query("降雨量");
                ofMaxTHI = daoWeatherCoes4Weekend.query("最大温湿指数");
                ofAveTHI = daoWeatherCoes4Weekend.query("平均温湿指数");
                ofMinTHI = daoWeatherCoes4Weekend.query("最低温湿指数");
                ofMaxRTT = daoWeatherCoes4Weekend.query("最高实感温度");
                ofAveRTT = daoWeatherCoes4Weekend.query("平均实感温度");
                ofMinRTT = daoWeatherCoes4Weekend.query("最低实感温度");
                ofMaxCI = daoWeatherCoes4Weekend.query("最高舒适度");
                ofAveCI = daoWeatherCoes4Weekend.query("平均舒适度");
                ofMinCI = daoWeatherCoes4Weekend.query("最低舒适度");
                ofMaxCHI = daoWeatherCoes4Weekend.query("最高寒湿指数");
                ofAveCHI = daoWeatherCoes4Weekend.query("平均寒湿指数");
                ofMinCHI = daoWeatherCoes4Weekend.query("最低寒湿指数");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, WeatherCoes4Workday> map;

    @Override
    public Map<String, WeatherCoes4Workday> toMap() {
        if (map != null)
            return map;
        map = new HashMap<String, WeatherCoes4Workday>();
        map.put("max_temp", ofMaxTemp);
        map.put("ave_temp", ofAveTemp);
        map.put("min_temp", ofMinTemp);
        map.put("max_hum", ofMaxHum);
        map.put("ave_hum", ofAveHum);
        map.put("min_hum", ofMinHum);
        map.put("mean_wind", ofWindSpeed);
        map.put("precip", ofRainFall);
        map.put("max_temphum", ofMaxTHI);
        map.put("ave_temphum", ofAveTHI);
        map.put("min_temphum", ofMinTHI);
        map.put("max_efftemp", ofMaxRTT);
        map.put("ave_efftemp", ofAveRTT);
        map.put("min_efftemp", ofMinRTT);
        map.put("max_com", ofMaxCI);
        map.put("ave_com", ofAveCI);
        map.put("min_com", ofMinCI);
        map.put("max_chihum", ofMaxCHI);
        map.put("ave_chihum", ofAveCHI);
        map.put("min_chihum", ofMinCHI);
        return map;
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }

    @Override
    public void print(PrintStream ps) {
        toMap();
        String[] keys = WeatherDataMappingKeys.keys;
        ps.print("气象指标: ");
        for (int i = 0; i < keys.length; i++) {
            ps.printf(map.get(keys[i]).getType());
        }
        ps.print("\n");
        for (int i = 0; i < keys.length; i++) {
            ps.printf("%2.4f, ", map.get(keys[i]).getRelationCoe());
        }
        ps.print("\n");
        for (int i = 0; i < keys.length; i++) {
            ps.printf("%2.4f, ", map.get(keys[i]).getMaxBase());
        }
        ps.print("\n");
        for (int i = 0; i < keys.length; i++) {
            ps.printf("%2.4f, ", map.get(keys[i]).getMinBase());
        }
        ps.print("\n");
        for (int i = 0; i < keys.length; i++) {
            ps.printf("%2.4f, ", map.get(keys[i]).getWeight());
        }
        ps.print("\n");
        ps.print("\n");
    }

//    @Override
//    public void print(DataOutputStream dos) {
//        ofMaxTemp=daoWeatherCoes.query("最大温度","");
//        ofAveTemp=daoWeatherCoes.query("平均温度","");
//        ofMinTemp=daoWeatherCoes.query("最低温度","");
//        ofMaxHum=daoWeatherCoes.query("最大湿度","");
//        ofAveHum=daoWeatherCoes.query("平均湿度","");
//        ofMinHum=daoWeatherCoes.query("最低湿度","");
//        ofWindSpeed=daoWeatherCoes.query("风速","");
//        ofRainFall=daoWeatherCoes.query("降雨量","");
//        ofMaxTHI=daoWeatherCoes.query("最大温湿指数","");
//        ofAveTHI=daoWeatherCoes.query("平均温湿指数","");
//        ofMinTHI=daoWeatherCoes.query("最低温湿指数","");
//        ofMaxRTT=daoWeatherCoes.query("最高实感温度","");
//        ofAveRTT=daoWeatherCoes.query("平均实感温度","");
//        ofMinRTT=daoWeatherCoes.query("最低实感温度","");
//        ofMaxCI=daoWeatherCoes.query("最高舒适度","");
//        ofAveCI=daoWeatherCoes.query("平均舒适度","");
//        ofMinCI=daoWeatherCoes.query("最低舒适度","");
//        ofMaxCHI=daoWeatherCoes.query("最高寒湿指数","");
//        ofAveCHI=daoWeatherCoes.query("平均寒湿指数","");
//        ofMinCHI=daoWeatherCoes.query("最低寒湿指数","");
//    }
}
