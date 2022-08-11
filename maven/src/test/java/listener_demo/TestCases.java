package listener_demo;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(listener_demo.ListenerTest.class)	

public class TestCases {

	public static WebDriver driver;
	public static WebDriver driver2;
	public static String url = "https://www.saucedemo.com/";

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
			driver2 = new ChromeDriver();
		} else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
	
	@Test
	//Test Homepage Title
	public void verifyHomepageTitle() {
		driver.get(url);
		driver.manage().window().maximize();
		String expectedTitle = "Swag Labs";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		System.out.println("The actual title of the webpage is: " + actualTitle);
	}
	// Get current URL
	@Test
	public void verifyCurrentURL() {
		driver.get(url);
		String expectedURL = url;
		String actualURL = driver.getCurrentUrl();
		System.out.println("Expected URL is " + expectedURL + " Actual URL is " + actualURL);
		Assert.assertEquals(actualURL, expectedURL);
	}
	// Retrieve page source
	@Test 
	public void verifyPageSource() {
		String pagesource = driver.getPageSource();
		System.out.println("The page source is: " + pagesource);
	}
	// Navigate to a different URL
	@Test
	public void navigateToDifferentUrl() {
		String previousURL = url;
		String differentURL = "http://www.google.com";
		driver.navigate().to(differentURL);
		String newURL = driver.getCurrentUrl();
		System.out.println("The previous url was " + previousURL + " The current URL is " + newURL);
		Assert.assertNotEquals(previousURL, newURL);
	}
	// Calculate page load time
	@Test
	public void calculatePageLoadTime() {
		long timeBeforePageLoad = System.currentTimeMillis();
		driver.get(url);
		WebDriverWait waitTime = new WebDriverWait(driver,Duration.ofSeconds(5));
		waitTime.until(ExpectedConditions.elementToBeClickable(By.className("submit-button")));
		long timeAfterPageLoad = System.currentTimeMillis();
		long pageLoadTime = timeAfterPageLoad - timeBeforePageLoad;
		System.out.println("Page loaded in " + pageLoadTime + " milliseconds");
	}
	// Test successful login
	@Test
	public void testSuccessfulLogin() {
		// Expected URL after login
		String expectedUrlAfterLogin = "https://www.saucedemo.com/inventory.html";
		driver.get(url);
		// Get login input using elements id
		WebElement username = driver.findElement(By.id("user-name"));
		// Enter correct user name
		username.sendKeys("standard_user");
		// Get password input using elements name
		WebElement password = driver.findElement(By.name("password"));
		//Enter correct password
		password.sendKeys("secret_sauce");
		// Find login button using xpath
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
		// Click login
		loginButton.click();
		// Wait for page to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		// Check url after login
		String loggedInUrl = driver.getCurrentUrl();
		Assert.assertEquals(loggedInUrl, expectedUrlAfterLogin);
		System.out.println("Expected Url after login is " + expectedUrlAfterLogin +
				" And the actual Url after login is " + loggedInUrl);
	}
	
	// Verify number of elements on a particular page using findElements and xpath
	@Test
	public void verifyNumberOfProducts() {
		driver.get("https://www.saucedemo.com/inventory.html");
		// Create custom xpath to get number of items by counting the divs generated under the inventory list div. 
		// Use .size() method to return number.
		// Make a list of the web elements
		List<WebElement> webelements = driver.findElements(By.xpath("//div[@class='inventory_list']/div"));
		int actualNumberOfItems = webelements.size();
		int expectedNumberOfItems = 6;
		System.out.println("There are " + actualNumberOfItems  + " items in the array");
		System.out.println("The web elements are " + webelements);
		Assert.assertEquals(actualNumberOfItems, expectedNumberOfItems);
		
	}
	
	// Get window handle(s) example
	@Test
	public void getWindowHandle() {
		driver2.get("https://demoqa.com/browser-windows");
		String mainWindowHandle = driver2.getWindowHandle();
		System.out.println("The window handle is: " + mainWindowHandle);
		driver2.findElement(By.xpath("//*[@id=\"windowButton\"]")).click();
		driver2.findElement(By.xpath("//*[@id=\"messageWindowButton\"]")).click();
		Set<String> windowhandles = driver2.getWindowHandles();
		System.out.println("The window handles are: " + windowhandles);
		// Loop to close windows
		for(String window : windowhandles) {
			if(!mainWindowHandle.equalsIgnoreCase(window)) {
				driver2.switchTo().window(window);
				driver2.close();			
			}
		}
		driver2.switchTo().window(mainWindowHandle).close();
	}
	
	@AfterTest
	public void terminateBrowser() {
		System.out.println("Testing completed... Closing the browser session.");
		driver.close();
	}
}
