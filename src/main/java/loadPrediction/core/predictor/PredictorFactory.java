/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor;

import  loadPrediction.core.predictor.excelling.ExcellingQingmingPredictor;
import loadPrediction.core.predictor.excelling.ExcellingWorkdayPredictor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.dataAccess.DAOSimpleDate;
import  loadPrediction.exception.LPE;
import loadPrediction.aop.BeanFactory;
import  loadPrediction.utils.DateUtil;
import  loadPrediction.utils.PowerSystemDateUtil;

import java.sql.Date;
/**
 * 李倍存 创建于 2015-02-25 13:33。电邮 1174751315@qq.com。
 */
public class PredictorFactory {
    private static PredictorFactory instance = new PredictorFactory();
private DAOSimpleDate daoSimpleDate;
private PowerSystemDateUtil powerSystemDateUtil;

    public void setPowerSystemDateUtil(PowerSystemDateUtil powerSystemDateUtil) {
        this.powerSystemDateUtil = powerSystemDateUtil;
    }

    public void setDaoSimpleDate(DAOSimpleDate daoSimpleDate) {
        this.daoSimpleDate = daoSimpleDate;
    }

    public static PredictorFactory getInstance() {
        return instance;
    }

    private PredictorFactory() {
    }

    /**
     * @param date 预测基准日。
     * @return 对应于date的合适的预测器。首选Excelling预测器。
     * @throws Exception 当在获取预测器示例的过程中发生任何其它异常
     */
    public IPredictor getProperPredictor(Date date) throws Exception {
//        DAOSimpleDate dao=DAOFactory.getDefault().createDaoSimpleDate();
        if (daoSimpleDate==null)
            daoSimpleDate=DAOFactory.getDefault().createDaoSimpleDate();
        if (powerSystemDateUtil==null)
            powerSystemDateUtil=new PowerSystemDateUtil();
        String ds=date.toLocalDate().toString();
        if (DateUtil.getISOWeekday(date) == 6 &&daoSimpleDate.query(ds).getDateType().getCode()==1){
//            IPredictor predictor=(IPredictor) BeanFactory.INSTANCE.getBean("excellingWeekendPredictor");
            IPredictor predictor=new ExcellingWorkdayPredictor();
            predictor.setDate(date);
            return predictor;
        }

        if (DateUtil.getISOWeekday(date) == 7 && daoSimpleDate.query(ds).getDateType().getCode()==1)//周日且非节假日
            throw new LPE("请不要选择周日作为第一预测日。\n若要进行工作日预测，请选择周一至周五中的某一天；\n若要进行周末预测，请选择周六。");
        if (daoSimpleDate.query(ds).getDateType().getCode()==0){
            if (powerSystemDateUtil.isPowerSystemWorkday(date) ){
                IPredictor predictor= (IPredictor) BeanFactory.INSTANCE.getBean("excellingWorkdayPredictor");
                predictor.setDate(date);
                return predictor;
             }
        }
        if(daoSimpleDate.query(date.toLocalDate().toString()).getDateType().getCode().equals(4))
            return new ExcellingQingmingPredictor(date);
        throw new LPE("对不起，尚未实现所选日期 【 "+ds+" 】对应的预测算法。请联系开发者。" );
//        throw new Exception("未能获取合适的预测器，因此预测算法不能启动。\n" +
//                "原因可能是提供了非法的参数或对应预测器尚未实现。");
    }
    /**
     * @param date 形如“1900-01-01”格式的预测基准日字符串。
     * @return 对应于date的合适的预测器。首选Excelling预测器。
     * @throws Exception 当在获取预测器示例的过程中发生任何其它异常
     */
    public IPredictor getProperPredictor(String date) throws Exception {
        return getProperPredictor(Date.valueOf(date));
    }
    }
