/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import  common.IListable;
import  common.IMaxAveMinable;
import  common.IPrintable;
import  common.MaxAveMinTuple;
import  loadPrediction.domain.visitors.IDomainVisitor;
import  loadPrediction.domain.visitors.LoadDataVisitor;
import  loadPrediction.resouce.TimeLabels;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LBC on 2015/1/22.
 * 表示96点负荷数据的实际值或预测值。
 */
public class LoadData implements IDomain, IListable<Double>, IMaxAveMinable<Double>, IPrintable {
    private LoadData next;

    public LoadData getNext() {
        return next;
    }

    public void setNext(LoadData next) {
        if (next == this)
            return;
        this.next = next;
    }

    private boolean maxed = false, mined = false, aveed = false;
    private Double max, min, ave;
    private Integer maxIndex=0,minIndex=0;
    public String getMaxLabel(){
        getMax();
        return TimeLabels.labels[maxIndex];
    }
    public String getMinLabel(){
        getMin();
        return TimeLabels.labels[minIndex];
    }
    public Double getMax() {
        if (maxed == true)
            return max;
        this.update();
        maxed = true;
        max = lst.get(0);
        for (int i = 0; i < 96; i++) {
            if (max < lst.get(i)){
                max = lst.get(i);
                maxIndex=i;
            }

        }
        return max;
    }

    public Double getMin() {
        if (mined == true)
            return min;
        this.update();
        mined = true;
        min = lst.get(0);
        for (int i = 0; i < 96; i++) {
            if (min > lst.get(i)){
                min = lst.get(i);
                minIndex=i;
            }

        }
        return min;
    }

