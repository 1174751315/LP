/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.test;

import common.IEnhancedCommand;
import prediction.dataAccess.DAOFactory;
import prediction.dataAccess.DAOWorkdayTestRecord;

/**
 * 李倍存 创建于 2015-02-22 22:10。电邮 1174751315@qq.com。
 */
public class SaveWorkdayTestRecordCommand implements IEnhancedCommand {
    protected static DAOWorkdayTestRecord daoWorkdayTestRecord = DAOFactory.getDefault().createDaoWorkdayTestRecord();
    private WorkdayTestRecord testRecord;

    public SaveWorkdayTestRecordCommand(WorkdayTestRecord test) {
        testRecord = test;
    }

    /**
     * 保存一条工作日负荷预测测试记录至指定的数据库。
     *
     * @return 测试记录的错误提示信息。
     */
    @Override
    public Object execute() throws Exception {
        daoWorkdayTestRecord.insert(this.testRecord);
        return this.testRecord.err();
    }
}
