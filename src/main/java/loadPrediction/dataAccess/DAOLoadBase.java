/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import db.eDbType;
import loadPrediction.domain.LoadBase;
import loadPrediction.exception.DAE;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 创建：2015/2/4 21:02
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class DAOLoadBase extends AbstractDAO {
    public DAOLoadBase(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public LoadBase query(Integer year, Integer month) throws DAE {
        Session defaultSession = SuperDAO.getInstanceOf(eDbType.ACCESS).getSessionFactory().openSession();

        Transaction t = defaultSession.beginTransaction();

        String ql = "from LoadBase where year=? and month=?";
        Query sq = defaultSession.createQuery(ql);
        sq.setParameter(0, year);
        sq.setParameter(1, month);
        t.commit();
//        session.close();

        LoadBase o = (LoadBase) sq.uniqueResult();
        if (o == null) {
            throw new DAE(DAE.eType.NOT_FOUND);
        }
        return o;

    }

}
