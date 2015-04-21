package prediction.core.predictor.excelling.xlAccessor;

import common.ElementPrintableLinkedList;
import prediction.core.predictor.excelling.CellPosition;
import prediction.domain.WeatherData;
import prediction.resouce.WeatherDataMappingKeys;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * 李倍存 创建于 2015-04-16 15:54。电邮 1174751315@qq.com。
 */
public class XlLpWeatherDataAccessor extends AbstractXLAccessor {
    public void writeSomeWeatherData2Cells(CellPosition position, List<WeatherData> datas) {
        for (int k = 0; k < datas.size(); k++) {
            WeatherData weatherData = datas.get(k);
            this.writeOneWeatherData2Cells(position.ofRowAfter(k), weatherData);
        }
    }

    private void writeOneWeatherData2Cells(CellPosition position, WeatherData data) {

        String sheetName = position.getSheetName();
        Short col = position.getCol();
        Integer row = position.getRow();
        Sheet sheet = workbook.getSheet(sheetName);
        Map<String, Double> map = data.toMap();
        Row row_ = sheet.getRow(row);
        for (int j = 0; j < WeatherDataMappingKeys.keys.length; j++) {
            try {
                row_.getCell(col + j).setCellValue(map.get(WeatherDataMappingKeys.keys[j]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        forceCalcAllFormulas();
    }

    public void writeSomeWeatherData2Cells(List<CellPosition> positions, List<ElementPrintableLinkedList<WeatherData>> datas) {
        for (int i = 0; i < positions.size(); i++) {
            CellPosition pos = positions.get(i);
            this.writeSomeWeatherData2Cells(pos, datas.get(i));
        }
    }


}
