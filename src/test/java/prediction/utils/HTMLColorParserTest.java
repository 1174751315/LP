package prediction.utils;

import prediction.utils.color.HTMLColorParser;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class HTMLColorParserTest {
    @Test
    public  void  testParse()throws  Exception{
        Color myColor=new HTMLColorParser().parse("HTML:01100A");
        assertEquals(myColor.getRed(),1);
        assertEquals(myColor.getGreen(),16);
        assertEquals(myColor.getBlue(),10);
    }
}