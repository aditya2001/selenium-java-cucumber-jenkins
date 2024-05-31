package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;
    private static String environment = null;
    private static String browserType = null;

    /**
     * This method is used to load the properties from config.properties file
     * @return it returns Properties prop object
     */


    public static void setBrowserType(String browser) {
        browserType = browser;
    }
    public static String getBrowserType() {
        if (browserType != null)
            return browserType;
        else
            throw new RuntimeException("browser not specified in the testng.xml");
    }

    public static void setEnv(String env) {
        environment = env;
    }
    public static String getEnv() {
        if (environment != null)
            return environment;
        else
            throw new RuntimeException("env not specified in the testng.xml");
    }

    public Properties init_prop() {

        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

}
