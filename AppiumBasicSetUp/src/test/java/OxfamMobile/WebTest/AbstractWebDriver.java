package OxfamMobile.WebTest;

import io.appium.java_client.AppiumDriver;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import PageObject.HomePage;


/**
 * This class handle WebDriver for test classes, and 
 * set up the initial Home page environment for the test site. 
 */
public class AbstractWebDriver {
	
	// WebDriver instance variable 
	protected AppiumDriver driver;
	
	// HomePage type Object  
	protected HomePage onHomePage;
	
	// Set up driver 
	@Before
	public void setUp() throws Exception {	
		
		//Set the capabilities for the device and the chromedriver.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("app", "chrome");
	
		//Set the ChromeOptions so that the chromedriver can use the 
		//instance of the Chrome App Within the device. 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidPackage", "com.android.chrome");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		//Point AppiumDriver to the Appium server, along with set capabilities. 
		//The default address/port can be changed manually from the Appium app.
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);	
		
		onHomePage = new HomePage(driver);
		onHomePage.navigateToHomePage();
	}
	
	// Quit driver 
	@After
	public void shutDown() {
		driver.close();
		driver.quit();
	}

}
