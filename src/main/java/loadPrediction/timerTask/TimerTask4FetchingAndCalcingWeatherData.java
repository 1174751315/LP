/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.timerTask;

import  loadPrediction.resouce.IOPaths;
import  loadPrediction.weather.WeatherUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TimerTask;

/**
 * 李倍存 创建于 2015/3/12 10:18。电邮 1174751315@qq.com。
 */
public class TimerTask4FetchingAndCalcingWeatherData extends TimerTask {
    public TimerTask4FetchingAndCalcingWeatherData() {
    }

    @Override
    public void run() {

        try {
            /*首先检查气象预报数据库是否有遗留数据，若有则处理之。*/
            WeatherUtil.getWeatherDataFormDb().print(System.err);

            /*同步气象预报远程数据库至本地ORACLE。*/
            Process pr = Runtime.getRuntime().exec("python " + IOPaths.PYTHON_SCRIPT_SYNC_WEATHER);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();

            /*再次检查气象预报数据库，若有数据则处理之。*/
            WeatherUtil.getWeatherDataFormDb().print(System.err);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
