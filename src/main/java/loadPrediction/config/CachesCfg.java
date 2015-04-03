/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;

/**
 * 李倍存 创建于 2015-03-20 17:45。电邮 1174751315@qq.com。
 */
public class CachesCfg {
    public CachesCfg() {
    }

    public CacheContent getPredictionContent() {
        return predictionContent;
    }

    public void setPredictionContent(CacheContent predictionContent) {
        this.predictionContent = predictionContent;
    }

    public CacheContent getAccuracyCheckingContent() {
        return accuracyCheckingContent;
    }

    public void setAccuracyCheckingContent(CacheContent accuracyCheckingContent) {
        this.accuracyCheckingContent = accuracyCheckingContent;
    }

    private CacheContent predictionContent;

    public CachesCfg(CacheContent predictionContent, CacheContent accuracyCheckingContent) {
        this.predictionContent = predictionContent;
        this.accuracyCheckingContent = accuracyCheckingContent;
    }

    private CacheContent accuracyCheckingContent;

    public CachesCfg clone() {
        return new CachesCfg(this.predictionContent.clone(), this.accuracyCheckingContent.clone());
    }
}
