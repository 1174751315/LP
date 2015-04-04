package loadPrediction.domain.visitors;

import loadPrediction.domain.LoadData;
import loadPrediction.domain.LoadData_1;

/**
 * Created by LBC on 2015/4/3.
 */
public class LoadData2AlterObjVisitor implements LoadDataVisitor {
    @Override
    public Object visit(LoadData loadData) {
        LoadData_1 loadData_1=new LoadData_1();
        loadData_1.setDateString(loadData.getDateString());
        loadData_1.setP1(loadData.getData00());
        return loadData_1;
    }
}
