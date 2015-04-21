package loadPrediction.domain;

/**
 * 李倍存 创建于 2015-04-13 21:14。电邮 1174751315@qq.com。
 */
public class WeatherCoes {
    private String type;
    private Double maxBase;
    private Double minBase;
    private Double relationCoe;
    private Double weight;

    public Double getMaxBase() {
        return maxBase;
    }

    public void setMaxBase(Double maxBase) {
        this.maxBase = maxBase;
    }

    public Double getMinBase() {
        return minBase;
    }

    public void setMinBase(Double minBase) {
        this.minBase = minBase;
    }

    public Double getRelationCoe() {
        return relationCoe;
    }

    public void setRelationCoe(Double relationCoe) {
        this.relationCoe = relationCoe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
