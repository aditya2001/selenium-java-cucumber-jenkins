package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
//import utils.BaseDriverClass;
//https://testsigma.com/blog/page-object-model-in-selenium/

public class DynamicPage {
	private WebDriver driver;
	// Locator for Email Address
	public static final String firstPart = "//*[@id='customers']/tbody/tr[";
	public static final String secondPart = "]/td[";
	public static final String thirdPart = "]";

	@FindBy(xpath = "//*[@id='customers']/tbody/tr/th")
	@CacheLookup
	private List<WebElement> columns;

	@FindBy(xpath = "//*[@id='customers']/tbody/tr")
	@CacheLookup
	private List<WebElement> rows;


	public DynamicPage(WebDriver driver) {
//		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getColumnList() {
		return columns;
	}

	public List<WebElement> getRowList() {
		return rows;
	}

	public String getFirstPart() {
		return firstPart;
	}
	public String getSecondPart() {
		return secondPart;
	}
	public String getThirdPart() {
		return thirdPart;
	}

	public String getTableData(int i, int j) {
		String finalXpath = getFirstPart() + i + getSecondPart() + j + getThirdPart();
		return driver.findElement(By.xpath(finalXpath)).getText();
	  }
}

