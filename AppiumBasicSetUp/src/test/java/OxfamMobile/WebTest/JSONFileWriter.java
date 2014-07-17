package OxfamMobile.WebTest;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class create JSON file for each device, and save it in the project
 * folder. Warning! the program will overwrite the existing file if they have the same name. 
 * 
 * @author piteeyaporn
 */
public class JSONFileWriter {

	// Map object contain json file's name as the key and UDID, file's path and Appium's port. 
	private Map<String, List<String>> jsonFileMap = new HashMap<String, List<String>>();
	
	// Instant variable for deviceCap Map object which contain the
	// device keys and their corresponding capabilities values.
	private Map<String, List<String>> deviceCap;
	
	// Keep track of the number of file created and use the number as part
	// of the file name.
	private int fileNumber = 0;
	
	// Default port number for Appium server. 
	private int appiumPort = 4723;
	
	// String variables for the default set up of JSON file.
	private String maxInstances = "1";
	private String cleanUpCycle = "200";
	private String timeout = "90000";
	private String proxy = "org.openqa.grid.selenium.proxy.DefaultRemoteProxy";		
	private String maxSession = "1";	
	private String appiumHost = "127.0.0.1";	
	private String register = "true";	
	private String registerCycle = "5000";	
	private String hubPort = "4444";	
	private String hubHost = "localhost";
	
	// The constructor takes in the deviceCap Map input and assigned it to the
	// above variable.
	public JSONFileWriter(Map deviceCap) {

		this.deviceCap = deviceCap;
	}
	
	// Call out the method that write JSON file for each device. 
	public void writeJSONFile() throws Exception {
		
		for (Map.Entry<String, List<String>> entry : deviceCap.entrySet()){
			createJSONFile(entry.getKey(), entry.getValue());
			fileNumber++;
			appiumPort++;
		}
	}

	// Create the JSON file by writing it out line by line. 
	private void createJSONFile(String key, List<String> value) throws Exception {
		
		String fileName = getFileName(); 
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		
		writer.println("{");
		writer.println("\"capabilities\":");
		writer.println("[");
		writer.println("{");
		writer.println("\"DeviceName\": \"" + key + "\",");
		writer.println("\"device\": \"" + value.get(1) + "\",");
		writer.println("\"app\": \"" + value.get(2) + "\",");
		writer.println("\"browserName\": \"" + value.get(3) + "\",");
		writer.println("\"version\":\"" + value.get(4) + "\",");		
		writer.println("\"maxInstances\": " + maxInstances);
		writer.println("}");		
		writer.println("],");	
		writer.println("\"configuration\":");
		writer.println("{");
		writer.println("\"cleanUpCycle\":" + cleanUpCycle + ",");
		writer.println("\"timeout\":" + timeout + ",");
		writer.println("\"proxy\": " + "\"" + proxy + "\",");
		writer.println("\"url\":"+ "\"http://" + appiumHost + ":" + appiumPort + "/wd/hub\",");
		writer.println("\"maxSession\": " + maxSession + ",");
		writer.println("\"port\": " + appiumPort + ",");
		writer.println("\"host\": " + "\"" + appiumHost + "\",");
		writer.println("\"register\": " + register + ",");
		writer.println("\"registerCycle\": " + registerCycle + ",");
		writer.println("\"hubPort\": " + hubPort + ",");
		writer.println("\"hubHost\": " + "\"" + hubHost + "\"");
		writer.println("}");
		writer.println("}");
		writer.close();
		
		// Add fileName to Map object as key with UDID, file's path and Appium port as values. 
		setJSONFileMap(fileName, value.get(0), getFilePath(fileName), appiumPort);
	}
	
	// Set the Map with the given key and values. 
	private void setJSONFileMap(String fileName, String udid,
			String filePath, int port) {
		
		jsonFileMap.put(fileName, new ArrayList<String>());
		jsonFileMap.get(fileName).add(udid);
		jsonFileMap.get(fileName).add(filePath);
		jsonFileMap.get(fileName).add(String.valueOf(port));
	}

	// Return file's path as string. 
	private String getFilePath(String name) {
		
		File file = new File(name);
		return file.getAbsolutePath();
	}

	// Return file name with file number attached at the end of the name.
	// so the file are not overwritten by the createJSONFile method. 
	private String getFileName() {
		
		String name = "appiumGrid";
		if (fileNumber != 0){
			name = name + String.valueOf(fileNumber);
		}
		name = name + ".json";
		
		return name;
	}
	
	// Return the jsonFileMap object. 
	public Map<String, List<String>> getJSONFileMap() {
		
		return jsonFileMap;
	}
}
