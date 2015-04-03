/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;

/**
 * 李倍存 创建于 2015-02-20 17:35。电邮 1174751315@qq.com。
 */
public class WeekendPredictorCfg extends CommonPredictorCfg {
    public WeekendPredictorCfg() {
    }

    private Integer historyWorkdayNumber;
    private Integer historyWeekendNumber;

    public Integer getPredictionWeekendNumber() {
        return predictionWeekendNumber;
    }

    public void setPredictionWeekendNumber(Integer predictionWeekendNumber) {
        this.predictionWeekendNumber = predictionWeekendNumber;
    }

    public Integer getHistoryWeekendNumber() {
        return historyWeekendNumber;
    }

    public void setHistoryWeekendNumber(Integer historyWeekendNumber) {
        this.historyWeekendNumber = historyWeekendNumber;
    }

    public Integer getHistoryWorkdayNumber() {
        return historyWorkdayNumber;
    }

    public void setHistoryWorkdayNumber(Integer historyWorkdayNumber) {
        this.historyWorkdayNumber = historyWorkdayNumber;
    }

    private Integer predictionWeekendNumber;

    @Override
    public WeekendPredictorCfg clone() {
        WeekendPredictorCfg weekendPredictorCfg = new WeekendPredictorCfg();
        weekendPredictorCfg.setPredictionWeekendNumber(this.predictionWeekendNumber);
        weekendPredictorCfg.setHistoryWeekendNumber(this.historyWeekendNumber);
        weekendPredictorCfg.setHistoryWorkdayNumber(this.historyWorkdayNumber);

        weekendPredictorCfg.setTestCfg(super.getTestCfg().clone());
        weekendPredictorCfg.setIsEnabled(super.getIsEnabled());
        return weekendPredictorCfg;
    }

}
