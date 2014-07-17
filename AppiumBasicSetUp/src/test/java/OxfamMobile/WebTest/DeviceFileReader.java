package OxfamMobile.WebTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceFileReader {

	// Instance variable for storing Key String for the Map.
		private String capKey = null;

		// Create map to store devices details.
		private static final Map<String, List<String>> deviceCap = new HashMap<String, List<String>>();

		// The constructor automatically read txt file with the desire capabilities
		// and store them in deviceCap Map object for later use.
		public DeviceFileReader() {

			BufferedReader readCap = null;
			try {
				readCap = new BufferedReader(new FileReader(
						"deviceCapabilities.txt"));
				while (true) {
					String line = readCap.readLine();
					if (line == null) {
						break;
					}
					setValueToMap(line);
				}
			} catch (IOException e) {
				System.out.println("Problem with file reader!!");
			}

			// Close the readCap reader when the parsing is done.
			try {
				readCap.close();
			} catch (IOException e) {
				System.out.println("Problem with closing file reader!!");
			}

		}

		// This method handle the input of Keys and their corresponding values
		// into the Map object deviceCap.
		private void setValueToMap(String line) {

			if (line.contains("DeviceName")) {
				capKey = formattedStr(line);
				setDeviceMap();

			} else {
				deviceCap.get(capKey).add(formattedStr(line));
			}
		}

		// Set the Map object with the capKey variable as the key and empty
		// ArrayList of type String.
		private void setDeviceMap() {

			deviceCap.put(capKey, new ArrayList<String>());
		}

		// This method format the String by removing the title of the
		// capabilities and return only the capability's characters
		// ** Revise this method, it relies too much on hard code.
		private String formattedStr(String line) {

			String formatted = null;

			if (line.contains("DeviceName")) {
				formatted = line.replace("DeviceName ", "");
			} else if (line.contains("DeviceID")) {
				formatted = line.replace("DeviceID ", "");
			} else if (line.contains("DeviceType")) {
				formatted = line.replace("DeviceType ", "");
			} else if (line.contains("App")) {
				formatted = line.replace("App ", "");
			} else if (line.contains("BrowserName")) {
				formatted = line.replace("BrowserName ", "");
			} else if (line.contains("Version")) {
				formatted = line.replace("Version ", "");
			}

			return formatted;
		}

		// Return the deviceCap Map object that contain device name and capabilities
		public Map<String, List<String>> getDesiredMap() {

			return deviceCap;
		}
}
