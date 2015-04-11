/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.WeatherData;
import loadPrediction.exception.LPE;

import loadPrediction.utils.MyColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad2ReportPictureVisitor extends UnifiedImageOutputVisitor {
    private static final Integer WIDTH=1400,HEIGHT=200;



    private static final String[] LEFT_LABELS={},TOP_LABELS={"最大负荷","最大负荷时刻","最小负荷","最小负荷时刻","平均负荷","峰谷差","最高温度","平均温度","最低温度","降雨量"};

    @Override
    protected Object doVisitAndOutput(IPredictor predictor, String fileAbsPath) throws LPE {
        List<List<String>>  outputs=new LinkedList<List<String>>();
        List<LoadData> predictions=predictor.getPrediction96PointLoads();
        List<WeatherData> weatherDatas=predictor.getPredictionWeathers();
        for (int i = 0; i < predictor.getPrediction96PointLoads().size(); i++) {
            List<String> list=new LinkedList<String>();
            MaxAveMinTuple<Double> tuple=predictions.get(i).toMaxAveMin();
            list.add(tuple.max.toString());
            list.add(predictions.get(i).getMaxLabel());
            list.add(tuple.min.toString());
            list.add(predictions.get(i).getMinLabel());
            list.add(tuple.ave.toString());
            Double diff=tuple.max - tuple.min;
            list.add(diff.toString());
            MaxAveMinTuple<Double> weather=weatherDatas.get(i).toMaxAveMin();
            list.add(weather.max.toString());
            list.add(weather.ave.toString());
            list.add(weather.min.toString());
            list.add(weatherDatas.get(i).getRainFall().toString());

            outputs.add(list);
        }


        Integer AC_HEIGHT=HEIGHT+20;


        BufferedImage bufferedImage=new BufferedImage(WIDTH,AC_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics= bufferedImage.createGraphics();
        graphics.setPaint(imageColorCfg.getBackGround());
        graphics.setBackground(imageColorCfg.getBackGround());

        graphics.fill3DRect(0, 0, WIDTH, AC_HEIGHT, true);
        graphics.setPaint(imageColorCfg.getForeGround());


        List<String> leftLabels=new LinkedList<String>();
        for (int i = 0; i <predictions.size() ; i++) {
            leftLabels.add(predictions.get(i).getDateString());
        }
        Integer row=predictions.size()+1,col=TOP_LABELS.length+1;
        beforeDrawing(row,col);
//        drawTableGrid(graphics, MyColor.c3, predictions.size() + 1, TOP_LABELS.length + 1);

        graphics.setFont(new Font("微软雅黑", Font.BOLD, 16));

        drawLeftLabels(graphics, leftLabels);
        drawTopLabels(graphics, TOP_LABELS);
        drawTableGridValue(graphics,imageColorCfg.getForeGround(), outputs);
//        graphics.drawString(outputs.get(predictions.get(0).getDateString()).get("max_load"),0,100);

        try {
            ImageIO.write(bufferedImage, "jpg", new File(fileAbsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileAbsPath;
    }

    Integer perRow;
    Integer perCol;
    private void drawTableGrid(Graphics2D g,Color color,Integer row,Integer col){
        Paint old=g.getPaint();
        g.setPaint(color);
        for (int i = 0; i <row ; i++) {
            g.drawLine(0,(i+1)*perRow,WIDTH,(i+1)*perRow);
        }
        for (int i=0;i<col;i++)
            g.drawLine((i+1)*perCol,0,(i+1)*perCol,HEIGHT);

        g.setPaint(old);
    }
    private void drawTableGridValue(Graphics2D g,Color color,List<List<String>> values ){
        Paint old=g.getPaint();
        g.setPaint(color);
        Integer row=values.size();

        for (int i = 0; i <row ; i++) {
            Integer col=values.get(i).size();
            for (int j = 0; j <col ; j++) {
                String s=values.get(i).get(j);
                try {
                    s=String.format("%5.2f",Double.valueOf(s));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                g.drawString(s,(j+1)*perCol,(i+2)*perRow);
            }
        }
        g.setPaint(old);
    }

    private void drawLeftLabels(Graphics2D g ,List<String> labels){
        for (int i = 0; i <labels.size() ; i++) {
            g.drawString(labels.get(i),0,(2+i)*perRow);
        }
    }
    private void drawTopLabels(Graphics2D g,String[] labels){
        for (int i = 0; i <labels.length ; i++) {
            g.drawString(labels[i],(1+i)*perCol,perRow);
        }
    }
    private void beforeDrawing(Integer row,Integer col){
        perRow=HEIGHT/row;
        perCol=WIDTH/col;
    }
    private Boolean isNumeric(String s){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(s);
        return isNum.matches();
    }



    @Override
    protected String getFileNamePostfix() {
        return "RPT";
    }

    public PredictionLoad2ReportPictureVisitor(String dir,String ds){
        super(dir,ds);
    }
}

