/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.adm.user;

import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.dataAccess.DAOUser;

/**
 * 李倍存 创建于 2015-02-24 16:08。电邮 1174751315@qq.com。
 */
public class UserUtils {
    public static Boolean exist(String username) {
        try {
            User user = daoUser.query(username);
            return (user != null);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public static Boolean validate(String username, String password) {
        try {

            User user = daoUser.query(username);
            return (user != null && user.getPassword().equals(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static DAOUser daoUser = DAOFactory.getDefault().createDaoUser();

    public static void add(User user) throws Exception {
        if (exist(user.getUsername()))
            throw new Exception("用户名 " + user.getUsername() + " 已被使用。");
        daoUser.insert(user);
    }

    public static void add(String username, String password) throws Exception {
        if (exist(username))
            throw new Exception("用户名 " + username + " 已被使用。");
        User user = new User(username, password);
        daoUser.insert(user);
    }

    public static void delete(String username) throws Exception {
        if (!exist(username))
            throw new Exception("用户名 " + username + " 不存在");
        daoUser.delete(username);
    }

    public static void update(String username, String password) throws Exception {
        if (!exist(username))
            throw new Exception("用户名 " + username + " 不存在");
        User user = new User(username, password);
        daoUser.update(user);
    }

    public static void update(User user) throws Exception {
        update(user.getUsername(), user.getPassword());
    }

}
