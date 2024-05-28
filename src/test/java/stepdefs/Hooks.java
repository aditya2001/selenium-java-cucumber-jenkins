package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.util.Properties;

public class Hooks {
	private DriverFactory driverFactory;
    private ConfigReader configReader;
    private WebDriver driver;
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
