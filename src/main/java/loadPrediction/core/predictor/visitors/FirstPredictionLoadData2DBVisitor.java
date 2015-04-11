/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.domain.PredictionLoadData;
import loadPrediction.exception.LPE;

/**
 * 李倍存 创建于 2015-03-24 21:45。电邮 1174751315@qq.com。
 */
public class FirstPredictionLoadData2DBVisitor extends AbstractPredictionAccessDBVisitor {
    @Override
    protected Object doAccessDB(IPredictor predictor) throws LPE{
        PredictionLoadData loadData = null;
        try {
            loadData = predictor.getPrediction96PointLoads().get(0).convertLower();
            DAOFactory.getDefault().createDaoPredictionLoadData().insertOrUpdate(loadData);
        } catch (Exception e) {
            throw new LPE("在将预测负荷存入数据库时发生异常");
        }
        return loadData;
    }
}
