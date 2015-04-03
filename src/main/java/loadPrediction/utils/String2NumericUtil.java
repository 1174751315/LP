/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.utils;

/**
 * 李倍存 创建于 2015-02-21 13:28。电邮 1174751315@qq.com。
 */
public class String2NumericUtil {
    public String2NumericUtil() {
    }

    public static Integer toInteger(String string) {
        Double intValue = 0.;
        Integer intLen = string.length();
        for (int j = 0; j < intLen; j++)    //计算整数部分的值//
            intValue += (string.charAt(j) - '0') * Math.round((Double) Math.pow(10, intLen - 1 - j));
        return intValue.intValue();

    }

    public static Boolean toBoolean(String string) {

        String upper = string.toUpperCase();
        return upper.equals("TRUE");

    }

    public static Double toDouble(String string) {
        return Double.valueOf(string);
    }
}
