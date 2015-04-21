package prediction.config.ui;

import prediction.utils.color.IColorParser;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class UiCfgTest {

    @Test
    public void testGetCfgFilePath() throws Exception {
        init();
        UiCfg uiCfg=UiCfg.INSTANCE;
        uiCfg.setSaxReader(mockSaxReader);
        uiCfg.setColorParser(mockParser);
        uiCfg.update();
        ChartColorCfg chartColorCfg=uiCfg.getOutputChartImageCfg();

        Color back=chartColorCfg.getBackGround();
        Color fore=chartColorCfg.getForeGround();
        Color grid=chartColorCfg.getGrid();
        List<Color> series=chartColorCfg.getSeries();

        Color myColor=new  Color(255,255,255);
        assertEquals(back,myColor);
        assertEquals(fore,myColor);
        assertEquals(grid,myColor);


    }


    private SAXReader mockSaxReader;
    private IColorParser mockParser;
    private void init()throws Exception{
         Document mockDocument;
         Element mockRootElement;
         Element mockColorsElement;
         Element mockChartColorsElement;
         Element foreColor,backColor,gridColor;


        mockSaxReader=mock(SAXReader.class);
        mockDocument=mock(Document.class);
        mockRootElement=mock(Element.class);
        mockColorsElement=mock(Element.class);
        mockChartColorsElement=mock(Element.class);
        foreColor=mock(Element.class);
        backColor=mock(Element.class);
        gridColor=mock(Element.class);
        mockParser=mock(IColorParser.class);


        when(mockParser.parse(anyString())).thenReturn(new Color(255,255,255));

        when(mockSaxReader.read(any(File.class))).thenReturn(mockDocument);
        when(mockDocument.getRootElement()).thenReturn(mockRootElement);
        when(mockRootElement.element("colors")).thenReturn(mockColorsElement);
        when(mockColorsElement.element("chart-colors")).thenReturn(mockChartColorsElement);
        when(mockChartColorsElement.element("fore-color")).thenReturn(foreColor);
        when(mockChartColorsElement.element("background-color")).thenReturn(backColor);
        when(mockChartColorsElement.element("grid-color")).thenReturn(gridColor);
        when(mockChartColorsElement.element("series-colors")).thenReturn(new DOMElement(""));
        when(gridColor.getText()).thenReturn("HTML:FF0000");
        when(foreColor.getText()).thenReturn("HTML:00FF00");
        when(backColor.getText()).thenReturn("HTML:0000FF");

    }
}