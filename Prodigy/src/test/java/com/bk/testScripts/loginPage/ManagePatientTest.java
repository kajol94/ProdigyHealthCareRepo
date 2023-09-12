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

	String userRole = null;
	String getUserName = null;

	@Test(description = "Verify menu(Manage User, Manage Ownership, User Request List) visibility to claim adjuster")
	public void verifyVisibilityOfMenus() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		managePatientPage = new ManagePatientPage(driver);
		managePatientPage.clickOnManageMenu();
		
		  boolean getUserReqListName = managePatientPage.verifyUserRequestListMenuVisibility(); 
		  boolean getmanageuser = managePatientPage.verifyManageUserMenuVisibility(); 
		  boolean getmanageOwnership = managePatientPage.verifyManageOwnershipMenuVisibility();
		 
		userRole = managePatientPage.verifyLoggedInUserRole();
		getUserName = managePatientPage.getLoggedInUserName();
		List<WebElement> menuList = managePatientPage.getListOfSubMenu();
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < menuList.size(); i++) {
				String menuName = menuList.get(i).getText();
				if (menuName.equals("Manage Claim") || menuName.equals("Manage Patient")) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				/*if (getUserReqListName && getmanageuser && getmanageOwnership) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}*/
			}

		} else {
			System.out.println(getUserName + " is assigned as " + userRole);
		}
	}

	/*
	 * @Test(priority = 2,description =
	 * "Verify list of patient records in manage patient page") public void
	 * verifyListOfPatientRecord() { loginPage = new LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * managePatientPage = new ManagePatientPage(driver);
	 * 
	 * managePatientPage.verifyManagePatientView(); userRole =
	 * managePatientPage.verifyLoggedInUserRole(); List<WebElement> list =
	 * managePatientPage.getListOfPatient(); for (int i = 0; i < list.size(); i++) {
	 * list.get(i).click(); try { Thread.sleep(5000); } catch (InterruptedException
	 * e) { e.printStackTrace(); }
	 * 
	 * String recordOwnerName = managePatientPage.getRecordOwnerName(); if
	 * (recordOwnerName != null && recordOwnerName != " ") { if
	 * (recordOwnerName.equals("Himanshu Batham") &&
	 * userRole.equals("Claim AdjusterAuthenticated Users")) {
	 * Assert.assertTrue(true); } else { Assert.assertFalse(false); }
	 * driver.navigate().back(); } else {
	 * System.out.println("Record Owner value is not present."); } } }
	 * 
	 * @Test(priority = 1, description =
	 * "verify patient record & check if patients are assigned with more than one source organization"
	 * ) public void verifyNoOfOrganization() { loginPage = new LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * managePatientPage = new ManagePatientPage(driver); String getUserName =
	 * managePatientPage.getLoggedInUserName(); userRole =
	 * managePatientPage.verifyLoggedInUserRole();
	 * managePatientPage.verifyManagePatientView(); List<WebElement> list =
	 * managePatientPage.getListOfPatient();
	 * 
	 * int count = 0; String sourceOrg1 = null; String sourceOrg2 = null; if
	 * (userRole.contains("Claim Adjuster")) { for (int i = 0; i < list.size() - 1;
	 * i++) { list.get(i).click(); sourceOrg1 =
	 * managePatientPage.getSourceOrganizationName();
	 * 
	 * try { Thread.sleep(5000); } catch (InterruptedException e1) {
	 * 
	 * e1.printStackTrace(); } driver.navigate().back(); for (int j = i + 1; j <
	 * list.size(); j++) { list.get(j).click(); try { Thread.sleep(5000); } catch
	 * (InterruptedException e1) { e1.printStackTrace(); } sourceOrg2 =
	 * managePatientPage.getSourceOrganizationName(); driver.navigate().back(); if
	 * (sourceOrg1 != null && sourceOrg2 != null) { try { if (i != j) { if
	 * (sourceOrg1.equalsIgnoreCase(sourceOrg2)) { Assert.assertTrue(true); } else {
	 * count++; System.out.println("There are " + count +
	 * "additional source organization assigend to patient(s)."); } } else {
	 * System.out.println("Element Omited"); } }
	 * 
	 * catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * if (count > 1) { Assert.assertTrue(false); } else { Assert.assertTrue(true);
	 * }
	 * 
	 * } }
	 * 
	 * } else { System.out.println(getUserName + "is assigned as " + userRole); }
	 * 
	 * }
	 */

}
