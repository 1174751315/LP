package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * 李倍存 创建于 2015-04-22 21:48。电邮 1174751315@qq.com。
 */
public class Dom4jFacade {
    public Dom4jFacade() {
    }

    public static Document readDocument(String xmlFilePath){

    SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new File(xmlFilePath));
        } catch (DocumentException e) {
            return null;
        }
        return document;
    }
}
