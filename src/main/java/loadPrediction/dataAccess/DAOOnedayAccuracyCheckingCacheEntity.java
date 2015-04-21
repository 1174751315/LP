/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import loadPrediction.core.cache.OnedayAccuracyCheckingCacheEntity;
import loadPrediction.exception.DAE;

import java.util.List;

/**
 * 李倍存 创建于 2015-03-20 20:07。电邮 1174751315@qq.com。
 */
public class DAOOnedayAccuracyCheckingCacheEntity extends AbstractDAO {

    public DAOOnedayAccuracyCheckingCacheEntity(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public void insertOrUpdate(OnedayAccuracyCheckingCacheEntity entity) {
//        if (query(entity.getDateString())!=null){
//            delete(entity.getDateString());
//        }
        superDAO.insertOrUpdate(entity);
    }

    public void delete(String dateString) {
        superDAO.delete(OnedayAccuracyCheckingCacheEntity.class, dateString);
    }

    public OnedayAccuracyCheckingCacheEntity query(String dateString) throws DAE {
        OnedayAccuracyCheckingCacheEntity o = (OnedayAccuracyCheckingCacheEntity) superDAO.query(OnedayAccuracyCheckingCacheEntity.class, dateString);
        if (o == null) {
            throw new DAE(OnedayAccuracyCheckingCacheEntity.class.getSimpleName());
        }
        return o;
    }

    public List query() {
        return superDAO.query(OnedayAccuracyCheckingCacheEntity.class);
    }
}
