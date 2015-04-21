/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.config;

import common.IPrintable;

import java.io.PrintStream;

/**
 * 李倍存 创建于 2015-02-20 17:39。电邮 1174751315@qq.com。
 */
public class OutputCfg implements IPrintable {
    private static Integer objCount = 0;

    public static Integer getCount() {
        return objCount;
    }

    private Integer currentCount;

    public OutputCfg() {
        objCount++;
        currentCount = objCount;
    }

    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public void print(PrintStream ps) {
        ps.println("第 " + currentCount + " 个输出配置: " + destination);
    }

    @Override
    public OutputCfg clone() {
        OutputCfg outputCfg = new OutputCfg();
        outputCfg.setDestination(this.destination);

        return outputCfg;
    }
}
