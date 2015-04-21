/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import common.IMaxAveMinable;
import common.IPrintable;
import common.MaxAveMinTuple;
import loadPrediction.domain.visitors.IDomainVisitor;

import java.io.PrintStream;

/**
 * Created by LBC on 2015/1/22.
 */
public class LoadBase implements IDomain, IPrintable, IMaxAveMinable<Double> {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Double maxLoadBase;

    public Double getAveLoadBase() {
        return aveLoadBase;
    }

    public void setAveLoadBase(Double aveLoadBase) {
        this.aveLoadBase = aveLoadBase;
    }

    public Double getMaxLoadBase() {
        return maxLoadBase;
    }

    public void setMaxLoadBase(Double maxLoadBase) {
        this.maxLoadBase = maxLoadBase;
    }

    public Double getMinLoadBase() {
        return minLoadBase;
    }

    public void setMinLoadBase(Double minLoadBase) {
        this.minLoadBase = minLoadBase;
    }

    private Double aveLoadBase;
    private Double minLoadBase;

    private Integer year;
    private Integer month;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public void print(PrintStream ps) {
        ps.println(getYear() + "/" + getMonth() + ":  Max " + getMaxLoadBase() + ", Min " + getMinLoadBase() + ", Ave " + getAveLoadBase());
    }

    @Override
    public MaxAveMinTuple<Double> toMaxAveMin() {
        MaxAveMinTuple<Double> v = new MaxAveMinTuple<Double>("load_base");
        v.max = this.maxLoadBase;
        v.min = this.minLoadBase;
        v.ave = this.aveLoadBase;
        return v;
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
