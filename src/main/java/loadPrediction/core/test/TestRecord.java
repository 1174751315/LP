/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.test;

import java.time.LocalDateTime;

/**
 * 创建：2015/2/18 9:26
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class TestRecord {
    public TestRecord() {
    }

    public TestRecord(String status, String type) {
        this.status = status;
        this.type = type;
        this.time = LocalDateTime.now().toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String time;
    private String type;
    private String status;


    public String err() {
        return status;
    }
}
