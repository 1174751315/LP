/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.config;

/**
 * 李倍存 创建于 2015-02-20 17:33。电邮 1174751315@qq.com。
 */
public class WorkdayPredictorCfg extends CommonPredictorCfg {
    public WorkdayPredictorCfg() {
    }

    private Integer historyWorkdayNumber;
    private Integer predictionWorkdayNumber;

    public Integer getHistoryWorkdayNumber() {
        return historyWorkdayNumber;
    }

    public void setHistoryWorkdayNumber(Integer historyWorkdayNumber) {
        this.historyWorkdayNumber = historyWorkdayNumber;
    }


    public Integer getPredictionWorkdayNumber() {
        return predictionWorkdayNumber;
    }

    public void setPredictionWorkdayNumber(Integer predictionWorkdayNumber) {
        this.predictionWorkdayNumber = predictionWorkdayNumber;
    }

    @Override
    public WorkdayPredictorCfg clone() {
        WorkdayPredictorCfg workdayPredictorCfg = new WorkdayPredictorCfg();
        workdayPredictorCfg.setHistoryWorkdayNumber(this.historyWorkdayNumber);
        workdayPredictorCfg.setPredictionWorkdayNumber(this.predictionWorkdayNumber);

        workdayPredictorCfg.setTestCfg(super.getTestCfg().clone());
        workdayPredictorCfg.setIsEnabled(super.getIsEnabled());
        return workdayPredictorCfg;

    }
}
