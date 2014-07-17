package OxfamMobile.WebTest;

import io.appium.java_client.AppiumDriver;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import PageObject.HomePage;

/**
 * This class handle WebDriver for test classes, and set up the initial Home
 * page environment for the test site.
 */
public class AbstractWebDriver {

	// WebDriver instance variable
	protected AppiumDriver driver;

	// HomePage type Object
	protected HomePage onHomePage;

	// Map type object which contain all the keys and their corresponding
	// values.
	private Map<String, List<String>> deviceCap = new DeviceFileReader()
			.getDesiredMap();

	// SeleniumGridLauncher object for launching grid.
	private SeleniumGridLauncher myGrid;

	// AppiumServerLaunch object for launching appium node.
	private AppiumServerLaunch myAppium;

	// Local path to Appiunm cmd.
	private String appiumPath = "C:/Users/piteeyaporn/Development/AppiumForWindows-1.0.0/AppiumForWindows/node_modules/.bin/appium ";

	// Create JSON configuration, Launch Selenium Grid, Launch Appium.
	@BeforeClass
	public void setTestEnvironment() throws Exception {

		// Create JSON configuration files.
		JSONFileWriter myFile = new JSONFileWriter(deviceCap);
		myFile.writeJSONFile();
		System.out.println("File created.");

		// Start Selenium grid hub.
		myGrid = new SeleniumGridLauncher();
		myGrid.startSeleniumGrid();
		System.out.println("Grid started.");

		// Start Appium nodes. 
		myAppium = new AppiumServerLaunch(myFile.getJSONFileMap(), appiumPath);
		myAppium.startAppiumServer();
		System.out.println("Appium started");
	}

	// Close Selenium grid and Appium server.
	@AfterClass
	public void closeEnvironment() throws Exception {

		myGrid.shutDownSeleniumGrid();
		Thread.sleep(1000);
		myAppium.appiumShutDown();
	}

	// Set up driver
	@Parameters("device")
	@BeforeMethod
	public void setUp(String device) throws Exception {

		CapDesiredForDriver capDesired = new CapDesiredForDriver(deviceCap);

		// Set the capabilities for the device and the chromedriver.
		// See CapDesiredForDriver class for more details.
		DesiredCapabilities cap = capDesired.getCapability(device);

		// Set the ChromeOptions so that the chromedriver can use the
		// instance of the Chrome App Within the device.
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidPackage", "com.android.chrome");
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Point AppiumDriver to the Selenium Grid server, along with set
		// capabilities.
		driver = new AppiumDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);

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
