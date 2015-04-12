package loadPrediction.core.predictor.util;

import loadPrediction.dataAccess.DAOFactory;

/**
 * 李倍存 创建于 2015-04-12 9:53。电邮 1174751315@qq.com。
 */
public abstract class AbstractDataObtainer {
    public AbstractDataObtainer() {
    }

    protected DAOFactory generalDaoFactory,backupDaoFactory;

    public AbstractDataObtainer(DAOFactory generalDaoFactory, DAOFactory backupDaoFactory) {
        this.generalDaoFactory = generalDaoFactory;
        this.backupDaoFactory = backupDaoFactory;
    }

    public DAOFactory getGeneralDaoFactory() {

        return generalDaoFactory;
    }

    public void setGeneralDaoFactory(DAOFactory generalDaoFactory) {
        this.generalDaoFactory = generalDaoFactory;
    }

    public DAOFactory getBackupDaoFactory() {
        return backupDaoFactory;
    }

    public void setBackupDaoFactory(DAOFactory backupDaoFactory) {
        this.backupDaoFactory = backupDaoFactory;
    }
}
