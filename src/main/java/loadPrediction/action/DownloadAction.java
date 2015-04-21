/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import loadPrediction.resouce.IOPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 李倍存 创建于 2015-03-14 11:49。电邮 1174751315@qq.com。
 */
public class DownloadAction extends ActionSupport {
    public DownloadAction() {
    }


    private String inputPath = "output.xls";

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    private InputStream targetFile;


    private String dateString;

    public void setTargetFile(InputStream targetFile) {
        this.targetFile = targetFile;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public InputStream getTargetFile() throws Exception {
//        targetFile=ServletActionContext.getServletContext().getResourceAsStream(IOPaths.WEB_ROOT+"index.css");
        targetFile = new FileInputStream(new File(IOPaths.WEB_CONTENT_TEMP + dateString + ".xls"));
        return targetFile;
    }
}
