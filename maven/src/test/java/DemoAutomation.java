

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoAutomation {

	public static void main(String[] args) {
		/*
		 * System.setProperty sets the system property specified by the key. For example,
		 * project the key is the webdriver and the value must be the
		 * location of where the driver is.
		 * Ensure selenium driver jar file has been configured with the build path.
		 */
		System.setProperty("webdriver.chrome.driver", "/home/kay/Desktop/BrowserDrivers/chromedriver_linux64/chromedriver");
		
		ChromeDriver driver = new ChromeDriver();
//		FirefoxDriver driver = new FirefoxDriver();
//		EdgeDriver driver = new EdgeDriver();
		driver.get("http://www.google.com");
		
	}

}
