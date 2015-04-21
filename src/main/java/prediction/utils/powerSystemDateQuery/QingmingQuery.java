/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.utils.powerSystemDateQuery;

import prediction.dataAccess.DAOFactory;
import prediction.domain.SimpleDate;
import prediction.exception.DAE;

import java.sql.Date;

/**
 * 李倍存 创建于 2015-03-26 22:15。电邮 1174751315@qq.com。
 */
public class QingmingQuery extends AbstractPowerSystemDayQuery {
    public QingmingQuery(Date date) throws Exception {
        super(date);
    }

    @Override
    protected Boolean doValidateBaseDate(Date date) {
        try {
            return DAOFactory.getDefault().createDaoSimpleDate().query(date.toLocalDate().toString()).getDateType().getCode().equals(CODE_QINGMING);
        } catch (DAE dae) {
            return false;
        }
    }

    @Override
    protected Boolean doGetMatchConditionOfNoneBaseDate(SimpleDate simpleDate) {
        return simpleDate.getDateType().getCode().equals(CODE_QINGMING);
    }
}
