/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package io;

import org.jfree.chart.renderer.category.BarRenderer;

import java.awt.*;


/**
 * 李倍存 创建于 2015-03-08 14:20。电邮 1174751315@qq.com。
 */
public class MyChartRender extends BarRenderer {
    private Paint[] colors;
    private String[] colorValues = {"#00FF00", "#FF0000"};

    public MyChartRender() {
        colors = new Paint[colorValues.length];
        for (int i = 0; i < colorValues.length; i++) {
            colors[i] = Color.decode(colorValues[i]);
        }
    }

    public Paint getItemPaint(int i, int j) {
        return colors[j % colors.length];
    }
}
