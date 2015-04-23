/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.action;

import com.opensymphony.xwork2.ActionSupport;
import prediction.core.cache.CachesManager;
import prediction.core.cache.PredictionCacheEntity;
import prediction.core.predictor.IPredictor;
import prediction.core.predictor.PredictorFactory;
import prediction.core.predictor.visitors.FirstPredictionLoadData2DBVisitor;
import prediction.core.predictor.visitors.PredictionLoad24LinePictureVisitor_1;
import prediction.core.predictor.visitors.PredictionLoad2ReportPictureVisitor;
import prediction.exception.LPE;
import prediction.resouce.IOPaths;
import prediction.utils.FileContentUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;


/**
 * 创建：2015/1/23 20:45
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class PredictionAction extends ActionSupport {
    private JFreeChart chart;

    public JFreeChart getChart() {
        return chart;
    }

    public String getRoot() {
        root = "TEMP/";
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    private String imgFileName;
    private String rptImgName;

    public String getRptImgName() {
        return rptImgName;
    }

    private String xlFileName;
    private String root = "";

    public String getXlFileName() {
        return xlFileName;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }


    private String predictorType = "未知预测器类型";

    public String getPredictorType() {
        return predictorType;
    }

    private String dateString;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


    private String warning = "OK";

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String intelli() throws Exception {
        try {
        /*若允许缓存，且缓存有对应项，则直接对用户返回缓存。*/
            if (useCaches && CachesManager.INSTANCE.hasPredictionCache(dateString)) {
                try {
                    PredictionCacheEntity cache = CachesManager.INSTANCE.getPredictionEntity(dateString);
                    warning = "OK";
                    String imgPath = cache.getOutputImagePath();
                    String xlPath = cache.getOutputExcelPath();
                    String rptImgPath = cache.getOutputRptImagePath();
                    File fxl = new File(xlPath);
                    File fimg = new File(imgPath);
                    File frptImg = new File(rptImgPath);
                    if (fxl.exists() && fimg.exists() && frptImg.exists()) {//若缓存文件均未丢失，直接返回文件路径
                        imgFileName = FileContentUtils.getFileNameFromPath(imgPath);
                        xlFileName = FileContentUtils.getFileNameFromPath(xlPath);
                        rptImgName = FileContentUtils.getFileNameFromPath(rptImgPath);
                        root = "";
                        return SUCCESS;
                    } else {//否则进行一次无缓存常规预测
                        return predict(dateString);
                    }
                } catch (Exception e) {
                    warning = failed(dateString, e);
                }

            } else {/*否则执行计算，返回计算结果并添加至缓存。*/
                return predict(dateString);
            }

            return SUCCESS;
        } catch (Exception ex) {
            warning = failed(dateString, ex);
            return SUCCESS;
        }
    }

    private String failed(String dateString, Exception e) {
        return dateString + "预测失败";


    }

    private Boolean useCaches = false;

    public void setUseCaches(Boolean useCaches) {
        this.useCaches = useCaches;
    }


    private void doPredict(String dateString) throws LPE, IllegalArgumentException, Exception {
        if (dateString == null) {
            java.util.Date date = new java.util.Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateString = simpleDateFormat.format(date);
        }
        String path = IOPaths.WEB_CONTENT_TEMP;
        IPredictor predictor = PredictorFactory.getInstance().getProperPredictor(Date.valueOf(dateString));
        warning = "OK";
        String temp = (String) predictor.predict();
        predictor.accept(new FirstPredictionLoadData2DBVisitor());
//        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad23LinePictureVisitor(path)));
//        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad21LinePictureVisitor(path)));
//        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad24LinePictureVisitor(path)));
        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad24LinePictureVisitor_1(path, dateString)));
//        xlFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new AllInformation2ExcelVisitor(path)));
        xlFileName = FileContentUtils.getFileNameFromPath(temp);

//        imgFileName=FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad24LinePictureVisitor_1(path, predictor.getDateString(), new ChartBuilderImpl1())));
//        imgFileName=FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad24LinePictureVisitor_1(path, predictor.getDateString(), new ChartBuilderImpl2())));
        rptImgName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad2ReportPictureVisitor(path, dateString)));
        root = "";//FileContentUtils.toWebContentFilePath(IOPaths.WEB_TEMP);
                /*构造缓存数据结构。*/
//        String t = predictor.getPredictionDays().get(0).getDateType().getName();
        predictorType = predictor.getPredictorType();
        PredictionCacheEntity entity = new PredictionCacheEntity(dateString, predictorType, path + xlFileName, path + imgFileName, path + rptImgName, warning);
                /*添加至缓存管理器。*/
        CachesManager.INSTANCE.addPredictionEntity(entity);
        ;
    }

    private String predict(String dateString) {
        try {
            doPredict(dateString);
            return SUCCESS;
        } catch (LPE e) {
            warning = failed(dateString, e);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {

            warning = failed(dateString, e);
            e.printStackTrace();
        } catch (Exception e) {
            warning = failed(dateString, e);
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
