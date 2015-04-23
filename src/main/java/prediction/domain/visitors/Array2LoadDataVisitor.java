/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.domain.visitors;

import prediction.domain.LoadData;

/**
 * 李倍存 创建于 2015-03-22 20:57。电邮 1174751315@qq.com。
 */
public class Array2LoadDataVisitor implements LoadDataVisitor {
    private Double[] values;

    public Array2LoadDataVisitor(Double[] nine6PointValues) {
        this.values = nine6PointValues;
    }

    @Override
    public Object visit(LoadData loadData) {
        if (values.length != 96)
            return loadData;
        loadData.setData00(values[0]);
        loadData.setData01(values[1]);
        loadData.setData02(values[2]);
        loadData.setData03(values[3]);
        loadData.setData04(values[4]);
        loadData.setData05(values[5]);
        loadData.setData06(values[6]);
        loadData.setData07(values[7]);
        loadData.setData08(values[8]);
        loadData.setData09(values[9]);
        loadData.setData10(values[10]);
        loadData.setData11(values[11]);
        loadData.setData12(values[12]);
        loadData.setData13(values[13]);
        loadData.setData14(values[14]);
        loadData.setData15(values[15]);
        loadData.setData16(values[16]);
        loadData.setData17(values[17]);
        loadData.setData18(values[18]);
        loadData.setData19(values[19]);
        loadData.setData20(values[20]);
        loadData.setData21(values[21]);
        loadData.setData22(values[22]);
        loadData.setData23(values[23]);
        loadData.setData24(values[24]);
        loadData.setData25(values[25]);
        loadData.setData26(values[26]);
        loadData.setData27(values[27]);
        loadData.setData28(values[28]);
        loadData.setData29(values[29]);
        loadData.setData30(values[30]);
        loadData.setData31(values[31]);
        loadData.setData32(values[32]);
        loadData.setData33(values[33]);
        loadData.setData34(values[34]);
        loadData.setData35(values[35]);
        loadData.setData36(values[36]);
        loadData.setData37(values[37]);
        loadData.setData38(values[38]);
        loadData.setData39(values[39]);
        loadData.setData40(values[40]);
        loadData.setData41(values[41]);
        loadData.setData42(values[42]);
        loadData.setData43(values[43]);
        loadData.setData44(values[44]);
        loadData.setData45(values[45]);
        loadData.setData46(values[46]);
        loadData.setData47(values[47]);
        loadData.setData48(values[48]);
        loadData.setData49(values[49]);
        loadData.setData50(values[50]);
        loadData.setData51(values[51]);
        loadData.setData52(values[52]);
        loadData.setData53(values[53]);
        loadData.setData54(values[54]);
        loadData.setData55(values[55]);
        loadData.setData56(values[56]);
        loadData.setData57(values[57]);
        loadData.setData58(values[58]);
        loadData.setData59(values[59]);
        loadData.setData60(values[60]);
        loadData.setData61(values[61]);
        loadData.setData62(values[62]);
        loadData.setData63(values[63]);
        loadData.setData64(values[64]);
        loadData.setData65(values[65]);
        loadData.setData66(values[66]);
        loadData.setData67(values[67]);
        loadData.setData68(values[68]);
        loadData.setData69(values[69]);
        loadData.setData70(values[70]);
        loadData.setData71(values[71]);
        loadData.setData72(values[72]);
        loadData.setData73(values[73]);
        loadData.setData74(values[74]);
        loadData.setData75(values[75]);
        loadData.setData76(values[76]);
        loadData.setData77(values[77]);
        loadData.setData78(values[78]);
        loadData.setData79(values[79]);
        loadData.setData80(values[80]);
        loadData.setData81(values[81]);
        loadData.setData82(values[82]);
        loadData.setData83(values[83]);
        loadData.setData84(values[84]);
        loadData.setData85(values[85]);
        loadData.setData86(values[86]);
        loadData.setData87(values[87]);
        loadData.setData88(values[88]);
        loadData.setData89(values[89]);
        loadData.setData90(values[90]);
        loadData.setData91(values[91]);
        loadData.setData92(values[92]);
        loadData.setData93(values[93]);
        loadData.setData94(values[94]);
        loadData.setData95(values[95]);
        return loadData;
    }
}
