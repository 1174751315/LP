/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.dataAccess;

import prediction.adm.user.User;

/**
 * 创建：2015/2/19 9:43
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class DAOUser extends AbstractDAO {
    public DAOUser(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public void insert(User user) throws Exception {
        superDAO.insert(user);
    }

    public void update(User user) throws Exception {
        superDAO.update(user);
    }

    public void delete(String username) throws Exception {
        superDAO.delete(User.class, username);
    }

    public User query(String username) throws Exception {
        return (User) superDAO.query(User.class, username);
    }

}
