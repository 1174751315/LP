package loadPrediction.utils;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class MyColorTest {
    @Test
    public void testParse1()throws Exception{
        Color myColor=MyColor.parse("RGB:12,23,34");
        assertEquals(myColor.getRed(),12);
        assertEquals(myColor.getGreen(),23);
        assertEquals(myColor.getBlue(),34);
    }

    @Test
    public  void  testParse2()throws  Exception{
        Color myColor=MyColor.parse("HTML:01100A");
        assertEquals(myColor.getRed(),1);
        assertEquals(myColor.getGreen(),16);
        assertEquals(myColor.getBlue(),10);
    }
}