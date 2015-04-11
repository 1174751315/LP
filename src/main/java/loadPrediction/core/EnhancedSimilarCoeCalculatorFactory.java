/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core;

import loadPrediction.domain.WeatherCoesPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建：2015/2/19 15:16
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class EnhancedSimilarCoeCalculatorFactory {
    private static EnhancedSimilarCoeCalculatorFactory instance = new EnhancedSimilarCoeCalculatorFactory();
    private Map<String, EnhancedSimilarCoeCalculator> pool;

    public static EnhancedSimilarCoeCalculatorFactory getInstance() {
        return instance;
    }

    private EnhancedSimilarCoeCalculatorFactory() {
        pool = new HashMap<String, EnhancedSimilarCoeCalculator>();
        pool.put("basic", new EnhancedSimilarCoeCalculator(new WeatherCoesPackage("WORKDAY")));
    }

    public EnhancedSimilarCoeCalculator getBasic() {
        return pool.get("basic");
    }
}
