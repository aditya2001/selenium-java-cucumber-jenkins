package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import utils.ConfigReader;

@RunWith(Cucumber.class)
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

    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
