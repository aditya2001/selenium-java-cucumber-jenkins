package stepdefs;

import driverfactory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ActionsPage;
import pages.DynamicPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ActionsStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;

    private ActionsPage actionsPage;

    public ActionsStepDefinition() throws Exception {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actionsPage = new ActionsPage(driver, wait);

    }
    @Given("^A web browser is at heroku app$")
    public void loginPage() {
        DriverFactory.getDriver().get("https://the-internet.herokuapp.com/");
    }

    @When("user clicks on drop down button$")
    public void userClicksOnDropDownButton() throws Exception {
        actionsPage.clickOn();
    }

    @And("user select drop down value with$")
    public void userSelectDropDownValueWith(DataTable datatable) throws Exception {
        List<Map<String, String>> ffElements = datatable.asMaps(String.class, String.class);
        for(Map<String, String> ffElement : ffElements){
            actionsPage.selectDropDownValue(ffElement.get("value"));
        }

    }

    @Then("validate drop value displayed")
    public void validateDropValueDisplayed() {

    }

    @When("user clicks on java script alert popups$")
    public void userClicksOnJavaScriptAlertPopup() throws Exception {
        actionsPage.clickAlertPopUp();
    }

    @And("confirm java script alert popups$")
    public void confirmJavaScriptAlertPopup() throws Exception {
        actionsPage.confirmAlertPopup();
    }

    @When("user clicks on Entry Add window popup")
    public void userClicksOnEntryAddWindowPopup() throws Exception {
         actionsPage.clickAddPopup();
    }

    @And("close window add popup")
    public void closeWindowAddPopup() throws Exception {
         actionsPage.closeWindowAddPopUp();
    }

    @Then("click on re-enable button")
    public void clickOnReEnableButton() {
    }

    @When("user clicks on multiple windows button")
    public void userClicksOnMultipleWindowsButton() {
          actionsPage.clickWindowButton();

    }

    @And("user navigates to new window opened and print title")
    public void userNavigatesToNewWindowOpenedAndPrintTitle() {
          actionsPage.validateNewWindow();
    }

    @Then("navigate back to previous window")
    public void navigateBackToPreviousWindow() {
        actionsPage.navigateBackToMainWindow();
    }
}
