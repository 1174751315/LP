/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import  common.IPrintable;
import  loadPrediction.domain.visitors.IDomainVisitor;

import java.io.PrintStream;
import java.io.Serializable;

/**
 * Created by LBC on 2015/2/4.
 */
public class RawWeatherData implements IDomain, IPrintable, Serializable {
    private Double maxTemperature;
    private Double aveTemperature;
    private Double minTemperature;

    private Double maxHumid;
    private Double aveHumid;
    private Double minHumid;

    private Double maxWindSpeed;
    private Double aveWindSpeed;

    private Double rainFall;

    private Integer id;
    private String dateString;
    private String baseString;

    public String getBaseString() {
        return baseString;
    }

    public void setBaseString(String baseString) {
        this.baseString = baseString;
    }

    private Integer cityId;

    public Double getAveHumid() {
        return aveHumid;
    }

    public void setAveHumid(Double aveHumid) {
        this.aveHumid = aveHumid;
    }

    public Double getAveTemperature() {
        return aveTemperature;
    }

    public void setAveTemperature(Double aveTemperature) {
        this.aveTemperature = aveTemperature;
    }

    public Double getAveWindSpeed() {
        return aveWindSpeed;
    }

    public void setAveWindSpeed(Double aveWindSpeed) {
        this.aveWindSpeed = aveWindSpeed;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMaxHumid() {
        return maxHumid;
    }

    public void setMaxHumid(Double maxHumid) {
        this.maxHumid = maxHumid;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(Double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public Double getMinHumid() {
        return minHumid;
    }

    public void setMinHumid(Double minHumid) {
        this.minHumid = minHumid;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getRainFall() {
        return rainFall;
    }

    public void setRainFall(Double rainFall) {
        this.rainFall = rainFall;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void print(PrintStream ps) {

        ps.printf(dateString + ": ");
        ps.printf("%4.4f,%4.4f,%4.4f,%4.4f,%4.4f,%4.4f,%4.4f,%4.4f\n", maxTemperature, aveTemperature, minTemperature, maxHumid, aveHumid, minHumid, aveWindSpeed, rainFall);

    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
