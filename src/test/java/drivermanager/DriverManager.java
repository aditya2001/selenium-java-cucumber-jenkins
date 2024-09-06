package drivermanager;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager() {
    }

    private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */

    public static synchronized WebDriver getDriver() {
        return tldriver.get();
    }

    public static void setDriver(WebDriver driver) {
        tldriver.set(driver);
    }

    public static void unload() {
        tldriver.remove();
    }
}