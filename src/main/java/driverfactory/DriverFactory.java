package driverfactory;

import drivermanager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;


public final class DriverFactory {
	private DriverFactory(){

	}


	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 *
	 * @param browser
	 * @return this will return tldriver.
	 */
	public static void initDriver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			DriverManager.setDriver(new ChromeDriver(options));
		} else if (browser.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-headless");
			WebDriverManager.firefoxdriver().setup();
			DriverManager.setDriver(new FirefoxDriver(options));
		} else if (browser.equals("safari")) {
			DriverManager.setDriver(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().window().maximize();

	}

}
