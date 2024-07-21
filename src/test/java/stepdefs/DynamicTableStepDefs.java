package stepdefs;


import driverfactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DynamicPage;

import java.util.List;

public class DynamicTableStepDefs {
	private WebDriver driver;
	private WebDriverWait wait;

	private DynamicPage dynamicPage = new DynamicPage(DriverFactory.getDriver());


	@Given("^A web browser is at Techlistic dynamic web table page$")
	public void AWebBrowserIsAtTechlisticDynamicWebTablePages() {
		DriverFactory.getDriver()
				.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
	}

	@When("^user gets no of rows and columns from dynamic web table and validates$")
	public void userGetsNoOfRowsAndColumnsFromDynamicWebTableAndValidates() {
		List<WebElement> ColumnList = dynamicPage.getColumnList();
		System.out.println("No of Columns are :" + ColumnList.size());

		//To find no of Rows, first row is heading
		List<WebElement> RowList = dynamicPage.getRowList();
		System.out.println("No of Rows are :" + RowList.size());
	}


	@When("^user gets all the values from dynamic web table and validates$")
	public void userGetsAllTheValuesFromDynamicWebTableAndValidates() {
		List<WebElement> columnCount = dynamicPage.getColumnList();
		List<WebElement> rowCount = dynamicPage.getRowList();
		for (int i = 2; i <= rowCount.size(); i++) {
			//Used for loop for number of columns.
			for (int j = 1; j <= columnCount.size(); j++) {
//				String finalXpath = dynamicPage.getFirstPart() + i + dynamicPage.getSecondPart() + j + dynamicPage.getThirdPart();
				String tableData = dynamicPage.getTableData(i,j);
				System.out.println(tableData);
				System.out.println("---------------------------------");
			}

		}
	}
}

