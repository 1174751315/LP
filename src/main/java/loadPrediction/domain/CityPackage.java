/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.domain;

import common.IPrintable;
import loadPrediction.dataAccess.DAOFactory;
import loadPrediction.resouce.CityNames;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 李倍存 创建于 2015/3/11 20:52。电邮 1174751315@qq.com。
 */
public class CityPackage implements IPrintable {

    public CityPackage() throws Exception {
        if (f == false) {
            for (int i = 0; i < CityNames.ids.length; i++) {

                City c = DAOFactory.getDefault().createDaoCity().query(CityNames.ids[i]);
                citiesName.put(CityNames.names[i], c);
                citiesId.put(CityNames.ids[i], c);
            }
            f = true;
        }

    }

    private static boolean f = false;
    private static Map<String, City> citiesName = new HashMap<String, City>();
    private static Map<Integer, City> citiesId = new HashMap<Integer, City>();


    public City getCityByName(String name) {
        return citiesName.get(name);
    }

    public City getCityById(Integer id) {
        return citiesId.get(id);
    }

    @Override
    public void print(PrintStream ps) {

        for (int i = 0; i < CityNames.ids.length; i++) {
            citiesId.get(CityNames.ids[i]).print(ps);
        }
    }
}
