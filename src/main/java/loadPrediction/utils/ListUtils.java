package loadPrediction.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-11 21:34。电邮 1174751315@qq.com。
 */
public class ListUtils {
    public  static  <T> List<T> unnamed(T t1, T t2) {
        List<T> list = new LinkedList<T>();
        list.add(t1);
        list.add(t2);
        return list;
    }
}
