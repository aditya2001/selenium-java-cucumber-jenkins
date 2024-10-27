package params;

import lombok.Setter;

public class GlobalParams {
//    private static ThreadLocal<String> browserName = new ThreadLocal<String>();
    @Setter
    private static String browserType = null;

//    public static void setBrowserName(String browser){
//        browserName.set(browser);
//    }

    public static String getBrowserType() {
        if (browserType != null)
            return browserType;
        else
            throw new RuntimeException("browser not specified in the testng.xml");
    }

//    public static String getBrowserName(){
//        return browserName.get();
//    }

//    public void initializeGlobalParams(){
//        setBrowserName("chrome");
//    }
}
