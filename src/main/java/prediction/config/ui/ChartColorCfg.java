package prediction.config.ui;

import java.awt.*;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-11 12:38。电邮 1174751315@qq.com。
 */
public class ChartColorCfg {
    private Color backGround;
    private Color foreGround;
    private Color grid;
    private List<Color> series;

    public ChartColorCfg(Color backGround, Color foreGround, Color grid, List<Color> series) {
        this.backGround = backGround;
        this.foreGround = foreGround;
        this.grid = grid;
        this.series = series;
    }

    public ChartColorCfg() {
    }

    public Color getBackGround() {
        return backGround;
    }

    public void setBackGround(Color backGround) {
        this.backGround = backGround;
    }

    public Color getForeGround() {
        return foreGround;
    }

    public void setForeGround(Color foreGround) {
        this.foreGround = foreGround;
    }

    public Color getGrid() {
        return grid;
    }

    public void setGrid(Color grid) {
        this.grid = grid;
    }

    public List<Color> getSeries() {
        return series;
    }

    public void setSeries(List<Color> series) {
        this.series = series;
    }

    public ChartColorCfg clone() {
        return new ChartColorCfg(this.backGround, this.foreGround, this.grid, this.series);
    }
}
