/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import  common.IPrintable;
import  loadPrediction.domain.visitors.IDomainVisitor;

import java.io.PrintStream;

/**
 * Created by LBC on 2015/2/4.
 */
public class SimpleWeatherData implements IDomain, IPrintable {
    private float maxTemperature;
    private float aveTemperature;
    private float minTemperature;

    private float maxHumid;
    private float aveHumid;
    private float minHumid;

    private float maxWindSpeed;
    private float aveWindSpeed;

    private float rainFall;

    private Integer id;
    private SimpleDate simpleDate;
    private City city;

    public float getAveHumid() {
        return aveHumid;
    }

    public void setAveHumid(float aveHumid) {
        this.aveHumid = aveHumid;
    }

    public float getAveTemperature() {
        return aveTemperature;
    }

    public void setAveTemperature(float aveTemperature) {
        this.aveTemperature = aveTemperature;
    }

    public float getAveWindSpeed() {
        return aveWindSpeed;
    }

    public void setAveWindSpeed(float aveWindSpeed) {
        this.aveWindSpeed = aveWindSpeed;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getMaxHumid() {
        return maxHumid;
    }

    public void setMaxHumid(float maxHumid) {
        this.maxHumid = maxHumid;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(float maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public float getMinHumid() {
        return minHumid;
    }

    public void setMinHumid(float minHumid) {
        this.minHumid = minHumid;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getRainFall() {
        return rainFall;
    }

    public void setRainFall(float rainFall) {
        this.rainFall = rainFall;
    }

    public SimpleDate getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(SimpleDate simpleDate) {
        this.simpleDate = simpleDate;
    }

    @Override
    public void print(PrintStream ps) {
        ps.println(simpleDate.getDate().toLocalDate().toString() + "," + simpleDate.getDateType().getName() + "," + simpleDate.getWeatherType().getName() + ": " + city.getName());
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
