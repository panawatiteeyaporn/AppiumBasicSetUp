package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import PageObject.HomePage;

public class AbstractPage {

	// WebDriver instance variable
	protected WebDriver driver;

	// Assign the parameter driver to the driver of this class
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		this.Initialise(driver);
	}

	// Initialise elements of the page using PageFactory
	public void Initialise(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Return the current URL as string
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	// Return new HomePage object with specified URL
	public HomePage navigateToHomePage() {
		driver.get("https://www.oxfam.org.uk/");
		return new HomePage(driver);
	}
}
