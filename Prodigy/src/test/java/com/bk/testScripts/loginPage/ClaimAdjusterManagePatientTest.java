package com.bk.testScripts.loginPage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.ClaimAdjusterManagePatient;
import com.bk_pageObject.LoginPage;

public class ClaimAdjusterManagePatientTest extends TestBase {

	String userRole = null;
	String getUserName = null;
	int sourceOrgCount = 0;
	String sourceOrgValue1 = null;
	String nextSourceOrgValue = null;

	@Test(priority = 4, description = "Verify how many claims/prescription assigned to a patient")
	public void verifyNumberOfClaimAndPrescriptionAssignedToPatient() {

		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String patientName = list.get(i).getText();
				list.get(i).click();
				managePatient.clickOnClaimInformationTab();
				List<WebElement> getNumOfClaims = managePatient.getListOfPatientClaim();
				managePatient.clickOnPrescriptionTab();
				List<WebElement> getNumOfPrescription = managePatient.getListOfPrescription();
				System.out.println(patientName + " is assigned with " + getNumOfClaims.size() + " number of claims & "
						+ getNumOfPrescription.size() + " number of prescription");
				driver.navigate().back();
			}
		} else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}
	}

	@Test(priority = 3, description = "Verify Edit of patient records")
	public void verifyEditPatientReord() {
		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String recordOwnerName = managePatient.getRecordOwnerName();
				if (recordOwnerName != null || recordOwnerName != " ") {
					if (recordOwnerName.equalsIgnoreCase("Himanshu Batham")) {
						Assert.assertTrue(true);
					} else {
						Assert.assertFalse(false);
					}
				}
			}

		} else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}
	}

	@Test(priority = 2, description = "Verify menu(Manage User, Manage Ownership, User Request List) visibility to claim adjuster")
	public void verifyVisibilityOfMenus() {
		managePatient.clickOnManageMenu();

		boolean getUserReqListName = managePatient.verifyUserRequestListMenuVisibility();
		boolean getManageUser = managePatient.verifyManageUserMenuVisibility();
		boolean getManageOwnership = managePatient.verifyManageOwnershipMenuVisibility();

		userRole = managePatient.verifyLoggedInUserRole();
		getUserName = managePatient.getLoggedInUserName();
		List<WebElement> menuList = managePatient.getListOfSubMenu();
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < menuList.size() - 1; i++) {
				if (getUserReqListName && getManageUser && getManageOwnership) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
		} else {
			System.out.println(getUserName + " is assigned as " + userRole);
		}
	}

	@Test(priority = 1, description = "Verify list of patient records in manage patient page & source organization value")
	public void verifyListOfPatientRecordAndSourceOrganization() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		managePatient = new ClaimAdjusterManagePatient(driver);

		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String recordOwnerName = managePatient.getRecordOwnerName();
				if (recordOwnerName != null || recordOwnerName != " ") {
					if (recordOwnerName.equals("Himanshu Batham")) {
						Assert.assertTrue(true);
					} else {
						Assert.assertFalse(false);
					}
				}
			}
			System.out.print("---------------------------------------");
			for (int i = 0; i < 1; i++) {
				list.get(i).click();
				sourceOrgValue1 = managePatient.getSourceOrganizationName();

				driver.navigate().back();
				for (int j = i + 1; j < list.size(); j++) {
					list.get(j).click();

					nextSourceOrgValue = managePatient.getSourceOrganizationName();
					driver.navigate().back();
					if (sourceOrgValue1 != null || nextSourceOrgValue != null || sourceOrgValue1 != " "
							|| nextSourceOrgValue != " ") {
						if (i != j) {
							if (sourceOrgValue1.equalsIgnoreCase(nextSourceOrgValue)) {
								Assert.assertTrue(true);
							} else {
								sourceOrgCount++;
								System.out.println("There are " + sourceOrgCount
										+ "additional source organization assigend to patient(s).");
							}
						} else {
							System.out.println("Element Omited");
						}
					}
				}

				if (sourceOrgCount > 1) {
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}

			}

			System.out.print("-----------------------------------------");

		} else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}

	}

	/*
	 * @Test(priority = 1, description =
	 * "verify patient record & check if patients are assigned with more than one source organization"
	 * ) public void verifyNoOfOrganization() { loginPage = new LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * managePatientPage = new ManagePatientPage(driver);
	 * 
	 * managePatientPage.verifyManagePatientView();
	 * 
	 * getUserName = managePatientPage.getLoggedInUserName();
	 * 
	 * userRole = managePatientPage.verifyLoggedInUserRole();
	 * 
	 * List<WebElement> list = managePatientPage.getListOfPatient();
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
	 * (sourceOrg1 != null || sourceOrg2 != null || sourceOrg1 != " " || sourceOrg2
	 * != " ") { try { if (i != j) { if (sourceOrg1.equalsIgnoreCase(sourceOrg2)) {
	 * Assert.assertTrue(true); } else { count++; System.out.println("There are " +
	 * count + "additional source organization assigend to patient(s)."); } } else {
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
