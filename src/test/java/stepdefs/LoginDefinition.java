package stepdefs;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginDefinition {
	private WebDriver driver;
	private WebDriverWait wait;
	private LoginPage loginPage;
	private HomePage homePage;

	public LoginDefinition() throws Exception {
		driver = Hooks.driver;
		wait = new WebDriverWait(driver, 5);
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
	}

	@Given("^A web browser is at the saucelabs login page$")
	public void loginPage() {
		Assert.assertTrue(loginPage.verifyOnLoginPage());
	}

	@When("^A user provides incorrect credentials, and clicks on the login button with$")
	public void failedLogin(DataTable datatable) throws Exception {
		List<Map<String, String>> ffElements = datatable.asMaps(String.class, String.class);
		  for(Map<String, String> ffElement : ffElements){
			  loginPage.loginToSite(ffElement.get("userName"), ffElement.get("password"));
		  }

	}

	@Then("^The error message \"Epic sadface: Username and password do not match any user in this service\" is displayed$")
	public void successLogin() {
		Assert.assertTrue(homePage.verifyOnHomePage());
	}
}
