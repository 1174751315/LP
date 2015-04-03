/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.test;

import  common.IPrintable;
import  loadPrediction.domain.SimpleDate;

import java.io.PrintStream;
import java.util.List;

/**
 * 创建：2015/2/18 9:41
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class WeekendTestRecord extends TestRecord implements IPrintable {
    public WeekendTestRecord(List<SimpleDate> predictionDates, List<SimpleDate> similarWorkdayDates, List<SimpleDate> similarWeekendDates,
                             List<Double> accuracy, String status) {
        super(status, "周末预测");

        this.accuracy0 = accuracy.get(0);
        this.accuracy1 = accuracy.get(1);
        this.predictionDate0 = predictionDates.get(0).getDateString();
        this.predictionDate1 = predictionDates.get(1).getDateString();
        this.similarWorkdayDate0 = similarWorkdayDates.get(0).getDateString();
        this.similarWorkdayDate1 = similarWorkdayDates.get(1).getDateString();
        this.similarWeekendDate0 = similarWeekendDates.get(0).getDateString();
        this.similarWeekendDate1 = similarWeekendDates.get(1).getDateString();

    }

    public WeekendTestRecord() {
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

    public String getSimilarWeekendDate0() {
        return similarWeekendDate0;
    }

    public void setSimilarWeekendDate0(String similarWeekendDate0) {
        this.similarWeekendDate0 = similarWeekendDate0;
    }

    public String getSimilarWeekendDate1() {
        return similarWeekendDate1;
    }

    public void setSimilarWeekendDate1(String similarWeekendDate1) {
        this.similarWeekendDate1 = similarWeekendDate1;
    }

    public String getSimilarWorkdayDate0() {
        return similarWorkdayDate0;
    }

    public void setSimilarWorkdayDate0(String similarWorkdayDate0) {
        this.similarWorkdayDate0 = similarWorkdayDate0;
    }

    public String getSimilarWorkdayDate1() {
        return similarWorkdayDate1;
    }

    public void setSimilarWorkdayDate1(String similarWorkdayDate1) {
        this.similarWorkdayDate1 = similarWorkdayDate1;
    }

    private String similarWorkdayDate0;
    private String similarWorkdayDate1;

    private String similarWeekendDate0;
    private String similarWeekendDate1;

    private String predictionDate0;
    private String predictionDate1;


    private Double accuracy0;
    private Double accuracy1;

    @Override
    public void print(PrintStream ps) {

    }
}
