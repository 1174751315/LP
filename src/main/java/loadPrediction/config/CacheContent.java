/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;

/**
 * 李倍存 创建于 2015-03-20 17:45。电邮 1174751315@qq.com。
 */
public class CacheContent {
    public CacheContent() {
    }

    private String dataDir;
    private String contentPath;

    public CacheContent(String dataDir, String contentPath) {
        this.dataDir = dataDir;
        this.contentPath = contentPath;
    }

    public String getDataDir() {

        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public CacheContent clone() {
        return new CacheContent(this.dataDir, this.contentPath);
    }
}
