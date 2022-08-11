package listener_demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners(listener_demo.ListenerTest.class)	

public class WebElementsTesting {
	public static WebDriver driver;

}
