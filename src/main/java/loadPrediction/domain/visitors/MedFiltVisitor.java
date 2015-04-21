package loadPrediction.domain.visitors;

import common.FilterUtils;
import loadPrediction.domain.LoadData;

import java.util.List;

/**
 * Created by LBC on 2015/4/3.
 */
public class MedFiltVisitor implements LoadDataVisitor {

    private Integer method;
    private Integer wnd;
    public static final Integer MED = 0, AVE = 1;

    public MedFiltVisitor(Integer method, Integer wnd) {
        this.method = method;
        this.wnd = wnd;
    }

    public MedFiltVisitor(Integer method) {
        this(method, 3);
    }

    @Override
    public Object visit(LoadData loadData) {

        List<Double> list = loadData.toList();
        Double[] array = new Double[96];

        List<Double> list1 = null;
        if (method.equals(AVE))
            list1 = FilterUtils.medFilter(list.toArray(array), wnd, 300.);
        else
            list1 = FilterUtils.filter(list.toArray(array), wnd);
        return loadData.accept(new List2LoadDataVisitor(list1));

    }
}
