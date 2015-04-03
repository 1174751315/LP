/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package test.java.etc;

import main.java.loadPrediction.core.predictor.IPredictor;
import main.java.loadPrediction.core.predictor.hardCoding.HardCodingWeekendPredictor;
import main.java.loadPrediction.core.predictor.visitors.AllInformation2ExcelVisitor;
import main.java.loadPrediction.resouce.IOPaths;
import org.junit.Test;

import java.sql.Date;

public class HardCodingWeekendPredictorTest {

    @Test
    public void testPredict() throws Exception {
        Date date = Date.valueOf("2014-06-14");
        IPredictor predictor = new HardCodingWeekendPredictor(date);
        predictor.predict();
        predictor.accept(new AllInformation2ExcelVisitor(IOPaths.WEB_CONTENT_TEMP));
    }
}