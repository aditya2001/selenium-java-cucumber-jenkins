package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Objects;
import exceptions.PropertyFileException;
import enums.ConfigProperties;


public final class ConfigReader {
    private ConfigReader(){
    }
    private static Properties property= new Properties();
    private static Map<String, String> CONFIGMAP= new HashMap<>();
    private static String environment = null;

    /**
     * This method is used to load the properties from config.properties file
     * @return it returns Properties prop object
     */


    public static void setEnv(String env) {
        environment = env;
    }
    public static String getEnv() {
        if (environment != null)
            return environment;
        else
            throw new RuntimeException("env not specified in the testng.xml");
    }


    public static void initializeConfigMap(String env) {
        //try with resources
        try {
                FileInputStream file = new FileInputStream(FrameworkConstants.getConfigPath() + env + FrameworkConstants.getConfigFile());
                property.load(file);

            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
            //property.entrySet().forEach(entry ->CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        } catch (Exception e ) {
            //throw new InvalidPathForPropertyFileException("Please check the Path of the config.properties file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(ConfigProperties key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileException("Property name "+ key +" is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

    //Hashtable -- little slow, thread safe
    public static String getValue(String key){

        if (Objects.isNull(property.getProperty(key)) || Objects.isNull(key)) {
            throw new PropertyFileException("Property name "+ key +" is not fournd. Please check config.properties");
        }
        return property.getProperty(key);
    }


}
