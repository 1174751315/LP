/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor;

import common.ElementPrintableLinkedList;
import common.PrintableLinkedList;
import loadPrediction.core.predictor.visitors.IPredictorVisitor;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.SimpleDate;
import loadPrediction.domain.WeatherData;
import loadPrediction.exception.LPE;

import java.sql.Date;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-21 14:48。电邮 1174751315@qq.com。
 */
public interface IPredictor {
    void setDate(Date date);

    /**
     * @return 包含预测相关信息的对象；该对象包含何种信息未有定义，由实现者决定。
     * @throws LPE 当预测过程发生数据库访问异常、文件访问异常或其它任何异常
     */
    Object predict() throws LPE;


    /**
     * 执行该函数之前必须调用predict函数。
     *
     * @return 精度链表。
     */
    PrintableLinkedList<Double> getAccuracy();


    /**
     * @return 形如“1900-01-01”格式的日期字符串。
     */
    String getDateString();


    /**
     * @return 历史日个数链表。
     */
    List<Integer> getHistoryDaysNumbers();


    /**
     * @return 预测日个数。
     */
    Integer getPredictionDaysNumber();

    /**
     * @return 预测日1D链表。
     */
    ElementPrintableLinkedList<SimpleDate> getPredictionDays();

    /**
     * @return 历史日2D链表。
     */
    ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getHistoryDays();

    /**
     * @return 相似日2D链表。
     */
    ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getSimilarDays();

    /**
     * @return 预测负荷数据链表。
     */
    ElementPrintableLinkedList<LoadData> getPrediction96PointLoads();

    /**
     * @return 实际负荷数据链表。
     */
    ElementPrintableLinkedList<LoadData> getActual96PointLoads();

    /**
     * @return 相似日负荷2D链表。
     */
    ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> getSimilarLoad();

    ElementPrintableLinkedList<WeatherData> getPredictionWeathers();


    /**
     * 访问者模式接口，用于接受一个访问者对象。
     *
     * @param visitor 访问者对象。
     * @return 包含特定信息的对象；包含何种信息由 IPredictorVisitor 接口的实现者指定。
     */
    Object accept(IPredictorVisitor visitor) throws LPE;

    /**
     * @return 表明预测器类型的说明字符串。
     */
    String getPredictorType();
}
