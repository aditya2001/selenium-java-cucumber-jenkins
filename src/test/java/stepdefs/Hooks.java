package stepdefs;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driverfactory.DriverFactory;
import utils.ConfigReader;

public class Hooks {
	private DriverFactory driverFactory;
    private ConfigReader configReader;
    WebDriver driver;
    Properties prop;


    @Before(order = 0)
    public void getProperty() {
//        configReader = new ConfigReader();
        String env = ConfigReader.getEnv();
        prop = configReader.loadProperties(env);
    }

    @Before(order = 1)
    public void launchBrowser() {

        System.out.println("inside hooks");
        String browser = ConfigReader.getBrowserType();
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browser);
    }

    @After
    public void embedScreenshot(Scenario scenario) {

//        if(scenario.isFailed()) {
//            try {
//                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//                scenario.embed(screenshot, "image/png");
//            } catch (WebDriverException noSupportScreenshot) {
//                System.err.println(noSupportScreenshot.getMessage());
//            }
//        }
        driver.quit();
    }
}
