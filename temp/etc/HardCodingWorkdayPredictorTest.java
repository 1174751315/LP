/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.core.predictor.IPredictor;
import main.java.loadPrediction.core.predictor.hardCoding.HardCodingWeekendPredictor;
import main.java.loadPrediction.core.predictor.hardCoding.HardCodingWorkdayPredictor;
import main.java.loadPrediction.core.predictor.visitors.Accuracy2DBVisitor;
import main.java.loadPrediction.core.predictor.visitors.AllInformation2ExcelVisitor;

import main.java.loadPrediction.core.predictor.visitors.PredictionLoad2PictureVisitor;

import main.java.loadPrediction.core.predictor.visitors.PredictionLoad23LinePictureVisitor;

import main.java.loadPrediction.domain.SimpleDate;
import main.java.loadPrediction.resouce.IOPaths;
import main.java.loadPrediction.utils.PowerSystemDateUtil;
import main.java.loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class HardCodingWorkdayPredictorTest {
    public HardCodingWorkdayPredictorTest() {
    }
//    public WorkdayPredictorTest(String beginDate,Integer number){
//        this.date=Date.valueOf(beginDate);
//        this.number=number;
//    }

    private Date date;
    private Integer number;

    @Test
    public void testPredict() throws Exception {

//        Session session= AccessSessionFactoryFactory.getInstance().getSessionFactory().openSession();
//        SimpleDateType sdt=(SimpleDateType)session.get(SimpleDateType.class,0);
//        session.close();
//
//
//        session= OracleSessionFactoryFactory.getInstance().getSessionFactory().openSession();
//        sdt=(SimpleDateType)session.get(SimpleDateType.class,0);
//        session.close();


//        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("D:\\系统文档\\Documents\\IdeaProjects\\TestStruts2\\src\\main.java.loadPrediction\\core\\workday\\workdayPredictionHelper.xml"));
//
//        WorkdayPredictor h=(WorkdayPredictor)beanFactory.getBean("workdayHelper");
//        h.predict();
        try {

            if (date == null) {
                date = Date.valueOf("2014-02-07");
                number = 10;
            }


            PowerSystemWorkdayQuery dq = null;
            try {
                dq = new PowerSystemWorkdayQuery(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<SimpleDate> dates = dq.list(1, this.number);
            System.out.println("即将预测天数  [  " + dates.size() + "  ]");
            System.out.println("即将预测天数  [  " + dates.size() + "  ]");
            int count = 0;

            for (int i = 0; i < dates.size(); i++) {
                if (dates.get(i).getDateType().getCode() != 0)
                    System.exit(-1);
            }
            try {
                for (int i = 0; i < dates.size(); i++) {


                    IPredictor helper =
                            PowerSystemDateUtil.isPowerSystemWorkday(dates.get(i).getDate()) ?
                                    new HardCodingWorkdayPredictor(dates.get(i).getDate(), true) :
                                    new HardCodingWeekendPredictor(dates.get(i).getDate());

                    helper.predict();
                    helper.accept(new Accuracy2DBVisitor());
                    helper.accept(new AllInformation2ExcelVisitor(IOPaths.WEB_CONTENT_TEMP));
//          helper.accept(new PredictionLoad2DBVisitor(new OracleDAOFactory()));
                    helper.accept(new PredictionLoad2PictureVisitor(IOPaths.WEB_CONTENT_TEMP));
                    helper.accept(new PredictionLoad23LinePictureVisitor(IOPaths.WEB_CONTENT_TEMP));
                    String s = String.format("%2.2f", (Double) (i + 1.0) / dates.size() * 100);
                    String s2 = String.format("%2.2f", (Double) (count + 0.) / dates.size() * 100);
//                System.out.close();
                    System.out.flush();
                    System.out.println("完成  工作日  预测基准日  [  " + dates.get(i).getDateString() + "  ]  [  " + s + "%  ]  [  " + s2 + "%  异常]  ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}