package loadPrediction.core.predictor.util;

import common.ElementPrintableLinkedList;
import loadPrediction.dataAccess.AbstractDAO;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.domain.LoadData;
import loadPrediction.exception.DAE;
import loadPrediction.exception.LPE;
import loadPrediction.log.Logging;
import loadPrediction.timerTask.LoadDataCopy;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-12 9:50。电邮 1174751315@qq.com。
 */
public class LoadsObtainer extends AbstractDataObtainer {


    public LoadsObtainer() {
    }

    public LoadsObtainer(DAOFactory generalDaoFactory, DAOFactory backupDaoFactory) {
        super(generalDaoFactory, backupDaoFactory);
    }

    public ElementPrintableLinkedList<LoadData> tryGetSomeLoads(List<String> dateStrings) throws LPE {
        Integer size=dateStrings.size();
        ElementPrintableLinkedList<LoadData> loadDatas=new ElementPrintableLinkedList<LoadData>("");
        for (int i = 0; i <size ; i++) {

            String dateString=dateStrings.get(i);
            try {
                loadDatas.add(generalDaoFactory.createDaoLoadData().query(dateString));
            }catch (DAE e){
                Logging.instance().createLogger().info("负荷数据缺失  "+dateStrings.get(i)+"。尝试重新同步负荷");
                /*处理数据缺失异常：同步负荷*/
                try {
                    loadDatas.add((LoadData) new LoadDataCopy(backupDaoFactory, generalDaoFactory).copy(dateString));
                    /*若在同步负荷的过程中又出现异常，则处理失败，抛出业务异常。*/
                } catch (DAE dae) {
                    Logging.instance().createLogger().info("同步失败");
                    throw new LPE(Date.valueOf(dateString));
                }catch (Exception e1){
                    Logging.instance().createLogger().info("同步失败");
                    throw new LPE(Date.valueOf(dateString));
                }
                Logging.instance().createLogger().info("同步成功");
            }
        }
        return loadDatas;
    }
}
