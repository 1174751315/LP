/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.dao;

import main.java.loadPrediction.core.cache.PredictionCacheEntity;
import main.java.loadPrediction.dataAccess.DAOFactory;
import org.junit.Test;

public class DAOPredictionCacheEntityTest {

    @Test
    public void testInsert() throws Exception {
        PredictionCacheEntity entity = new PredictionCacheEntity();
        DAOFactory.getDefault().createDAOPredictionCacheEntity().insertOrUpdate(entity);
        PredictionCacheEntity entity1 = DAOFactory.getDefault().createDAOPredictionCacheEntity().query("TEST");
    }
}