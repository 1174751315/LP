/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.config.predictionCalculator;

import common.IPrintable;
import prediction.exception.LPE;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * 李倍存 创建于 2015-03-04 18:11。电邮 1174751315@qq.com。
 */
public class XmlCalculatorCfg implements ICalculatorCfgReader, IPrintable {
    private List<Map<String, Double>> coes = new LinkedList<Map<String, Double>>();

    public List<Map<String, Double>> getConfiguration() {
        return coes;
    }

    public XmlCalculatorCfg(String xmlFilePath) throws LPE {

        try {
            Document document = new SAXReader().read(new File(xmlFilePath));
            Element root = document.getRootElement();
            List<String> factors = new LinkedList<String>();
            List t = root.elements("factor");
            for (int i = 0; i < t.size(); i++) {
                factors.add(((Element) t.get(i)).attribute("type").getValue());
            }

            Integer c = factors.size();
            for (int i = 0; i < 96; i++) {
                Map<String, Double> m = new HashMap<String, Double>();
                unnamed(m);
                String s = root.element("data" + i).getText();

                String[] s_coes = s.split(",");
                if (s_coes.length != c)
                    throw new LPE("预测计算器配置文件错误。请检查  data" + i + "元素。");
                for (int j = 0; j < s_coes.length; j++) {
                    m.put(factors.get(j), Double.valueOf(s_coes[j]));
                }
                coes.add(m);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void unnamed(Map<String, Double> map) {
        map.put("similar-load-point", 0.);
        map.put("correct-coe-max", 0.);
        map.put("correct-coe-min", 0.);
        map.put("constant-1", 0.);
    }

    @Override
    public void print(PrintStream ps) {
        for (int i = 0; i < coes.size(); i++) {
            Collection<Double> c = coes.get(i).values();
            Set<String> k = coes.get(i).keySet();
            ps.print("【data" + i + "】");
            for (int j = 0; j < c.size(); j++) {
                ps.print(k.toArray()[j].toString() + ":" + c.toArray()[j] + ",");
            }
            ps.println();
        }
    }
}
