/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.domain.visitors;

import loadPrediction.domain.LoadData;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.CategoryTableXYDataset;

/**
 * 李倍存 创建于 2015-03-21 9:12。电邮 1174751315@qq.com。
 */
public class AppendTableXYDatasetVisitor implements LoadDataVisitor {
    private CategoryTableXYDataset ds;
    private String label;

    public AppendTableXYDatasetVisitor(CategoryTableXYDataset ds, String label) {
        this.ds = ds;
        this.label = label;
    }

    @Override
    public Object visit(LoadData loadData) {
        ds.add(0.0,loadData.getData00(),label);
        ds.add(0.25,loadData.getData01(),label);
        ds.add(0.5,loadData.getData02(),label);
        ds.add(0.75,loadData.getData03(),label);
        ds.add(1.0,loadData.getData04(),label);
        ds.add(1.25,loadData.getData05(),label);
        ds.add(1.5,loadData.getData06(),label);
        ds.add(1.75,loadData.getData07(),label);
        ds.add(2.0,loadData.getData08(),label);
        ds.add(2.25,loadData.getData09(),label);
        ds.add(2.5,loadData.getData10(),label);
        ds.add(2.75,loadData.getData11(),label);
        ds.add(3.0,loadData.getData12(),label);
        ds.add(3.25,loadData.getData13(),label);
        ds.add(3.5,loadData.getData14(),label);
        ds.add(3.75,loadData.getData15(),label);
        ds.add(4.0,loadData.getData16(),label);
        ds.add(4.25,loadData.getData17(),label);
        ds.add(4.5,loadData.getData18(),label);
        ds.add(4.75,loadData.getData19(),label);
        ds.add(5.0,loadData.getData20(),label);
        ds.add(5.25,loadData.getData21(),label);
        ds.add(5.5,loadData.getData22(),label);
        ds.add(5.75,loadData.getData23(),label);
        ds.add(6.0,loadData.getData24(),label);
        ds.add(6.25,loadData.getData25(),label);
        ds.add(6.5,loadData.getData26(),label);
        ds.add(6.75,loadData.getData27(),label);
        ds.add(7.0,loadData.getData28(),label);
        ds.add(7.25,loadData.getData29(),label);
        ds.add(7.5,loadData.getData30(),label);
        ds.add(7.75,loadData.getData31(),label);
        ds.add(8.0,loadData.getData32(),label);
        ds.add(8.25,loadData.getData33(),label);
        ds.add(8.5,loadData.getData34(),label);
        ds.add(8.75,loadData.getData35(),label);
        ds.add(9.0,loadData.getData36(),label);
        ds.add(9.25,loadData.getData37(),label);
        ds.add(9.5,loadData.getData38(),label);
        ds.add(9.75,loadData.getData39(),label);
        ds.add(10.0,loadData.getData40(),label);
        ds.add(10.25,loadData.getData41(),label);
        ds.add(10.5,loadData.getData42(),label);
        ds.add(10.75,loadData.getData43(),label);
        ds.add(11.0,loadData.getData44(),label);
        ds.add(11.25,loadData.getData45(),label);
        ds.add(11.5,loadData.getData46(),label);
        ds.add(11.75,loadData.getData47(),label);
        ds.add(12.0,loadData.getData48(),label);
        ds.add(12.25,loadData.getData49(),label);
        ds.add(12.5,loadData.getData50(),label);
        ds.add(12.75,loadData.getData51(),label);
        ds.add(13.0,loadData.getData52(),label);
        ds.add(13.25,loadData.getData53(),label);
        ds.add(13.5,loadData.getData54(),label);
        ds.add(13.75,loadData.getData55(),label);
        ds.add(14.0,loadData.getData56(),label);
        ds.add(14.25,loadData.getData57(),label);
        ds.add(14.5,loadData.getData58(),label);
        ds.add(14.75,loadData.getData59(),label);
        ds.add(15.0,loadData.getData60(),label);
        ds.add(15.25,loadData.getData61(),label);
        ds.add(15.5,loadData.getData62(),label);
        ds.add(15.75,loadData.getData63(),label);
        ds.add(16.0,loadData.getData64(),label);
        ds.add(16.25,loadData.getData65(),label);
        ds.add(16.5,loadData.getData66(),label);
        ds.add(16.75,loadData.getData67(),label);
        ds.add(17.0,loadData.getData68(),label);
        ds.add(17.25,loadData.getData69(),label);
        ds.add(17.5,loadData.getData70(),label);
        ds.add(17.75,loadData.getData71(),label);
        ds.add(18.0,loadData.getData72(),label);
        ds.add(18.25,loadData.getData73(),label);
        ds.add(18.5,loadData.getData74(),label);
        ds.add(18.75,loadData.getData75(),label);
        ds.add(19.0,loadData.getData76(),label);
        ds.add(19.25,loadData.getData77(),label);
        ds.add(19.5,loadData.getData78(),label);
        ds.add(19.75,loadData.getData79(),label);
        ds.add(20.0,loadData.getData80(),label);
        ds.add(20.25,loadData.getData81(),label);
        ds.add(20.5,loadData.getData82(),label);
        ds.add(20.75,loadData.getData83(),label);
        ds.add(21.0,loadData.getData84(),label);
        ds.add(21.25,loadData.getData85(),label);
        ds.add(21.5,loadData.getData86(),label);
        ds.add(21.75,loadData.getData87(),label);
        ds.add(22.0,loadData.getData88(),label);
        ds.add(22.25,loadData.getData89(),label);
        ds.add(22.5,loadData.getData90(),label);
        ds.add(22.75,loadData.getData91(),label);
        ds.add(23.0,loadData.getData92(),label);
        ds.add(23.25,loadData.getData93(),label);
        ds.add(23.5,loadData.getData94(),label);
        ds.add(23.75,loadData.getData95(),label);


        return ds;
    }
}
