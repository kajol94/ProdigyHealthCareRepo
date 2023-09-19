package com.bk_pageObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
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

public class ClaimAdjusterManageClaim {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ClaimAdjusterManageClaim.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@title=\"Manage\"]")
	WebElement manageMenu;

	@FindBy(xpath = "//*[@title=\"Manage Claim\"]")
	WebElement manageClaimMenu;

	@FindBy(id="prd_group")
	WebElement groupNameValue;
	
	@FindBy(id="prd_sourceorganization")
	WebElement sourceOrganizationValue;
	
	@FindBy(xpath = "//input[@aria-label=\"First Name\"]")
	WebElement patientFname;

	@FindBy(xpath = "//input[@id=\"user-role\"]")
	WebElement userRole;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement getUserName;

	@FindBy(xpath = "//tr/td/child::a")
	List<WebElement> claimList;

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

	@FindBy(id = "prd_emailid")
	WebElement emailAddress;

	@FindBy(xpath = "//ul[@class=\"nav nav-tabs\"]/li[2]/child::a")
	WebElement claimInformationTab;

	@FindBy(xpath = "//tr[@data-entity=\"prd_claiminformations\"]")
	List<WebElement> getListOfClaims;

	@FindBy(xpath = "//a[text()=\"Patient - Prescription Details\"]")
	WebElement prescriptionTab;

	@FindBy(xpath = "//tr[@data-entity=\"prd_prescriptionview\"]")
	List<WebElement> getListOfPrescription;
	
	
	
	public String getHTMLResponse(String url){
		JavascriptExecutor js = (JavascriptExecutor) driver;
	      String body = (String)js.executeAsyncScript(
	            "var callback = arguments[arguments.length - 1];" +
	                    "var xhr = new XMLHttpRequest();" +
	                    "xhr.open('GET', '"+ url + "', true);" +
	                    "xhr.onreadystatechange = function() {" +
	                    "  if (xhr.readyState == 4) {" +
	                    "    callback(xhr.responseText);" +
	                    "  }" +
	                    "};" +
	                    "xhr.send();");
	      return body;
	  }
	
	
	public ClaimAdjusterManageClaim(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Manage Patient page object has been created");
	}
	
	

	public void clickOnClaimInformationTab() {
		log.info("clicking on claim Information tab...");
		logExtentReport("clicking on submit button...");
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

	public void clickOnManageClaimMenu() {
		log.info("Clicking on manage claim button...");
		logExtentReport("Clicking on manage claim button...");
		this.manageClaimMenu.click();
	}

	public List<WebElement> getListOfClaim() {
		log.info("Getting the list of claim record");
		logExtentReport("Getting the list of claim record");
		return claimList;
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

	public String getGroupName() {
		String getGroupNameId = groupNameValue.getAttribute("value");
		return getGroupNameId;
	}
	
	public String getSourceOrganization() {
		String sourceOrgId = sourceOrganizationValue.getAttribute("value");
		return sourceOrgId;
	}

	public void verifyManageClaimView() {
		clickOnManageMenu();
		clickOnManageClaimMenu();
	}

	public String getPatientFirstName() {
		String getFirstName = patientFname.getAttribute("value");
		return getFirstName;
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

	public boolean verifySuccessLogOut() {
		return new VerificationHelper(driver).isDisplayed(signoutPage);
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	public boolean verifySuccessLogin() {
		return new VerificationHelper(driver).isDisplayed(userRole);
	}

	public String verifyFailLoginMsg() {
		return new VerificationHelper(driver).getText(userRole);
	}

	public boolean verifyBlankUserLogin() {
		return new VerificationHelper(driver).isDisplayed(userRole);
	}

	public void verifyUserLogin(boolean result) {

		Assert.assertEquals(userRole.isDisplayed(), result);
		logExtentReport("User Login successfully....");

	}

	public void logout() {
		clickOnProfileIconButton();
		clickOnLogoutButton();
	}

	public void verifyUserLogOut(boolean result) {

		Assert.assertEquals(userRole.isDisplayed(), result);
		logExtentReport("User LogOut successfully....");

	}

}
