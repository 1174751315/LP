/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess.access;

import  db.eDbType;
import  loadPrediction.dataAccess.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 李倍存 创建于 2015-03-25 21:44。电邮 1174751315@qq.com。
 */
public class AccessDAOFactory_Backup extends DAOFactory {
    protected static Map<String, Object> pool;
    public AccessDAOFactory_Backup() {
        if (pool == null) {
            pool = new HashMap<String, Object>();
            pool.put("loaddata", new DAOLoadData(SuperDAO.getInstanceOf(eDbType.BACKUP)));
            pool.put("weatherdata", new DAOWeatherData(SuperDAO.getInstanceOf(eDbType.BACKUP)));
        }
    }

    @Override
    protected Object doGetDAO(String name) {
        return pool.get(name);
    }
}
