package loadPrediction.utils.color;

/**
 * 李倍存 创建于 2015-04-15 10:18。电邮 1174751315@qq.com。
 */
public class ColorParserFactory {
    private ColorParserFactory() {
    }

    public static ColorParserFactory INSTANCE = new ColorParserFactory();

    public IColorParser getProperParser(String s) {
        if (s.substring(0, 3).equals("RGB"))
            return new RGBColorParser();
        if (s.substring(0, 4).equals("HTML"))
            return new HTMLColorParser();
        return null;
    }
}
