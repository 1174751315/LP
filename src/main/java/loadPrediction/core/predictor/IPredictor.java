/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor;

import  common.ElementPrintableLinkedList;
import  loadPrediction.core.predictor.visitors.IPredictorVisitor;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.exception.LPE;

import java.util.List;

/**
 * 李倍存 创建于 2015-03-21 14:48。电邮 1174751315@qq.com。
 */
public interface IPredictor {

    Object predict() throws LPE;

    ElementPrintableLinkedList<Accuracy> getAccuracy();

    String getDateString();

    List<Integer> getHistoryDaysNumbers();

    Integer getPredictionDaysNumber();

    ElementPrintableLinkedList<SimpleDate> getPredictionDays();

    ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getHistoryDays();

    ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> getSimilarDays();

    ElementPrintableLinkedList<LoadData> getPrediction96PointLoads();

    ElementPrintableLinkedList<LoadData> getActual96PointLoads();

    ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> getSimilarLoad();

    Object accept(IPredictorVisitor visitor);

    String getPredictorType();
}
