package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;

	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	protected void waitUntilElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public String getWebTableCellValue(String tableXpath, int rowIndex, int columnIndex) {
		String finalXpath = tableXpath + rowIndex + "]/td[" + columnIndex + "]";
		return driver.findElement(By.xpath(finalXpath)).getText();
	}

	public List<String> getWebTableColumnNames(String tableXpath){
		List<WebElement> elements = driver.findElements(By.xpath(tableXpath + "/tbody/tr/th"));
		List<String> columnNames = new ArrayList<>();
		for(int i=1; i<=elements.size(); i++){
			WebElement colElement = driver.findElement(By.xpath(tableXpath + "/tbody/tr[1]/th[" + i + "]"));
			columnNames.add(colElement.getText());
		}
		return columnNames;

	}

	public String getPageTitle(){
		return driver.getTitle();
	}
}
