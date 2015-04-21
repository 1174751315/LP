/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.adm.user;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 创建：2015/1/16 9:27
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class aUserLogin extends ActionSupport {

    private boolean userOrAdminExists;

    public boolean isUserOrAdminExists() {
        return userOrAdminExists;
    }

    public void setUserOrAdminExists(boolean userOrAdminExists) {
        this.userOrAdminExists = userOrAdminExists;
    }

    private User adm;


//    @Override
//    public String execute() throws Exception {
//        daoAdm d=new daoAdm();
//        if(d.ExistUser(adm)==true){
//            userOrAdminExists=true;
//            return "success";
//        }
//        userOrAdminExists=false;
//        return "success";
//    }
//
//    public String executeUserLogin() throws Exception {
//        daoAdm d=new daoAdm();
//        if(d.ValidateUser(adm)==true){
//            return "success";
//        }
//        return "success";
//    }
//    public String executeAdminLogin() throws  Exception{
//        daoAdm d=new daoAdm();
//        if(d.ValidateAdmin(adm)==true){
//            return "success";
//        }
//        return "success";
//    }
//    public String executeAdminCheck() throws  Exception{
//        daoAdm d=new daoAdm();
//        if(d.ExistsAdmin(adm)==true){
//            userOrAdminExists=true;
//            return "success";
//        }
//        userOrAdminExists=false;
//        return "success";
//    }
//    public String executeUserCheck() throws Exception{
//        daoAdm d=new daoAdm();
//        if(d.ExistUser(adm)==true){
//            userOrAdminExists=true;
//            return "success";
//        }
//        userOrAdminExists=false;
//        return "success";
//    }
//
//    public boolean ValidateAdmin(bAdm adm)throws SQLException {
//
//
//        for (int i = 0; i < lstAdm.size(); i++) {
//            if (lstAdm.get(i)[0].equals(adm.getUsername())){
//                if (lstAdm.get(i)[1].equals(adm.getPassword())){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    public boolean ValidateUser(bAdm adm)throws  SQLException{
//        for (int i = 0; i < lstUser.size(); i++) {
//            if (lstUser.get(i)[0].equals(adm.getUsername())){
//                if (lstUser.get(i)[1].equals(adm.getPassword())){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    public boolean ExistsAdmin(bAdm adm){
//        for (int i = 0; i < lstAdm.size(); i++) {
//            if (lstAdm.get(i)[0].equals(adm.getUsername())){
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean ExistUser(bAdm adm){
//        for (int i = 0; i < lstUser.size(); i++) {
//            if (lstUser.get(i)[0].equals(adm.getUsername())){
//                return true;
//            }
//        }
//        return false;
//    }
}
