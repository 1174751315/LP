/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import jfreechart.JFreeChartFacade;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.core.predictor.IQingmingPredictor;
import loadPrediction.core.predictor.IWeekendPredictor;
import loadPrediction.core.predictor.IWorkdayPredictor;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.visitors.LoadDataAppend2DatasetVisitor;
import loadPrediction.exception.LPE;
import loadPrediction.utils.AccuracyUtils;
import loadPrediction.utils.FileContentUtils;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad2ReportPictureVisitor implements IPredictorVisitor {

    private String dir;

    public PredictionLoad2ReportPictureVisitor(String dir) {
        this.dir = dir;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) throws LPE {
        return unnamed(predictor, "WORKDAY_4_LINE");
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) throws LPE {
        return unnamed(predictor, "WEEKEND_4_LINE");
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) throws LPE {
        throw new LPE("方法未实现");
    }


    public String unnamed(IPredictor predictor, String prefix) {
        String fileName = FileContentUtils.autoFileName(prefix + predictor.getDateString(), "RP.JPG");
        Map<String,Map<String,String>>  outputs=new HashMap<String, Map<String, String>>();
        List<LoadData> predictions=predictor.getPrediction96PointLoads();
        for (int i = 0; i < predictor.getPrediction96PointLoads().size(); i++) {
            Map<String,String> map=new HashMap<String, String>();
            MaxAveMinTuple<Double> tuple=predictions.get(i).toMaxAveMin();
            map.put("max_load",tuple.max.toString());
            map.put("min_load",tuple.min.toString());
            map.put("ave_load",tuple.ave.toString());
            Double diff=tuple.max - tuple.min;
            map.put("diff", diff.toString());
            outputs.put(predictions.get(i).getDateString(),map);
        }

        Color c1=new Color(210,222,239);
        Color c2=new Color(118,183,247);
        Color c3=new Color(124,187,0);
        Color c4=new Color(0,114,51);
        Color c5=new Color(0,24,143);
        Color c6=new Color(0,160,233);
        Color c7=new Color(0,114,198);
        Color c8=new Color(131,204,254);
        Color c9=new Color(188,178,167);
        Color c10=new Color(200,228,155);
        BufferedImage bufferedImage=new BufferedImage(1000,200,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics= bufferedImage.createGraphics();
        graphics.setPaint(c3);
        graphics.setBackground(c3);
        graphics.fill3DRect(0, 0, 1000, 200, true);
        graphics.setPaint(Color.BLACK);
        graphics.drawString("最大负荷",0,50);
        graphics.drawString("最大负荷时刻",100,50);
        graphics.drawString("最小负荷",200,50);
        graphics.drawRect(0,0,1000,200);


        graphics.drawString(outputs.get(predictions.get(0).getDateString()).get("max_load"),0,100);

        try {
            ImageIO.write(bufferedImage, "jpg", new File(dir+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dir + fileName;
    }
}

