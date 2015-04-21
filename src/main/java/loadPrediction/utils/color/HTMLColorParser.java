package loadPrediction.utils.color;


//import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.awt.*;

/**
 * 李倍存 创建于 2015-04-14 21:40。电邮 1174751315@qq.com。
 */
public class HTMLColorParser implements IColorParser {
    public Color parse(String s) {
        if (s.indexOf("HTML:") == -1) {
            return null;
        }

        String c = s.substring(5);
        String r = c.substring(0, 2);
        String g = c.substring(2, 4);
        String b = c.substring(4, 6);

        return MyColor.getColor(r, g, b, 16);

    }

    /**
     * 根据HTML格式的颜色码获取对应的Color对象。
     * @param code 代表一种颜色值的形如“HTML:01234”格式的字符串
     * @return 对应于code的Color对象
     */

}
