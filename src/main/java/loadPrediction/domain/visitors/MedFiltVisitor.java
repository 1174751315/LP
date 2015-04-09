package loadPrediction.domain.visitors;

import common.FilterUtils;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.LoadData_1;

import java.util.List;

/**
 * Created by LBC on 2015/4/3.
 */
public class MedFiltVisitor implements LoadDataVisitor {

    private Integer method;
    public static final Integer MED=0,AVE=1;
    public MedFiltVisitor(Integer method){
        this.method=method;
    }
    @Override
    public Object visit(LoadData loadData) {

        List<Double> list=loadData.toList();
        Double[] array=new Double[96];

        List<Double> list1=null;
        if (method.equals(AVE))
            list1= FilterUtils.medFilter(list.toArray(array),3,300.);
        else
            list1= FilterUtils.filter(list.toArray(array),3);
        return loadData.accept(new List2LoadDataVisitor(list1));

    }
}
