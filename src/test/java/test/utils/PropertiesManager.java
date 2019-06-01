package test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    static Properties properties;

    public static void initializeProperties() throws IOException {
        properties = new Properties();
        FileInputStream iStr = new FileInputStream("src/test/java/test/resources/testsettings.properties");
        properties.load(iStr);
        iStr.close();
    }

    public static String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName,"Not Found");
    }
}