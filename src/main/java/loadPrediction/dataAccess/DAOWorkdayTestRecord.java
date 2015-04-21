/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import loadPrediction.core.test.WorkdayTestRecord;
import loadPrediction.utils.Date2StringAdapter;

import java.sql.Date;

/**
 * 创建：2015/2/18 11:48
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class DAOWorkdayTestRecord extends AbstractDAO {
    public DAOWorkdayTestRecord(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public void insert(WorkdayTestRecord o) {
//        superDAO.insert(o.getPredictionDate1());
//        superDAO.insert(o.getPredictionDate0());
        superDAO.insert(o);
//        Transaction t=session.beginTransaction();
//        session.save(o);
//        session.save(o.getPredictionDate1());
//        t.commit();
    }

    public WorkdayTestRecord query(Date date) {
        return (WorkdayTestRecord) superDAO.query(WorkdayTestRecord.class, Date2StringAdapter.toString(date));
    }

    public void delete(Date date) {
        superDAO.delete(WorkdayTestRecord.class, date);
    }
}
