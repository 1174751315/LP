/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.core.predictor.PredictorFactory;
import org.junit.Test;

public class PredictorFactoryTest {

    @Test
    public void testGetProperPredictor() throws Exception {
//             for (int i = 0;true; i++) {
//            System.err.printf("请输入一个日期（示例：2010-01-01）以进行一次预测。若要退出请输入\"exit\"。\n  ");
//            String inputString;
//            byte[] inputBytes=new byte[10];
//            System.in.read(inputBytes);
//            inputString=new String(inputBytes);
//            if (inputString.equals("exit"))
//                break;
//            PredictorFactory.getInstance().getProperPredictor(Date.valueOf(inputString)).predict();
//        }

//        User user1=new User("access","access");
//        DAOUser daoUserAccess= AccessDAOFactory.getInstance().createDaoUser();daoUserAccess.insert(user1);
//
//        User user2=new User("oracle","oracle");
//        DAOUser daoUserOracle= OracleDAOFactory.getInstance().createDaoUser();daoUserOracle.insert(user2);


//        SimpleDateWeatherType wt1=new SimpleDateWeatherType();wt1.setCode(5);wt1.setName("access");
//        SimpleDateType dt1=new SimpleDateType();dt1.setCode(5);dt1.setName("access");
//        SimpleDate sd1=new SimpleDate();sd1.setDate(Date.valueOf("2020-01-01"));
//        sd1.setDateType(dt1);sd1.setWeatherType(wt1);
//        DAOSimpleDate daoSimpleDateAccess=AccessDAOFactory.getInstance().createDaoSimpleDate();
//        daoSimpleDateAccess.insert(sd1);
//
//        SimpleDateWeatherType wt2 = new SimpleDateWeatherType();
//        wt2.setCode(5);
//        wt2.setName("oracle");
//        SimpleDateType dt2 = new SimpleDateType();
//        dt2.setCode(5);
//        dt2.setName("oracle");
//        SimpleDate sd2 = new SimpleDate();
//        sd2.setDateString(("2020-01-02"));
//        sd2.setDateType(dt2);
//        sd2.setWeatherType(wt2);
//        DAOSimpleDate daoSimpleDateOracle = DAOFactory.getDefault().createDaoSimpleDate();

        PredictorFactory.getInstance().getProperPredictor("2014-04-05").predict();


    }
}