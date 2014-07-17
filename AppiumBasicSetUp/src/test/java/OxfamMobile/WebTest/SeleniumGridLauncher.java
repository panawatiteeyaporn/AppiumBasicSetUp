package OxfamMobile.WebTest;

import java.net.BindException;
import java.net.ServerSocket;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.server.RemoteControlConfiguration;

/**
 * This class launch the selenium grid for the test.
 * 
 * @author piteeyaporn
 */
public class SeleniumGridLauncher {

	// Selenium server hub;
	private Hub hub;

	// Empty constructor.
	public SeleniumGridLauncher() {

	}

	// Start Selenium grid by creating and starting Selenium Hub object.
	// Also check if the socket is in use before launching the grid. 
	public void startSeleniumGrid() throws Exception {

		// Check if the port is already in used by trying to close the socket.
		try {
			ServerSocket serverSocket = new ServerSocket(
					RemoteControlConfiguration.DEFAULT_PORT);
			serverSocket.close();

			// Server not up, start the hub
			try {
				GridHubConfiguration config = new GridHubConfiguration();
				config.setHost("localhost");
				hub = new Hub(config);
				hub.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (BindException e) {
			System.out.println("Selenium server already up, will reuse...");
		}
	}

	// Close Selenium grid.
	public void shutDownSeleniumGrid() throws Exception {
	
		hub.stop();
	}
}
