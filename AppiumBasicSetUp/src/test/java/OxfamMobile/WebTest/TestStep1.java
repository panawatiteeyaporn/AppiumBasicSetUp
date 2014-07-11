package OxfamMobile.WebTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.AccountRegisterPage;
import PageObject.DonationPage;
import PageObject.LoginPage;

public class TestStep1 extends AbstractWebDriver {

	private LoginPage onLoginPage;
	private AccountRegisterPage onRegistrationPage;
	private DonationPage onDonationPage;

	@Test
	// Test that you are on the right HomePage
	public void testHomePageURL() {
		Assert.assertEquals(onHomePage.getPageUrl(),
				"https://www.oxfam.org.uk/");
	}

	
	@Test
	// Test that you are on the right LoginPage
	public void testLoginPageURL() {
		onLoginPage = onHomePage.clickLogin();
		Assert.assertEquals(onLoginPage.getPageUrl(),
				"https://www.oxfam.org.uk/account/login");
	}

	@Test
	// Test that you are on the right RegistrationPage
	public void testAccountRegisterPageURL() {
		onRegistrationPage = onHomePage.clickCreateAccount();
		Assert.assertEquals(onRegistrationPage.getPageUrl(),
				"https://www.oxfam.org.uk/account/register/");
	}

	//@Test
	// Test that you are on the right DonationPage
	public void testDonationPageURL() {
		onDonationPage = onHomePage.clickDonation();
		Assert.assertEquals(onDonationPage.getPageUrl(),
				"https://donate.oxfam.org.uk/southsudan?intcmp=hp_325b_hero_south-sudan-donate_2014-05-14");
	}
	

	
}
