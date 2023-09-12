package com.bk_pageObject;

import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bk.testbase.TestBase;
import com.bk_helper.assertion.VerificationHelper;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_helper.logger.LoggerHelper;
import com.bk_helper.wait.WaitHelper;

/*
 * @author  Kajol Gupta
 *
 */
public class LoginPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	WaitHelper waitHelper;

	@FindBy(xpath = "//button[@name=\"provider\"]")
	WebElement SignInButton;

	@FindBy(name = "loginfmt")
	WebElement emailAddress;

	@FindBy(xpath = "//input[@value=\"Next\"]")
	WebElement NextBtn;

	@FindBy(name = "passwd")
	WebElement password;

	@FindBy(xpath = "//input[@value=\"Sign in\"]")
	WebElement submitLogin;

	@FindBy(xpath = "//div[contains(text(),'Stay signed in?')]")
	WebElement StaySignIn;

	@FindBy(xpath = "//input[@id='idSIButton9']")
	WebElement clickToYes;

	@FindBy(id = "usernameError")
	WebElement loginError;

	@FindBy(id = "passwordError")
	WebElement pwdloginError;

	@FindBy(id = "idBtn_Back")
	WebElement clickBackBtn;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement profileName;

	@FindBy(xpath = "//a[@title=\"Sign out\"]")
	WebElement logout;

	@FindBy(xpath = "//h1[@id=\"external-login-heading\"]")
	WebElement signoutPage;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(SignInButton,5);
		TestBase.logExtentReport("Login Page Object Created");
	}

	public void clickOnSignInButton() {
		log.info("clicking on sign in button...");
		logExtentReport("clicking on sign in button...");
		this.SignInButton.click();
	}

	public void enterEmailAddress(String emailAddress) {
		log.info("entering email address...." + emailAddress);
		logExtentReport("entering email address...." + emailAddress);
		this.emailAddress.clear();
		this.emailAddress.sendKeys(emailAddress);
	}

	public void clickOnNextButton() {
		log.info("Clicking on next button");
		logExtentReport("Clicking on next button");
		this.NextBtn.click();
	}

	public void enterPassword(String password) {
		this.password.clear();
		log.info("Entering password....******");
		logExtentReport("Entering password....******");
		this.password.sendKeys(password);
	}

	public void clickOnSubmitButton() {
		log.info("clicking on submit button...");
		logExtentReport("clicking on submit button...");
		this.submitLogin.click();
	}

	public void clickOnBackButton() {
		log.info("Clicking on back button");
		logExtentReport("Clicking on back button");
		this.clickBackBtn.click();
	}

	public void handleAdditionalPopup() {
		log.info("clicking on yes button to continue...");
		logExtentReport("clicking on yes button to continue...");
		clickToYes.click();
		log.info("User Login successfully...");
	}

	public void clickOnProfileIconButton() {
		log.info("clicking on profile icon button...");
		logExtentReport("clicking on profile icon button...");
		this.profileName.click();
	}

	public void clickOnLogoutButton() {
		log.info("clicking on logout button...");
		logExtentReport("clicking on logout button...");
		waitHelper.waitForElement(logout, 10);
		this.logout.click();
	}

	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInButton();
		enterEmailAddress(emailAddress);
		clickOnNextButton();
		enterPassword(password);
		clickOnSubmitButton();
		handleAdditionalPopup();
	}

	public void logintoApplication(String emailAddress) {
		clickOnSignInButton();
		enterEmailAddress(emailAddress);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickOnNextButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void loginToApplication2(String emailAddress, String password) {
		enterEmailAddress(emailAddress);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickOnNextButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterPassword(password);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickOnSubmitButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifySuccessLogin() {
		return new VerificationHelper(driver).isDisplayed(profileName);
	}

	public String verifyEmailLoginMsg() {
		return new VerificationHelper(driver).getText(loginError);
	}

	public String verifyPassLoginMsg() {
		waitHelper.waitForElement(pwdloginError, 5);
		return new VerificationHelper(driver).getText(pwdloginError);
	}

	public boolean verifyBlankUserLogin() {
		return new VerificationHelper(driver).isDisplayed(emailAddress);
	}

	public void verifyUserLogin(boolean result) {
		Assert.assertEquals(profileName.isDisplayed(), result);
		logExtentReport("User Login successfully....");
	}

	public void logout() {
		clickOnProfileIconButton();
		clickOnLogoutButton();
	}

	public void verifyUserLogOut(boolean result) {
		Assert.assertEquals(emailAddress.isDisplayed(), result);
		logExtentReport("User LogOut successfully....");
	}

	public boolean verifySuccessLogOut() {
		return new VerificationHelper(driver).isDisplayed(signoutPage);
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

}
