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
import com.bk_pageObject.LoginPage;

public class ClaimAdjusterManageClaimTest extends TestBase {

	String userRole = null;
	String userId = null;
	String getUserName = null;
	String claimAdjGroupValue = null;
	String patientGroupValue = null;

	/*@Test
	public void json() throws JSONException, IOException {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver); //
		manageClaim.verifyManageClaimView();
		String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";

		String keyValue = manageClaim.getHTMLResponse(url);
		JSONObject jsonObject = new JSONObject(keyValue);
		JSONArray tsmresponse = (JSONArray) jsonObject.get("groups");
		for (int i = 0; i < tsmresponse.length(); i++) {
			ArrayList<String> groupList = new ArrayList<String>();
			boolean s = groupList.add(tsmresponse.get(i).toString());
			if (s) {
				System.out.println(groupList);
			}
		}

	}*/

	@Test(priority = 1, description = "Verify edit option based on the Claim Adjuster value avaialble in claim details")
	public void verifyEditClaimRecords() {

		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver);
		manageClaim.verifyManageClaimView();

		getUserName = manageClaim.getLoggedInUserName();

		String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";
		String keyValue = manageClaim.getHTMLResponse(url);
		JSONObject jsonObject = new JSONObject(keyValue);

		userRole = jsonObject.get("webRole").toString();
		if (userRole.contains("Claim Adjuster")) {

			List<WebElement> getClaimList = manageClaim.getListofClaims();
			for (int j = 0; j < getClaimList.size(); j++) {
				getClaimList.get(j).click();
				userId = jsonObject.get("userId").toString();
				String claimAdjusterid = manageClaim.getClaimAdjusterId();

				boolean isreadOnly = manageClaim.isAttribtuePresent();
				if (claimAdjusterid != null || claimAdjusterid != " " || userId != null || userId != " ") {
					if (claimAdjusterid.equals(userId)) {
						if (isreadOnly) {
							System.out.println("form is readonly hence cannot edit records");
						} else {
							System.out.println("form is not readonly hence can edit records");
						}

					} else {
						Assert.assertFalse(false);
					}
				}
				driver.navigate().back();
			}

		} else {
			System.out.println(getUserName + " falls under different role ");

		}

	}

	/*
	 * @Test(priority = 0, description =
	 * "Verify list of claim records in manage claim page") public void
	 * verifyListOfClaimRecord() {
	 * 
	 * loginPage = new LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * manageClaim = new ClaimAdjusterManageClaim(driver);
	 * manageClaim.verifyManageClaimView();
	 * 
	 * getUserName = manageClaim.getLoggedInUserName();
	 * 
	 * String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";
	 * String keyValue = manageClaim.getHTMLResponse(url); JSONObject jsonObject =
	 * new JSONObject(keyValue);
	 * 
	 * userRole = jsonObject.get("webRole").toString(); if
	 * (userRole.contains("Claim Adjuster")) { JSONArray tsmresponse = (JSONArray)
	 * jsonObject.get("groups"); for (int i = 0; i < tsmresponse.length(); i++) {
	 * claimAdjGroupValue = tsmresponse.getString(i).toString(); List<WebElement>
	 * patientGroup = manageClaim.getGroupId(); for (int j = 0; j <
	 * patientGroup.size(); j++) { String getGroupNameId =
	 * patientGroup.get(j).getAttribute("data-value");
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
	 * } else { System.out.println(getUserName + " falls under different role ");
	 * 
	 * }
	 * 
	 * }
	 */
}
