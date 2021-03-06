/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.dataAccess;

import prediction.core.test.WeekendTestRecord;

import java.sql.Date;

/**
 * 李倍存 创建于 2015-02-20 15:16。电邮 1174751315@qq.com。
 */
public class DAOWeekendTestRecord extends AbstractDAO {
    public DAOWeekendTestRecord(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public void insert(WeekendTestRecord o) {
        superDAO.insert(o);
    }

    public WeekendTestRecord query(Date date) {
        return (WeekendTestRecord) superDAO.query(WeekendTestRecord.class, date);
    }
}
