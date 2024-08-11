package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


//import utils.BaseDriverClass;
//https://testsigma.com/blog/page-object-model-in-selenium/

public class ActionsPage extends BasePage {
	private WebDriver driver;
	// Locator for Email Address
	private static final String dropDownButton = Constants.XPATH+"~"+"//a[text()='Dropdown']";
	private static final String selectDropDown = Constants.XPATH+"~"+"//select[@id='dropdown']";
	private static final String rows = "//*[@id='customers']/tbody/tr";
	private static final String columns = Constants.XPATH+"~"+"//*[@id='customers']";


	public ActionsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOn() throws Exception {
		click(dropDownButton);
	}

	public void selectDropDownValue(String value) throws Exception {
		selectDropDown(selectDropDown, value);
	}

}

