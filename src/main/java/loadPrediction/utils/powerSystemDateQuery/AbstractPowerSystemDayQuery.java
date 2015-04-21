/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.utils.powerSystemDateQuery;

import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOSimpleDate;
import loadPrediction.domain.SimpleDate;
import loadPrediction.utils.Date2StringAdapter;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建：2015/2/13 20:42
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public abstract class AbstractPowerSystemDayQuery {
    protected static final Integer CODE_WORKDAY = 0;
    protected static final Integer CODE_WEEKEND = 1;
    protected static final Integer CODE_FESTIVAL = 2;
    protected static final Integer CODE_SPRING_FESTIVAL = 3;
    protected static final Integer CODE_QINGMING = 4;

    public AbstractPowerSystemDayQuery(Date date) throws Exception {
        this.setDate(date);
    }

    public AbstractPowerSystemDayQuery() {
    }

    private SimpleDate currentDay;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws Exception {
        if (!doValidateBaseDate(date))
            throw new Exception("AbstractPowerSystemDayQuery中的doValidate函数未返回true。");
        this.date = date;
        try {
            this.currentDay = dao.query(Date2StringAdapter.toString(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Date date;
    protected DAOSimpleDate dao = DAOFactory.getDefault().createDaoSimpleDate();
    ;

    public List<SimpleDate> list(Integer from, Integer count) throws Exception {
        if (date == null)
            throw new Exception("请预先调用setDate或者在构造函数中传入Date，并确保date是有效的值。");
        if (currentDay == null)
            throw new Exception("请检查数据库【日期】表数据是否完整。");
//        if (from>count)
//            throw new Exception("请确保参数from小于或等于count。");

        List<SimpleDate> list = new LinkedList<SimpleDate>();
        for (int i = 0; i < count; i++) {
            list.add(null);
        }
        Date d = new Date(date.getTime());
        try {
            for (int i = 2; i <= from; ) {
                d.setTime(d.getTime() - 86400000);
                SimpleDate sd = dao.query(Date2StringAdapter.toString(new Date(d.getTime())));
                if (this.doGetMatchConditionOfNoneBaseDate(sd)) {
                    list.set(from - i, sd);
                    i++;
                }
            }
            if (from > list.size())
                return list;
            list.set(from - 1, currentDay);
            d = new Date(date.getTime());
            for (int i = 0; i <= count - from - 1; ) {
                d.setTime(d.getTime() + 86400000);
                SimpleDate sd = dao.query(Date2StringAdapter.toString(new Date(d.getTime())));
                if (this.doGetMatchConditionOfNoneBaseDate(sd)) {
                    list.set(from + i, sd);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    protected abstract Boolean doValidateBaseDate(Date date);

    protected abstract Boolean doGetMatchConditionOfNoneBaseDate(SimpleDate simpleDate);


//    protected SimpleDate query(eType t,Integer after){
//        try {
//            SimpleDate sd=date;
//            Integer begin,end;
//            if (after==0) return null;
//            if (after>0){
//                begin=0;end=after;
//            }else{
//                begin=after;end=0;
//            }
//            for (int i = begin; i<=end;) {
//                Date newDate=new Date(sd.getDate().getTime()+(after>0?86400000:-86400000));
//                sd=dao.query(newDate,"");
//                String type=new String(sd.getDateType().getName());
//                boolean condition=false;
//                if (t==eType.NoneWorkday){
//                    condition=!type.equals("工作日");
//                }
//                else if(t==eType.Workday){
//                    condition=type.equals("工作日");
//                }
//                if(condition)
//                    i++;
//            }
//            return sd;
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }return null;
//    }
}
