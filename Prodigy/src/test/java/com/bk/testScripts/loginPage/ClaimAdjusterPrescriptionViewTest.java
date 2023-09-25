package com.bk.testScripts.loginPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.ClaimAdjusterManageClaim;
import com.bk_pageObject.ClaimAdjusterPrescriptionView;
import com.bk_pageObject.LoginPage;

public class ClaimAdjusterPrescriptionViewTest extends TestBase {

	String userRole = null;
	String userId = null;
	String getUserName = null;
	String claimAdjGroupValue = null;
	String prescriptionGroupValue = null;
	String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";
	String keyValue;
	JSONObject jsonObject;
	String claimAdjusterid;

	/*
	 * @Test(priority = 2, description =
	 * "Verify medication guidlines records visiblity based on claim adjuster value in claim details"
	 * ) public void verifyMedicationGuidlinesView() {
	 * manageClaim.verifyManageClaimView();
	 * 
	 * getUserName = manageClaim.getLoggedInUserName();
	 * 
	 * keyValue = manageClaim.getHTMLResponse(url); jsonObject = new
	 * JSONObject(keyValue);
	 * 
	 * userRole = jsonObject.get("webRole").toString(); if
	 * (userRole.contains("Claim Adjuster")) {
	 * 
	 * // Verify group(s) of source organization
	 * System.out.println("---------------------------------------------");
	 * JSONArray tsmresponse = (JSONArray) jsonObject.get("groups"); for (int i = 0;
	 * i < tsmresponse.length(); i++) { claimAdjGroupValue =
	 * tsmresponse.getString(i).toString(); List<WebElement> patientGroup =
	 * manageClaim.getGroupId(); for (int j = 0; j < patientGroup.size(); j++) {
	 * String getGroupNameId = patientGroup.get(j).getAttribute("data-value");
	 * 
	 * Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,"); Matcher m =
	 * p.matcher(getGroupNameId); if (m.find()) { patientGroupValue =
	 * m.group(1).trim(); }
	 * 
	 * if (patientGroupValue != null || patientGroupValue != " " ||
	 * claimAdjGroupValue != null || claimAdjGroupValue != " ") { if
	 * (patientGroupValue.equals(claimAdjGroupValue)) { Assert.assertTrue(true); }
	 * else { Assert.assertFalse(false); } } }
	 * 
	 * }
	 * 
	 * System.out.println("---------------------------------------------"); //
	 * Verify claim adjuster value & medication guidelines visiblity
	 * 
	 * List<WebElement> getClaimList = manageClaim.getListofClaims(); for (int j =
	 * 0; j < getClaimList.size(); j++) {
	 * 
	 * getClaimList.get(j).click();
	 * 
	 * manageClaim.clickOnMedicationGuidlinesTab();
	 * 
	 * userId = jsonObject.get("userId").toString(); claimAdjusterid =
	 * manageClaim.getClaimAdjusterId();
	 * 
	 * boolean isElementPresent = manageClaim.isElementPresent();
	 * 
	 * if (claimAdjusterid != null || claimAdjusterid != " " || userId != null ||
	 * userId != " ") {
	 * 
	 * if (claimAdjusterid.equals(userId)) {
	 * 
	 * if (isElementPresent) {
	 * System.out.println("Add medication guidelines button is displayed"); } else {
	 * System.out.println("Add medication guidelines button is not displayed"); }
	 * 
	 * } else { Assert.assertFalse(false); } } driver.navigate().back(); }
	 * 
	 * } else { System.out.println(getUserName + " falls under different role ");
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Test(priority = 1, description =
	 * "Verify edit option based on the Claim Adjuster value avaialble in claim details"
	 * ) public void verifyEditClaimRecords() { manageClaim.verifyManageClaimView();
	 * 
	 * getUserName = manageClaim.getLoggedInUserName();
	 * 
	 * keyValue = manageClaim.getHTMLResponse(url); jsonObject = new
	 * JSONObject(keyValue);
	 * 
	 * userRole = jsonObject.get("webRole").toString(); if
	 * (userRole.contains("Claim Adjuster")) {
	 * 
	 * List<WebElement> getClaimList = manageClaim.getListofClaims(); for (int j =
	 * 0; j < getClaimList.size(); j++) { getClaimList.get(j).click(); userId =
	 * jsonObject.get("userId").toString(); claimAdjusterid =
	 * manageClaim.getClaimAdjusterId();
	 * 
	 * boolean isreadOnly = manageClaim.isAttribtuePresent(); if (claimAdjusterid !=
	 * null || claimAdjusterid != " " || userId != null || userId != " ") { if
	 * (claimAdjusterid.equals(userId)) { if (isreadOnly) {
	 * System.out.println("form is readonly hence cannot edit records"); } else {
	 * System.out.println("form is not readonly hence can edit records"); }
	 * 
	 * } else { Assert.assertFalse(false); } } driver.navigate().back(); }
	 * 
	 * } else { System.out.println(getUserName + " falls under different role ");
	 * 
	 * }
	 * 
	 * }
	 */

	@Test(priority = 0, description = "Verify list of prescription records in prescription view page")
	public void verifyListOfPrescriptionRecord() {

		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		prescriptionView = new ClaimAdjusterPrescriptionView(driver);

		getUserName = prescriptionView.getLoggedInUserName();

		keyValue = prescriptionView.getHTMLResponse(url);
		jsonObject = new JSONObject(keyValue);

		prescriptionView.clickOnPrescriptionFilterDropdown();
		userRole = jsonObject.get("webRole").toString();
		if (userRole.contains("Claim Adjuster")) {
			JSONArray tsmresponse = (JSONArray) jsonObject.get("groups");
			for (int i = 0; i < tsmresponse.length(); i++) {

				claimAdjGroupValue = tsmresponse.getString(i).toString();

				List<WebElement> prescriptionGroup = prescriptionView.getGroupId();
				for (int j = 0; j < prescriptionGroup.size(); j++) {

					userId = jsonObject.get("userId").toString();

					String prescription_ClaimAdjusterValue = prescriptionView.getClaimAdjusterId();

					List<WebElement> getGroupNameId = prescriptionView.getGrpupdId();
					String s = getGroupNameId.get(j).getAttribute("data-value");

					Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,");
					Matcher m = p.matcher(s);
					if (m.find()) {
						prescriptionGroupValue = m.group(1).trim();
					}

					if (prescriptionGroupValue != null || prescriptionGroupValue != " " || claimAdjGroupValue != null
							|| claimAdjGroupValue != " ") {
						if (prescriptionGroupValue.equals(claimAdjGroupValue)
								&& (prescription_ClaimAdjusterValue.equals(userId))) {
							Assert.assertTrue(true);
						} else {
							Assert.assertFalse(false);
						}
					}
				}

			}

		} else {
			System.out.println(getUserName + " falls under different role ");

		}

	}

}
