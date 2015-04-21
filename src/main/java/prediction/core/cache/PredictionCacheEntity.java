/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.cache;

/**
 * 李倍存 创建于 2015/3/19 0:02。电邮 1174751315@qq.com。
 */
public class PredictionCacheEntity {

    public PredictionCacheEntity(String dateString, String type, String outputExcelPath, String outputImagePath, String outputRptImagePath, String warning) {
        this.dateString = dateString;
        this.type = type;
        this.outputExcelPath = outputExcelPath;
        this.outputImagePath = outputImagePath;
        this.warning = warning;
        this.outputRptImagePath = outputRptImagePath;
    }

    private String dateString;
    private String type;
    private String outputExcelPath;
    private String outputImagePath;

    public String getOutputRptImagePath() {
        return outputRptImagePath;
    }

    public void setOutputRptImagePath(String outputRptImagePath) {
        this.outputRptImagePath = outputRptImagePath;
    }

    private String outputRptImagePath;
    private String warning;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutputExcelPath() {
        return outputExcelPath;
    }

    public void setOutputExcelPath(String outputExcelPath) {
        this.outputExcelPath = outputExcelPath;
    }

    public String getOutputImagePath() {
        return outputImagePath;
    }

    public void setOutputImagePath(String outputImagePath) {
        this.outputImagePath = outputImagePath;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public PredictionCacheEntity() {
    }

    public Boolean validate() {
        return (this.outputExcelPath != null && this.outputImagePath != null && this.warning != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredictionCacheEntity entity = (PredictionCacheEntity) o;

        if (!dateString.equals(entity.dateString)) return false;
        if (!outputExcelPath.equals(entity.outputExcelPath)) return false;
        if (!outputImagePath.equals(entity.outputImagePath)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dateString.hashCode();
        result = 31 * result + outputExcelPath.hashCode();
        result = 31 * result + outputImagePath.hashCode();
        return result;
    }
}
