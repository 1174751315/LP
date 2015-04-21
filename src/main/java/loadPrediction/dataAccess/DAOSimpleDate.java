/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.dataAccess;

import loadPrediction.domain.SimpleDate;
import loadPrediction.exception.DAE;

/**
 * 创建：2015/1/24 9:21
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class DAOSimpleDate extends AbstractDAO {
    public DAOSimpleDate(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public void insert(SimpleDate simpleDate) {
        superDAO.insert(simpleDate);
    }

    public SimpleDate query(String key) throws DAE {
        SimpleDate o = (SimpleDate) superDAO.query(SimpleDate.class, (key));
        if (o == null) {
            throw new DAE(SimpleDate.class.getSimpleName());
        }
        return o;
    }

    public void delete(String key) {
        superDAO.delete(SimpleDate.class, key);
    }
}
