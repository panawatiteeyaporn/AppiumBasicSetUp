package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

	@FindBy(id = "header_0_ctl01_StandardLogin")
	WebElement loginButton;
	
	@FindBy(id = "header_0_ctl01_Register")
	WebElement registerationButton;
	
	@FindBy(css = "div[class='sm sm-image']>p")
	WebElement donationButton;
	
	
	// Initialise driver and WebElements
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// Click login button and return new LoginPage object
	public LoginPage clickLogin() {
		loginButton.click();
		return new LoginPage(driver);
	}
	
	// Click create account button and return new AccountRegistrationPage object
	public AccountRegisterPage clickCreateAccount() {
		registerationButton.click();
		return new AccountRegisterPage(driver);
	}
	
	// Click donation button and return new DonationPage object
	public DonationPage clickDonation() {
		donationButton.click();
		return new DonationPage(driver);
	}

}
