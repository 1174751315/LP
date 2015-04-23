/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.adm.user;

/**
 * 李倍存 创建于 2015-02-24 16:21。电邮 1174751315@qq.com。
 */
public class UserValidatorImpl2 implements IUserValidator {
    private static UserValidatorImpl2 instance = new UserValidatorImpl2();

    public static UserValidatorImpl2 getInstance() {
        return instance;
    }

    private UserValidatorImpl2() {
    }

    @Override
    public Boolean validate(User user) {
        return UserUtils.validate(user.getUsername(), user.getPassword());
    }
}
