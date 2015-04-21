package prediction.core.cache;

import prediction.dataAccess.DAOFactory;
import prediction.exception.LPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 李倍存 创建于 2015-04-08 17:21。电邮 1174751315@qq.com。
 */
public class CachesManager {

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

    private CachesManager() {
        this.loadCaches();
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


    public static CachesManager INSTANCE = new CachesManager();
}
