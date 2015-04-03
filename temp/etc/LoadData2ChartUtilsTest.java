/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.core.predictor.PredictorFactory;
import main.java.loadPrediction.core.predictor.IPredictor;
import main.java.loadPrediction.domain.LoadData;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class LoadData2ChartUtilsTest {

    @Test
    public void testLoadData2Chart() throws Exception {
        IPredictor predictor = PredictorFactory.getInstance().getProperPredictor("2014-02-07");
        predictor.predict();
        List<LoadData> ls = predictor.getActual96PointLoads();
        List<String> ss = new LinkedList<String>();
        for (int i = 0; i < ls.size(); i++) {
            ss.add(ls.get(i).getDateString());
        }
//        JFreeChart chart=new LoadData2ChartUtils().loadData2Chart(ls,ss);
//        new JFreeChartFacade().saveAs(chart,"f:\\lbc\\TEST.jpg");
    }
}