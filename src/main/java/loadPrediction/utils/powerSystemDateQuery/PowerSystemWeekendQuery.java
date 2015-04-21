/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.utils.powerSystemDateQuery;

import loadPrediction.domain.SimpleDate;
import loadPrediction.utils.DateUtil;

import java.sql.Date;

/**
 * Created by LBC on 2015/2/13.
 */
public class PowerSystemWeekendQuery extends AbstractPowerSystemDayQuery {
    public PowerSystemWeekendQuery(Date date) throws Exception {
        super(date);
    }

    public PowerSystemWeekendQuery() {
    }

    @Override
    protected Boolean doValidateBaseDate(Date date) {
        Integer day = DateUtil.getISOWeekday(date);
        return (day == 6);
    }

    @Override
    protected Boolean doGetMatchConditionOfNoneBaseDate(SimpleDate simpleDate) {
        return simpleDate.getDateType().getCode() == CODE_WEEKEND;
    }
}