    public Double getAve() {
        if (aveed == true)
            return ave;
        this.update();
        aveed = true;
        Double sum = Double.valueOf(0);
        for (int i = 0; i < 96; i++) {
            sum += lst.get(i);
        }
        ave = sum / 96.;
        return ave;
    }
//

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
//        setDate(Date2StringAdapter.toDate(dateString));
        this.dateString = dateString;
    }

    private String dateString;

    public void setData(List<Double> data) {
        if (data.size() != 96)
            return;
        setData00(data.get(0));
        setData01(data.get(1));
        setData02(data.get(2));
        setData03(data.get(3));
        setData04(data.get(4));
        setData05(data.get(5));
        setData06(data.get(6));
        setData07(data.get(7));
        setData08(data.get(8));
        setData09(data.get(9));
        setData10(data.get(10));
        setData11(data.get(11));
        setData12(data.get(12));
        setData13(data.get(13));
        setData14(data.get(14));
        setData15(data.get(15));
        setData16(data.get(16));
        setData17(data.get(17));
        setData18(data.get(18));
        setData19(data.get(19));
        setData20(data.get(20));
        setData21(data.get(21));
        setData22(data.get(22));
        setData23(data.get(23));
        setData24(data.get(24));
        setData25(data.get(25));
        setData26(data.get(26));
        setData27(data.get(27));
        setData28(data.get(28));
        setData29(data.get(29));
        setData30(data.get(30));
        setData31(data.get(31));
        setData32(data.get(32));
        setData33(data.get(33));
        setData34(data.get(34));
        setData35(data.get(35));
        setData36(data.get(36));
        setData37(data.get(37));
        setData38(data.get(38));
        setData39(data.get(39));
        setData40(data.get(40));
        setData41(data.get(41));
        setData42(data.get(42));
        setData43(data.get(43));
        setData44(data.get(44));
        setData45(data.get(45));
        setData46(data.get(46));
        setData47(data.get(47));
        setData48(data.get(48));
        setData49(data.get(49));
        setData50(data.get(50));
        setData51(data.get(51));
        setData52(data.get(52));
        setData53(data.get(53));
        setData54(data.get(54));
        setData55(data.get(55));
        setData56(data.get(56));
        setData57(data.get(57));
        setData58(data.get(58));
        setData59(data.get(59));
        setData60(data.get(60));
        setData61(data.get(61));
        setData62(data.get(62));
        setData63(data.get(63));
        setData64(data.get(64));
        setData65(data.get(65));
        setData66(data.get(66));
        setData67(data.get(67));
        setData68(data.get(68));
        setData69(data.get(69));
        setData70(data.get(70));
        setData71(data.get(71));
        setData72(data.get(72));
        setData73(data.get(73));
        setData74(data.get(74));
        setData75(data.get(75));
        setData76(data.get(76));
        setData77(data.get(77));
        setData78(data.get(78));
        setData79(data.get(79));
        setData80(data.get(80));
        setData81(data.get(81));
        setData82(data.get(82));
        setData83(data.get(83));
        setData84(data.get(84));
        setData85(data.get(85));
        setData86(data.get(86));
        setData87(data.get(87));
        setData88(data.get(88));
        setData89(data.get(89));
        setData90(data.get(90));
        setData91(data.get(91));
        setData92(data.get(92));
        setData93(data.get(93));
        setData94(data.get(94));
        setData95(data.get(95));

    }

    private double data00;
    private double data01;
    private double data02;
    private double data03;
    private double data04;
    private double data05;
    private double data06;
    private double data07;
    private double data08;
    private double data09;
    private double data10;
    private double data11;
    private double data12;
    private double data13;
    private double data14;
    private double data15;
    private double data16;
    private double data17;
    private double data18;
    private double data19;
    private double data20;
    private double data21;
    private double data22;
    private double data23;
    private double data24;
    private double data25;
    private double data26;
    private double data27;
    private double data28;
    private double data29;
    private double data30;
    private double data31;
    private double data32;
    private double data33;
    private double data34;
    private double data35;
    private double data36;
    private double data37;
    private double data38;
    private double data39;
    private double data40;
    private double data41;
    private double data42;
    private double data43;
    private double data44;
    private double data45;
    private double data46;
    private double data47;
    private double data48;
    private double data49;
    private double data50;
    private double data51;
    private double data52;
    private double data53;
    private double data54;
    private double data55;
    private double data56;
    private double data57;
    private double data58;
    private double data59;
    private double data60;
    private double data61;
    private double data62;
    private double data63;
    private double data64;
    private double data65;
    private double data66;
    private double data67;
    private double data68;
    private double data69;
    private double data70;
    private double data71;
    private double data72;
    private double data73;
    private double data74;
    private double data75;
    private double data76;
    private double data77;
    private double data78;
    private double data79;
    private double data80;
    private double data81;
    private double data82;
    private double data83;
    private double data84;
    private double data85;

    public double getData86() {
        return data86;
    }

    public void setData86(double data86) {
        this.data86 = data86;
    }

    public double getData00() {
        return data00;
    }

    public void setData00(double data00) {
        this.data00 = data00;
    }

    public double getData01() {
        return data01;
    }

    public void setData01(double data01) {
        this.data01 = data01;
    }

    public double getData02() {
        return data02;
    }

    public void setData02(double data02) {
        this.data02 = data02;
    }

    public double getData03() {
        return data03;
    }

    public void setData03(double data03) {
        this.data03 = data03;
    }

    public double getData04() {
        return data04;
    }

    public void setData04(double data04) {
        this.data04 = data04;
    }

    public double getData05() {
        return data05;
    }

    public void setData05(double data05) {
        this.data05 = data05;
    }

    public double getData06() {
        return data06;
    }

    public void setData06(double data06) {
        this.data06 = data06;
    }

    public double getData07() {
        return data07;
    }

    public void setData07(double data07) {
        this.data07 = data07;
    }

    public double getData08() {
        return data08;
    }

    public void setData08(double data08) {
        this.data08 = data08;
    }

    public double getData09() {
        return data09;
    }

    public void setData09(double data09) {
        this.data09 = data09;
    }

    public double getData10() {
        return data10;
    }

    public void setData10(double data10) {
        this.data10 = data10;
    }

    public double getData11() {
        return data11;
    }

    public void setData11(double data11) {
        this.data11 = data11;
    }

    public double getData12() {
        return data12;
    }

    public void setData12(double data12) {
        this.data12 = data12;
    }

    public double getData13() {
        return data13;
    }

    public void setData13(double data13) {
        this.data13 = data13;
    }

    public double getData14() {
        return data14;
    }

    public void setData14(double data14) {
        this.data14 = data14;
    }

    public double getData15() {
        return data15;
    }

    public void setData15(double data15) {
        this.data15 = data15;
    }

    public double getData16() {
        return data16;
    }

    public void setData16(double data16) {
        this.data16 = data16;
    }

    public double getData17() {
        return data17;
    }

    public void setData17(double data17) {
        this.data17 = data17;
    }

    public double getData18() {
        return data18;
    }

    public void setData18(double data18) {
        this.data18 = data18;
    }

    public double getData19() {
        return data19;
    }

    public void setData19(double data19) {
        this.data19 = data19;
    }

    public double getData20() {
        return data20;
    }

    public void setData20(double data20) {
        this.data20 = data20;
    }

    public double getData21() {
        return data21;
    }

    public void setData21(double data21) {
        this.data21 = data21;
    }

    public double getData22() {
        return data22;
    }

    public void setData22(double data22) {
        this.data22 = data22;
    }

    public double getData23() {
        return data23;
    }

    public void setData23(double data23) {
        this.data23 = data23;
    }

    public double getData24() {
        return data24;
    }

    public void setData24(double data24) {
        this.data24 = data24;
    }

    public double getData25() {
        return data25;
    }

    public void setData25(double data25) {
        this.data25 = data25;
    }

    public double getData26() {
        return data26;
    }

    public void setData26(double data26) {
        this.data26 = data26;
    }

    public double getData27() {
        return data27;
    }

    public void setData27(double data27) {
        this.data27 = data27;
    }

    public double getData28() {
        return data28;
    }

    public void setData28(double data28) {
        this.data28 = data28;
    }

    public double getData29() {
        return data29;
    }

    public void setData29(double data29) {
        this.data29 = data29;
    }

    public double getData30() {
        return data30;
    }

    public void setData30(double data30) {
        this.data30 = data30;
    }

    public double getData31() {
        return data31;
    }

    public void setData31(double data31) {
        this.data31 = data31;
    }

    public double getData32() {
        return data32;
    }

    public void setData32(double data32) {
        this.data32 = data32;
    }

    public double getData33() {
        return data33;
    }

    public void setData33(double data33) {
        this.data33 = data33;
    }

    public double getData34() {
        return data34;
    }

    public void setData34(double data34) {
        this.data34 = data34;
    }

    public double getData35() {
        return data35;
    }

    public void setData35(double data35) {
        this.data35 = data35;
    }

    public double getData36() {
        return data36;
    }

    public void setData36(double data36) {
        this.data36 = data36;
    }

    public double getData37() {
        return data37;
    }

    public void setData37(double data37) {
        this.data37 = data37;
    }

    public double getData38() {
        return data38;
    }

    public void setData38(double data38) {
        this.data38 = data38;
    }

    public double getData39() {
        return data39;
    }

    public void setData39(double data39) {
        this.data39 = data39;
    }

    public double getData40() {
        return data40;
    }

    public void setData40(double data40) {
        this.data40 = data40;
    }

    public double getData41() {
        return data41;
    }

    public void setData41(double data41) {
        this.data41 = data41;
    }

    public double getData42() {
        return data42;
    }

    public void setData42(double data42) {
        this.data42 = data42;
    }

    public double getData43() {
        return data43;
    }

    public void setData43(double data43) {
        this.data43 = data43;
    }

    public double getData44() {
        return data44;
    }

    public void setData44(double data44) {
        this.data44 = data44;
    }

    public double getData45() {
        return data45;
    }

    public void setData45(double data45) {
        this.data45 = data45;
    }

    public double getData46() {
        return data46;
    }

    public void setData46(double data46) {
        this.data46 = data46;
    }

    public double getData47() {
        return data47;
    }

    public void setData47(double data47) {
        this.data47 = data47;
    }

    public double getData48() {
        return data48;
    }

    public void setData48(double data48) {
        this.data48 = data48;
    }

    public double getData49() {
        return data49;
    }

    public void setData49(double data49) {
        this.data49 = data49;
    }

    public double getData50() {
        return data50;
    }

    public void setData50(double data50) {
        this.data50 = data50;
    }

    public double getData51() {
        return data51;
    }

    public void setData51(double data51) {
        this.data51 = data51;
    }

    public double getData52() {
        return data52;
    }

    public void setData52(double data52) {
        this.data52 = data52;
    }

    public double getData53() {
        return data53;
    }

    public void setData53(double data53) {
        this.data53 = data53;
    }

    public double getData54() {
        return data54;
    }

    public void setData54(double data54) {
        this.data54 = data54;
    }

    public double getData55() {
        return data55;
    }

    public void setData55(double data55) {
        this.data55 = data55;
    }

    public double getData56() {
        return data56;
    }

    public void setData56(double data56) {
        this.data56 = data56;
    }

    public double getData57() {
        return data57;
    }

    public void setData57(double data57) {
        this.data57 = data57;
    }

    public double getData58() {
        return data58;
    }

    public void setData58(double data58) {
        this.data58 = data58;
    }

    public double getData59() {
        return data59;
    }

    public void setData59(double data59) {
        this.data59 = data59;
    }

    public double getData60() {
        return data60;
    }

    public void setData60(double data60) {
        this.data60 = data60;
    }

    public double getData61() {
        return data61;
    }

    public void setData61(double data61) {
        this.data61 = data61;
    }

    public double getData62() {
        return data62;
    }

    public void setData62(double data62) {
        this.data62 = data62;
    }

    public double getData63() {
        return data63;
    }

    public void setData63(double data63) {
        this.data63 = data63;
    }

    public double getData64() {
        return data64;
    }

    public void setData64(double data64) {
        this.data64 = data64;
    }

    public double getData65() {
        return data65;
    }

    public void setData65(double data65) {
        this.data65 = data65;
    }

    public double getData66() {
        return data66;
    }

    public void setData66(double data66) {
        this.data66 = data66;
    }

    public double getData67() {
        return data67;
    }

    public void setData67(double data67) {
        this.data67 = data67;
    }

    public double getData68() {
        return data68;
    }

    public void setData68(double data68) {
        this.data68 = data68;
    }

    public double getData69() {
        return data69;
    }

    public void setData69(double data69) {
        this.data69 = data69;
    }

    public double getData70() {
        return data70;
    }

    public void setData70(double data70) {
        this.data70 = data70;
    }

    public double getData71() {
        return data71;
    }

    public void setData71(double data71) {
        this.data71 = data71;
    }

    public double getData72() {
        return data72;
    }

    public void setData72(double data72) {
        this.data72 = data72;
    }

    public double getData73() {
        return data73;
    }

    public void setData73(double data73) {
        this.data73 = data73;
    }

    public double getData74() {
        return data74;
    }

    public void setData74(double data74) {
        this.data74 = data74;
    }

    public double getData75() {
        return data75;
    }

    public void setData75(double data75) {
        this.data75 = data75;
    }

    public double getData76() {
        return data76;
    }

    public void setData76(double data76) {
        this.data76 = data76;
    }

    public double getData77() {
        return data77;
    }

    public void setData77(double data77) {
        this.data77 = data77;
    }

    public double getData78() {
        return data78;
    }

    public void setData78(double data78) {
        this.data78 = data78;
    }

    public double getData79() {
        return data79;
    }

    public void setData79(double data79) {
        this.data79 = data79;
    }

    public double getData80() {
        return data80;
    }

    public void setData80(double data80) {
        this.data80 = data80;
    }

    public double getData81() {
        return data81;
    }

    public void setData81(double data81) {
        this.data81 = data81;
    }

    public double getData82() {
        return data82;
    }

    public void setData82(double data82) {
        this.data82 = data82;
    }

    public double getData83() {
        return data83;
    }

    public void setData83(double data83) {
        this.data83 = data83;
    }

    public double getData84() {
        return data84;
    }

    public void setData84(double data84) {
        this.data84 = data84;
    }

    public double getData85() {
        return data85;
    }

    public void setData85(double data85) {
        this.data85 = data85;
    }

    public double getData87() {
        return data87;
    }

    public void setData87(double data87) {
        this.data87 = data87;
    }

    public double getData88() {
        return data88;
    }

    public void setData88(double data88) {
        this.data88 = data88;
    }

    public double getData89() {
        return data89;
    }

    public void setData89(double data89) {
        this.data89 = data89;
    }

    public double getData90() {
        return data90;
    }

    public void setData90(double data90) {
        this.data90 = data90;
    }

    public double getData91() {
        return data91;
    }

    public void setData91(double data91) {
        this.data91 = data91;
    }

    public double getData92() {
        return data92;
    }

    public void setData92(double data92) {
        this.data92 = data92;
    }

    public double getData93() {
        return data93;
    }

    public void setData93(double data93) {
        this.data93 = data93;
    }

    public double getData94() {
        return data94;
    }

    public void setData94(double data94) {
        this.data94 = data94;
    }

    public double getData95() {
        return data95;
    }

    public void setData95(double data95) {
        this.data95 = data95;
    }

    private double data86;
    private double data87;
    private double data88;
    private double data89;
    private double data90;
    private double data91;
    private double data92;
    private double data93;
    private double data94;
    private double data95;

    private List<Double> lst = new LinkedList<Double>();
    private boolean updated = false;

    public void unList(List<Double > list){
        if (list.size()!=96)
            return;;
        updated=false;
        data00=list.get(0);
        data01=list.get(1);
        data02=list.get(2);
        data03=list.get(3);
        data04=list.get(4);
        data05=list.get(5);
        data06=list.get(6);
        data07=list.get(7);
        data08=list.get(8);
        data09=list.get(9);
        data10=list.get(10);
        data11=list.get(11);
        data12=list.get(12);
        data13=list.get(13);
        data14=list.get(14);
        data15=list.get(15);
        data16=list.get(16);
        data17=list.get(17);
        data18=list.get(18);
        data19=list.get(19);
        data20=list.get(20);
        data21=list.get(21);
        data22=list.get(22);
        data23=list.get(23);
        data24=list.get(24);
        data25=list.get(25);
        data26=list.get(26);
        data27=list.get(27);
        data28=list.get(28);
        data29=list.get(29);
        data30=list.get(30);
        data31=list.get(31);
        data32=list.get(32);
        data33=list.get(33);
        data34=list.get(34);
        data35=list.get(35);
        data36=list.get(36);
        data37=list.get(37);
        data38=list.get(38);
        data39=list.get(39);
        data40=list.get(40);
        data41=list.get(41);
        data42=list.get(42);
        data43=list.get(43);
        data44=list.get(44);
        data45=list.get(45);
        data46=list.get(46);
        data47=list.get(47);
        data48=list.get(48);
        data49=list.get(49);
        data50=list.get(50);
        data51=list.get(51);
        data52=list.get(52);
        data53=list.get(53);
        data54=list.get(54);
        data55=list.get(55);
        data56=list.get(56);
        data57=list.get(57);
        data58=list.get(58);
        data59=list.get(59);
        data60=list.get(60);
        data61=list.get(61);
        data62=list.get(62);
        data63=list.get(63);
        data64=list.get(64);
        data65=list.get(65);
        data66=list.get(66);
        data67=list.get(67);
        data68=list.get(68);
        data69=list.get(69);
        data70=list.get(70);
        data71=list.get(71);
        data72=list.get(72);
        data73=list.get(73);
        data74=list.get(74);
        data75=list.get(75);
        data76=list.get(76);
        data77=list.get(77);
        data78=list.get(78);
        data79=list.get(79);
        data80=list.get(80);
        data81=list.get(81);
        data82=list.get(82);
        data83=list.get(83);
        data84=list.get(84);
        data85=list.get(85);
        data86=list.get(86);
        data87=list.get(87);
        data88=list.get(88);
        data89=list.get(89);
        data90=list.get(90);
        data91=list.get(91);
        data92=list.get(92);
        data93=list.get(93);
        data94=list.get(94);
        data95=list.get(95);

    }
    private void update() {
        if (updated == true)
            return;
        updated = true;
        for (int i = 0; i < 96; i++) {
            lst.add(0.);
        }
        lst.set(0, data00);
        lst.set(1, data01);
        lst.set(2, data02);
        lst.set(3, data03);
        lst.set(4, data04);
        lst.set(5, data05);
        lst.set(6, data06);
        lst.set(7, data07);
        lst.set(8, data08);
        lst.set(9, data09);
        lst.set(10, data10);
        lst.set(11, data11);
        lst.set(12, data12);
        lst.set(13, data13);
        lst.set(14, data14);
        lst.set(15, data15);
        lst.set(16, data16);
        lst.set(17, data17);
        lst.set(18, data18);
        lst.set(19, data19);
        lst.set(20, data20);
        lst.set(21, data21);
        lst.set(22, data22);
        lst.set(23, data23);
        lst.set(24, data24);
        lst.set(25, data25);
        lst.set(26, data26);
        lst.set(27, data27);
        lst.set(28, data28);
        lst.set(29, data29);
        lst.set(30, data30);
        lst.set(31, data31);
        lst.set(32, data32);
        lst.set(33, data33);
        lst.set(34, data34);
        lst.set(35, data35);
        lst.set(36, data36);
        lst.set(37, data37);
        lst.set(38, data38);
        lst.set(39, data39);
        lst.set(40, data40);
        lst.set(41, data41);
        lst.set(42, data42);
        lst.set(43, data43);
        lst.set(44, data44);
        lst.set(45, data45);
        lst.set(46, data46);
        lst.set(47, data47);
        lst.set(48, data48);
        lst.set(49, data49);
        lst.set(50, data50);
        lst.set(51, data51);
        lst.set(52, data52);
        lst.set(53, data53);
        lst.set(54, data54);
        lst.set(55, data55);
        lst.set(56, data56);
        lst.set(57, data57);
        lst.set(58, data58);
        lst.set(59, data59);
        lst.set(60, data60);
        lst.set(61, data61);
        lst.set(62, data62);
        lst.set(63, data63);
        lst.set(64, data64);
        lst.set(65, data65);
        lst.set(66, data66);
        lst.set(67, data67);
        lst.set(68, data68);
        lst.set(69, data69);
        lst.set(70, data70);
        lst.set(71, data71);
        lst.set(72, data72);
        lst.set(73, data73);
        lst.set(74, data74);
        lst.set(75, data75);
        lst.set(76, data76);
        lst.set(77, data77);
        lst.set(78, data78);
        lst.set(79, data79);
        lst.set(80, data80);
        lst.set(81, data81);
        lst.set(82, data82);
        lst.set(83, data83);
        lst.set(84, data84);
        lst.set(85, data85);
        lst.set(86, data86);
        lst.set(87, data87);
        lst.set(88, data88);
        lst.set(89, data89);
        lst.set(90, data90);
        lst.set(91, data91);
        lst.set(92, data92);
        lst.set(93, data93);
        lst.set(94, data94);
        lst.set(95, data95);
    }

    public void dbgPrint() {
        System.out.println(getName() + "," + getDateString());
        this.update();
        for (int j = 0; j < 24; j++) {
            System.out.println(j + ":");
            for (int i = 0; i < 4; i++) {
                System.out.print(lst.get(j * 4 + i) + ",");
            }
            System.out.println("");
        }

    }


    @Override
    public List<Double> toList() {
        this.update();
        return lst;
    }

    @Override
    public MaxAveMinTuple<Double> toMaxAveMin() {
        MaxAveMinTuple<Double> v = new MaxAveMinTuple<Double>("load_data");
        v.max = this.getMax();
        v.ave = this.getAve();
        v.min = this.getMin();
        return v;
    }

    @Override
    public void print(PrintStream ps) {
        update();
        try {

            ps.print("负荷数据  :");
            LoadData next = this;
            while (next != null) {
                ps.print(next.getDateString() + "    ");
                next = next.getNext();
            }
            ps.print("\n");
            for (int i = 0; i < 96; i++) {
                ps.print(TimeLabels.labels[i] + " : ");
                next = this;
                while (next != null) {
                    ps.printf("%4.4f        ", next.toList().get(i));
                    next = next.getNext();
                }
                ps.print("\n");
            }

        } catch (Exception e) {

        }
    }

    public MaxAveMinTuple<Double> getPerUnits(LoadBase loadBase) {
        MaxAveMinTuple<Double> lodaBaseTuple = loadBase.toMaxAveMin();
        MaxAveMinTuple<Double> loadDataMaxAveMinTuple = new MaxAveMinTuple<Double>("default");

        loadDataMaxAveMinTuple.max = getMax() / lodaBaseTuple.max;
        loadDataMaxAveMinTuple.ave = getAve() / lodaBaseTuple.ave;
        loadDataMaxAveMinTuple.min = getMin() / lodaBaseTuple.min;


        return loadDataMaxAveMinTuple;
    }

    public LoadData multiple(Double v) {
        LoadData loadData = new LoadData();
        loadData.setDateString(this.getDateString());
        loadData.setData00(this.getData00() * v);
        loadData.setData01(this.getData01() * v);
        loadData.setData02(this.getData02() * v);
        loadData.setData03(this.getData03() * v);
        loadData.setData04(this.getData04() * v);
        loadData.setData05(this.getData05() * v);
        loadData.setData06(this.getData06() * v);
        loadData.setData07(this.getData07() * v);
        loadData.setData08(this.getData08() * v);
        loadData.setData09(this.getData09() * v);
        loadData.setData10(this.getData10() * v);
        loadData.setData11(this.getData11() * v);
        loadData.setData12(this.getData12() * v);
        loadData.setData13(this.getData13() * v);
        loadData.setData14(this.getData14() * v);
        loadData.setData15(this.getData15() * v);
        loadData.setData16(this.getData16() * v);
        loadData.setData17(this.getData17() * v);
        loadData.setData18(this.getData18() * v);
        loadData.setData19(this.getData19() * v);
        loadData.setData20(this.getData20() * v);
        loadData.setData21(this.getData21() * v);
        loadData.setData22(this.getData22() * v);
        loadData.setData23(this.getData23() * v);
        loadData.setData24(this.getData24() * v);
        loadData.setData25(this.getData25() * v);
        loadData.setData26(this.getData26() * v);
        loadData.setData27(this.getData27() * v);
        loadData.setData28(this.getData28() * v);
        loadData.setData29(this.getData29() * v);
        loadData.setData30(this.getData30() * v);
        loadData.setData31(this.getData31() * v);
        loadData.setData32(this.getData32() * v);
        loadData.setData33(this.getData33() * v);
        loadData.setData34(this.getData34() * v);
        loadData.setData35(this.getData35() * v);
        loadData.setData36(this.getData36() * v);
        loadData.setData37(this.getData37() * v);
        loadData.setData38(this.getData38() * v);
        loadData.setData39(this.getData39() * v);
        loadData.setData40(this.getData40() * v);
        loadData.setData41(this.getData41() * v);
        loadData.setData42(this.getData42() * v);
        loadData.setData43(this.getData43() * v);
        loadData.setData44(this.getData44() * v);
        loadData.setData45(this.getData45() * v);
        loadData.setData46(this.getData46() * v);
        loadData.setData47(this.getData47() * v);
        loadData.setData48(this.getData48() * v);
        loadData.setData49(this.getData49() * v);
        loadData.setData50(this.getData50() * v);
        loadData.setData51(this.getData51() * v);
        loadData.setData52(this.getData52() * v);
        loadData.setData53(this.getData53() * v);
        loadData.setData54(this.getData54() * v);
        loadData.setData55(this.getData55() * v);
        loadData.setData56(this.getData56() * v);
        loadData.setData57(this.getData57() * v);
        loadData.setData58(this.getData58() * v);
        loadData.setData59(this.getData59() * v);
        loadData.setData60(this.getData60() * v);
        loadData.setData61(this.getData61() * v);
        loadData.setData62(this.getData62() * v);
        loadData.setData63(this.getData63() * v);
        loadData.setData64(this.getData64() * v);
        loadData.setData65(this.getData65() * v);
        loadData.setData66(this.getData66() * v);
        loadData.setData67(this.getData67() * v);
        loadData.setData68(this.getData68() * v);
        loadData.setData69(this.getData69() * v);
        loadData.setData70(this.getData70() * v);
        loadData.setData71(this.getData71() * v);
        loadData.setData72(this.getData72() * v);
        loadData.setData73(this.getData73() * v);
        loadData.setData74(this.getData74() * v);
        loadData.setData75(this.getData75() * v);
        loadData.setData76(this.getData76() * v);
        loadData.setData77(this.getData77() * v);
        loadData.setData78(this.getData78() * v);
        loadData.setData79(this.getData79() * v);
        loadData.setData80(this.getData80() * v);
        loadData.setData81(this.getData81() * v);
        loadData.setData82(this.getData82() * v);
        loadData.setData83(this.getData83() * v);
        loadData.setData84(this.getData84() * v);
        loadData.setData85(this.getData85() * v);
        loadData.setData86(this.getData86() * v);
        loadData.setData87(this.getData87() * v);
        loadData.setData88(this.getData88() * v);
        loadData.setData89(this.getData89() * v);
        loadData.setData90(this.getData90() * v);
        loadData.setData91(this.getData91() * v);
        loadData.setData92(this.getData92() * v);
        loadData.setData93(this.getData93() * v);
        loadData.setData94(this.getData94() * v);
        loadData.setData95(this.getData95() * v);

        return loadData;
    }

    public Object accept(LoadDataVisitor visitor) {
        return visitor.visit(this);
    }

    public PredictionLoadData convertLower() {
        PredictionLoadData predictionLoadData = new PredictionLoadData();
        predictionLoadData.setData00(this.getData00());
        predictionLoadData.setData01(this.getData01());
        predictionLoadData.setData02(this.getData02());
        predictionLoadData.setData03(this.getData03());
        predictionLoadData.setData04(this.getData04());
        predictionLoadData.setData05(this.getData05());
        predictionLoadData.setData06(this.getData06());
        predictionLoadData.setData07(this.getData07());
        predictionLoadData.setData08(this.getData08());
        predictionLoadData.setData09(this.getData09());
        predictionLoadData.setData10(this.getData10());
        predictionLoadData.setData11(this.getData11());
        predictionLoadData.setData12(this.getData12());
        predictionLoadData.setData13(this.getData13());
        predictionLoadData.setData14(this.getData14());
        predictionLoadData.setData15(this.getData15());
        predictionLoadData.setData16(this.getData16());
        predictionLoadData.setData17(this.getData17());
        predictionLoadData.setData18(this.getData18());
        predictionLoadData.setData19(this.getData19());
        predictionLoadData.setData20(this.getData20());
        predictionLoadData.setData21(this.getData21());
        predictionLoadData.setData22(this.getData22());
        predictionLoadData.setData23(this.getData23());
        predictionLoadData.setData24(this.getData24());
        predictionLoadData.setData25(this.getData25());
        predictionLoadData.setData26(this.getData26());
        predictionLoadData.setData27(this.getData27());
        predictionLoadData.setData28(this.getData28());
        predictionLoadData.setData29(this.getData29());
        predictionLoadData.setData30(this.getData30());
        predictionLoadData.setData31(this.getData31());
        predictionLoadData.setData32(this.getData32());
        predictionLoadData.setData33(this.getData33());
        predictionLoadData.setData34(this.getData34());
        predictionLoadData.setData35(this.getData35());
        predictionLoadData.setData36(this.getData36());
        predictionLoadData.setData37(this.getData37());
        predictionLoadData.setData38(this.getData38());
        predictionLoadData.setData39(this.getData39());
        predictionLoadData.setData40(this.getData40());
        predictionLoadData.setData41(this.getData41());
        predictionLoadData.setData42(this.getData42());
        predictionLoadData.setData43(this.getData43());
        predictionLoadData.setData44(this.getData44());
        predictionLoadData.setData45(this.getData45());
        predictionLoadData.setData46(this.getData46());
        predictionLoadData.setData47(this.getData47());
        predictionLoadData.setData48(this.getData48());
        predictionLoadData.setData49(this.getData49());
        predictionLoadData.setData50(this.getData50());
        predictionLoadData.setData51(this.getData51());
        predictionLoadData.setData52(this.getData52());
        predictionLoadData.setData53(this.getData53());
        predictionLoadData.setData54(this.getData54());
        predictionLoadData.setData55(this.getData55());
        predictionLoadData.setData56(this.getData56());
        predictionLoadData.setData57(this.getData57());
        predictionLoadData.setData58(this.getData58());
        predictionLoadData.setData59(this.getData59());
        predictionLoadData.setData60(this.getData60());
        predictionLoadData.setData61(this.getData61());
        predictionLoadData.setData62(this.getData62());
        predictionLoadData.setData63(this.getData63());
        predictionLoadData.setData64(this.getData64());
        predictionLoadData.setData65(this.getData65());
        predictionLoadData.setData66(this.getData66());
        predictionLoadData.setData67(this.getData67());
        predictionLoadData.setData68(this.getData68());
        predictionLoadData.setData69(this.getData69());
        predictionLoadData.setData70(this.getData70());
        predictionLoadData.setData71(this.getData71());
        predictionLoadData.setData72(this.getData72());
        predictionLoadData.setData73(this.getData73());
        predictionLoadData.setData74(this.getData74());
        predictionLoadData.setData75(this.getData75());
        predictionLoadData.setData76(this.getData76());
        predictionLoadData.setData77(this.getData77());
        predictionLoadData.setData78(this.getData78());
        predictionLoadData.setData79(this.getData79());
        predictionLoadData.setData80(this.getData80());
        predictionLoadData.setData81(this.getData81());
        predictionLoadData.setData82(this.getData82());
        predictionLoadData.setData83(this.getData83());
        predictionLoadData.setData84(this.getData84());
        predictionLoadData.setData85(this.getData85());
        predictionLoadData.setData86(this.getData86());
        predictionLoadData.setData87(this.getData87());
        predictionLoadData.setData88(this.getData88());
        predictionLoadData.setData89(this.getData89());
        predictionLoadData.setData90(this.getData90());
        predictionLoadData.setData91(this.getData91());
        predictionLoadData.setData92(this.getData92());
        predictionLoadData.setData93(this.getData93());
        predictionLoadData.setData94(this.getData94());
        predictionLoadData.setData95(this.getData95());
        predictionLoadData.setDateString(this.getDateString());
        return predictionLoadData;
    }


    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }

    public LoadData clone() {
        LoadData predictionLoadData = new LoadData();
        predictionLoadData.setData00(this.getData00());
        predictionLoadData.setData01(this.getData01());
        predictionLoadData.setData02(this.getData02());
        predictionLoadData.setData03(this.getData03());
        predictionLoadData.setData04(this.getData04());
        predictionLoadData.setData05(this.getData05());
        predictionLoadData.setData06(this.getData06());
        predictionLoadData.setData07(this.getData07());
        predictionLoadData.setData08(this.getData08());
        predictionLoadData.setData09(this.getData09());
        predictionLoadData.setData10(this.getData10());
        predictionLoadData.setData11(this.getData11());
        predictionLoadData.setData12(this.getData12());
        predictionLoadData.setData13(this.getData13());
        predictionLoadData.setData14(this.getData14());
        predictionLoadData.setData15(this.getData15());
        predictionLoadData.setData16(this.getData16());
        predictionLoadData.setData17(this.getData17());
        predictionLoadData.setData18(this.getData18());
        predictionLoadData.setData19(this.getData19());
        predictionLoadData.setData20(this.getData20());
        predictionLoadData.setData21(this.getData21());
        predictionLoadData.setData22(this.getData22());
        predictionLoadData.setData23(this.getData23());
        predictionLoadData.setData24(this.getData24());
        predictionLoadData.setData25(this.getData25());
        predictionLoadData.setData26(this.getData26());
        predictionLoadData.setData27(this.getData27());
        predictionLoadData.setData28(this.getData28());
        predictionLoadData.setData29(this.getData29());
        predictionLoadData.setData30(this.getData30());
        predictionLoadData.setData31(this.getData31());
        predictionLoadData.setData32(this.getData32());
        predictionLoadData.setData33(this.getData33());
        predictionLoadData.setData34(this.getData34());
        predictionLoadData.setData35(this.getData35());
        predictionLoadData.setData36(this.getData36());
        predictionLoadData.setData37(this.getData37());
        predictionLoadData.setData38(this.getData38());
        predictionLoadData.setData39(this.getData39());
        predictionLoadData.setData40(this.getData40());
        predictionLoadData.setData41(this.getData41());
        predictionLoadData.setData42(this.getData42());
        predictionLoadData.setData43(this.getData43());
        predictionLoadData.setData44(this.getData44());
        predictionLoadData.setData45(this.getData45());
        predictionLoadData.setData46(this.getData46());
        predictionLoadData.setData47(this.getData47());
        predictionLoadData.setData48(this.getData48());
        predictionLoadData.setData49(this.getData49());
        predictionLoadData.setData50(this.getData50());
        predictionLoadData.setData51(this.getData51());
        predictionLoadData.setData52(this.getData52());
        predictionLoadData.setData53(this.getData53());
        predictionLoadData.setData54(this.getData54());
        predictionLoadData.setData55(this.getData55());
        predictionLoadData.setData56(this.getData56());
        predictionLoadData.setData57(this.getData57());
        predictionLoadData.setData58(this.getData58());
        predictionLoadData.setData59(this.getData59());
        predictionLoadData.setData60(this.getData60());
        predictionLoadData.setData61(this.getData61());
        predictionLoadData.setData62(this.getData62());
        predictionLoadData.setData63(this.getData63());
        predictionLoadData.setData64(this.getData64());
        predictionLoadData.setData65(this.getData65());
        predictionLoadData.setData66(this.getData66());
        predictionLoadData.setData67(this.getData67());
        predictionLoadData.setData68(this.getData68());
        predictionLoadData.setData69(this.getData69());
        predictionLoadData.setData70(this.getData70());
        predictionLoadData.setData71(this.getData71());
        predictionLoadData.setData72(this.getData72());
        predictionLoadData.setData73(this.getData73());
        predictionLoadData.setData74(this.getData74());
        predictionLoadData.setData75(this.getData75());
        predictionLoadData.setData76(this.getData76());
        predictionLoadData.setData77(this.getData77());
        predictionLoadData.setData78(this.getData78());
        predictionLoadData.setData79(this.getData79());
        predictionLoadData.setData80(this.getData80());
        predictionLoadData.setData81(this.getData81());
        predictionLoadData.setData82(this.getData82());
        predictionLoadData.setData83(this.getData83());
        predictionLoadData.setData84(this.getData84());
        predictionLoadData.setData85(this.getData85());
        predictionLoadData.setData86(this.getData86());
        predictionLoadData.setData87(this.getData87());
        predictionLoadData.setData88(this.getData88());
        predictionLoadData.setData89(this.getData89());
        predictionLoadData.setData90(this.getData90());
        predictionLoadData.setData91(this.getData91());
        predictionLoadData.setData92(this.getData92());
        predictionLoadData.setData93(this.getData93());
        predictionLoadData.setData94(this.getData94());
        predictionLoadData.setData95(this.getData95());
        predictionLoadData.setDateString(this.getDateString());
        return predictionLoadData;
    }
}
