/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package test.java.etc;

import main.java.loadPrediction.resouce.TimeLabels;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 创建：2015/2/11 20:43
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class CodeGenerator {
    @Test
    public void generate() {
        try {
            PrintStream ps = new PrintStream(new File("code.txt"));
            print7(ps);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void print1(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.print("ds.addValue(upr.data" + i + "," + "col," + "\"" + TimeLabels.labels[i] + "\"" + ");\n");
        }
    }

    private void print2(PrintStream ps) {
        for (int i = 27; i <= 95; i++) {
            ps.print("loadData.setData" + i + "(similarDataLoadData.getData" + i + "() + coe.max);\n");
        }
    }

    private void print3(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.print("predictionLoadData.setData" + i + "(this.getData" + i + "());\n");
        }
    }

    private void print4(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.println("<!ELEMENT data" + i + " (#PCDATA)>");
        }
    }

    private void print5(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.print("data" + i + ",");
        }
    }

    private void print6(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.println("setData" + i + "(data.get(" + i + "));");
        }
    }

    private void print7(PrintStream ps) {
        for (int i = 0; i < 96; i++) {
            ps.println("loadData.setData" + i + "(values.get(" + i + "));");
        }
    }
}
