/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.dao;

import main.java.loadPrediction.adm.user.User;
import main.java.loadPrediction.dataAccess.DAOFactory;
import org.junit.Test;

public class DAOUserTest {

    @Test
    public void testInsert() throws Exception {
        DAOFactory.getDefault().createDaoUser().insert(new User("李倍存", "123456"));
    }

    @Test
    public void testUpdate() throws Exception {
        DAOFactory.getDefault().createDaoUser().update(new User("李倍存", "000000"));
    }

    @Test
    public void testDelete() throws Exception {
        DAOFactory.getDefault().createDaoUser().delete("李倍存");
        DAOFactory.getDefault().createDaoUser().delete("lbc");
    }

    @Test
    public void testQuery() throws Exception {
        DAOFactory.getDefault().createDaoUser().query("李倍存").print(System.out);
    }
}