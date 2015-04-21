/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.utils;

import prediction.dataAccess.DAOFactory;

import java.sql.Date;

/**
 * 创建：2015/2/12 20:43
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class PowerSystemDateUtil {
    public boolean isPowerSystemWorkday(Date date) {
        try {
            return DAOFactory.getDefault().createDaoSimpleDate().query(Date2StringAdapter.toString(date)).getDateType().getCode() == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPowerSystemNoneWorkday(Date date) {
        return !isPowerSystemWorkday(date);
    }

    public boolean isPowerSystemWorkday(String dateString) {
        return isPowerSystemWorkday(Date.valueOf(dateString));
    }

    public boolean isPowerSystemNoneWorkday(String dateString) {
        return isPowerSystemNoneWorkday(Date.valueOf(dateString));
    }

}
