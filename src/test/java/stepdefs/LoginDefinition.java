package stepdefs;


import pages.LoginPage;
import driverfactory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class LoginDefinition {
	private WebDriver driver;
	private WebDriverWait wait;

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

//	public LoginDefinition() throws Exception {
//		try {
//			driver = Hooks.driver;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		wait = new WebDriverWait(driver);
//		loginPage = new LoginPages(driver);
//
//	}

	@Given("^A web browser is at the sauce labs login page$")
	public void loginPage() {
		DriverFactory.getDriver()
				.get("https://www.saucedemo.com/");
		Assert.assertTrue(loginPage.verifyOnLoginPage());
	}

	@When("^A user provides incorrect credentials, and clicks on the login button with$")
	public void failedLogin(DataTable datatable) throws Exception {
		List<Map<String, String>> ffElements = datatable.asMaps(String.class, String.class);
		for(Map<String, String> ffElement : ffElements){
			loginPage.loginToSite(ffElement.get("userName"), ffElement.get("password"));
		}

	}

//	@Then("^The error message \"Epic sadface: Username and password do not match any user in this service\" is displayed$")
//	public void successLogin() {
//		Assert.assertTrue(homePage.verifyOnHomePage());
//	}
}
