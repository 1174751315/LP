/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import common.MaxAveMinTuple;
import jfreechart.JFreeChartFacade;
import loadPrediction.core.predictor.IPredictor;
import loadPrediction.domain.LoadData;
import loadPrediction.exception.LPE;
import loadPrediction.utils.MyColor;

import java.util.LinkedList;
import java.util.List;


/**
 * 李倍存 创建于 2015-03-21 9:09。电邮 1174751315@qq.com。
 */
public class PredictionLoad24LinePictureVisitor_1 extends UnifiedImageOutputVisitor {

    public JChartBuilder4Predictor getChartBuilder() {
        return chartBuilder;
    }

    public void setChartBuilder(JChartBuilder4Predictor chartBuilder) {
        this.chartBuilder = chartBuilder;
    }

    private JChartBuilder4Predictor chartBuilder;
    public PredictionLoad24LinePictureVisitor_1 (String dir,String ds,JChartBuilder4Predictor builder){
        super(dir,ds);
        this.chartBuilder=builder;
    }

    @Override
    protected Object doVisitAndOutput(IPredictor predictor, String absPath) throws LPE {
        new JFreeChartFacade().saveAs(chartBuilder.build(predictor),absPath);
        return absPath;
    }

    public PredictionLoad24LinePictureVisitor_1(String dir,String ds) {
        this(dir, ds, new ChartBuilderImpl1(MyColor.COMMON_FOREGROUND,MyColor.COMMON_BACKGROUND,MyColor.COMMON_GRID_LINE));
    }

    @Override
    protected String getFileNamePostfix() {
        return "-4LINE_EXT";
    }

    public static MaxAveMinTuple<Double> unnamed(List<LoadData> list ){
        List<MaxAveMinTuple<Double>> list1=new LinkedList<MaxAveMinTuple<Double>>();
        for (int i = 0; i <list.size() ; i++) {
            list1.add(list.get(i).toMaxAveMin());
        }

        MaxAveMinTuple<Double> t=new MaxAveMinTuple<Double>(0.,0.,100000.);

        for (int i = 0; i <list1.size() ; i++) {
            if (t.max<list1.get(i).getMax())
                t.max=list1.get(i).getMax();
            if (t.min>list1.get(i).getMin())
                t.min=list1.get(i).getMin();
        }
        return t;
    }
}

