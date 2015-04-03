/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import  loadPrediction.domain.PredictionLoadData;
import  loadPrediction.exception.DAE;

/**
 * 李倍存 创建于 2015-03-02 21:45。电邮 1174751315@qq.com。
 */
public class DAOPredictionLoadData extends AbstractDAO {

    public DAOPredictionLoadData(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public PredictionLoadData query(String key) throws DAE {
        PredictionLoadData o = (PredictionLoadData) superDAO.query(PredictionLoadData.class, (key));
        if (o == null)
            throw new DAE(PredictionLoadData.class.getSimpleName());
        return o;
    }

    public void insertOrUpdate(PredictionLoadData o) throws Exception {
        if (superDAO.query(PredictionLoadData.class, o.getDateString()) != null) {
            superDAO.delete(PredictionLoadData.class, o.getDateString());
        }
        superDAO.insert(o);
    }

    public void delete(String key) throws Exception {
        superDAO.delete(PredictionLoadData.class, key);
    }
}
