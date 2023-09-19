package com.bk.testScripts.loginPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	String recordOwnerId = null;

	@Test(priority = 4, description = "Verify Edit of patient records")
	public void verifyEditPatientReord() {
		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String getRecordOwnerKeyString = managePatient.getRecordOwnerValue();
				Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,");
				Matcher m = p.matcher(getRecordOwnerKeyString);

				if (m.find()) {
					recordOwnerId = m.group(1).trim();
				}
				if (recordOwnerId != null || recordOwnerId != " ") {
					if (recordOwnerId.equalsIgnoreCase("585daa99-122a-ee11-bdf4-0022482fc7e6")) {
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

	@Test(priority = 3, description = "Verify how many claims/prescription assigned to a patient")
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

	@Test(priority = 1, description = "Verify list of patient records in manage patient page")
	public void verifyListOfPatientRecord() {

		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String getRecordOwnerKeyString = managePatient.getRecordOwnerValue();
				Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,");
				Matcher m = p.matcher(getRecordOwnerKeyString);

				if (m.find()) {
					recordOwnerId = m.group(1).trim();
				}
				if (recordOwnerId != null || recordOwnerId != " ") {
					if (recordOwnerId.equals("585daa99-122a-ee11-bdf4-0022482fc7e6")) {
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

	@Test(priority = 0, description = "verify patient record & check if patients are assigned with more than one source organization")
	public void verifyNoOfOrganization() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		managePatient = new ClaimAdjusterManagePatient(driver);

		managePatient.verifyManagePatientView();

		getUserName = managePatient.getLoggedInUserName();

		userRole = managePatient.verifyLoggedInUserRole();

		List<WebElement> list = managePatient.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
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

		} else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}
	}

}
