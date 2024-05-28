package runners;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ConfigReader;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs"
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters({"browser","env"})
    public void beforeRun(String browser, String env) throws Throwable {
        ConfigReader.setBrowserType(browser);
        ConfigReader.setEnv(env);
    }
    
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
