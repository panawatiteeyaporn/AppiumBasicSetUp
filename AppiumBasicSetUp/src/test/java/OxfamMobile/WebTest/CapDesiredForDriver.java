package OxfamMobile.WebTest;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class handle the desired capabilities and return a set of capabilities 
 * which have been retrieve from the the text file. (Read text not yet implemented)
 * @author piteeyaporn
 *
 */
public class CapDesiredForDriver {
	
	public CapDesiredForDriver() {
		
	}
	
	public DesiredCapabilities getCapability(String device){
		
		//Set the capabilities for the device and the chromedriver.
		DesiredCapabilities cap = new DesiredCapabilities();
		
		if (device.equalsIgnoreCase("Samsung")){
			
			cap.setCapability("device", "Android");
			cap.setCapability("app", "chrome");
			cap.setCapability("browserName", "Chrome");
			cap.setCapability("version", "4.1.2");
			cap.setCapability("DeviceName", "GT-I8190N");
			
		} else if (device.equalsIgnoreCase("HTC")){
			
			cap.setCapability("device", "Android");
			cap.setCapability("app", "chrome");
			cap.setCapability("browserName", "Chrome");
			cap.setCapability("version", "4.0.3");
		
		}
				
		return cap;
	}

}
