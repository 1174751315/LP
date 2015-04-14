package loadPrediction.utils;

import loadPrediction.utils.color.RGBColorParser;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * 李倍存 创建于 2015-04-14 21:53。电邮 1174751315@qq.com。
 */
public class RGBColorParserTest {
    public RGBColorParserTest() {
    }
    @Test
    public void testParse()throws Exception{
        Color myColor= new RGBColorParser().parse("RGB:12,23,34");
        assertEquals(myColor.getRed(),12);
        assertEquals(myColor.getGreen(),23);
        assertEquals(myColor.getBlue(),34);
    }

}
