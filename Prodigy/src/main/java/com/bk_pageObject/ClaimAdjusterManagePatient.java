package com.bk_pageObject;

import java.util.List;

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

public class ClaimAdjusterManagePatient {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ClaimAdjusterManagePatient.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@title=\"Manage\"]")
	WebElement manageMenu;

	@FindBy(xpath = "//*[@title=\"Manage Patient\"]")
	WebElement managePatientMenu;

	@FindBy(xpath = "//input[@id='prd_sourceorganization_name']")
	WebElement sourceOrganizationValue;

	@FindBy(xpath = "//input[@id=\"user-role\"]")
	WebElement userRole;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement getUserName;
	
	@FindBy(xpath="//td[@data-attribute=\"prd_recordowner\"]")
	WebElement getRecordOwnerId;

	@FindBy(xpath = "//td/child::a")
	List<WebElement> patientList;

	@FindBy(xpath = "//li/a[@aria-label=\"Manage\"]/following-sibling::ul/li/child::a")
	List<WebElement> listOfSubMenuOfManageMenu;

	@FindBy(xpath = "//a[@aria-label=\"User Request List\"]")
	WebElement userRequestListMenu;

	@FindBy(xpath = "//a[@aria-label=\"Manage User\"]")
	WebElement manageUserMenu;

	@FindBy(xpath = "//a[@aria-label=\"Manage Ownership\"]")
	WebElement manageOwnershipMenu;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement profileName;

	@FindBy(xpath = "//a[@title=\"Sign out\"]")
	WebElement logout;

	@FindBy(xpath = "//h1[@id=\"external-login-heading\"]")
	WebElement signoutPage;

	@FindBy(xpath = "//ul[@class=\"nav nav-tabs\"]/li[2]/child::a")
	WebElement claimInformationTab;

	@FindBy(xpath = "//tr[@data-entity=\"prd_claiminformations\"]")
	List<WebElement> getListOfClaims;

	@FindBy(xpath = "//a[text()=\"Patient - Prescription Details\"]")
	WebElement prescriptionTab;

	@FindBy(xpath = "//tr[@data-entity=\"prd_prescriptionview\"]")
	List<WebElement> getListOfPrescription;
	
	
	public ClaimAdjusterManagePatient(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Manage Patient page object has been created");
	}

	public void clickOnClaimInformationTab() {
		log.info("clicking on claim Information tab...");
		logExtentReport("clicking on claim Information tab...");
		this.claimInformationTab.click();
	}

	public void clickOnManageMenu() {
		log.info("Clicking on manage menu...");
		logExtentReport("Clicking on manage menu...");
		waitHelper.WaitForElementClickable(manageMenu, 5);
		this.manageMenu.click();
	}

	public void clickOnPrescriptionTab() {
		log.info("clicking on prescription tab...");
		logExtentReport("clicking on prescription tab...");
		this.prescriptionTab.click();
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
		log.info(
				"Get the list of sub menus and check the visiblity of Manage User, Manage Ownership, User Request List.");
		logExtentReport(
				"Get the list of sub menus and check the visiblity of Manage User, Manage Ownership, User Request List.");
		return listOfSubMenuOfManageMenu;
	}

	public List<WebElement> getListOfPatientClaim() {
		log.info("Getting the list of patient claim");
		logExtentReport("Getting the list of patient claim");
		return getListOfClaims;
	}

	public List<WebElement> getListOfPrescription() {
		log.info("Getting the list of patient prescription");
		logExtentReport("Getting the list of patient prescription");
		return getListOfPrescription;
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
		String getUserRole = userRole.getAttribute("value");
		return getUserRole;
	}

	public String getLoggedInUserName() {
		log.info("Get logged in user name...");
		logExtentReport("Get logged in user name...");
		return new VerificationHelper(driver).getText(getUserName);
	}

	public String getRecordOwnerValue() {
		String getRecordOwnerValue = getRecordOwnerId.getAttribute("data-value");
		return getRecordOwnerValue;
	}

	public void verifyManagePatientView() {
		clickOnManageMenu();
		clickOnManagePatientMenu();
	}

	public String getSourceOrganizationName() {
		String sourceOrgName = sourceOrganizationValue.getAttribute("value");
		return sourceOrgName;
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

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	public void logout() {
		clickOnProfileIconButton();
		clickOnLogoutButton();
	}
}
