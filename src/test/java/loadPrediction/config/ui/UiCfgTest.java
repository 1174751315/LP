package loadPrediction.config.ui;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class UiCfgTest {

    @Test
    public void testGetCfgFilePath() throws Exception {
        init();
        UiCfg uiCfg=UiCfg.INSTANCE;
        uiCfg.setSaxReader(mockSaxReader);
        uiCfg.update();
        ChartColorCfg chartColorCfg=uiCfg.getOutputChartImageCfg();
    }


    private SAXReader mockSaxReader;

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



        when(mockSaxReader.read(any(File.class))).thenReturn(mockDocument);
        when(mockDocument.getRootElement()).thenReturn(mockRootElement);
        when(mockRootElement.element("colors")).thenReturn(mockColorsElement);
        when(mockColorsElement.element("chart-colors")).thenReturn(mockChartColorsElement);
        when(mockChartColorsElement.element("fore-color")).thenReturn(foreColor);
        when(mockChartColorsElement.element("background-color")).thenReturn(backColor);
        when(mockChartColorsElement.element("grid-color")).thenReturn(gridColor);
        when(mockChartColorsElement.element("series-colors")).thenReturn(new DOMElement(""));
        when(gridColor.getText()).thenReturn("#FF0000");
        when(foreColor.getText()).thenReturn("#00FF00");
        when(backColor.getText()).thenReturn("#0000FF");

    }
}