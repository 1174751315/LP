package loadPrediction.utils;

import java.awt.*;
import java.awt.color.ColorSpace;

/**
 * 李倍存 创建于 2015-04-08 11:01。电邮 1174751315@qq.com。
 */
public class MyColor extends Color {
    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    public MyColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public MyColor(int rgb) {
        super(rgb);
    }

    public MyColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public MyColor(float r, float g, float b) {
        super(r, g, b);
    }

    public MyColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public MyColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }


    public static final Color c1=new Color(210,222,239);
    public static final Color c2=new Color(118,183,247);
    public static final Color c3=new Color(124,187,0);
    public static final Color c4=new Color(0,114,51);
    public static final Color c5=new Color(0,24,143);
    public static final Color c6=new Color(0,160,233);
    public static final Color c7=new Color(0,114,198);
    public static final Color c8=new Color(131,204,254);
    public static final Color c9=new Color(188,178,167);
    public static final Color c10=new Color(200,228,155);


    public static final Color COMMON_BLUE_BACKGROUND=c2;
}
