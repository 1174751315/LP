/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.exception.LPE;
import org.junit.Test;

import java.util.Locale;

public class LPETest {

    @Test
    public void testPrintStackTrace() throws Exception {
        try {
            Locale locale = Locale.CHINESE;
            System.err.println(locale.getUnicodeLocaleKeys());
            throw new LPE("测试");
        } catch (LPE e) {
            e.printStackTrace();
        }
    }
}