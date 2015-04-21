/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.cache;

/**
 * 李倍存 创建于 2015-03-20 18:03。电邮 1174751315@qq.com。
 */
public class OnedayAccuracyCheckingCacheEntity {


    private Double accuracy;
    private String dateString;
    private String outputImagePath;

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getOutputImagePath() {
        return outputImagePath;
    }

    public void setOutputImagePath(String outputImagePath) {
        this.outputImagePath = outputImagePath;
    }

    public OnedayAccuracyCheckingCacheEntity(Double accuracy, String dateString, String outputImagePath) {

        this.accuracy = accuracy;
        this.dateString = dateString;
        this.outputImagePath = outputImagePath;
    }

    public OnedayAccuracyCheckingCacheEntity() {

    }

}
