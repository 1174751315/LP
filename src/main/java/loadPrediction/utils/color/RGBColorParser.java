package loadPrediction.utils.color;

import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.awt.*;

/**
 * 李倍存 创建于 2015-04-14 21:48。电邮 1174751315@qq.com。
 */
public class RGBColorParser implements IColorParser {
    @Override
    public Color parse(String s) {
        if (!s.contains(new CharArrayString("RGB:"))) {
            return null;
        }
        String code=s.substring(4);
        String[] rgb=code.split(",");
        String r=rgb[0];
        String g=rgb[1];
        String b=rgb[2];

        return MyColor.getColor(r,g,b,10);
    }
}
