/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.resouce;

/**
 * Created by LBC on 2015/2/11.
 */
public class IOPaths {
    private static final String WORKSPACE_ROOT = "E:\\IdeaProjects\\";
    public static final String PYTHON_SCRIPT_SYNC_WEATHER = WORKSPACE_ROOT + "load_python\\Syncb.py";


    public static final String WEB_CONTENT_ROOT = "D:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\LPU\\";//WEB根目录
    private static final String WEB_CONTENT_NAME = WEB_CONTENT_ROOT.substring(WEB_CONTENT_ROOT.substring(WEB_CONTENT_ROOT.length() - 1).lastIndexOf("\\") + 1);
    public static final String WEB_CONTENT_CACHES = WEB_CONTENT_ROOT + "CACHES\\";//WEB的CACHES目录，用于保存缓存记录
    public static final String WEB_CONTENT_TEMP = WEB_CONTENT_ROOT + "TEMP\\";//WEB的TEMP目录，用于缓存数据或者其他
    public static final String WEB_CONTENT_CONFIG = WEB_CONTENT_ROOT + "CONFIG\\";//WEB的CONFIG目录，用于保存配置文件
    public static final String WEB_CONTENT_WORKDAY_WINTER_TEMPLATE_PATH = WEB_CONTENT_CONFIG + "template_workday_winter.xls";
    public static final String WEB_CONTENT_WORKDAY_SUMMER_TEMPLATE_PATH = WEB_CONTENT_CONFIG + "template_workday_summer.xls";
    public static final String WEB_CONTENT_WEEKEND_TEMPLATE_PATH = WEB_CONTENT_CONFIG + "template_weekend.xls";
    public static final String WORKDAY_PREDICTION_CALCULATOR_CONFIGURATION = WEB_CONTENT_CONFIG + "workday_prediction_calculator_cfg.xml";
    public static final String PREDICTION_CFG_FILES_DIR = WEB_CONTENT_CONFIG + "load_prediction_cfg.xml";
    public static final String WEB_CONTENT_WORKDAY_OUTPUT_TEMPLATE_XL_PATH=WEB_CONTENT_CONFIG +  "template.xls";
    public static final String WEB_CONTENT_WEEKEND_OUTPUT_TEMPLATE_XL_PATH=WEB_CONTENT_WORKDAY_OUTPUT_TEMPLATE_XL_PATH;
    public static final String WEB_CONTENT_UI_CFG_PATH=WEB_CONTENT_CONFIG+"ui_cfg.xml";

    public static final String WEB_CONTENT_LOG_ROOT=WEB_CONTENT_ROOT+"LOG\\";

    public static String WEB_RELATIVE_PATH_OF(String content) {
        return WEB_CONTENT_NAME + content;
    }

    public static final String WEB_CONTENT_LOG4J_PROPERTIES_PATH = WEB_CONTENT_CONFIG + "log.properties";

}
