/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.config;

/**
 * 李倍存 创建于 2015-02-20 17:37。电邮 1174751315@qq.com。
 */
public class FestivalPredictorCfg extends CommonPredictorCfg {
    public FestivalPredictorCfg() {
    }

    private Integer historyFestivalNumber;
    private Integer predictionFestivalNumber;

    public Integer getHistoryFestivalNumber() {
        return historyFestivalNumber;
    }

    public void setHistoryFestivalNumber(Integer historyFestivalNumber) {
        this.historyFestivalNumber = historyFestivalNumber;
    }

    public Integer getPredictionFestivalNumber() {
        return predictionFestivalNumber;
    }

    public void setPredictionFestivalNumber(Integer predictionFestivalNumber) {
        this.predictionFestivalNumber = predictionFestivalNumber;
    }

    @Override
    public FestivalPredictorCfg clone() {
        FestivalPredictorCfg festivalPredictorCfg = new FestivalPredictorCfg();
        festivalPredictorCfg.setHistoryFestivalNumber(this.historyFestivalNumber);
        festivalPredictorCfg.setPredictionFestivalNumber(this.predictionFestivalNumber);
        festivalPredictorCfg.setTestCfg(super.getTestCfg().clone());
        festivalPredictorCfg.setIsEnabled(super.getIsEnabled());

        return festivalPredictorCfg;

    }
}
