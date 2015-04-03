/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.domain.visitors;

import  loadPrediction.domain.LoadData;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 李倍存 创建于 2015-03-21 9:12。电邮 1174751315@qq.com。
 */
public class LoadDataAppend2DatasetVisitor implements LoadDataVisitor {
    private DefaultCategoryDataset ds;
    private String label;

    public LoadDataAppend2DatasetVisitor(DefaultCategoryDataset ds, String label) {
        this.ds = ds;
        this.label = label;
    }

    @Override
    public Object visit(LoadData loadData) {
        ds.addValue(loadData.getData00(), label, "00:00:00");
        ds.addValue(loadData.getData01(), label, "");
        ds.addValue(loadData.getData02(), label, "");
        ds.addValue(loadData.getData03(), label, "");
        ds.addValue(loadData.getData04(), label, "01:00:00");
        ds.addValue(loadData.getData05(), label, "");
        ds.addValue(loadData.getData06(), label, "");
        ds.addValue(loadData.getData07(), label, "");
        ds.addValue(loadData.getData08(), label, "02:00:00");
        ds.addValue(loadData.getData09(), label, "");
        ds.addValue(loadData.getData10(), label, "");
        ds.addValue(loadData.getData11(), label, "");
        ds.addValue(loadData.getData12(), label, "03:00:00");
        ds.addValue(loadData.getData13(), label, "");
        ds.addValue(loadData.getData14(), label, "");
        ds.addValue(loadData.getData15(), label, "");
        ds.addValue(loadData.getData16(), label, "04:00:00");
        ds.addValue(loadData.getData17(), label, "");
        ds.addValue(loadData.getData18(), label, "");
        ds.addValue(loadData.getData19(), label, "");
        ds.addValue(loadData.getData20(), label, "05:00:00");
        ds.addValue(loadData.getData21(), label, "");
        ds.addValue(loadData.getData22(), label, "");
        ds.addValue(loadData.getData23(), label, "");
        ds.addValue(loadData.getData24(), label, "06:00:00");
        ds.addValue(loadData.getData25(), label, "");
        ds.addValue(loadData.getData26(), label, "");
        ds.addValue(loadData.getData27(), label, "");
        ds.addValue(loadData.getData28(), label, "07:00:00");
        ds.addValue(loadData.getData29(), label, "");
        ds.addValue(loadData.getData30(), label, "");
        ds.addValue(loadData.getData31(), label, "");
        ds.addValue(loadData.getData32(), label, "08:00:00");
        ds.addValue(loadData.getData33(), label, "");
        ds.addValue(loadData.getData34(), label, "");
        ds.addValue(loadData.getData35(), label, "");
        ds.addValue(loadData.getData36(), label, "09:00:00");
        ds.addValue(loadData.getData37(), label, "");
        ds.addValue(loadData.getData38(), label, "");
        ds.addValue(loadData.getData39(), label, "");
        ds.addValue(loadData.getData40(), label, "10:00:00");
        ds.addValue(loadData.getData41(), label, "");
        ds.addValue(loadData.getData42(), label, "");
        ds.addValue(loadData.getData43(), label, "");
        ds.addValue(loadData.getData44(), label, "11:00:00");
        ds.addValue(loadData.getData45(), label, "");
        ds.addValue(loadData.getData46(), label, "");
        ds.addValue(loadData.getData47(), label, "");
        ds.addValue(loadData.getData48(), label, "12:00:00");
        ds.addValue(loadData.getData49(), label, "");
        ds.addValue(loadData.getData50(), label, "");
        ds.addValue(loadData.getData51(), label, "");
        ds.addValue(loadData.getData52(), label, "13:00:00");
        ds.addValue(loadData.getData53(), label, "");
        ds.addValue(loadData.getData54(), label, "");
        ds.addValue(loadData.getData55(), label, "");
        ds.addValue(loadData.getData56(), label, "14:00:00");
        ds.addValue(loadData.getData57(), label, "");
        ds.addValue(loadData.getData58(), label, "");
        ds.addValue(loadData.getData59(), label, "");
        ds.addValue(loadData.getData60(), label, "15:00:00");
        ds.addValue(loadData.getData61(), label, "");
        ds.addValue(loadData.getData62(), label, "");
        ds.addValue(loadData.getData63(), label, "");
        ds.addValue(loadData.getData64(), label, "16:00:00");
        ds.addValue(loadData.getData65(), label, "");
        ds.addValue(loadData.getData66(), label, "");
        ds.addValue(loadData.getData67(), label, "");
        ds.addValue(loadData.getData68(), label, "17:00:00");
        ds.addValue(loadData.getData69(), label, "");
        ds.addValue(loadData.getData70(), label, "");
        ds.addValue(loadData.getData71(), label, "");
        ds.addValue(loadData.getData72(), label, "18:00:00");
        ds.addValue(loadData.getData73(), label, "");
        ds.addValue(loadData.getData74(), label, "");
        ds.addValue(loadData.getData75(), label, "");
        ds.addValue(loadData.getData76(), label, "19:00:00");
        ds.addValue(loadData.getData77(), label, "");
        ds.addValue(loadData.getData78(), label, "");
        ds.addValue(loadData.getData79(), label, "");
        ds.addValue(loadData.getData80(), label, "20:00:00");
        ds.addValue(loadData.getData81(), label, "");
        ds.addValue(loadData.getData82(), label, "");
        ds.addValue(loadData.getData83(), label, "");
        ds.addValue(loadData.getData84(), label, "21:00:00");
        ds.addValue(loadData.getData85(), label, "");
        ds.addValue(loadData.getData86(), label, "");
        ds.addValue(loadData.getData87(), label, "");
        ds.addValue(loadData.getData88(), label, "22:00:00");
        ds.addValue(loadData.getData89(), label, "");
        ds.addValue(loadData.getData90(), label, "");
        ds.addValue(loadData.getData91(), label, "");
        ds.addValue(loadData.getData92(), label, "23:00:00");
        ds.addValue(loadData.getData93(), label, "");
        ds.addValue(loadData.getData94(), label, "");
        ds.addValue(loadData.getData95(), label, "");
        return ds;
    }
}
