/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.util;

import common.ConvertUtils;
import common.ElementPrintableLinkedList;
import loadPrediction.domain.SimpleDate;
import loadPrediction.exception.LPE;
import loadPrediction.utils.DateUtil;
import loadPrediction.utils.powerSystemDateQuery.PowerSystemWeekendQuery;
import loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-24 9:17。电邮 1174751315@qq.com。
 */
public class WeekendUtils {

    public static ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays(Date date, List<Integer> numbers) throws LPE {

        if (numbers.size() != 2)
            return null;

        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("historyDays of " + date.toLocalDate().toString());
        try {
            Date thursday = DateUtil.getDateBefore(date, 2);
            PowerSystemWorkdayQuery wq1 = new PowerSystemWorkdayQuery(thursday);
            ElementPrintableLinkedList<SimpleDate> workdays = ConvertUtils.toElementPrintableLinkedList(wq1.list(numbers.get(0), numbers.get(0)), "workdays");
            historyDays.add(workdays);

            PowerSystemWeekendQuery wq2 = new PowerSystemWeekendQuery(date);
            ElementPrintableLinkedList<SimpleDate> weekends = ConvertUtils.toElementPrintableLinkedList(wq2.list(numbers.get(1) + 1, numbers.get(1)), "weekends");
            historyDays.add(weekends);
        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }

        return historyDays;
    }

    public static ElementPrintableLinkedList<SimpleDate>
    doGetPredictionDays(Date date, Integer number) throws LPE {
        try {
            PowerSystemWeekendQuery wq = new PowerSystemWeekendQuery(date);
            return ConvertUtils.toElementPrintableLinkedList(wq.list(1, 2), "predictionDays of " + date.toLocalDate().toString());

        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
    }
}
