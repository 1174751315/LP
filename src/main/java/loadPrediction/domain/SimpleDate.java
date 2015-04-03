/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import  common.IPrintable;
import  loadPrediction.domain.visitors.IDomainVisitor;
import  loadPrediction.utils.Date2StringAdapter;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Date;

/**
 * 李倍存 创建于 2015/1/23 17:19。电邮 1174751315@qq.com。
 */
public class SimpleDate implements IDomain, Serializable, IPrintable, Comparable<SimpleDate> {
    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public SimpleDate() {
    }

    public SimpleDate(String dateString, SimpleDateType dateType, SimpleDateWeatherType weatherType) {
        this.dateString = dateString;
        this.dateType = dateType;
        this.weatherType = weatherType;
    }

    //    private String month;
//    private String year;
//    private String day;
//    public String getDefaulteDateString(){
//        return year+"-"+month+"-"+day;
//    }
//    public String getDateString(String divChar){
//        return year+divChar+month+divChar+day;
//    }
//    public String getDateString(String divChar,boolean showZeroPreffix){
//        if (showZeroPreffix)
//            return year+divChar+month+divChar+day;
//        return year+divChar+(month.charAt(0)=='0'?month.substring(1,2):month)+divChar+(day.charAt(0)=='0'?day.substring(1,2):day);
//    }
//    public String getMonth() {
//        return month;
//    }
//
//    public void setMonth(String month) {
//        if (month.length()!=2)
//            return;
//        this.month = month;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        if (year.length()!=4)
//            return;
//        this.year = year;
//    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        if (day.length()!=2)
//            return;
//        this.day = day;
//    }
//    public SimpleDate(String month, String year, String day) {
//        this.month = month;
//        this.year = year;
//        this.day = day;
//    }
//    public SimpleDate(String date){
//        if (date.length()!=10)
//            return;
//        year=date.substring(0,4);
//        month=date.substring(5,7);
//        day=date.substring(8,10);
//    }
    private String dateString;

    public Date getDate() {
        return Date2StringAdapter.toDate(dateString);
    }

    private SimpleDateType dateType;
    private SimpleDateWeatherType weatherType;

    public SimpleDateType getDateType() {
        return dateType;
    }

    public void setDateType(SimpleDateType dateType) {
        this.dateType = dateType;
    }

    public SimpleDateWeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(SimpleDateWeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public void dbgPrint() {
        System.out.println(getDateString() + ": " + getDateType().getName() + "," + getWeatherType().getName());
    }

    @Override
    public void print(PrintStream ps) {
        ps.printf("[  " + dateString + "  ]  [  " + dateType.getName() + "  ]  [  " + weatherType.getName() + "  ]\n");
    }

    @Override
    public int compareTo(SimpleDate anotherSimpleDate) {
        return 0;
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
