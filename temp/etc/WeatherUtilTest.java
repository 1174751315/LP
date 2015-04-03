/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package test.java.etc;

import main.java.loadPrediction.timerTask.TimerTask4FetchingAndCalcingWeatherData;
import org.junit.Test;

public class WeatherUtilTest {

    @Test
    public void testCalcWeatherData() throws Exception {
//       new TimerTask4FetchingAndCalcingWeatherData().run();
//       WeatherUtil.getWeatherDataFormDb(("2015-03-12")).print(System.out);
        new TimerTask4FetchingAndCalcingWeatherData().run();

    }
}