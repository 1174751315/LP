/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;


import common.ElementPrintableLinkedList;
import loadPrediction.adm.user.User;
import loadPrediction.resouce.IOPaths;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * 李倍存 创建于 2015-02-20 17:29。电邮 1174751315@qq.com。
 */
public class XmlConfigure implements IConfigure {
    public XmlConfigure() {

    }

    public XmlConfigure(String xmlFilePath) {
        this.cfgFilePath = xmlFilePath;
    }

    private String defaultCfgFilePath = IOPaths.PREDICTION_CFG_FILES_DIR;
    private String cfgFilePath = this.defaultCfgFilePath;


    private Boolean needsUpdate = true;
    private AllConfiguration configuration = new AllConfiguration();

    /**
     * 修改默认的XML文件路径和文件名。
     *
     * @param newXmlFilePath 新的文件绝对路径。
     */
    public void setXmlFilePath(String newXmlFilePath) {
        this.cfgFilePath = newXmlFilePath;
        needsUpdate = true;
    }

    /**
     * 获取由XML指定的配置。
     *
     * @return 在默认路径中load_prediction_cfg.xml文件指定的配置信息。
     */
    @Override
    public AllConfiguration getAllConfiguration() {
        if (!needsUpdate)
            return configuration;
        SAXReader saxReader = new SAXReader();
        org.dom4j.Document document;
        try {
            document = saxReader.read(new File(cfgFilePath));
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

        Element root = document.getRootElement();
        getPredictorsCfg(configuration, root.element("predictors"));
        getUsersCfg(configuration, root.element("users"));
        getOutputsCfg(configuration, root.element("outputs"));
        getCachesCfg(configuration, root.element("caches"));
        needsUpdate = false;
        return configuration;
    }

    private void getPredictorsCfg(AllConfiguration cfg, Element root) {
        WorkdayPredictorCfg workdayCfg = new WorkdayPredictorCfg();
        WeekendPredictorCfg weekendCfg = new WeekendPredictorCfg();
        FestivalPredictorCfg festivalCfg = new FestivalPredictorCfg();

        TestCfg testCfg1 = new TestCfg();
        System.out.println(root.element("workday-predictor").attribute("enabled") != null);
        workdayCfg.setIsEnabled(Boolean.valueOf(root.element("workday-predictor").attribute("enabled").getValue()));
        workdayCfg.setPredictionWorkdayNumber(Integer.valueOf(root.element("workday-predictor").element("prediction-workday-number").getText()));
        workdayCfg.setHistoryWorkdayNumber(Integer.valueOf(root.element("workday-predictor").element("history-workday-number").getText()));
        testCfg1.setAccuracyThreshold(Double.valueOf(root.element("workday-predictor").element("test").element("accuracy-threshold").getText()));
        testCfg1.setAccuracyCounter(Integer.valueOf(root.element("workday-predictor").element("test").element("accuracy-counter").getText()));
        workdayCfg.setTestCfg(testCfg1);

        TestCfg testCfg2 = new TestCfg();
        weekendCfg.setIsEnabled(Boolean.valueOf(root.element("weekend-predictor").attribute("enabled").getValue()));
        weekendCfg.setHistoryWorkdayNumber(Integer.valueOf(root.element("weekend-predictor").element("history-workday-number").getText()));
        weekendCfg.setHistoryWeekendNumber(Integer.valueOf(root.element("weekend-predictor").element("history-weekend-number").getText()));
        weekendCfg.setPredictionWeekendNumber(Integer.valueOf(root.element("weekend-predictor").element("prediction-weekend-number").getText()));
        testCfg2.setAccuracyThreshold(Double.valueOf(root.element("weekend-predictor").element("test").element("accuracy-threshold").getText()));
        testCfg2.setAccuracyCounter(Integer.valueOf(root.element("weekend-predictor").element("test").element("accuracy-counter").getText()));
        weekendCfg.setTestCfg(testCfg2);

        TestCfg testCfg3 = new TestCfg();
        festivalCfg.setIsEnabled(Boolean.valueOf(root.element("festival-predictor").attribute("enabled").getValue()));
        festivalCfg.setHistoryFestivalNumber(Integer.valueOf(root.element("festival-predictor").element("history-festival-number").getText()));
        festivalCfg.setPredictionFestivalNumber(Integer.valueOf(root.element("festival-predictor").element("prediction-festival-number").getText()));
        testCfg3.setAccuracyThreshold(Double.valueOf(root.element("festival-predictor").element("test").element("accuracy-threshold").getText()));
        testCfg3.setAccuracyCounter(Integer.valueOf(root.element("festival-predictor").element("test").element("accuracy-counter").getText()));
        festivalCfg.setTestCfg(testCfg3);

        cfg.setWorkdayPredictorCfg(workdayCfg);
        cfg.setWeekendPredictorCfg(weekendCfg);
        cfg.setFestivalPredictorCfg(festivalCfg);
    }

    private void getUsersCfg(AllConfiguration cfg, Element root) {
        ElementPrintableLinkedList<User> users = new ElementPrintableLinkedList<User>("");
        List list = root.elements();
        for (int i = 0; i < list.size(); i++) {
            Element ele = (Element) list.get(i);
            User user = new User();

            user.setUsername(ele.element("username").getText());
            user.setPassword(ele.element("password").getText());
            users.add(user);
        }
        cfg.setUsers(users);
    }

    private void getOutputsCfg(AllConfiguration cfg, Element root) {
        ElementPrintableLinkedList<OutputCfg> outputs = new ElementPrintableLinkedList<OutputCfg>("");
        List list = root.elements();
        for (int i = 0; i < list.size(); i++) {
            Element ele = (Element) list.get(i);
            OutputCfg output = new OutputCfg();

            output.setDestination(ele.attribute("destination").getValue());
            outputs.add(output);
        }
        cfg.setOutputCfgs(outputs);
    }

    private void getCachesCfg(AllConfiguration cfg, Element root) {


        CacheContent predictionContent = new CacheContent(root.element("prediction-caches").element("data-dir").getText(), root.element("prediction-caches").element("content-file-path").getText());
        CacheContent accuracyCheckingContent = new CacheContent(root.element("accuracy-checking-caches").element("data-dir").getText(), root.element("accuracy-checking-caches").element("content-file-path").getText());

        CachesCfg cachesCfg = new CachesCfg(predictionContent, accuracyCheckingContent);

        cfg.setCachesCfg(cachesCfg);

    }

}
