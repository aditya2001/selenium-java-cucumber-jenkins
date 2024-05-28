package stepdefs;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public void initDriver(String browser){

    }
}
