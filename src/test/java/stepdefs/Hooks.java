package stepdefs;

import driverfactory.DriverFactory;
import org.apache.commons.io.FileUtils;
import utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Hooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browser = ConfigReader.getBrowserType();
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browser);

    }

    @After(order = 0)
    public void quitBrowser() {
//        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            //Use TakesScreenshot method to capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            //Saving the screenshot in desired location
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            //Saving the screenshot in desired location
            FileUtils.copyFile(source, new File("./SeleniumScreenshots/" + screenshotName + ".png"));
            System.out.println("Screenshot is captured");
        }
    }

}
