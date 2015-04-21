/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.timerTask;

import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOLoadData;
import loadPrediction.dataAccess.DAOLoadData_1;
import loadPrediction.domain.LoadData;
import loadPrediction.domain.LoadData_1;
import loadPrediction.domain.visitors.AlterObj2LoadDataVisitor;
import loadPrediction.exception.DAE;

/**
 * 李倍存 创建于 2015-03-02 22:44。电邮 1174751315@qq.com。
 */
public class ActualLoadDataMove implements IDataCopy {
    @Override
    public Object copy(Object dateString) throws DAE {
        DAOLoadData daoLoadData = DAOFactory.getDefault().createDaoLoadData();
        DAOLoadData_1 daoLoadData_1 = DAOFactory.get4ActualLoad().createDAOLoadData_1();

        LoadData_1 loadData_1 = daoLoadData_1.query((String) dateString);
        LoadData loadData = new LoadData();
        daoLoadData.insertOrUpdate((LoadData) loadData.accept(new AlterObj2LoadDataVisitor(loadData_1)));
        return loadData;
    }
}
