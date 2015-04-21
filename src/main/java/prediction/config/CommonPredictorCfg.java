/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.config;

/**
 * 李倍存 创建于 2015-02-20 17:35。电邮 1174751315@qq.com。
 */
public class CommonPredictorCfg {
    public CommonPredictorCfg() {
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    private Boolean isEnabled;


    private TestCfg testCfg;

    public TestCfg getTestCfg() {
        return testCfg;
    }

    public void setTestCfg(TestCfg testCfg) {
        this.testCfg = testCfg;
    }

    @Override
    public CommonPredictorCfg clone() {
        CommonPredictorCfg commonPredictorCfg = new CommonPredictorCfg();
        commonPredictorCfg.setIsEnabled(this.isEnabled);
        commonPredictorCfg.setTestCfg(this.testCfg.clone());

        return commonPredictorCfg;
    }
}
