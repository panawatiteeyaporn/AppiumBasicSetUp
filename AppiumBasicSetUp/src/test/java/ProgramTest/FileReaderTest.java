package ProgramTest;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import OxfamMobile.WebTest.AppiumServerLaunch;
import OxfamMobile.WebTest.CapDesiredForDriver;
import OxfamMobile.WebTest.DeviceFileReader;
import OxfamMobile.WebTest.JSONFileWriter;
import OxfamMobile.WebTest.SeleniumGridLauncher;

public class FileReaderTest {

	// Local path to Appiunm cmd.
	private String appiumPath = "C:/Users/piteeyaporn/Development/AppiumForWindows-1.0.0/AppiumForWindows/node_modules/.bin/appium ";
	
	/**
	 * May not need these anymore. TO BE DELETED.
	// Local path to Selenium grid jar file
	private String gridPath = "C:/Users/piteeyaporn/Development/Selenium Server";

	// Command to launch Selenium grid jar file.
	private String command = "java -jar selenium-server-standalone-2.42.2.jar -role hub";
	
	private String jarFile = "selenium-server-standalone-2.42.2.jar";
	*/ 
	
	// Use DeviceFileReader class to read the file and store the infor in deviceCap Map.
	private Map<String, List<String>> deviceCap = new DeviceFileReader()
			.getDesiredMap();
	
	// Testing FileReader's Map
	//@Test
	public void test() {

		System.out.println("Print out Map");

		for (Map.Entry<String, List<String>> entry : deviceCap.entrySet()) {

			System.out.println("Key = " + entry.getKey());
			System.out.println("Values = " + entry.getValue());

			System.out.println("DeviceName: " + entry.getKey());
			System.out.println("device: " + entry.getValue().get(1));
			System.out.println("app: " + entry.getValue().get(2));
			System.out.println("browserName: " + entry.getValue().get(3));
			System.out.println("version: " + entry.getValue().get(4));

			System.out.println("Size = " + entry.getValue().size());
			System.out.println();
		}

	}

	// Testing CapDesiredForDriver return object.
	//@Test
	public void test1() {

		String device = "GT-I8190N";
		CapDesiredForDriver capDesired = new CapDesiredForDriver(deviceCap);
		System.out.println(capDesired.getCapability(device).toString());
	}

	// Testing the JSONWriter class by creating the file base on
	// the deviceCapabilities text file.
	//@Test
	public void test2() throws Exception {

		JSONFileWriter myFile = new JSONFileWriter(deviceCap);
		myFile.writeJSONFile();
		System.out.println("File created.");
		System.out.println("Contents are as follows; ");
		
		Map<String, List<String>> fileList = myFile.getJSONFileMap();
		
		for (Map.Entry<String, List<String>> entry : fileList.entrySet()) {
			
			System.out.println("File name = " + entry.getKey());
			System.out.println("Values = " + entry.getValue());
			System.out.println("UDID: " + entry.getValue().get(0));
			System.out.println("File path: " + entry.getValue().get(1));
			System.out.println("Appium port: " + entry.getValue().get(2));
			
		}
	}

	// Test the SeleniumGridLaunch.java by launching the grid and check the
	// localhost:4444 port.
	//@Test
	public void test4() throws Exception {

		SeleniumGridLauncher myGrid = new SeleniumGridLauncher();
		myGrid.startSeleniumGrid();
		System.out.println("Grid started");
		
		try {
			Thread.sleep(50000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println("Grid closed");
		myGrid.shutDownSeleniumGrid();
	}

	// Test launching the Appium nodes.
	@Test
	public void test5() throws Exception {
		
		JSONFileWriter myFile = new JSONFileWriter(deviceCap);
		myFile.writeJSONFile();
		System.out.println("File created.");
		
		AppiumServerLaunch myAppium = new AppiumServerLaunch(myFile.getJSONFileMap(), appiumPath);
		myAppium.startAppiumServer();
		System.out.println("Appium started");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println("Appium ended");
		myAppium.appiumShutDown();
	}
}
