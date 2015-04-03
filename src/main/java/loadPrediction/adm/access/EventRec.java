/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.adm.access;

import java.util.List;

/**
 * Created by LBC on 2015/1/20.
 */
public class EventRec {
    public EventRec() {
    }

    private List<EventRecDatas> eventDatas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public List<EventRecDatas> getEventDatas() {
        return eventDatas;
    }

    public void setEventDatas(List<EventRecDatas> eventDatas) {
        this.eventDatas = eventDatas;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String operator;

    public String getOperator() {
        return operator;
    }


    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void dbgPrint() {
        System.out.print(id + "," + type + "," + operator + "\n");
        for (int i = 0; i < eventDatas.size(); i++) {
            System.out.print("----");
            eventDatas.get(i).dbgPrint();
        }
    }
}
