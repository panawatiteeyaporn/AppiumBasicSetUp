package OxfamMobile.WebTest;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class handle the desired capabilities and return a set of capabilities
 * which have been retrieve from the the text file. 
 * 
 * @author piteeyaporn
 * 
 */
public class CapDesiredForDriver {
	
	// Instant variable for deviceCap Map object which contain the 
	// device keys and their corresponding capabilities values. 
	private Map<String, List<String>> deviceCap;
	
	// The constructor takes in the deviceCap Map input and assigned it to the above 
	// variable. 
	public CapDesiredForDriver(Map deviceCap) {
		
		this.deviceCap = deviceCap;	
	}

	// Return the set of capabilities for the device.
	public DesiredCapabilities getCapability(String device) {

		// Set the capabilities for the device and the chromedriver.
		DesiredCapabilities cap = new DesiredCapabilities();

		// Check if the key is in the deviceCap Map object, if yes,
		// set all capabilities accordingly.
		if (deviceCap.containsKey(device)) {

			cap.setCapability("DeviceName", device);
			cap.setCapability("device", (deviceCap.get(device)).get(1));
			cap.setCapability("app", (deviceCap.get(device)).get(2));
			cap.setCapability("browserName", (deviceCap.get(device)).get(3));
			cap.setCapability("version", (deviceCap.get(device)).get(4));
		}

		return cap;
	}

	
	
}
