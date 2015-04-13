package loadPrediction.config.ui;

import loadPrediction.resouce.IOPaths;
import loadPrediction.utils.MyColor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-11 12:42。电邮 1174751315@qq.com。
 */
public class UiCfg {
    public ChartColorCfg getOutputChartImageCfg() {
        return outputChartImageCfg;
    }

    private ChartColorCfg outputChartImageCfg=new ChartColorCfg();

    private String cfgFilePath= IOPaths.WEB_CONTENT_UI_CFG_PATH;

    public String getCfgFilePath() {
        return cfgFilePath;
    }

    public void setCfgFilePath(String cfgFilePath) {
        this.cfgFilePath = cfgFilePath;
    }

    private UiCfg(){
    }

    public static UiCfg INSTANCE=new UiCfg();
    public void update(){
        Document document=null;
        try {
            if (saxReader==null){
                saxReader=new SAXReader();
            }
            document = saxReader.read(new File(cfgFilePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //获取根节点 <user-interface-configuration>
        Element root = document.getRootElement();
        Element colors=root.element("colors");
        Element chart_colors=colors.element("chart-colors");

        String text=chart_colors.element("background-color").getText();
        outputChartImageCfg.setBackGround(MyColor.getColorOfHTMLFormat(text));
        text=chart_colors.element("fore-color").getText();
        outputChartImageCfg.setForeGround(MyColor.getColorOfHTMLFormat(text));
        text=chart_colors.element("grid-color").getText();
        outputChartImageCfg.setGrid(MyColor.getColorOfHTMLFormat(text));

        Element seriesColors=chart_colors.element("series-colors");
        List series=seriesColors.elements();
        List<Color> colorList=new LinkedList<Color>();
        for (int i = 0; i < series.size(); i++) {
            colorList.add(MyColor.getColorOfHTMLFormat(((Element) series.get(i)).getText()));
        }
        outputChartImageCfg.setSeries(colorList);
    }

    private SAXReader saxReader;
    public SAXReader getSaxReader() {
        return saxReader;
    }

    public void setSaxReader(SAXReader saxReader) {
        this.saxReader = saxReader;
    }
}
