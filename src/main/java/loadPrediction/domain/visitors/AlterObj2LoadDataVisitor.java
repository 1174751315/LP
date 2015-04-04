package loadPrediction.domain.visitors;

import loadPrediction.domain.LoadData;
import loadPrediction.domain.LoadData_1;

/**
 * Created by LBC on 2015/4/3.
 */
public class AlterObj2LoadDataVisitor implements LoadDataVisitor {
    public AlterObj2LoadDataVisitor(LoadData_1 loadData_1){
        this.loadData_1=loadData_1;
    }
    private LoadData_1 loadData_1;
    @Override
    public Object visit(LoadData loadData) {
        loadData.setDateString(loadData_1.getDateString());
        return loadData;
    }
}
