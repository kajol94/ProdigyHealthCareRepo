package com.bk_pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	@FindBy(xpath = "//td[@data-attribute=\"prd_group\"]")
	List<WebElement> groupId;

	@FindBy(xpath = "//input[@id=\"user-role\"]")
	WebElement userRole;

	@FindBy(xpath = "//span[@class=\"username\"]")
	WebElement getUserName;

	@FindBy(xpath = "//tr/td/child::a")
	List<WebElement> listOfClaims;

	@FindBy(id = "prd_claimadjuster")
	WebElement claimAdjustId;

	@FindBy(xpath = "//div[@class =\"form-readonly entity-form\"]")
	WebElement checkReadOnly;

	public ClaimAdjusterManageClaim(WebDriver driver) {
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

	public void clickOnManageMenu() {
		log.info("Clicking on manage menu...");
		logExtentReport("Clicking on manage menu...");
		waitHelper.WaitForElementClickable(manageMenu, 5);
		this.manageMenu.click();
	}

	public void clickOnManageClaimMenu() {
		log.info("Clicking on manage claim button...");
		logExtentReport("Clicking on manage claim button...");
		this.manageClaimMenu.click();
	}

	public String getLoggedInUserName() {
		log.info("Get logged in user name...");
		logExtentReport("Get logged in user name...");
		return new VerificationHelper(driver).getText(getUserName);
	}

	public List<WebElement> getGroupId() {
		return groupId;
	}

	public List<WebElement> getListofClaims() {
		return listOfClaims;
	}

	public String getClaimAdjusterId() {
		String claimAdjusterId = claimAdjustId.getAttribute("value");
		return claimAdjusterId;
	}

	public boolean isAttribtuePresent() {
		boolean result = false;

		try {
			String value = this.checkReadOnly.getAttribute("readonly");
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void verifyManageClaimView() {
		clickOnManageMenu();
		clickOnManageClaimMenu();
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	/*public void clickOnProfileIconButton() {
		log.info("clicking on profile icon button...");
		logExtentReport("clicking on profile icon button...");
		this.getUserName.click();
	}

	public boolean verifySuccessLogOut() {
		return new VerificationHelper(driver).isDisplayed(signoutPage);
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

	}*/

}
