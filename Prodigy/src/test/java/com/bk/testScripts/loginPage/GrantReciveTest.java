package com.bk.testScripts.loginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.GrantRecievePage;
import com.bk_pageObject.LoginPage;

public class GrantReciveTest extends TestBase {

	/*
	 * @Test(description = "Checking Grant Recieve Flow") public void createGrants()
	 * { loginPage = new LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword()); grantRecievePage = new
	 * GrantRecievePage(driver);
	 * grantRecievePage.GrantRecieveProcessFlow("Kids Education");
	 * 
	 * }
	 */

	@Test
	public void invalidGrantRecieveCase() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		grantRecievePage = new GrantRecievePage(driver);
		grantRecievePage.grantBlankUserFieldTest();
		String actuaErroMsg = grantRecievePage.verifyEmptySubjectFieldMsg();
		String actuaErroMsg2 = grantRecievePage.verifyFailLoginMsg2();
		if (actuaErroMsg != null && actuaErroMsg2!=null) {
			String expErrorMsg = "Subject: Required fields must be filled in.";
			String expErrorMsg2 = "Requested Amount: Required fields must be filled in.";
			if (actuaErroMsg.equalsIgnoreCase(expErrorMsg) && actuaErroMsg2.equalsIgnoreCase(expErrorMsg2)) {
				Assert.assertTrue(true);

			} else {
				Assert.assertFalse(false);
			}
		}

	}

}
