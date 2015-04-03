/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import junit.framework.TestCase;
import main.java.loadPrediction.core.predictor.IPredictor;
import main.java.loadPrediction.core.predictor.excelling.ExcellingWorkdayPredictor;
import main.java.loadPrediction.core.predictor.visitors.AllInformation2ExcelVisitor;
import org.junit.Test;

import java.sql.Date;

public class AbstractTemplateMethodExcellingPredictorTest extends TestCase {

    @Test
    public void testPredict() throws Exception {
        try {
            IPredictor predictor=new ExcellingWorkdayPredictor(Date.valueOf("2014-04-21"));
            predictor.predict();
            predictor.accept(new AllInformation2ExcelVisitor("F:\\"));
            assertEquals(7,predictor.getPredictionDays().size());

        }catch (Exception e){
            e.printStackTrace();
        }
//


    }
}