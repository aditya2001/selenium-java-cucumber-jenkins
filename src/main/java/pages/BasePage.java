package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

	protected void waitUntilElementVisible(String locatorValue) {
		String description = locatorValue.split("~")[1].trim();
		By SearchBy=By.xpath(description);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBy));
		}
		catch(ElementNotInteractableException e){
			System.out.println("Element is hidden");
		}
	}


	protected void waitUntilElementClickable(By element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(ElementNotInteractableException e){
			System.out.println("Element is not clickable");
		}
	}


	public WebElement getElement(String locatorValue) throws Exception {
		String identifier;
		String description;
		WebElement element=null;
		By SearchBy=null;
		if (locatorValue == null || locatorValue.trim().equals("")){
			throw new Exception ("Locator not provided");

		}else {
			identifier= locatorValue.split("~")[0].trim();
			description= locatorValue.split("~")[1].trim();
			if (identifier.equals("xpath")){
				SearchBy=By.xpath(description);
			}
			else if (identifier.equals("name")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("id")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("css")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("tagname")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("partialinktext")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("classname")){
				SearchBy= By.name(description);
			}
			else{
				throw new Exception ("Invalid locator syntax");
			}

			try {
				element = driver.findElement(SearchBy);
			}
			catch(InvalidSelectorException e){
				System.out.println("Unable to find element,check locator........");
			}
			JavascriptExecutor jse= (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView()", element);
			return element;
		}
	}


	public List<WebElement> getElements(String locatorValue) throws Exception{
		String identifier;
		String description;
		List<WebElement> elements=null;
		By SearchBy=null;
		if (locatorValue == null || locatorValue.trim().equals("")){
			throw new Exception ("Locator not provided");

		}else {
			identifier= locatorValue.split("~")[0].trim();
			description= locatorValue.split("~")[1].trim();
			if (identifier.equals("xpath")){
				SearchBy=By.xpath(description);
			}
			else if (identifier.equals("name")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("id")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("css")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("tagname")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("partialinktext")){
				SearchBy= By.name(description);
			}
			else if (identifier.equals("classname")){
				SearchBy= By.name(description);
			}
			else{
				throw new Exception ("Locator not valid");
			}
			try {
				elements = driver.findElements(SearchBy);
			}
			catch(InvalidSelectorException e){
				System.out.println("Unable to find element,check locator........");
			}

//			JavascriptExecutor jse= (JavascriptExecutor) driver;
//			jse.executeScript("arguments[0].scrollIntoView()", elements);
			return elements;
		}
	}

	public void click(String locatorValue) throws Exception {
		WebElement element= getElement(locatorValue);
		try {
			element.click();
		}
		catch(ElementNotInteractableException e){
			System.out.println("Exception not clickable");
		}

	}


	public void selectDropDown(String locator, String valueToSelect) throws Exception {
		WebElement element= getElement(locator);
		Select select= new Select(element);
		try {
			select.selectByVisibleText(valueToSelect);
		}
		catch(ElementNotInteractableException e){
			System.out.println("Element not selectable");
		}
	}

	public void selectDropDown(String locator, int index) throws Exception {
		WebElement element= getElement(locator);
		Select select= new Select(element);
		try {
			select.selectByIndex(index);
		}
		catch (ElementNotInteractableException e){
			System.out.println("Element not selectable");
		}
	}

	public String getWebTableCellValue(String tableXpath, int rowIndex, int columnIndex) throws Exception {
		String finalXpath = tableXpath + rowIndex + "]/td[" + columnIndex + "]";
		return getElement(finalXpath).getText();
	}


	public List<String> getWebTableColumnNames(String tableXpath) throws Exception {
		String xpathInitial = tableXpath + "/tbody/tr/th";
		List<WebElement> elements = getElements(xpathInitial);
		List<String> columnNames = new ArrayList<>();
		for(int i=1; i<=elements.size(); i++){
			String xpath = tableXpath + "/tbody/tr[1]/th[" + i + "]";
			WebElement colElement = getElement(xpath);
			columnNames.add(colElement.getText());
		}
		return columnNames;

	}

	public String getPageTitle(){
		System.out.println("Returning Title");
		return driver.getTitle();
	}
}
