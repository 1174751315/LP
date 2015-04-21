package loadPrediction.dataAccess.etc;

import db.eDbType;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOLoadData_1;
import loadPrediction.dataAccess.SuperDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/4/3.
 */
public class ActualLoadDAOFacory extends DAOFactory {

    private static Map<String, Object> pool;

    public ActualLoadDAOFacory() {
        if (pool == null) {
            pool = new HashMap<String, Object>();
            pool.put("load-data-1", new DAOLoadData_1(SuperDAO.getInstanceOf(eDbType.ACTUAL_LOAD)));
        }

    }

    @Override
    protected Object doGetDAO(String name) {
        return pool.get(name);
    }
}
