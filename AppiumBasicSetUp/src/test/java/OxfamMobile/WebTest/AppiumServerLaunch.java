package OxfamMobile.WebTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class contain methods which launch Appium server for the test.
 * 
 * @author piteeyaporn
 */
public class AppiumServerLaunch {

	// Instant variable for deviceCap Map object which contain the
	// device keys and their corresponding capabilities values.
	private Map<String, List<String>> jsonFileMap;

	// Default port for chromeDriver.
	private int chromeDriverPortDefault = 9690;

	// Path to appium cmd on the local machine.
	private String appiumPath;

	// ArrayList of Runtime processes.
	private List<Process> appiumProcesses = new ArrayList<Process>();

	// ArrayList of chromeDriver's port in used.
	private List<String> chromeDriverPortList = new ArrayList<String>();

	// The constructor takes in the deviceCap Map input and assigned it to the
	// above variable.
	public AppiumServerLaunch(Map jsonFileMap, String appiumPath) {

		this.jsonFileMap = jsonFileMap;
		this.appiumPath = appiumPath;
		System.out.println("Executed1");
	}

	// Call on method that launch Appium for each device.
	public void startAppiumServer() throws Exception {

		for (Map.Entry<String, List<String>> entry : jsonFileMap.entrySet()) {

			String udid_command = getUDID(entry.getValue().get(0));
			String nodeconfig_command = getJSONFilePath(entry.getValue().get(1));
			String appiumPort_command = getAppiumPort(entry.getValue().get(2));
			String chromeDriverPort_command = getChromeDriverPort();

			System.out.println("Executed2");
			launchAppiumNode(udid_command, nodeconfig_command,
					appiumPort_command, chromeDriverPort_command);
		}

	}

	// Create appium nodes and store each process in the appiumProcesses array.
	private void launchAppiumNode(String udid, String nodeconfig,
			String appiumPort, String chromeDriverPort) throws Exception {

		System.out.println("Launching Appium node - waiting");
		String line = appiumPath + nodeconfig + appiumPort + udid
				+ chromeDriverPort;

		Process pr = Runtime.getRuntime().exec("cmd /C " + line);

		Thread.sleep(10000);
		appiumProcesses.add(pr);
		chromeDriverPortList.add(chromeDriverPort);
		System.out.println("Launching Appium node - completed");
	}

	// Return cmd command to open specified chromedriver port.
	private String getChromeDriverPort() {

		String chromedriver_command = "--chromedriver-port "
				+ String.valueOf(chromeDriverPortDefault) + " ";
		chromeDriverPortDefault++;
		return chromedriver_command;
	}

	// Return cmd command to open specified port for appium.
	private String getAppiumPort(String port) {

		String port_command = "--port " + port + " ";
		return port_command;
	}

	// Return cmd command to connect Appium node to the device.
	private String getUDID(String deviceID) {

		String id_command = "-U " + deviceID + " ";
		return id_command;
	}

	// Return cmd command for nodeconfig with path to the json file.
	private String getJSONFilePath(String path) {

		String nodeconfig_command = "--nodeconfig " + path + " ";
		return nodeconfig_command;
	}

	// Destroy appium and chromedriver processes.
	public void appiumShutDown() throws Exception {
		
		for (int i = 0; i < appiumProcesses.size(); i++){
			appiumProcesses.get(i).destroy();
			String killport = getKillPortCommand(chromeDriverPortList.get(i)); 
			Process p = Runtime.getRuntime().exec("cmd /C " + killport);
			Thread.sleep(3000);
			p.destroy();
		}
	}

	// Return the command for closing the chromedriver port. 
	private String getKillPortCommand(String port) {

		String command = "FOR /F \"tokens=4 delims= \" %%P IN ('netstat -a -n -o ^| findstr :"
				+ port + "') DO @ECHO TaskKill.exe /PID %%P";
		return command;
	}
}
