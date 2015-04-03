/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.adm.user;

import  common.ElementPrintableLinkedList;
import  loadPrediction.config.ConfigureFactory;

/**
 * 李倍存 创建于 2015-02-24 15:43。电邮 1174751315@qq.com。
 */
public class UserValidatorImpl1 implements IUserValidator {
    private static UserValidatorImpl1 instance = new UserValidatorImpl1();

    public static UserValidatorImpl1 getInstance() {
        return instance;
    }

    private UserValidatorImpl1() {
    }

    private ElementPrintableLinkedList<User> users = ConfigureFactory.getInstance().getXmlConfigure().getAllConfiguration().getUsers();

    @Override
    public Boolean validate(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.getUsername().equals(users.get(i).getUsername())) {
                User item = users.get(i);
                if (user.getPassword().equals(item.getPassword()))
                    return true;
            }

        }
        return false;
    }
}
