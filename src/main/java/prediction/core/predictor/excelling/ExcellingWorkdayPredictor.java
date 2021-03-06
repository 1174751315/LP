/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package prediction.core.predictor.excelling;

import common.ConvertUtils;
import common.ElementPrintableLinkedList;
import common.MaxAveMinTuple;
import prediction.config.predictor.PredictorCfg;
import prediction.core.predictor.IWorkdayPredictor;
import prediction.core.predictor.visitors.IPredictorVisitor;
import prediction.dataAccess.DAOFactory;
import prediction.domain.LoadData;
import prediction.domain.SimpleDate;
import prediction.domain.WeatherData;
import prediction.exception.DAE;
import prediction.exception.LPE;
import prediction.resouce.IOPaths;
import prediction.utils.Season;
import prediction.utils.SeasonIdentifier;
import prediction.utils.powerSystemDateQuery.AbstractPowerSystemDayQuery;
import prediction.utils.powerSystemDateQuery.PowerSystemWorkdayQuery;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-22 9:25。电邮 1174751315@qq.com。
 */
public class ExcellingWorkdayPredictor extends AbstractTemplateMethodExcellingPredictor implements IWorkdayPredictor {
    private Integer predictionDaysNbr = 7;
    private Integer historyDaysNbr = 14;


    private static PredictorCfg cfg=new PredictorCfg(IOPaths.WEB_CONTENT_WORKDAY_PREDICTOR_CFG_PATH);
    private static final Integer WINTER = 0, SUMMER = 1;
    private Season season = Season.SUMMER;

    public ExcellingWorkdayPredictor() {
//        this(null,null);
    }

    public ExcellingWorkdayPredictor(Date date, DAOFactory defaultDaoFactory) {
        super(date, defaultDaoFactory);

//        try {
//            dayQuery4HistoryDays=new PowerSystemWorkdayQuery(date);
//            dayQuery4PredictionDays=new PowerSystemWorkdayQuery(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ExceptionHandlerFactory.INSTANCE.getLowerHandler().handle(e, "查询历史日或预测日时发生异常");
//        }

        String dateString = date.toLocalDate().toString();
        getSeason(dateString);
    }

    private void getSeason(String ds) {
        try {
            WeatherData weatherData = defaultDaoFactory.createDaoWeatherData().query(ds);
            season = SeasonIdentifier.getSeasonByWeather(weatherData);
            return;
        } catch (DAE dae) {
            dae.printStackTrace();
        } catch (Exception e) {

        }
        season = Season.SUMMER;
    }



    @Override
    public String getPredictorType() {
        String modelName = season.equals(Season.SUMMER) ? "夏季" : "冬季";
        return "EXCELLING 工作日 " + (modelName + "模型");
    }


    public void setDayQuery4PredictionDays(AbstractPowerSystemDayQuery dayQuery4PredictionDays) {
        this.dayQuery4PredictionDays = dayQuery4PredictionDays;
    }

    public void setDayQuery4HistoryDays(AbstractPowerSystemDayQuery dayQuery4HistoryDays) {
        this.dayQuery4HistoryDays = dayQuery4HistoryDays;
    }

    private AbstractPowerSystemDayQuery dayQuery4PredictionDays, dayQuery4HistoryDays;

    @Override
    protected ElementPrintableLinkedList<SimpleDate> doGetPredictionDays() throws LPE {
        Integer number = predictionDaysNbr;
        try {
            if (dayQuery4PredictionDays == null)
                dayQuery4PredictionDays = new PowerSystemWorkdayQuery(date);
            List<SimpleDate> list = dayQuery4PredictionDays.list(1, number);
            if (list.size() != number)
                throw new LPE("获取预测日时发生异常：数据不完整", LPE.eScope.USER);
            return ConvertUtils.toElementPrintableLinkedList(list, "prediction Days");
        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }

    }

    @Override
    protected ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> doGetHistoryDays() throws LPE {
        ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("unnamed");
        try {
            history = new ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>>("historyDays");
            if (dayQuery4HistoryDays == null)
                dayQuery4HistoryDays = new PowerSystemWorkdayQuery(date);
            ElementPrintableLinkedList<SimpleDate> list = ConvertUtils.toElementPrintableLinkedList(dayQuery4HistoryDays.list(historyDaysNbr + 1, historyDaysNbr + 1), "workday");
            list.removeLast();
            history.add(list);
        } catch (Exception e) {
            throw new LPE(e.getMessage(), LPE.eScope.USER);
        }
        return history;
    }

    @Override
    protected String doGetInputWorkbookPath() {
        if (season == null) {
            getSeason(dateString);
        }
        if (season.equals(Season.SUMMER))
            return IOPaths.WEB_CONTENT_WORKDAY_SUMMER_TEMPLATE_PATH;
        return IOPaths.WEB_CONTENT_WORKDAY_WINTER_TEMPLATE_PATH;
    }

    @Override
    protected String doGetOutputWorkbookPath() {
        return IOPaths.WEB_CONTENT_TEMP + dateString + "WD.xls";
    }

    @Override
    protected String doGetXmlConfigFilePath() {
        return IOPaths.WEB_CONTENT_WORKDAY_PREDICTOR_CFG_PATH;
    }


    @Override
    public Object accept(IPredictorVisitor visitor) throws LPE {
        return visitor.visitWorkdayPredictor(this);
    }


    private List<CellPosition> getHistoryLoadTuplesExcelPosition() {
        List<CellPosition> list = new LinkedList<CellPosition>();
        list.add(new CellPosition("K2", "工作日气象数据"));
        return list;
    }

    @Override
    protected Boolean doValidate(Date date) {
        return true;
    }

    @Override
    protected void doAfterInjectWeathers(Workbook activeWorkbook, ElementPrintableLinkedList<ElementPrintableLinkedList<SimpleDate>> historyDays, ElementPrintableLinkedList<SimpleDate> predictionDays) throws LPE {
        /*获取历史负荷*/
        ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>> historyLoads = new ElementPrintableLinkedList<ElementPrintableLinkedList<LoadData>>("历史负荷");
        historyLoads = commonUtils.getSimilarDaysLoad(historyDays);
        historyLoads.print(System.err);

        /*填充历史负荷三值*/
        List<CellPosition> ofHistoryLoadTuples = getHistoryLoadTuplesExcelPosition();
        if (ofHistoryLoadTuples != null) {
            for (int i = 0; i < ofHistoryLoadTuples.size(); i++) {
                CellPosition pos = ofHistoryLoadTuples.get(i);
                Sheet ws1 = activeWorkbook.getSheet(pos.getSheetName());
                for (int j = 0; j < historyLoads.get(i).size(); j++) {
                    MaxAveMinTuple<Double> t = historyLoads.get(i).get(j).toMaxAveMin();
                    Row row = ws1.getRow(pos.getRow() + j);
                    row.getCell(pos.getCol()).setCellValue(t.getMax());
                    row.getCell(pos.getCol() + 1).setCellValue(t.getAve());
                    row.getCell(pos.getCol() + 2).setCellValue(t.getMin());
                }
                ws1.setForceFormulaRecalculation(true);
            }
        }
    }
}