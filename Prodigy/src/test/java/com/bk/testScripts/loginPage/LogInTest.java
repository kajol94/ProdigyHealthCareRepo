package com.bk.testScripts.loginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.assertion.AssertionHelper;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_helper.excel.ExcelHelper;
import com.bk_helper.resource.ResourceHelper;
import com.bk_pageObject.LoginPage;

/*
 * @author  Kajol Gupta
 *
 */
public class LogInTest extends TestBase {

	ExcelHelper excelHelper = new ExcelHelper();

	@Test(priority = 3, description = "Login with valid credentials")
	public void LoginWithValidCredentials() {

		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		boolean status = loginPage.verifySuccessLogin();
		if (status) {
			//loginPage.logout();
			//status = loginPage.verifySuccessLogOut();
			AssertionHelper.updateTestStatus(true);
		} else {
			AssertionHelper.updateTestStatus(false);
		}
	}

	@Test(dataProvider = "emailloginData", dataProviderClass = ExcelHelper.class, priority = 1, description = "Login with invalid email credentials")
	public void LoginWithInvalidEmailCredentials(String scenario, String email) throws InterruptedException {
		loginPage = new LoginPage(driver);

		loginPage.logintoApplication(email);

		if (scenario.equals("invalidEmail1")) {
			String actErrorMsg = loginPage.verifyEmailLoginMsg();
			String ExpErrMsg = "Enter a valid email address, phone number, or Skype name.";
			Assert.assertEquals(actErrorMsg, ExpErrMsg);
		} else if (scenario.equals("invalidEmail2")) {
			String actErrorMsg = loginPage.verifyEmailLoginMsg();
			String ExpErrMsg = "This username may be incorrect. Make sure you typed it correctly. Otherwise, contact your admin.";
			Assert.assertEquals(actErrorMsg, ExpErrMsg);
		} else if (scenario.equals("invalidEmail3")) {
			String actErrorMsg = loginPage.verifyEmailLoginMsg();
			String ExpErrMsg = "Enter a valid email address or phone number.";
			Assert.assertEquals(actErrorMsg, ExpErrMsg);
		} else if (scenario.equals("invalidEmail4")) {
			String actErrorMsg = loginPage.verifyEmailLoginMsg();
			String ExpErrMsg = ".com isn't in our system. Make sure you typed it correctly.";
			Assert.assertEquals(actErrorMsg, ExpErrMsg);
		} else {
			System.out.println("Something went wrong");
		}

	}

	@Test(priority = 2, description = "Login with valid email & invalid password")
	public void loginWithInvalidPasswordCredentials() throws InterruptedException {
		String excelFilePath = "\\src\\test\\resources\\dataSheet\\testData.xlsx";

		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInButton();
		for (int i = 1; i <= 2; i++) {
			loginPage.loginToApplication2(
					excelHelper.getData(ResourceHelper.getResourcePath(excelFilePath), i, "UserName",
							"InvalidPwdLoginData"),
					excelHelper.getData(ResourceHelper.getResourcePath(excelFilePath), i, "Password",
							"InvalidPwdLoginData"));
			String actErrorMsg = loginPage.verifyPassLoginMsg();
			switch (actErrorMsg) {
			case "Please enter your password.":
				Assert.assertTrue(true);
				break;
			case "Your account or password is incorrect. If":
				Assert.assertTrue(true);
				break;
			case "Your account or password is incorrect. If you don't remember your password, reset it now.":
				Assert.assertTrue(true);
				break;
			default:
				System.out.println("No matching case found");

			}
			loginPage.clickOnBackButton();
			Thread.sleep(3000);
		}

	}

}