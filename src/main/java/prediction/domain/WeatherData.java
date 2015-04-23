/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.domain;

import common.IMapable;
import common.IMaxAveMinable;
import common.IPrintable;
import common.MaxAveMinTuple;
import prediction.domain.visitors.IDomainVisitor;
import prediction.resouce.WeatherDataMappingKeys;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/1/22.
 * 表示综合气象数据和一般气象数据。
 */
public class WeatherData implements IDomain, IPrintable, IMapable<String, Double>, IMaxAveMinable<Double> {

    private String dateString;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getAveTemperature() {
        return aveTemperature;
    }

    public void setAveTemperature(Double aveTemperature) {
        this.aveTemperature = aveTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxHumid() {
        return maxHumid;
    }

    public void setMaxHumid(Double maxHumid) {
        this.maxHumid = maxHumid;
    }

    public Double getAveHumid() {
        return aveHumid;
    }

    public void setAveHumid(Double aveHumid) {
        this.aveHumid = aveHumid;
    }

    public Double getMinHumid() {
        return minHumid;
    }

    public void setMinHumid(Double minHumid) {
        this.minHumid = minHumid;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getRainFall() {
        return rainFall;
    }

    public void setRainFall(Double rainFall) {
        this.rainFall = rainFall;
    }

    public Double getMaxTHI() {
        return maxTHI;
    }

    public void setMaxTHI(Double maxTHI) {
        this.maxTHI = maxTHI;
    }

    public Double getAveTHI() {
        return aveTHI;
    }

    public void setAveTHI(Double aveTHI) {
        this.aveTHI = aveTHI;
    }

    public Double getMinTHI() {
        return minTHI;
    }

    public void setMinTHI(Double minTHI) {
        this.minTHI = minTHI;
    }

    public Double getMaxRTT() {
        return maxRTT;
    }

    public void setMaxRTT(Double maxRTT) {
        this.maxRTT = maxRTT;
    }

    public Double getAveRTT() {
        return aveRTT;
    }

    public void setAveRTT(Double aveRTT) {
        this.aveRTT = aveRTT;
    }

    public Double getMinRTT() {
        return minRTT;
    }

    public void setMinRTT(Double minRTT) {
        this.minRTT = minRTT;
    }

    public Double getMaxCI() {
        return maxCI;
    }

    public void setMaxCI(Double maxCI) {
        this.maxCI = maxCI;
    }

    public Double getAveCI() {
        return aveCI;
    }

    public void setAveCI(Double aveCI) {
        this.aveCI = aveCI;
    }

    public Double getMinCI() {
        return minCI;
    }

    public void setMinCI(Double minCI) {
        this.minCI = minCI;
    }

    public Double getMaxCHI() {
        return maxCHI;
    }

    public void setMaxCHI(Double maxCHI) {
        this.maxCHI = maxCHI;
    }

    public Double getAveCHI() {
        return aveCHI;
    }

    public void setAveCHI(Double aveCHI) {
        this.aveCHI = aveCHI;
    }

    public Double getMinCHI() {
        return minCHI;
    }

    public void setMinCHI(Double minCHI) {
        this.minCHI = minCHI;
    }

    public Double getMaxLT() {
        return maxLT;
    }

    public void setMaxLT(Double maxLT) {
        this.maxLT = maxLT;
    }

    public Double getAveVI() {
        return aveVI;
    }

    public void setAveVI(Double aveVI) {
        this.aveVI = aveVI;
    }

    public Double getMinSLP() {
        return minSLP;
    }

    public void setMinSLP(Double minSLP) {
        this.minSLP = minSLP;
    }

    private Double maxTemperature = 0.;
    private Double aveTemperature = 0.;
    private Double minTemperature = 0.;

    private Double maxHumid = 0.;
    private Double aveHumid = 0.;
    private Double minHumid = 0.;

    private Double windSpeed = 0.;

    private Double rainFall = 0.;

    private Double maxTHI = 0.;
    private Double aveTHI = 0.;
    private Double minTHI = 0.;

    private Double maxRTT = 0.;
    private Double aveRTT = 0.;
    private Double minRTT = 0.;


    private Double maxCI = 0.;
    private Double aveCI = 0.;
    private Double minCI = 0.;

    private Double maxCHI = 0.;
    private Double aveCHI = 0.;
    private Double minCHI = 0.;

    private Double maxLT = 0.;
    private Double aveVI = 0.;
    private Double minSLP = 0.;

    private static boolean isPrinting = false;

    @Override
    public void print(PrintStream ps) {

        toMap();
        String[] keys = WeatherDataMappingKeys.keys;
        ps.print(this.dateString + ": ");
        for (int i = 0; i < keys.length; i++) {
            ps.printf("%2.4f, ", map.get(keys[i]));
        }
        ps.print("\n");
    }

    private Map<String, Double> map = new HashMap<String, Double>();

    @Override
    public Map<String, Double> toMap() {
//        map.put("max_chihum",maxCHI);
        map.put("max_com", maxCI);
        map.put("max_hum", maxHumid);
        map.put("maxLT", maxLT);
        map.put("max_efftemp", maxRTT);
        map.put("max_temp", maxTemperature);
        map.put("max_temphum", maxTHI);
        map.put("max_chihum", maxCHI);
        map.put("min_chihum", minCHI);
        map.put("min_com", minCI);
        map.put("min_hum", minHumid);
        map.put("min_efftemp", minRTT);
        map.put("minSLP", minSLP);
        map.put("min_temp", minTemperature);
        map.put("min_temphum", minTHI);
        map.put("ave_chihum", aveCHI);
        map.put("ave_com", aveCI);
        map.put("ave_hum", aveHumid);
        map.put("ave_efftemp", aveRTT);
        map.put("ave_temp", aveTemperature);
        map.put("ave_temphum", aveTHI);
        map.put("aveVI", aveVI);
        map.put("mean_wind", windSpeed);
        map.put("precip", rainFall);

        return map;
    }

    public void putMap(String k, Double v) {
        map.put(k, v);
    }

    public void unMap() {
        maxCHI = map.get("maxCHI");
        maxCI = map.get("max_com");
        maxHumid = map.get("max_hum");
        maxLT = map.get("maxLT");
        maxRTT = map.get("max_efftemp");
        maxTemperature = map.get("max_temp");
        maxTHI = map.get("max_temphum");
        maxCHI = map.get("max_chihum");
        minCHI = map.get("min_chihum");
        minCI = map.get("min_com");
        minHumid = map.get("min_hum");
        minRTT = map.get("min_efftemp");
        minSLP = map.get("minSLP");
        minTemperature = map.get("min_temp");
        minTHI = map.get("min_temphum");
        aveCHI = map.get("ave_chihum");
        aveCI = map.get("ave_com");
        aveHumid = map.get("ave_hum");
        aveRTT = map.get("ave_efftemp");
        aveTemperature = map.get("ave_temp");
        aveTHI = map.get("ave_temphum");
        aveVI = map.get("aveVI");
        windSpeed = map.get("mean_wind");
        rainFall = map.get("precip");
    }

    @Override
    public MaxAveMinTuple<Double> toMaxAveMin() {
        MaxAveMinTuple<Double> t = new MaxAveMinTuple<Double>("max_ave_min");
        t.setMax(this.maxTemperature);
        t.setAve(this.aveTemperature);
        t.setMin(this.minTemperature);
        return t;
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }


    public WeatherData add(WeatherData newdata) {
        this.maxTemperature += newdata.getMaxTemperature();
        this.aveTemperature += newdata.getAveTemperature();
        this.minTemperature += newdata.getMinTemperature();

        this.maxHumid += newdata.getMaxHumid();
        this.aveHumid += newdata.getAveHumid();
        this.minHumid += newdata.getMinHumid();

        this.windSpeed += newdata.getWindSpeed();

        this.rainFall += newdata.getRainFall();

        this.maxTHI += newdata.getMaxTHI();
        this.aveTHI += newdata.getAveTHI();
        this.minTHI += newdata.getMinTHI();

        this.maxRTT += newdata.getMaxRTT();
        this.aveRTT += newdata.getAveRTT();
        this.minRTT += newdata.getMinRTT();


        this.maxCI += newdata.getMaxCI();
        this.aveCI += newdata.getAveCI();
        this.minCI += newdata.getMinCI();

        this.maxCHI += newdata.getMaxCHI();
        this.aveCHI += newdata.getAveCHI();
        this.minCHI += newdata.getMinCHI();

        this.maxLT += newdata.getMaxLT();
        this.aveVI += newdata.getAveVI();
        this.minSLP += newdata.getMinSLP();
        return this;
    }


    public WeatherData multiple(Double d) {
        this.maxTemperature *= d;
        this.aveTemperature *= d;
        this.minTemperature *= d;
        this.maxHumid *= d;
        this.aveHumid *= d;
        this.minHumid *= d;
        this.windSpeed *= d;
        this.rainFall *= d;
        this.maxTHI *= d;
        this.aveTHI *= d;
        this.minTHI *= d;
        this.maxRTT *= d;
        this.aveRTT *= d;
        this.minRTT *= d;
        this.maxCI *= d;
        this.aveCI *= d;
        this.minCI *= d;
        this.maxCHI *= d;
        this.aveCHI *= d;
        this.minCHI *= d;
        this.maxLT *= d;
        this.aveVI *= d;
        this.minSLP *= d;
        return this;
    }
}
