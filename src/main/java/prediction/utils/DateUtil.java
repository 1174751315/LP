/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.utils;

import java.sql.Date;

/**
 * 李倍存 创建于 2015/2/19 20:20。电邮 1174751315@qq.com。
 */
public class DateUtil {
    public static Date getDateBefore(Date date, Integer before) {
        if (before == 0)
            return new Date(date.getTime());
        Date d = new Date(date.getTime());
        for (int i = 0; i < before; i++) {
            d.setTime(d.getTime() - 86400000);
        }
        return d;
    }

    public static Date getDateAfter(Date date, Integer after) {
        if (after == 0)
            return new Date(date.getTime());
        return null;
    }

    public static Integer getISOWeekday(Date date) {
        return date.toLocalDate().getDayOfWeek().getValue();
    }
}
