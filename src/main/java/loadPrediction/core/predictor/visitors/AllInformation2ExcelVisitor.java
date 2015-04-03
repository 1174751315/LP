/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.predictor.visitors;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import  loadPrediction.core.predictor.IQingmingPredictor;
import  loadPrediction.core.predictor.IWeekendPredictor;
import  loadPrediction.core.predictor.IWorkdayPredictor;
import  loadPrediction.domain.Accuracy;
import  loadPrediction.domain.LoadData;
import  loadPrediction.domain.SimpleDate;
import  loadPrediction.resouce.IOPaths;
import  loadPrediction.resouce.TimeLabels;
import  loadPrediction.utils.Date2StringAdapter;
import  loadPrediction.utils.FileContentUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-05 21:05。电邮 1174751315@qq.com。
 */
public class AllInformation2ExcelVisitor implements IPredictorVisitor {
    private String dir;

    public AllInformation2ExcelVisitor(String directoryPath) {
        dir = directoryPath;
    }

    @Override
    public Object visitWorkdayPredictor(IWorkdayPredictor predictor) {
        String fileName = FileContentUtils.autoFileName("WORKDAY" + predictor.getDateString().replaceAll("-", ""), ".xls");
        String path = dir + fileName;
        try {
            Workbook template = Workbook.getWorkbook(new File(IOPaths.WEB_CONTENT_WORKDAY_OUTPUT_TEMPLATE_XL_PATH));
            WritableWorkbook wwb = Workbook.createWorkbook(new File(path), template);
            Integer count;

            WritableSheet ws0 = wwb.getSheet("SIMILAR_DAYS");

            ws0.addCell(new Label(0, 0, "PREDICTION_DAYS"));
            List<SimpleDate> predictionDays = predictor.getPredictionDays();
            for (int i = 0; i < predictionDays.size(); i++) {
                ws0.addCell(new Label(0, 1 + i, predictionDays.get(i).getDateString()));
            }
            ws0.addCell(new Label(1, 0, "SIMILAR_DAYS"));
            List<SimpleDate> similarDays = predictor.getSimilarDays().get(0);
            for (int i = 0; i < similarDays.size(); i++) {
                ws0.addCell(new Label(1, 1 + i, Date2StringAdapter.toString(similarDays.get(i).getDate())));
            }

            WritableSheet ws1 = wwb.getSheet("PREDICTION_LOADS");
            for (int i = 0; i < predictionDays.size(); i++) {
                ws1.addCell(new Label(1 + i, 0, predictionDays.get(i).getDateString()));
            }
            for (int i = 0; i < 96; i++) {
                ws1.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
            List<LoadData> predictionLoads = predictor.getPrediction96PointLoads();
            for (int i = 0; i < predictionLoads.size(); i++) {
                List<Double> loadData = predictionLoads.get(i).toList();
                for (int j = 0; j < 96; j++) {
                    ws1.addCell(new jxl.write.Number(1 + i, 1 + j, loadData.get(j)));
                }
            }

            WritableSheet ws2 = wwb.getSheet("SIMILAR_LOADS");
            List<LoadData> similarLoad = predictor.getSimilarLoad().get(0);
            for (int i = 0; i < similarLoad.size(); i++) {
                ws2.addCell(new Label(1 + i, 0, similarLoad.get(i).getDateString()));
            }
            for (int i = 0; i < 96; i++) {
                ws2.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
            for (int i = 0; i < similarLoad.size(); i++) {
                List<Double> loadData = similarLoad.get(i).toList();
                for (int j = 0; j < 96; j++) {
                    ws2.addCell(new jxl.write.Number(1 + i, 1 + j, loadData.get(j)));
                }

            }

            WritableSheet ws3 = wwb.getSheet("ACCURACIES");
            List<Accuracy> accuracies = predictor.getAccuracy();
            for (int i = 0; i < accuracies.size(); i++) {
                ws3.addCell(new Label(i, 0, accuracies.get(i).getDateString()));
                ws3.addCell(new Number(i, 1, accuracies.get(i).getAccuracy()));
            }


//            WritableSheet ws4 = wwb.getSheet("MAX_AVE_MIN");
//            ws4.addCell(new Label(0, 1, "PREDICTION_LOADS最大值"));
//            ws4.addCell(new Label(0, 2, "PREDICTION_LOADS平均值"));
//            ws4.addCell(new Label(0, 3, "PREDICTION_LOADS最小值"));
//            ws4.addCell(new Label(0, 4, "ACTUAL_LOADS最大值"));
//            ws4.addCell(new Label(0, 5, "ACTUAL_LOADS平均值"));
//            ws4.addCell(new Label(0, 6, "ACTUAL_LOADS最小值"));
//            ws4.addCell(new Label(0, 7, "最值预测-最大负荷"));
//            ws4.addCell(new Label(0, 8, "最值预测-平均负荷"));
//            ws4.addCell(new Label(0, 9, "最值预测-最小负荷"));
//            for (int i = 0; i < predictionDays.size(); i++) {
//                ws4.addCell(new Label(1 + i, 0, predictionDays.get(i).getDateString()));
//            }
//            for (int i = 0; i < predictionDays.size(); i++) {
//                LoadData loadDataPredicton = predictor.getPrediction96PointLoads().get(i);
//                LoadData loadDataActual = predictor.getActual96PointLoads().get(i);
//                MaxAveMinTuple<Double> tuple=predictor.getPredictionLoadTuple().get(i);
//                ws4.addCell(new Number(1 + i, 1, loadDataPredicton.getMax()));
//                ws4.addCell(new Number(1 + i, 2, loadDataPredicton.getAve()));
//                ws4.addCell(new Number(1 + i, 3, loadDataPredicton.getMin()));
//                ws4.addCell(new Number(1 + i, 4, loadDataActual.getMax()));
//                ws4.addCell(new Number(1 + i, 5, loadDataActual.getAve()));
//                ws4.addCell(new Number(1 + i, 6, loadDataActual.getMin()));
//
//                ws4.addCell(new Number(1 + i, 7, tuple.getMax()));
//                ws4.addCell(new Number(1 + i, 8, tuple.getAve()));
//                ws4.addCell(new Number(1 + i, 9, tuple.getMin()));
//            }

            WritableSheet ws5 = wwb.getSheet("ACTUAL_LOADS");
            List<LoadData> actualLoads = predictor.getActual96PointLoads();
            for (int i = 0; i < predictionDays.size(); i++) {
                ws5.addCell(new Label(1 + i, 0, predictionDays.get(i).getDateString()));
            }
            for (int i = 0; i < 96; i++) {
                ws5.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
            for (int i = 0; i < actualLoads.size(); i++) {
                List<Double> loadData = actualLoads.get(i).toList();
                for (int j = 0; j < 96; j++) {
                    ws5.addCell(new Number(1 + i, 1 + j, loadData.get(j)));
                }

            }

//            WritableSheet ws6 = wwb.getSheet("COES");
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                ws6.addCell(new Label(0, 1 + i, predictor.getPredictionDays().get(i).getDateString()));
//            }
//
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                List<Double> coes = predictor.getSimilarCoes().get(0).get(i);
//                for (int j = 0; j < predictor.getHistoryDaysNumbers().get(0); j++) {
//                    ws6.addCell(new Number(1 + j, 1 + i, coes.get(j)));
//                }
//            }
            WritableSheet ws7 = wwb.getSheet("ALL_DAYS");
            int index = 1;
            ws7.addCell(new Label(0, 0, "日期"));
            ws7.addCell(new Label(1, 0, "类别"));
            for (int i = 0; i < predictor.getHistoryDaysNumbers().get(0); i++, index++) {
                ws7.addCell(new Label(0, index, Date2StringAdapter.toString(predictor.getHistoryDays().get(0).get(i).getDate())));
                ws7.addCell(new Label(1, index, predictor.getHistoryDays().get(0).get(i).getDateType().getName()));
            }
            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++, index++) {
                ws7.addCell(new Label(0, index, Date2StringAdapter.toString(predictor.getPredictionDays().get(i).getDate())));
                ws7.addCell(new Label(1, index, predictor.getPredictionDays().get(i).getDateType().getName()));
            }
//            WritableSheet ws8=wwb.createSheet("",8);
//            WritableSheet ws9=wwb.createSheet("",9);
//            WritableSheet ws10=wwb.createSheet("",10);
//            WritableSheet ws11=wwb.createSheet("",11);
//            WritableSheet ws12=wwb.createSheet("",12);
//            WritableSheet ws13=wwb.createSheet("",13);
//            WritableSheet ws14=wwb.createSheet("",14);
//            WritableSheet ws15=wwb.createSheet("",15);
//            WritableSheet ws16=wwb.createSheet("",16);
//            WritableSheet ws17=wwb.createSheet("",17);
//            WritableSheet ws18=wwb.createSheet("",18);
//            WritableSheet ws19=wwb.createSheet("",19);


            wwb.write();
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public Object visitQingmingPredictor(IQingmingPredictor predictor) {
        return null;
    }

    @Override
    public Object visitWeekendPredictor(IWeekendPredictor predictor) {
        String fileName = FileContentUtils.autoFileName("WEEKEND" + predictor.getDateString().replaceAll("-", ""), ".xls");
        String path = dir + fileName;
        try {
            Workbook template = Workbook.getWorkbook(new File(IOPaths.WEB_CONTENT_WEEKEND_OUTPUT_TEMPLATE_XL_PATH));
            WritableWorkbook wwb = Workbook.createWorkbook(new File(path), template);


            WritableSheet ws0 = wwb.getSheet("SIMILAR_DAYS");
            ws0.addCell(new Label(0, 0, "PREDICTION_DAYS"));
            ws0.addCell(new Label(1, 0, "SIMILAR_DAYS-工作日"));
            ws0.addCell(new Label(2, 0, "SIMILAR_DAYS-周末"));
            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
                ws0.addCell(new Label(0, 1 + i, predictor.getPredictionDays().get(i).getDateString()));
                ws0.addCell(new Label(1, 1 + i, Date2StringAdapter.toString(predictor.getSimilarDays().get(0).get(i).getDate())));
                ws0.addCell(new Label(1, 1 + i, Date2StringAdapter.toString(predictor.getSimilarDays().get(1).get(i).getDate())));
            }

            WritableSheet ws1 = wwb.getSheet("PREDICTION_LOADS");
            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
                ws1.addCell(new Label(1 + i, 0, predictor.getPredictionDays().get(i).getDateString()));
            }
            for (int i = 0; i < 96; i++) {
                ws1.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
                List<Double> loadData = predictor.getPrediction96PointLoads().get(i).toList();
                for (int j = 0; j < 96; j++) {
                    ws1.addCell(new jxl.write.Number(1 + i, 1 + j, loadData.get(j)));
                }
            }

            WritableSheet ws2 = wwb.getSheet("SIMILAR_LOADS");
            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
                ws2.addCell(new Label(1 + i, 0, predictor.getSimilarDays().get(0).get(i).getDate().toLocalDate().toString()));
            }
            for (int i = 0; i < 96; i++) {
                ws2.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                List<Double> loadData = predictor.getSimilarLoad().get(0).get(i).toList();
//                for (int j = 0; j < 96; j++) {
//                    ws2.addCell(new jxl.write.Number(1 + i, 1 + j, loadData.get(j)));
//                }
//
//            }
//            for (int i = 2; i < predictor.getPredictionDaysNumber()+2; i++) {
//                List<Double> loadData = predictor.getSimilarLoad().get(1).get(i).toList();
//                for (int j = 0; j < 96; j++) {
//                    ws2.addCell(new jxl.write.Number(1 + i, 1 + j, loadData.get(j)));
//                }
//
//            }

            WritableSheet ws3 = wwb.getSheet("ACCURACIES");
            List<Accuracy> accuracies = predictor.getAccuracy();
            for (int i = 0; i < accuracies.size(); i++) {
                ws3.addCell(new Label(i, 0, accuracies.get(i).getDateString()));
            }
            for (int i = 0; i < accuracies.size(); i++) {
                ws3.addCell(new Number(i, 1, accuracies.get(i).getAccuracy()));
            }

//
//            WritableSheet ws4 = wwb.getSheet("MAX_AVE_MIN");
//            ws4.addCell(new Label(0, 1, "PREDICTION_LOADS最大值"));
//            ws4.addCell(new Label(0, 2, "PREDICTION_LOADS平均值"));
//            ws4.addCell(new Label(0, 3, "PREDICTION_LOADS最小值"));
//            ws4.addCell(new Label(0, 4, "ACTUAL_LOADS最大值"));
//            ws4.addCell(new Label(0, 5, "ACTUAL_LOADS平均值"));
//            ws4.addCell(new Label(0, 6, "ACTUAL_LOADS最小值"));
//            ws4.addCell(new Label(0, 7, "最值预测-最大负荷"));
//            ws4.addCell(new Label(0, 8, "最值预测-平均负荷"));
//            ws4.addCell(new Label(0, 9, "最值预测-最小负荷"));
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                ws4.addCell(new Label(1 + i, 0, predictor.getPredictionDays().get(i).getDateString()));
//            }
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                LoadData loadDataPredicton = predictor.getPrediction96PointLoads().get(i);
//                LoadData loadDataActual = predictor.getActual96PointLoads().get(i);
//                MaxAveMinTuple<Double> tuple=predictor.getPredictionLoadTuple().get(i);
//                ws4.addCell(new Number(1 + i, 1, loadDataPredicton.getMax()));
//                ws4.addCell(new Number(1 + i, 2, loadDataPredicton.getAve()));
//                ws4.addCell(new Number(1 + i, 3, loadDataPredicton.getMin()));
//                ws4.addCell(new Number(1 + i, 4, loadDataActual.getMax()));
//                ws4.addCell(new Number(1 + i, 5, loadDataActual.getAve()));
//                ws4.addCell(new Number(1 + i, 6, loadDataActual.getMin()));
//
//                ws4.addCell(new Number(1 + i, 7, tuple.getMax()));
//                ws4.addCell(new Number(1 + i, 8, tuple.getAve()));
//                ws4.addCell(new Number(1 + i, 9, tuple.getMin()));
//            }

            WritableSheet ws5 = wwb.getSheet("ACTUAL_LOADS");
            List<LoadData> actualLoads = predictor.getActual96PointLoads();
            for (int i = 0; i < actualLoads.size(); i++) {
                ws5.addCell(new Label(1 + i, 0, actualLoads.get(i).getDateString()));
            }
            for (int i = 0; i < 96; i++) {
                ws5.addCell(new Label(0, 1 + i, TimeLabels.labels[i]));
            }
            for (int i = 0; i < actualLoads.size(); i++) {
                List<Double> loadData = actualLoads.get(i).toList();
                for (int j = 0; j < 96; j++) {
                    ws5.addCell(new Number(1 + i, 1 + j, loadData.get(j)));
                }

            }

//            WritableSheet ws6 = wwb.getSheet("COES");
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                ws6.addCell(new Label(0, 1 + i, predictor.getPredictionDays().get(i).getDateString()));
//            }
//
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++) {
//                List<Double> coes = predictor.getSimilarCoes().get(0).get(i);
//                for (int j = 0; j < predictor.getHistoryDaysNumbers().get(0); j++) {
//                    ws6.addCell(new Number(1 + j, 1 + i, coes.get(j)));
//                }
//            }
//            WritableSheet ws7 = wwb.getSheet("ALL_DAYS");
//            int index = 1;
//            ws7.addCell(new Label(0, 0, "日期"));
//            ws7.addCell(new Label(1, 0, "类别"));
//            for (int i = 0; i < predictor.getHistoryDaysNumbers().get(0); i++, index++) {
//                ws7.addCell(new Label(0, index, Date2StringAdapter.toString(predictor.getHistoryDays().get(0).get(i).getDate())));
//                ws7.addCell(new Label(1, index, predictor.getHistoryDays().get(0).get(i).getDateType().getName()));
//            }
//            for (int i = 0; i < predictor.getPredictionDaysNumber(); i++, index++) {
//                ws7.addCell(new Label(0, index, Date2StringAdapter.toString(predictor.getPredictionDays().get(i).getDate())));
//                ws7.addCell(new Label(1, index, predictor.getPredictionDays().get(i).getDateType().getName()));
//            }
//            WritableSheet ws8=wwb.createSheet("",8);
//            WritableSheet ws9=wwb.createSheet("",9);
//            WritableSheet ws10=wwb.createSheet("",10);
//            WritableSheet ws11=wwb.createSheet("",11);
//            WritableSheet ws12=wwb.createSheet("",12);
//            WritableSheet ws13=wwb.createSheet("",13);
//            WritableSheet ws14=wwb.createSheet("",14);
//            WritableSheet ws15=wwb.createSheet("",15);
//            WritableSheet ws16=wwb.createSheet("",16);
//            WritableSheet ws17=wwb.createSheet("",17);
//            WritableSheet ws18=wwb.createSheet("",18);
//            WritableSheet ws19=wwb.createSheet("",19);


            wwb.write();
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return path;
    }


}
