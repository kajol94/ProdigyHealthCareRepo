package com.bk.testScripts.loginPage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.LoginPage;
import com.bk_pageObject.ManagePatientPage;

public class ManagePatientTest extends TestBase {

	/*
	 * @Test(description = "Verify list of patient records in manage patiient page")
	 * public void ManagePatientView() throws InterruptedException { loginPage = new
	 * LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * managePatientPage = new ManagePatientPage(driver); String getUserRole =
	 * managePatientPage.verifyLoggedUserRole();
	 * managePatientPage.verifyManagePatientView();
	 * 
	 * List<WebElement> list = managePatientPage.getListOfPatientList(); for (int i
	 * = 0; i < list.size(); i++) { list.get(i).click(); Thread.sleep(5000);
	 * 
	 * String recordOwnerName = managePatientPage.verifyListOfPatientRecord(); if
	 * (recordOwnerName.equals("Himanshu Batham") &&
	 * getUserRole.equals("Claim AdjusterAuthenticated Users")) {
	 * Assert.assertTrue(true); } else { Assert.assertFalse(false); }
	 * driver.navigate().back(); //managePatientPage.clickOnManageMenu();
	 * //managePatientPage.clickOnManagePatientMenu(); } }
	 */

	@Test
	public void verifyNoOfOrganization() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		managePatientPage = new ManagePatientPage(driver);
		String getUserName = managePatientPage.getLoggedInUserName();
		String userRole = managePatientPage.verifyLoggedUserRole();

		managePatientPage.verifyManagePatientView();
		List<WebElement> list = managePatientPage.getListOfPatientList();

		int j = 0;
		int count = 0;
		String sourceOrg1 = null;
		String sourceOrg2 = null;
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
				sourceOrg1 = managePatientPage.getSourceOrganizationName();

				Thread.sleep(5000);
				driver.navigate().back();
				for (j = 1; j < list.size(); j++) {
					list.get(j).click();
					Thread.sleep(5000);
					sourceOrg2 = managePatientPage.getSourceOrganizationName();
					driver.navigate().back();
					if (sourceOrg1.equals(sourceOrg2)) {
						System.out.println("Only one organization is present");
					} else {
						System.out.println(count++);
					}

					/*if (count > 1) {
						Assert.assertTrue(false);
					} else {
						Assert.assertTrue(true);
					}*/
					System.out.println(count++);

				}
			}

		} else {
			System.out.println(getUserName + "is assigned as " + userRole);
		}

	}

}
