/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.action;

import com.opensymphony.xwork2.ActionSupport;
import  loadPrediction.core.cache.CachesManager;
import  loadPrediction.core.cache.PredictionCacheEntity;
import  loadPrediction.core.predictor.IPredictor;
import  loadPrediction.core.predictor.PredictorFactory;
import  loadPrediction.core.predictor.visitors.*;
import  loadPrediction.exception.LPE;
import  loadPrediction.log.Logging;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.utils.FileContentUtils;
import org.apache.log4j.Logger;
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
    //    OracleDAOFactory oracleDaoFactory = OracleDAOFactory.getInstance();
    private JFreeChart chart;

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    private String imgFileName;
    private String xlFileName;
    private String root = "";

    public String getXlFileName() {
        return xlFileName;
    }

    public void setXlFileName(String xlFileName) {
        this.xlFileName = xlFileName;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
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
        Logger log = Logging.instance().createLogger("智能预测");
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//        mailMessage.setFrom("1174715@qq.com");
//        mailMessage.setTo("1174751315@qq.com");
//        mailMessage.setSubject("None");
//        mailMessage.setText("None");
//
//        JavaMailSenderImpl sender=new JavaMailSenderImpl();
//
//
//        sender.send(mailMessage);
        /*若允许缓存，且缓存有对应项，则直接对用户返回缓存。*/
        if (useCaches && CachesManager.instance().hasPredictionCache(dateString)) {
            try {
                PredictionCacheEntity cache = CachesManager.instance().getPredictionEntity(dateString);
                warning = "OK";
                String imgPath=cache.getOutputImagePath();
                String xlPath=cache.getOutputExcelPath();
                File fxl=new File(xlPath);
                File fimg=new File(imgPath);
                if (fxl.exists()&&fimg.exists()) {//若缓存文件均未丢失，直接返回文件路径
                    imgFileName = FileContentUtils.getFileNameFromPath(imgPath);
                    xlFileName = FileContentUtils.getFileNameFromPath(xlPath);
                    root = "";
                    log.info("【" + dateString + "】  成功地进行了一次预测  【未知预测类型】  【使用缓存】");
                    return SUCCESS;
                }
                else {//否则进行一次无缓存常规预测
                    log.info("【"+dateString+"】  在进行【使用缓存】的预测时发现文件丢失，转而进行无缓存常规预测。"  );
                    return predict(dateString,log);
                }
            } catch (Exception e) {
                warning = "未处理的异常\n" + e.getMessage();
                log.error(" 预测失败 "+e.getMessage());
                log.info(dateString + " 预测失败 ");
            }

        } else {/*否则执行计算，返回计算结果并添加至缓存。*/
            return predict(dateString,log);
        }

        return SUCCESS;
    }
private String failed(Logger log,String dateString,String msg){
    log.error("【  "+dateString+"  】  预测失败\n"+msg);
    log.info("【  "+dateString+"  】" +"预测失败。");
    return "预测失败\n"+msg;

}
    private Boolean useCaches = false;

    public void setUseCaches(Boolean useCaches) {
        this.useCaches = useCaches;
    }


    private void doPredict(String dateString,Logger log)throws LPE,IllegalArgumentException,Exception{
        if (dateString == null){
            java.util.Date date=new java.util.Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            dateString=simpleDateFormat.format(date);
        }
        String path = IOPaths.WEB_CONTENT_ROOT;
        IPredictor predictor = PredictorFactory.getInstance().getProperPredictor(Date.valueOf(dateString));
        warning = "OK";
        String temp=(String) predictor.predict();
        predictor.accept(new FirstPredictionLoadData2DBVisitor());
        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad23LinePictureVisitor(path)));
        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad21LinePictureVisitor(path)));
        imgFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new PredictionLoad24LinePictureVisitor(path)));

        xlFileName = FileContentUtils.getFileNameFromPath((String) predictor.accept(new AllInformation2ExcelVisitor(path)));
        xlFileName=FileContentUtils.getFileNameFromPath(temp);
        root = "";//FileContentUtils.toWebContentFilePath(IOPaths.WEB_TEMP);
                /*构造缓存数据结构。*/
        String t = predictor.getPredictionDays().get(0).getDateType().getName();
        PredictionCacheEntity entity = new PredictionCacheEntity(dateString, t, path + xlFileName, path + imgFileName, warning);
                /*添加至缓存管理器。*/
        CachesManager.instance().addPredictionEntity(entity);
        ;
        log.info("【" + dateString + "】  成功地进行了一次预测  【" + predictor.getPredictorType() + "】  【不使用缓存】");
    }
    private String predict(String dateString,Logger log){
        try {
            doPredict(dateString,log);
            return SUCCESS;
        } catch (LPE e) {
            warning= failed(log,dateString,e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {

            warning =  failed(log,dateString,e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            warning=failed(log,dateString,e.getMessage());
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
