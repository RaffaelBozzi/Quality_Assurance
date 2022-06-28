package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    Properties properties = new Properties();

    public String getProperties(String propertieKey) {
        try {
            if(System.getProperty("env") == null) {
                properties.load(getClass().getClassLoader().getResourceAsStream("hml.properties"));
            } else {
                properties.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertieKey);
    }
}
