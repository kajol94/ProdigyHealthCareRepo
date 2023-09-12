package com.bk_pageObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bk.testbase.TestBase;
import com.bk_helper.assertion.VerificationHelper;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_helper.logger.LoggerHelper;
import com.bk_helper.wait.WaitHelper;

public class ManagePatientPage {
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ManagePatientPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@title=\"Manage\"]")
	WebElement manageMenu;

	@FindBy(xpath = "//*[@title=\"Manage Patient\"]")
	WebElement managePatientMenu;

	@FindBy(xpath = "//input[@aria-label=\"Record Owner\"]")
	WebElement recordOwnerValue;

	@FindBy(xpath = "//input[@id='prd_sourceorganization_name']")
	WebElement sourceOrganizationValue;

	@FindBy(xpath = "//input[@aria-label=\"First Name\"]")
	WebElement patientFname;

	@FindBy(xpath = "//input[@id=\"user-role\"]")
	WebElement userRole;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement getUserName;

	@FindBy(xpath = "//td/child::a")
	List<WebElement> patientList;
	
	@FindBy(xpath="//li/a[@aria-label=\"Manage\"]/following-sibling::ul/li/child::a")
	List<WebElement> listOfSubMenuUnderManageMenu;
	
	@FindBy(xpath="//a[@aria-label=\"User Request List\"]")
	WebElement userRequestListMenu;
	
	@FindBy(xpath="//a[@aria-label=\"Manage User\"]")
	WebElement manageUserMenu;
	
	@FindBy(xpath="//a[@aria-label=\"Manage Ownership\"]")
	WebElement manageOwnershipMenu;

	public ManagePatientPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Manage Patient page object has been created");
	}

	public void clickOnManageMenu() {
		log.info("Clicking on manage menu...");
		logExtentReport("Clicking on manage menu...");
		this.manageMenu.click();
	}

	public void clickOnManagePatientMenu() {
		log.info("Clicking on manage patient button...");
		logExtentReport("Clicking on manage patient button...");
		this.managePatientMenu.click();
	}

	public List<WebElement> getListOfPatient() {
		log.info("Getting the list of patient record");
		logExtentReport("Getting the list of patient record");
		return patientList;
	}
	
	public List<WebElement> getListOfSubMenu() {
		log.info("Getting the list of sub menus avaialble inside Manage Menu");
		logExtentReport("Getting the list of sub menus avaialble inside Manage Menu");
		return listOfSubMenuUnderManageMenu;
	}
	
	public boolean verifyUserRequestListMenuVisibility() {
		log.info("Check visibility of User Request List Menu...");
		logExtentReport("Check visibility of User Request List Menu...");
		return new VerificationHelper(driver).isNotDisplayed(userRequestListMenu);
	}
	
	public boolean verifyManageUserMenuVisibility() {
		log.info("Check visiblity of Manage User Menu...");
		logExtentReport("Check visiblity of Manage User Menu...");
		return new VerificationHelper(driver).isNotDisplayed(manageUserMenu);
	}
	
	public boolean verifyManageOwnershipMenuVisibility() {
		log.info("Check visiblity of Manage Ownership Menu...");
		logExtentReport("Check visiblity of Manage Ownership Menu...");
		return new VerificationHelper(driver).isNotDisplayed(manageOwnershipMenu);
	}


	public String verifyLoggedInUserRole() {
		log.info("Get user role...");
		logExtentReport("Get user role...");
		String getUserRole = userRole.getAttribute("value");
		return getUserRole;
	}

	public String getLoggedInUserName() {
		log.info("Get logged in user name...");
		logExtentReport("Get logged in user name...");
		return new VerificationHelper(driver).getText(getUserName);
	}

	public String getRecordOwnerName() {
		String getRecordOwnerName = recordOwnerValue.getAttribute("value");
		return getRecordOwnerName;
	}

	public void verifyManagePatientView() {
		clickOnManageMenu();
		clickOnManagePatientMenu();
	}

	public String getSourceOrganizationName() {
		String sourceOrgName = sourceOrganizationValue.getAttribute("value");
		return sourceOrgName;
	}

	public String getPatientFirstName() {
		String getFirstName = patientFname.getAttribute("value");
		return getFirstName;
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	public boolean verifySuccessLogin() {
		return new VerificationHelper(driver).isDisplayed(recordOwnerValue);
	}

	public String verifyFailLoginMsg() {
		return new VerificationHelper(driver).getText(recordOwnerValue);
	}

	public boolean verifyBlankUserLogin() {
		return new VerificationHelper(driver).isDisplayed(recordOwnerValue);
	}

	public void verifyUserLogin(boolean result) {

		Assert.assertEquals(recordOwnerValue.isDisplayed(), result);
		logExtentReport("User Login successfully....");

	}

	public void logout() {
		// clickOnProfileIconButton();
		// clickOnLogoutButton();
	}

	public void verifyUserLogOut(boolean result) {

		Assert.assertEquals(recordOwnerValue.isDisplayed(), result);
		logExtentReport("User LogOut successfully....");

	}

	public boolean verifySuccessLogOut() {
		return new VerificationHelper(driver).isDisplayed(recordOwnerValue);
	}

}
