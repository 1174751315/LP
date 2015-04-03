/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import  loadPrediction.domain.visitors.IDomainVisitor;

/**
 * Created by LBC on 2015/2/9.
 */
public class WeatherCoes4Workday implements IDomain {
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

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
