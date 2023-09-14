package com.bk.testScripts.loginPage;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.LoginPage;
import com.bk_pageObject.ManagePatientPage;

public class ManagePatientTest extends TestBase {

	String userRole = null;
	String getUserName = null;
	int sourceOrgCount = 0;
	String sourceOrgValue1 = null;
	String nextSourceOrgValue = null;

	
	@Test(priority = 0, description = "Verify how many claims assigned to a patient")
	public void verifyNumberOfClaimAssignedToPatient() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		managePatientPage = new ManagePatientPage(driver);
		managePatientPage.verifyManagePatientView();

		getUserName = managePatientPage.getLoggedInUserName();

		userRole = managePatientPage.verifyLoggedInUserRole();

		List<WebElement> list = managePatientPage.getListOfPatient();
		
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				String patientName = list.get(i).getText();
				list.get(i).click();
				managePatientPage.clickOnClaimInformationTab();
				List<WebElement> getNumOfClaims = managePatientPage.getListOfPatientClaim();
					managePatientPage.clickOnPrescriptionTab();
					List<WebElement> getNumOfPrescription = managePatientPage.getListOfPrescription();
						System.out.println(patientName+" is assigned with "+getNumOfClaims.size()+ " number of claims & "+getNumOfPrescription.size()+" number of prescription");
			driver.navigate().back();
			}	
		}else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}
	}
	/*@Test(priority = 0, description = "Verify Edit of patient records")
	public void verifyEditPatientReord() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		managePatientPage = new ManagePatientPage(driver);
		managePatientPage.verifyManagePatientView();

		getUserName = managePatientPage.getLoggedInUserName();

		userRole = managePatientPage.verifyLoggedInUserRole();

		List<WebElement> list = managePatientPage.getListOfPatient();
		
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
				String recordOwnerName = managePatientPage.getRecordOwnerName();
				if (recordOwnerName != null || recordOwnerName != " ") {
				if(recordOwnerName.equalsIgnoreCase("Himanshu Batham")) {
						Assert.assertTrue(true);
					} else {
						Assert.assertFalse(false);
					}
				}
			}
			driver.navigate().back();
		} else {
			System.out.println(getUserName + "is assigned as " + userRole);

		}
		
	}

	@Test(priority = 0, description = "Verify menu(Manage User, Manage Ownership, User Request List) visibility to claim adjuster")
	public void verifyVisibilityOfMenus() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		managePatientPage = new ManagePatientPage(driver);
		managePatientPage.clickOnManageMenu();

		boolean getUserReqListName = managePatientPage.verifyUserRequestListMenuVisibility();
		boolean getManageUser = managePatientPage.verifyManageUserMenuVisibility();
		boolean getManageOwnership = managePatientPage.verifyManageOwnershipMenuVisibility();

		userRole = managePatientPage.verifyLoggedInUserRole();
		getUserName = managePatientPage.getLoggedInUserName();
		List<WebElement> menuList = managePatientPage.getListOfSubMenu();
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
	public void verifyListOfPatientRecordAndSourceOrganization() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		managePatientPage = new ManagePatientPage(driver);

		managePatientPage.verifyManagePatientView();

		getUserName = managePatientPage.getLoggedInUserName();

		userRole = managePatientPage.verifyLoggedInUserRole();

		List<WebElement> list = managePatientPage.getListOfPatient();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
				String recordOwnerName = managePatientPage.getRecordOwnerName();
				if (recordOwnerName != null || recordOwnerName != " ") {
					if (recordOwnerName.equals("Himanshu Batham")) {
						Assert.assertTrue(true);
					} else {
						Assert.assertFalse(false);
					}
					driver.navigate().back();
				}
			}
			System.out.print("---------------------------------------");
			for (int i = 0; i < list.size() - 1; i++) {
				list.get(i).click();
				sourceOrgValue1 = managePatientPage.getSourceOrganizationName();

				driver.navigate().back();
				for (int j = i + 1; j < list.size(); j++) {
					list.get(j).click();

					nextSourceOrgValue = managePatientPage.getSourceOrganizationName();
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
		// managePatientPage.logout();

	}

	/*@Test(priority = 1, description = "verify patient record & check if patients are assigned with more than one source organization")
	public void verifyNoOfOrganization() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		managePatientPage = new ManagePatientPage(driver);

		managePatientPage.verifyManagePatientView();

		getUserName = managePatientPage.getLoggedInUserName();

		userRole = managePatientPage.verifyLoggedInUserRole();

		List<WebElement> list = managePatientPage.getListOfPatient();

		int count = 0;
		String sourceOrg1 = null;
		String sourceOrg2 = null;
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size() - 1; i++) {
				list.get(i).click();
				sourceOrg1 = managePatientPage.getSourceOrganizationName();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				driver.navigate().back();
				for (int j = i + 1; j < list.size(); j++) {
					list.get(j).click();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					sourceOrg2 = managePatientPage.getSourceOrganizationName();
					driver.navigate().back();
					if (sourceOrg1 != null || sourceOrg2 != null || sourceOrg1 != " " || sourceOrg2 != " ") {
						try {
							if (i != j) {
								if (sourceOrg1.equalsIgnoreCase(sourceOrg2)) {
									Assert.assertTrue(true);
								} else {
									count++;
									System.out.println("There are " + count
											+ "additional source organization assigend to patient(s).");
								}
							} else {
								System.out.println("Element Omited");
							}
						}

						catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (count > 1) {
						Assert.assertTrue(false);
					} else {
						Assert.assertTrue(true);
					}

				}
			}

		} else {
			System.out.println(getUserName + "is assigned as " + userRole);
		}

	}*/

}
