/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.test;

import common.IPrintable;
import prediction.domain.SimpleDate;

import java.io.PrintStream;
import java.util.List;

/**
 * 创建：2015/2/18 9:40
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class WorkdayTestRecord extends TestRecord implements IPrintable {
    public WorkdayTestRecord(List<SimpleDate> predictionDates, List<SimpleDate> similarDates,
                             List<Double> accuracy, String status) {
        super(status, "工作日预测");
        this.predictionDate0 = predictionDates.get(0).getDateString();
        this.predictionDate1 = predictionDates.get(1).getDateString();
        this.predictionDate2 = predictionDates.get(2).getDateString();
        this.predictionDate3 = predictionDates.get(3).getDateString();
        this.predictionDate4 = predictionDates.get(4).getDateString();
        this.predictionDate5 = predictionDates.get(5).getDateString();
        this.predictionDate6 = predictionDates.get(6).getDateString();

        this.similarDate0 = similarDates.get(0).getDateString();
        this.similarDate1 = similarDates.get(1).getDateString();
        this.similarDate2 = similarDates.get(2).getDateString();
        this.similarDate3 = similarDates.get(3).getDateString();
        this.similarDate4 = similarDates.get(4).getDateString();
        this.similarDate5 = similarDates.get(5).getDateString();
        this.similarDate6 = similarDates.get(6).getDateString();

        this.accuracy0 = accuracy.get(0);
        this.accuracy1 = accuracy.get(1);
        this.accuracy2 = accuracy.get(2);
        this.accuracy3 = accuracy.get(3);
        this.accuracy4 = accuracy.get(4);
        this.accuracy5 = accuracy.get(5);
        this.accuracy6 = accuracy.get(6);

    }

    private String similarDate0;
    private String similarDate1;
    private String similarDate2;
    private String similarDate3;
    private String similarDate4;
    private String similarDate5;
    private String similarDate6;

    public WorkdayTestRecord() {
    }

    public Double getAccuracy0() {
        return accuracy0;
    }

    public void setAccuracy0(Double accuracy0) {
        this.accuracy0 = accuracy0;
    }

    public Double getAccuracy1() {
        return accuracy1;
    }

    public void setAccuracy1(Double accuracy1) {
        this.accuracy1 = accuracy1;
    }

    public Double getAccuracy2() {
        return accuracy2;
    }

    public void setAccuracy2(Double accuracy2) {
        this.accuracy2 = accuracy2;
    }

    public Double getAccuracy3() {
        return accuracy3;
    }

    public void setAccuracy3(Double accuracy3) {
        this.accuracy3 = accuracy3;
    }

    public Double getAccuracy4() {
        return accuracy4;
    }

    public void setAccuracy4(Double accuracy4) {
        this.accuracy4 = accuracy4;
    }

    public Double getAccuracy5() {
        return accuracy5;
    }

    public void setAccuracy5(Double accuracy5) {
        this.accuracy5 = accuracy5;
    }

    public Double getAccuracy6() {
        return accuracy6;
    }

    public void setAccuracy6(Double accuracy6) {
        this.accuracy6 = accuracy6;
    }

    public String getPredictionDate0() {
        return predictionDate0;
    }

    public void setPredictionDate0(String predictionDate0) {
        this.predictionDate0 = predictionDate0;
    }

    public String getPredictionDate1() {
        return predictionDate1;
    }

    public void setPredictionDate1(String predictionDate1) {
        this.predictionDate1 = predictionDate1;
    }

    public String getPredictionDate2() {
        return predictionDate2;
    }

    public void setPredictionDate2(String predictionDate2) {
        this.predictionDate2 = predictionDate2;
    }

    public String getPredictionDate3() {
        return predictionDate3;
    }

    public void setPredictionDate3(String predictionDate3) {
        this.predictionDate3 = predictionDate3;
    }

    public String getPredictionDate4() {
        return predictionDate4;
    }

    public void setPredictionDate4(String predictionDate4) {
        this.predictionDate4 = predictionDate4;
    }

    public String getPredictionDate5() {
        return predictionDate5;
    }

    public void setPredictionDate5(String predictionDate5) {
        this.predictionDate5 = predictionDate5;
    }

    public String getPredictionDate6() {
        return predictionDate6;
    }

    public void setPredictionDate6(String predictionDate6) {
        this.predictionDate6 = predictionDate6;
    }

    public String getSimilarDate0() {
        return similarDate0;
    }

    public void setSimilarDate0(String similarDate0) {
        this.similarDate0 = similarDate0;
    }

    public String getSimilarDate1() {
        return similarDate1;
    }

    public void setSimilarDate1(String similarDate1) {
        this.similarDate1 = similarDate1;
    }

    public String getSimilarDate2() {
        return similarDate2;
    }

    public void setSimilarDate2(String similarDate2) {
        this.similarDate2 = similarDate2;
    }

    public String getSimilarDate3() {
        return similarDate3;
    }

    public void setSimilarDate3(String similarDate3) {
        this.similarDate3 = similarDate3;
    }

    public String getSimilarDate4() {
        return similarDate4;
    }

    public void setSimilarDate4(String similarDate4) {
        this.similarDate4 = similarDate4;
    }

    public String getSimilarDate5() {
        return similarDate5;
    }

    public void setSimilarDate5(String similarDate5) {
        this.similarDate5 = similarDate5;
    }

    public String getSimilarDate6() {
        return similarDate6;
    }

    public void setSimilarDate6(String similarDate6) {
        this.similarDate6 = similarDate6;
    }

    private String predictionDate0;
    private String predictionDate1;
    private String predictionDate2;
    private String predictionDate3;
    private String predictionDate4;
    private String predictionDate5;
    private String predictionDate6;

    private Double accuracy0;
    private Double accuracy1;
    private Double accuracy2;
    private Double accuracy3;
    private Double accuracy4;
    private Double accuracy5;
    private Double accuracy6;

    @Override
    public void print(PrintStream ps) {

    }


}
