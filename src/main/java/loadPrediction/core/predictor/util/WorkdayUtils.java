/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.util;

import  common.ConvertUtils;
import  common.ElementPrintableLinkedList;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.exception.LPE;
import  loadPrediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-22 10:51。电邮 1174751315@qq.com。
 */
public class WorkdayUtils {

    public static ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>
    getHistoryDays(Date date, List<Integer> numbers) throws LPE {
        PowerSystemWorkdayQuery dq = null;
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("unnamed");
        try {
            dq = new PowerSystemWorkdayQuery(date);
            history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("historyDays");
            ElementPrintableLinkedList<SimpleDate> list = ConvertUtils.toElementPrintableLinkedList(dq.list(numbers.get(0) + 1, numbers.get(0) + 1), "workday");
            list.removeLast();
            history.add(list);
        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
        return history;
    }

    public static ElementPrintableLinkedList<SimpleDate>
    getPredictionDays(Date date, Integer number) throws LPE {
        try {
            List<SimpleDate> list = new PowerSystemWorkdayQuery(date).list(1, number);
            if (list.size() != number)
                throw new LPE("");
            return ConvertUtils.toElementPrintableLinkedList(list, "prediction Days");
        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
    }
}
