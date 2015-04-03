/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.cache;

import  loadPrediction.dataAccess.DAOFactory;
import  loadPrediction.exception.LPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 李倍存 创建于 2015/3/18 23:58。电邮 1174751315@qq.com。
 */
public class CachesManager {
    private CachesManager() {
        this.loadCaches();
    }

    private static CachesManager instance = new CachesManager();

    public static CachesManager instance() {
        return instance;
    }

    private Map<String, PredictionCacheEntity> predictionCaches;
    private Map<String, OnedayAccuracyCheckingCacheEntity> accuracyCaches;

    private void loadCaches() {
        predictionCaches = new HashMap<String, PredictionCacheEntity>();
        accuracyCaches = new HashMap<String, OnedayAccuracyCheckingCacheEntity>();

        List p = DAOFactory.getDefault().createDAOPredictionCacheEntity().query();
        List a = DAOFactory.getDefault().createDAOOnedayAccuracyCheckingCacheEntity().query();

        for (int i = 0; i < p.size(); i++) {
            PredictionCacheEntity entity = ((PredictionCacheEntity) p.get(i));
            predictionCaches.put(entity.getDateString(), entity);
        }
        for (int i = 0; i < a.size(); i++) {
            OnedayAccuracyCheckingCacheEntity entity = (OnedayAccuracyCheckingCacheEntity) a.get(i);
            accuracyCaches.put(entity.getDateString(), entity);
        }
    }

    private void storeCaches(Map<String, PredictionCacheEntity> caches) {

    }

    public void addPredictionEntity(PredictionCacheEntity entity) throws LPE {
        if (!entity.validate()) {
            throw new LPE("未完整设置缓存实体对象。", LPE.eScope.DEVELOPER);
        }
        if (!entity.equals(predictionCaches.get(entity.getDateString()))) {
            predictionCaches.put(entity.getDateString(), entity);
            DAOFactory.getDefault().createDAOPredictionCacheEntity().insertOrUpdate(entity);
        }
    }

    public void addAccuracyEntity(OnedayAccuracyCheckingCacheEntity entity) throws LPE {
        if (false) {
            throw new LPE("未完整设置缓存实体对象。", LPE.eScope.DEVELOPER);
        }
        if (!entity.equals(accuracyCaches.get(entity.getDateString()))) {
            accuracyCaches.put(entity.getDateString(), entity);
            DAOFactory.getDefault().createDAOOnedayAccuracyCheckingCacheEntity().insertOrUpdate(entity);
        }

    }

    public void deletePredictionEntity(String dateString) {
        predictionCaches.remove(dateString);
    }

    public void deleteAccuracyEntity(String dateString) {
        accuracyCaches.remove(dateString);
    }

    public Boolean hasPredictionCache(String dateString) {
        return predictionCaches.get(dateString) != null;
    }

    public Boolean hasAccuracyCache(String dateString) {
        return accuracyCaches.get(dateString) != null;
    }

    public PredictionCacheEntity getPredictionEntity(String dateString) {
        return predictionCaches.get(dateString);
    }

    public OnedayAccuracyCheckingCacheEntity getAccuracyEntity(String dateString) {
        return accuracyCaches.get(dateString);
    }

    public void clearCaches() {
        predictionCaches.clear();
    }

    public void rebuildCaches() {
        PredictionCacheEntity[] entities = (PredictionCacheEntity[]) predictionCaches.values().toArray();
        for (int i = 0; i < entities.length; i++) {
            if (!checkEntityCacheFiles(entities[i]))
                flushEntityCacheFiles(entities[i]);
        }
    }

    private Boolean checkEntityCacheFiles(PredictionCacheEntity entity) {
        return true;
    }

    private void flushEntityCacheFiles(PredictionCacheEntity entity) {

    }

}
