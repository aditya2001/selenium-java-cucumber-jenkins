package runner;

import utils.PropertyUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


@CucumberOptions(
        tags = "not @ignore",
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/",
                "rerun:target/failedrerun.txt"
        },
        monochrome = true,
        glue = { "stepdefs" },
        features = { "src/test/resources/features" }
)

public class TestRunner extends AbstractTestNGCucumberTests {


    @BeforeTest
    @Parameters({"browser","env"})
    public void beforeRun(String browser, String env) throws Throwable {
        PropertyUtils.setBrowserType(browser);
        PropertyUtils.setEnv(env);

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}