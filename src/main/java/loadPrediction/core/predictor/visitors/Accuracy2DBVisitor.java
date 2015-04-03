/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.domain.LoadData;
import  loadPrediction.utils.Date2StringAdapter;

import java.util.List;

/**
 * 李倍存 创建于 2015-03-05 20:37。电邮 1174751315@qq.com。
 */
public class Accuracy2DBVisitor implements IPredictorVisitor {
    private DAOFactory factory;

    public Accuracy2DBVisitor(DAOFactory factory) {
        this.factory = factory;
    }

    public Accuracy2DBVisitor() {
        this(DAOFactory.getDefault());
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) {
                        /*若【日负荷曲线】表中已有【预测日1】对应的数据，直接求得准确度，并存入【预测准确度】表。*/
        return this.visit(predictor);

    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) {

        return this.visit(predictor);
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) {
        return null;
    }

    private Object visit(IPredictor predictor) {
        try {
            LoadData ld = factory.createDaoLoadData().query(predictor.getPredictionDays().get(0).getDateString());
            if (ld != null) {
                Accuracy accuracy = new Accuracy();
                accuracy.setAccuracy(this.calcOneAccuracy(ld, predictor.getPrediction96PointLoads().get(0)));
                accuracy.setDateString(Date2StringAdapter.toString(predictor.getPredictionDays().get(0).getDate()));
                factory.createDAOAccuracy().insertOrUpdate(accuracy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Double calcOneAccuracy(LoadData actualLoadData, LoadData predictionLoadData) {
        List<Double> actual = actualLoadData.toList();
        List<Double> prediction = predictionLoadData.toList();
        Double acc = 0.0;
        for (int j = 0; j < 96; j++) {
            acc += Math.pow((actual.get(j) - prediction.get(j)) / actual.get(j), 2);
        }
        acc /= 96.;
        acc = Math.sqrt(acc);
        acc = 1 - acc;
        return acc;
    }
}
