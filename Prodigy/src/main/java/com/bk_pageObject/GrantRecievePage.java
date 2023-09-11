package com.bk_pageObject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bk.testbase.TestBase;
import com.bk_helper.assertion.VerificationHelper;
import com.bk_helper.logger.LoggerHelper;
import com.bk_helper.wait.WaitHelper;

public class GrantRecievePage {

	private final Logger log = LoggerHelper.getLogger(GrantRecievePage.class);

	public WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//span[text()=\"Grant Receives\"]")
	WebElement grantMenu;

	@FindBy(xpath = "//span[text()=\"New\"]")
	WebElement clickOnNewButton;

	@FindBy(xpath = "//input[@aria-label=\"Subject\"]")
	WebElement grantSubject;

	@FindBy(xpath = "//span[text()=\"Save\"]")
	WebElement saveButton;

	@FindBy(xpath = "//span[@data-id=\"grant_grantreceivename-error-message\"]")
	WebElement subjectError;

	@FindBy(xpath = "//span[@data-id=\"grant_requestedamount-error-message\"]")
	WebElement requesteAmountError;

	public GrantRecievePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Grant Maker page object created");
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	public void clickOnGrantRecieveMenu() {
		log.info("Clicking on Grant Recive Menu");
		logExtentReport("Clicking on Grant Recive Menu");
		this.grantMenu.click();
		waitHelper.pageLoadTime(5, TimeUnit.SECONDS);
	}

	public void clickOnNewButton() {
		log.info("Clicking on new button to create new grant");
		logExtentReport("Clicking on new button to create new grant");
		this.clickOnNewButton.click();
		waitHelper.pageLoadTime(5, TimeUnit.SECONDS);
	}

	public void enterSubject(String subject) {
		log.info("Entering subject");
		logExtentReport("Entering subject");
		this.grantSubject.clear();
		this.grantSubject.sendKeys(subject);
	}

	public void clickOnSaveButton() {
		log.info("Clicking on save button");
		logExtentReport("Clicking on save button");
		this.saveButton.click();
	}

	public void GrantRecieveProcessFlow(String subject) {
		clickOnGrantRecieveMenu();
		clickOnNewButton();
		enterSubject(subject);
	}
	
	public void grantBlankUserFieldTest() {
		clickOnGrantRecieveMenu();
		clickOnNewButton();
		clickOnSaveButton();
	}

	public boolean verifySuccessLogin() {
		return new VerificationHelper(driver).isDisplayed(grantSubject);
	}

	public String verifyEmptySubjectFieldMsg() {
		return new VerificationHelper(driver).getText(subjectError);
	}

	public String verifyFailLoginMsg2() {

		waitHelper.waitForElement(requesteAmountError, 5);
		return new VerificationHelper(driver).getText(requesteAmountError);

	}

	public boolean verifyBlankUserField() {
		return new VerificationHelper(driver).isDisplayed(grantSubject);
	}

	public void verifyUserLogin(boolean result) {

		Assert.assertEquals(grantSubject.isDisplayed(), result);
		logExtentReport("User Login successfully....");

	}

	public void logout() {
		//clickOnProfileIconButton();
		//clickOnLogoutButton();
	}

	public void verifyUserLogOut(boolean result) {

		Assert.assertEquals(grantSubject.isDisplayed(), result);
		logExtentReport("User LogOut successfully....");

	}

	public boolean verifySuccessLogOut() {
		return new VerificationHelper(driver).isDisplayed(grantSubject);
	}

}
