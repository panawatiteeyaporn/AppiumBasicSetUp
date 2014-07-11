package OxfamMobile.WebTest;

import io.appium.java_client.AppiumDriver;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws Exception {	
	
		CapDesiredForDriver capDesired = new CapDesiredForDriver();
		
		//Set the capabilities for the device and the chromedriver.
		//See CapDesiredForDriver class for more details. 
		DesiredCapabilities cap = capDesired.getCapability(browser);
	
		//Set the ChromeOptions so that the chromedriver can use the 
		//instance of the Chrome App Within the device. 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidPackage", "com.android.chrome");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		//Point AppiumDriver to the Selenium Grid server, along with set capabilities.
		driver = new AppiumDriver(new URL("http://127.0.0.1:4444/wd/hub"),
				cap);	
		
		onHomePage = new HomePage(driver);
		onHomePage.navigateToHomePage();
	}
	
	// Quit driver 
	@AfterMethod
	public void shutDown() {
		driver.close();
		driver.quit();
	}

}
