package com.bk.testScripts.loginPage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.ClaimAdjusterManageClaim;
import com.bk_pageObject.ClaimAdjusterManagePatient;
import com.bk_pageObject.LoginPage;

public class ClaimAdjusterManageClaimTest extends TestBase {

	String userRole = null;
	String getUserName = null;
	int sourceOrgCount = 0;
	String sourceOrgValue1 = null;
	String nextSourceOrgValue = null;

	@Test(priority = 0, description = "Verify list of claim records in manage claim page")
	public void verifyListOfClaimRecord() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver);

		manageClaim.verifyManageClaimView();

		getUserName = manageClaim.getLoggedInUserName();

		userRole = manageClaim.verifyLoggedInUserRole();

		String claimAdjGroupName = "Prodigy Demo Group";
		List<WebElement> list = manageClaim.getListOfClaim();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i <list.size(); i++) {
				String patientGroupName = manageClaim.getGroupName();
				System.out.println(patientGroupName);
					if (patientGroupName != null || patientGroupName != " " || claimAdjGroupName != null
							|| claimAdjGroupName != "") {
						if (patientGroupName.equals(claimAdjGroupName)) {
							Assert.assertTrue(true);
						} else {
							Assert.assertFalse(false);
						}
					}
				}
			}
		}
	}


/*
 * @Test(priority = 4, description =
 * "Verify how many claims/prescription assigned to a patient") public void
 * verifyNumberOfClaimAndPrescriptionAssignedToPatient() {
 * 
 * managePatient.verifyManagePatientView();
 * 
 * getUserName = managePatient.getLoggedInUserName();
 * 
 * userRole = managePatient.verifyLoggedInUserRole();
 * 
 * List<WebElement> list = managePatient.getListOfPatient();
 * 
 * if (userRole.contains("Claim Adjuster")) { for (int i = 0; i < list.size();
 * i++) { String patientName = list.get(i).getText(); list.get(i).click();
 * managePatient.clickOnClaimInformationTab(); List<WebElement> getNumOfClaims =
 * managePatient.getListOfPatientClaim();
 * managePatient.clickOnPrescriptionTab(); List<WebElement> getNumOfPrescription
 * = managePatient.getListOfPrescription(); System.out.println(patientName +
 * " is assigned with " + getNumOfClaims.size() + " number of claims & " +
 * getNumOfPrescription.size() + " number of prescription");
 * driver.navigate().back(); } } else { System.out.println(getUserName +
 * "is assigned as " + userRole);
 * 
 * } }
 * 
 * @Test(priority = 3, description = "Verify Edit of patient records") public
 * void verifyEditPatientReord() { managePatient.verifyManagePatientView();
 * 
 * getUserName = managePatient.getLoggedInUserName();
 * 
 * userRole = managePatient.verifyLoggedInUserRole();
 * 
 * List<WebElement> list = managePatient.getListOfPatient();
 * 
 * if (userRole.contains("Claim Adjuster")) { for (int i = 0; i < list.size();
 * i++) { list.get(i).click(); String recordOwnerName =
 * managePatient.getRecordOwnerName(); if (recordOwnerName != null ||
 * recordOwnerName != " ") { if
 * (recordOwnerName.equalsIgnoreCase("Himanshu Batham")) {
 * Assert.assertTrue(true); } else { Assert.assertFalse(false); } }
 * driver.navigate().back(); }
 * 
 * } else { System.out.println(getUserName + "is assigned as " + userRole);
 * 
 * } }
 * 
 * @Test(priority = 2, description =
 * "Verify menu(Manage User, Manage Ownership, User Request List) visibility to claim adjuster"
 * ) public void verifyVisibilityOfMenus() { managePatient.clickOnManageMenu();
 * 
 * boolean getUserReqListName =
 * managePatient.verifyUserRequestListMenuVisibility(); boolean getManageUser =
 * managePatient.verifyManageUserMenuVisibility(); boolean getManageOwnership =
 * managePatient.verifyManageOwnershipMenuVisibility();
 * 
 * userRole = managePatient.verifyLoggedInUserRole(); getUserName =
 * managePatient.getLoggedInUserName(); List<WebElement> menuList =
 * managePatient.getListOfSubMenu(); if (userRole.contains("Claim Adjuster")) {
 * for (int i = 0; i < menuList.size() - 1; i++) { if (getUserReqListName &&
 * getManageUser && getManageOwnership) { Assert.assertTrue(true); } else {
 * Assert.assertTrue(false); } } } else { System.out.println(getUserName +
 * " is assigned as " + userRole); } }
 */
