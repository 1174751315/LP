/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.dataAccess;

import prediction.domain.Accuracy;
import prediction.exception.DAE;
import prediction.utils.Date2StringAdapter;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-02 22:19。电邮 1174751315@qq.com。
 */
public class DAOAccuracy extends AbstractDAO {
    public DAOAccuracy(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public Accuracy query(Date key) throws DAE {
        Accuracy o = (Accuracy) superDAO.query(Accuracy.class, Date2StringAdapter.toString(key));
        if (o == null) {
            throw new DAE(DAE.eType.NOT_FOUND);
        }
        return o;
    }

    public List query() throws DAE {
        return superDAO.query(Accuracy.class);
    }

    public Void insertOrUpdate(Accuracy o) throws DAE {
        if (superDAO.query(Accuracy.class, o.getDateString()) != null) {
            superDAO.delete(Accuracy.class, o.getDateString());
        }
        superDAO.insert(o);
        return null;
    }

    public void delete(Date key) throws DAE {
        superDAO.delete(DAOAccuracy.class, key);
    }

    public void delete() throws DAE {
        superDAO.delete(Accuracy.class);
    }
}
