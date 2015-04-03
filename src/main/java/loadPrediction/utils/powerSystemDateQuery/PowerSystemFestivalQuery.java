/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.utils.powerSystemDateQuery;

import  loadPrediction.domain.SimpleDate;

import java.sql.Date;

/**
 * 李倍存 创建于 2015/3/10 20:22。电邮 1174751315@qq.com。
 */
public class PowerSystemFestivalQuery extends AbstractPowerSystemDayQuery {
    public PowerSystemFestivalQuery(Date date) throws Exception {
        super(date);
    }

    public PowerSystemFestivalQuery() {
    }

    @Override
    protected Boolean doValidateBaseDate(Date date) {
        return null;
    }

    @Override
    protected Boolean doGetMatchConditionOfNoneBaseDate(SimpleDate simpleDate) {
        return simpleDate.getDateType().getCode() == CODE_FESTIVAL;
    }
}
