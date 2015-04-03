/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.adm.user.UserUtils;
import org.junit.Test;


public class UserTest {

    @Test
    public void testExist() throws Exception {
        if (UserUtils.exist("李倍存"))
            System.out.printf("用户名 李倍存 找到");
    }


    @Test
    public void testValidate() throws Exception {
        if (UserUtils.validate("李倍存", "000000"))
            System.out.printf("用户名 李倍存 密码 000000 找到");
        if (UserUtils.validate("李倍存", "123456"))
            System.out.printf("");
        else
            System.out.printf("验证失败。");
    }


    @Test
    public void testAdd() throws Exception {
        UserUtils.add("李倍存", "000000");
    }

    @Test
    public void testDelete() throws Exception {
//        UserUtils.delete("李倍存");
//        UserUtils.delete("lbc");
    }

    @Test
    public void testUpdate() throws Exception {
//        UserUtils.update("李倍存", "123456");
//        UserUtils.update("lbc", "lbc");
    }


}