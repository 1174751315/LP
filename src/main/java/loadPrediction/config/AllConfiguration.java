/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;


import common.ElementPrintableLinkedList;
import common.IPrintable;
import loadPrediction.adm.user.User;

import java.io.PrintStream;


/**
 * 李倍存 创建于 2015-02-20 17:32。电邮 1174751315@qq.com。
 */
public class AllConfiguration implements IPrintable {
//    private AllConfiguration() {
//    }
//    private static AllConfiguration instance=ConfigureFactory.getInstance().getXmlConfigure().getAllConfiguration();
//    public static AllConfiguration getInstance(){return instance;}


    private WorkdayPredictorCfg workdayPredictorCfg;
    private WeekendPredictorCfg weekendPredictorCfg;
    private FestivalPredictorCfg festivalPredictorCfg;
    private ElementPrintableLinkedList<OutputCfg> outputCfgs;
    private CachesCfg cachesCfg;

    public CachesCfg getCachesCfg() {
        return cachesCfg;
    }

    public void setCachesCfg(CachesCfg cachesCfg) {
        this.cachesCfg = cachesCfg;
    }

    private ElementPrintableLinkedList<User> users;

    public FestivalPredictorCfg getFestivalPredictorCfg() {
        return festivalPredictorCfg;
    }

    public void setFestivalPredictorCfg(FestivalPredictorCfg festivalPredictorCfg) {
        this.festivalPredictorCfg = festivalPredictorCfg;
    }

    public ElementPrintableLinkedList<OutputCfg> getOutputCfgs() {
        return outputCfgs;
    }

    public void setOutputCfgs(ElementPrintableLinkedList<OutputCfg> outputCfgs) {
        this.outputCfgs = outputCfgs;
    }


    public WeekendPredictorCfg getWeekendPredictorCfg() {
        return weekendPredictorCfg;
    }

    public void setWeekendPredictorCfg(WeekendPredictorCfg weekendPredictorCfg) {
        this.weekendPredictorCfg = weekendPredictorCfg;
    }

    public WorkdayPredictorCfg getWorkdayPredictorCfg() {
        return workdayPredictorCfg;
    }

    public void setWorkdayPredictorCfg(WorkdayPredictorCfg workdayPredictorCfg) {
        this.workdayPredictorCfg = workdayPredictorCfg;
    }


    public ElementPrintableLinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(ElementPrintableLinkedList<User> users) {
        this.users = users;
    }

    @Override
    public void print(PrintStream ps) {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).print(ps);
        }
    }


    public void changeAll() {
        users.remove(0);
    }

    public Memento createMemento() {
        Memento m = new Memento();
        m.setFestivalPredictorCfg(this.festivalPredictorCfg.clone());
        m.setWeekendPredictorCfg(this.weekendPredictorCfg.clone());
        m.setWorkdayPredictorCfg(this.workdayPredictorCfg.clone());

        ElementPrintableLinkedList<OutputCfg> outputCfgs = new ElementPrintableLinkedList<OutputCfg>("clone");
        for (int i = 0; i < this.outputCfgs.size(); i++) {
            outputCfgs.add(this.outputCfgs.get(i).clone());
        }
        m.setOutputCfgs(outputCfgs);

        ElementPrintableLinkedList<User> users = new ElementPrintableLinkedList<User>("clone");
        for (int i = 0; i < this.users.size(); i++) {
            users.add(this.users.get(i).clone());
        }
        m.setUsers(users);
        return m;
    }

    public void recoverFromMemento(Memento memento) {
        this.weekendPredictorCfg = memento.getWeekendPredictorCfg().clone();
        this.workdayPredictorCfg = memento.getWorkdayPredictorCfg().clone();
        this.festivalPredictorCfg = memento.getFestivalPredictorCfg().clone();
        ElementPrintableLinkedList<OutputCfg> outputCfgs = new ElementPrintableLinkedList<OutputCfg>("");
        for (int i = 0; i < memento.getOutputCfgs().size(); i++) {
            outputCfgs.add(memento.getOutputCfgs().get(i).clone());
        }
        ;
        this.outputCfgs = outputCfgs;

        ElementPrintableLinkedList<User> users = new ElementPrintableLinkedList<User>("");
        for (int i = 0; i < memento.getUsers().size(); i++) {
            users.add(memento.getUsers().get(i).clone());
        }
        this.users = users;
    }


    public class Memento {
        private FestivalPredictorCfg getFestivalPredictorCfg() {
            return festivalPredictorCfg;
        }

        private void setFestivalPredictorCfg(FestivalPredictorCfg festivalPredictorCfg) {
            this.festivalPredictorCfg = festivalPredictorCfg;
        }

        private ElementPrintableLinkedList<OutputCfg> getOutputCfgs() {
            return outputCfgs;
        }

        private void setOutputCfgs(ElementPrintableLinkedList<OutputCfg> outputCfgs) {
            this.outputCfgs = outputCfgs;
        }

        private ElementPrintableLinkedList<User> getUsers() {
            return users;
        }

        private void setUsers(ElementPrintableLinkedList<User> users) {
            this.users = users;
        }

        private WeekendPredictorCfg getWeekendPredictorCfg() {
            return weekendPredictorCfg;
        }

        private void setWeekendPredictorCfg(WeekendPredictorCfg weekendPredictorCfg) {
            this.weekendPredictorCfg = weekendPredictorCfg;
        }

        private WorkdayPredictorCfg getWorkdayPredictorCfg() {
            return workdayPredictorCfg;
        }

        private void setWorkdayPredictorCfg(WorkdayPredictorCfg workdayPredictorCfg) {
            this.workdayPredictorCfg = workdayPredictorCfg;
        }

        private WorkdayPredictorCfg workdayPredictorCfg;
        private WeekendPredictorCfg weekendPredictorCfg;
        private FestivalPredictorCfg festivalPredictorCfg;
        private ElementPrintableLinkedList<OutputCfg> outputCfgs;
        private ElementPrintableLinkedList<User> users;

    }
}
