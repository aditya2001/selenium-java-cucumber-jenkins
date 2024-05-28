package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static String environment = null;
    private static String browserType = null;
    private Properties prop;

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

    public Properties loadProperties(String env) {
        prop = new Properties();
        BufferedReader reader;
        if(env.equals("uat")) {
            try {
                reader = new BufferedReader(new FileReader("src/test/resources/config/config.properties"));
                prop.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(env.equals("qa")){
            try {
                reader = new BufferedReader(new FileReader("src/test/resources/config/config.properties"));
                prop.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}


