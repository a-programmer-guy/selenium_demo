package listener_demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {

	public static WebDriver driver;

	@BeforeTest
	public void setup() {
		System.out.println("Starting the browser session");
		String browser = "chrome";
		if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
	
	@Test
	public void verifyHomepageTitle() {
		String url = "https://www.saucedemo.com/";
		driver.get(url);
		driver.manage().window().maximize();
		String expectedTitle = "Swag Labs";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}
	@AfterTest
	public void terminateBrowser() {
		System.out.println("Closing the browser session");
		driver.close();
	}
}
