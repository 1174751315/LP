/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.dataAccess;

import loadPrediction.domain.LoadData_1;
import loadPrediction.exception.DAE;

/**
 * 创建：2015/2/3 9:21
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class DAOLoadData_1 extends AbstractDAO {

    public DAOLoadData_1(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public LoadData_1 query(String key) throws DAE {
        LoadData_1 o = (LoadData_1) superDAO.query(LoadData_1.class, (key));
        if (o == null) {
            throw new DAE("未找到相应的负荷数据");
        }
        return o;
    }

    public void insertOrUpdate(LoadData_1 o) {
        if (superDAO.query(LoadData_1.class, o.getDateString()) != null) {
            this.delete(o.getDateString());
        }
        superDAO.insert(o);
    }

    public void delete(String key) {
        superDAO.delete(LoadData_1.class, key);
    }
}
