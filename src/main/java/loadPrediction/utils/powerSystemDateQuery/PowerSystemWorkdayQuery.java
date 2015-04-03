/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.utils.powerSystemDateQuery;

import  loadPrediction.domain.SimpleDate;
import  loadPrediction.utils.Date2StringAdapter;

import java.sql.Date;

/**
 * Created by LBC on 2015/2/3.
 */
public class PowerSystemWorkdayQuery extends AbstractPowerSystemDayQuery {


    public PowerSystemWorkdayQuery(Date date) throws Exception {
        super(date);
    }

    public PowerSystemWorkdayQuery() {
    }

    @Override
    protected Boolean doValidateBaseDate(Date date) {
        try {
            SimpleDate sd = dao.query(Date2StringAdapter.toString(date));
            Integer code = sd.getDateType().getCode();
            return code.equals(CODE_WORKDAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected Boolean doGetMatchConditionOfNoneBaseDate(SimpleDate simpleDate) {
        return simpleDate.getDateType().getCode() == CODE_WORKDAY;
    }
}
