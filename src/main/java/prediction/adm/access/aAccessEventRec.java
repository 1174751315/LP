/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.adm.access;


import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 创建：2015/1/20 9:21
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class aAccessEventRec extends ActionSupport {
    public boolean isAdminAccess() {
        return isAdminAccess;
    }

    public void setAdminAccess(boolean isAdminAccess) {
        this.isAdminAccess = isAdminAccess;
    }

    private boolean isAdminAccess;

    private List<EventRec> events;
    private List<EventRecDatas> currentDatas;

    public List<EventRecDatas> getCurrentDatas() {
        return currentDatas;
    }

    public void setCurrentDatas(List<EventRecDatas> currentDatas) {
        this.currentDatas = currentDatas;
    }

    public List<EventRec> getEvents() {
        return events;
    }

    public void setEvents(List<EventRec> events) {
        this.events = events;
    }

    public String executeUserAccess() throws Exception {
//        daoAccessEventRec d = new daoAccessEventRec();
//        events=d.getEventRec();
        return "success";
    }

    public String executeAdminAccess() throws Exception {
        return super.execute();
    }

    private String idBy;

    public String getIdBy() {
        return idBy;
    }

    public void setIdBy(String idBy) {
        this.idBy = idBy;
    }

    public String executeDeleteRec() throws Exception {

        return "success";
    }

    public String executeShowDatasOfSelectedEvent() throws Exception {

        return "success";
    }
}
