package drivermanager;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager() {
    }
   // This is a thread-safe singleton class and we have made global access method synchronized so that only one thread can execute this method at a time.
    private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */

    public static synchronized WebDriver getInstance() {
        if (tldriver.get() == null) {
            tldriver = new ThreadLocal<>();
        }
        return tldriver.get();
    }

    public static void setDriver(WebDriver driver) {
        tldriver.set(driver);
    }

    public static void unload() {
        tldriver.remove();
    }
}