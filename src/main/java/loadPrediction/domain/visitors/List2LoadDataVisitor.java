/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.domain.visitors;

import loadPrediction.domain.LoadData;

import java.util.List;

/**
 * 李倍存 创建于 2015-03-22 20:57。电邮 1174751315@qq.com。
 */
public class List2LoadDataVisitor implements LoadDataVisitor {
    private List<Double> values;

    public List2LoadDataVisitor(List<Double> nine6PointValues) {
        this.values = nine6PointValues;
    }

    @Override
    public Object visit(LoadData loadData) {
        if (values.size() != 96)
            return loadData;
        loadData.setData00(values.get(0));
        loadData.setData01(values.get(1));
        loadData.setData02(values.get(2));
        loadData.setData03(values.get(3));
        loadData.setData04(values.get(4));
        loadData.setData05(values.get(5));
        loadData.setData06(values.get(6));
        loadData.setData07(values.get(7));
        loadData.setData08(values.get(8));
        loadData.setData09(values.get(9));
        loadData.setData10(values.get(10));
        loadData.setData11(values.get(11));
        loadData.setData12(values.get(12));
        loadData.setData13(values.get(13));
        loadData.setData14(values.get(14));
        loadData.setData15(values.get(15));
        loadData.setData16(values.get(16));
        loadData.setData17(values.get(17));
        loadData.setData18(values.get(18));
        loadData.setData19(values.get(19));
        loadData.setData20(values.get(20));
        loadData.setData21(values.get(21));
        loadData.setData22(values.get(22));
        loadData.setData23(values.get(23));
        loadData.setData24(values.get(24));
        loadData.setData25(values.get(25));
        loadData.setData26(values.get(26));
        loadData.setData27(values.get(27));
        loadData.setData28(values.get(28));
        loadData.setData29(values.get(29));
        loadData.setData30(values.get(30));
        loadData.setData31(values.get(31));
        loadData.setData32(values.get(32));
        loadData.setData33(values.get(33));
        loadData.setData34(values.get(34));
        loadData.setData35(values.get(35));
        loadData.setData36(values.get(36));
        loadData.setData37(values.get(37));
        loadData.setData38(values.get(38));
        loadData.setData39(values.get(39));
        loadData.setData40(values.get(40));
        loadData.setData41(values.get(41));
        loadData.setData42(values.get(42));
        loadData.setData43(values.get(43));
        loadData.setData44(values.get(44));
        loadData.setData45(values.get(45));
        loadData.setData46(values.get(46));
        loadData.setData47(values.get(47));
        loadData.setData48(values.get(48));
        loadData.setData49(values.get(49));
        loadData.setData50(values.get(50));
        loadData.setData51(values.get(51));
        loadData.setData52(values.get(52));
        loadData.setData53(values.get(53));
        loadData.setData54(values.get(54));
        loadData.setData55(values.get(55));
        loadData.setData56(values.get(56));
        loadData.setData57(values.get(57));
        loadData.setData58(values.get(58));
        loadData.setData59(values.get(59));
        loadData.setData60(values.get(60));
        loadData.setData61(values.get(61));
        loadData.setData62(values.get(62));
        loadData.setData63(values.get(63));
        loadData.setData64(values.get(64));
        loadData.setData65(values.get(65));
        loadData.setData66(values.get(66));
        loadData.setData67(values.get(67));
        loadData.setData68(values.get(68));
        loadData.setData69(values.get(69));
        loadData.setData70(values.get(70));
        loadData.setData71(values.get(71));
        loadData.setData72(values.get(72));
        loadData.setData73(values.get(73));
        loadData.setData74(values.get(74));
        loadData.setData75(values.get(75));
        loadData.setData76(values.get(76));
        loadData.setData77(values.get(77));
        loadData.setData78(values.get(78));
        loadData.setData79(values.get(79));
        loadData.setData80(values.get(80));
        loadData.setData81(values.get(81));
        loadData.setData82(values.get(82));
        loadData.setData83(values.get(83));
        loadData.setData84(values.get(84));
        loadData.setData85(values.get(85));
        loadData.setData86(values.get(86));
        loadData.setData87(values.get(87));
        loadData.setData88(values.get(88));
        loadData.setData89(values.get(89));
        loadData.setData90(values.get(90));
        loadData.setData91(values.get(91));
        loadData.setData92(values.get(92));
        loadData.setData93(values.get(93));
        loadData.setData94(values.get(94));
        loadData.setData95(values.get(95));
        return loadData;
    }
}
