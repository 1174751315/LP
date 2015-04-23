/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.core.noneWorkday;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import prediction.domain.SimpleDate;
import prediction.exception.LPE;

/**
 * Created by LBC on 2015/2/14.
 */
public class SimilarDayFinder {
    private static SimilarDayFinder ourInstance = new SimilarDayFinder();

    public static SimilarDayFinder getInstance() {
        return ourInstance;
    }

    private SimilarDayFinder() {
    }

    /**
     * 给定基准日期、候选日期列表和相似度列表，在候选日期列表中选出基准日期的相似日。
     *
     * @param date           基准日期，查找date的相似日。
     * @param daysToFindFrom 候选日期列表，该列表的日期必须随索引从小到大从较早到较晚的日期。
     * @param similarCoes    相似度列表，分别是各候选日期与基准日期的相似度，该列表必须与候选日期列表一一对应。
     * @throws Exception 当列表daysToFindFrom的元素数目小于similarCoes的元素数目时，抛出异常。
     */
    public SimpleDate getSimilarDay(SimpleDate date, ElementPrintableLinkedList<SimpleDate> daysToFindFrom, PrintableLinkedList<Double> similarCoes) throws Exception {
        Integer sizeDays = daysToFindFrom.size();
        Integer sizeCoes = similarCoes.size();
        if (sizeDays < sizeCoes) {
            throw new LPE("候选日期个数必须大于或等于相似度个数。");
        }

        Double min = similarCoes.get(0);
        Integer index = 0;
        Double v = 0.;

        for (int i = 0; i < sizeCoes; i++) {
            v = similarCoes.get(i);
            if (min > v) {
                min = v;
                index = i;
            }
        }
        return daysToFindFrom.get(sizeDays - index - 1);
    }
}
