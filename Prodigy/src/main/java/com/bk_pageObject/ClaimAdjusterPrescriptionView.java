package com.bk_pageObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.bk.testbase.TestBase;
import com.bk_helper.assertion.VerificationHelper;
import com.bk_helper.logger.LoggerHelper;
import com.bk_helper.wait.WaitHelper;

public class ClaimAdjusterPrescriptionView {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ClaimAdjusterPrescriptionView.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//td[@data-attribute=\"prd_group\"]")
	List<WebElement> getPrescriptionGroupId;

	@FindBy(xpath = "//input[@id=\"user-role\"]")
	WebElement userRole;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement getUserName;

	@FindBy(xpath = "//tr[@data-entity=\"prd_prescriptionview\"]/td")
	List<WebElement> listOfPrescriptions;

	@FindBy(xpath = "//td[@data-attribute=\"prd_claimadjuster\"]")
	WebElement claimAdjustId;

	@FindBy(className = "title")
	WebElement prescriptionFilterDropdown;
	
	@FindBy(xpath="//a[@aria-label=\"All Prescription View\"]")
	WebElement selectAllPrescriptionView;
	/*
	 * @FindBy(xpath = "//div[@class =\"form-readonly entity-form\"]") WebElement
	 * checkReadOnly;
	 * 
	 * @FindBy(xpath = "//a[@data-name=\"medication_tab\"]") WebElement
	 * clickOnMedicationTab;
	 * 
	 * @FindBy(xpath = "//a[text()=\"Add Medication Guideline\"]") WebElement
	 * isAddMedicationGuidelinePresent;
	 */

	public ClaimAdjusterPrescriptionView(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Manage Patient page object has been created");
	}

	public String getHTMLResponse(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String body = (String) js.executeAsyncScript("var callback = arguments[arguments.length - 1];"
				+ "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '" + url + "', true);"
				+ "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
				+ "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
		return body;
	}

	public String getLoggedInUserName() {
		log.info("Get logged in user name...");
		logExtentReport("Get logged in user name...");
		return new VerificationHelper(driver).getText(getUserName);
	}

	public List<WebElement> getGroupId() {
		return getPrescriptionGroupId;
	}

	public List<WebElement> getListofClaims() {
		return listOfPrescriptions;
	}

	public String getClaimAdjusterId() {
		String prescriptionClaimAdjusterId = null;

		String getGroupNameId = claimAdjustId.getAttribute("data-value");

		Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,");
		Matcher m = p.matcher(getGroupNameId);
		if (m.find()) {
			prescriptionClaimAdjusterId = m.group(1).trim();
		}
		return prescriptionClaimAdjusterId;
	}

	public List<WebElement> getGrpupdId() {
		return getPrescriptionGroupId;
	}

	public void clickOnPrescriptionFilterDropdown() {
		log.info("clicking on prescription filter dropdown...");
		logExtentReport("clicking on prescription filter dropdown...");
		this.prescriptionFilterDropdown.click();
		
		this.selectAllPrescriptionView.click();
		
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	/*
	 * public void clickOnProfileIconButton() {
	 * log.info("clicking on profile icon button...");
	 * logExtentReport("clicking on profile icon button...");
	 * this.getUserName.click(); }
	 * 
	 * public boolean verifySuccessLogOut() { return new
	 * VerificationHelper(driver).isDisplayed(signoutPage); }
	 * 
	 * public boolean verifySuccessLogin() { return new
	 * VerificationHelper(driver).isDisplayed(userRole); }
	 * 
	 * public String verifyFailLoginMsg() { return new
	 * VerificationHelper(driver).getText(userRole); }
	 * 
	 * public boolean verifyBlankUserLogin() { return new
	 * VerificationHelper(driver).isDisplayed(userRole); }
	 * 
	 * public void verifyUserLogin(boolean result) {
	 * 
	 * Assert.assertEquals(userRole.isDisplayed(), result);
	 * logExtentReport("User Login successfully....");
	 * 
	 * }
	 * 
	 * public void logout() { clickOnProfileIconButton(); clickOnLogoutButton(); }
	 * 
	 * public void verifyUserLogOut(boolean result) {
	 * 
	 * Assert.assertEquals(userRole.isDisplayed(), result);
	 * logExtentReport("User LogOut successfully....");
	 * 
	 * }
	 */

}
