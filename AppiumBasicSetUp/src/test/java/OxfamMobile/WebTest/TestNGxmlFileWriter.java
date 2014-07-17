package OxfamMobile.WebTest;

import java.util.List;
import java.util.Map;

/**
 * This class rewrite the TestNG.xml file. ***Since the test run from TestNG
 * file, it might not be possible to use java to write the file.
 * 
 * @author piteeyaporn
 */
public class TestNGxmlFileWriter {

	// Instant variable for deviceCap Map object which contain the
	// device keys and their corresponding capabilities values.
	private Map<String, List<String>> deviceCap;

	// The constructor takes in the deviceCap Map input and assigned it to the
	// above variable.
	public TestNGxmlFileWriter(Map deviceCap) {
		this.deviceCap = deviceCap;
	}

}
