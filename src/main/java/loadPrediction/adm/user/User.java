/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.adm.user;

import  common.IPrintable;
import  loadPrediction.config.eAccess;

import java.io.PrintStream;

/**
 * 创建：2015/1/21 9:31
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class User implements IPrintable {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User() {
    }


    private eAccess access;

    public eAccess getAccess() {
        return access;
    }

    public void setAccess(eAccess access) {
        this.access = access;
    }

    public void setAccess(String access) {
        if (access.equals("administrator"))
            this.access = eAccess.administrator;
        else
            this.access = eAccess.general;
    }

    @Override
    public void print(PrintStream ps) {
        ps.println("用户名  " + username + ";  " + "密码  " + password + ";");
    }

    @Override
    public User clone() {
        User user = new User(this.getUsername(), this.getPassword());
        return user;
    }
}
