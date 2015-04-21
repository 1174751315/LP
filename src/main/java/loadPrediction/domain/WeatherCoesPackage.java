/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;


import common.IMapable;
import common.IPrintable;
import loadPrediction.dataAccess.IDAOWeatherCoes;
import loadPrediction.domain.visitors.IDomainVisitor;
import loadPrediction.exception.LPE;
import loadPrediction.resouce.WeatherDataMappingKeys;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/2/9.
 */
public class WeatherCoesPackage implements IDomain, IMapable<String, WeatherCoes>, IPrintable {
    private IDAOWeatherCoes daoWeatherCoes;

    public IDAOWeatherCoes getDaoWeatherCoes() {
        return daoWeatherCoes;
    }

//    public void setDaoWeatherCoesAndUpdate(IDAOWeatherCoes daoWeatherCoes) {
//        this.daoWeatherCoes = daoWeatherCoes;
//        try {
//            update();
//        }
//        catch (Exception e){
//        }
//    }

    private void update() throws Exception {
        ofMaxTemp = daoWeatherCoes.query("最大温度");
        ofAveTemp = daoWeatherCoes.query("平均温度");
        ofMinTemp = daoWeatherCoes.query("最低温度");
        ofMaxHum = daoWeatherCoes.query("最大湿度");
        ofAveHum = daoWeatherCoes.query("平均湿度");
        ofMinHum = daoWeatherCoes.query("最低湿度");
        ofWindSpeed = daoWeatherCoes.query("风速");
        ofRainFall = daoWeatherCoes.query("降雨量");
        ofMaxTHI = daoWeatherCoes.query("最大温湿指数");
        ofAveTHI = daoWeatherCoes.query("平均温湿指数");
        ofMinTHI = daoWeatherCoes.query("最低温湿指数");
        ofMaxRTT = daoWeatherCoes.query("最高实感温度");
        ofAveRTT = daoWeatherCoes.query("平均实感温度");
        ofMinRTT = daoWeatherCoes.query("最低实感温度");
        ofMaxCI = daoWeatherCoes.query("最高舒适度");
        ofAveCI = daoWeatherCoes.query("平均舒适度");
        ofMinCI = daoWeatherCoes.query("最低舒适度");
        ofMaxCHI = daoWeatherCoes.query("最高寒湿指数");
        ofAveCHI = daoWeatherCoes.query("平均寒湿指数");
        ofMinCHI = daoWeatherCoes.query("最低寒湿指数");
    }

    public WeatherCoesPackage(IDAOWeatherCoes daoWeatherCoes) throws LPE {
        this.daoWeatherCoes = daoWeatherCoes;
        try {
            update();
        } catch (Exception e) {
            throw new LPE("获取气象指标时发生数据访问异常。");
        }
    }

    private WeatherCoes ofMaxTemp;

    public WeatherCoes getOfAveCHI() {
        return ofAveCHI;
    }

    public WeatherCoes getOfAveCI() {
        return ofAveCI;
    }

    public WeatherCoes getOfAveHum() {
        return ofAveHum;
    }

    public WeatherCoes getOfAveRTT() {
        return ofAveRTT;
    }

    public WeatherCoes getOfAveTemp() {
        return ofAveTemp;
    }

    public WeatherCoes getOfAveTHI() {
        return ofAveTHI;
    }

    public WeatherCoes getOfMaxCHI() {
        return ofMaxCHI;
    }

    public WeatherCoes getOfMaxCI() {
        return ofMaxCI;
    }

    public WeatherCoes getOfMaxHum() {
        return ofMaxHum;
    }

    public WeatherCoes getOfMaxRTT() {
        return ofMaxRTT;
    }

    public WeatherCoes getOfMaxTemp() {
        return ofMaxTemp;
    }

    public WeatherCoes getOfMaxTHI() {
        return ofMaxTHI;
    }

    public WeatherCoes getOfMinCHI() {
        return ofMinCHI;
    }

    public WeatherCoes getOfMinCI() {
        return ofMinCI;
    }

    public WeatherCoes getOfMinHum() {
        return ofMinHum;
    }

    public WeatherCoes getOfMinRTT() {
        return ofMinRTT;
    }

    public WeatherCoes getOfMinTemp() {
        return ofMinTemp;
    }

    public WeatherCoes getOfMinTHI() {
        return ofMinTHI;
    }

    public WeatherCoes getOfRainFall() {
        return ofRainFall;
    }

    public WeatherCoes getOfWindSpeed() {
        return ofWindSpeed;
    }

    private WeatherCoes ofMinTemp;
    private WeatherCoes ofAveTemp;
    private WeatherCoes ofMaxHum;
    private WeatherCoes ofMinHum;
    private WeatherCoes ofAveHum;
    private WeatherCoes ofWindSpeed;
    private WeatherCoes ofRainFall;
    private WeatherCoes ofMaxTHI;
    private WeatherCoes ofMinTHI;
    private WeatherCoes ofAveTHI;
    private WeatherCoes ofMaxRTT;
    private WeatherCoes ofMinRTT;
    private WeatherCoes ofAveRTT;
    private WeatherCoes ofMaxCI;
    private WeatherCoes ofMinCI;
    private WeatherCoes ofAveCI;
    private WeatherCoes ofMaxCHI;
    private WeatherCoes ofMinCHI;
    private WeatherCoes ofAveCHI;
    private Map<String, WeatherCoes> map;

    @Override
    public Map<String, WeatherCoes> toMap() {
        if (map != null) {
            return map;
        }
        map = new HashMap<String, WeatherCoes>();
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
