package com.bk.testScripts.loginPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.ClaimAdjusterManageClaim;
import com.bk_pageObject.LoginPage;


public class ClaimAdjusterManageClaimTest extends TestBase {

	String userRole = null;
	String getUserName = null;
	String claimAdjGroupValue = null;
	String patientGroupValue = null;

	/*
	 * @Test public void json() throws JSONException, IOException { loginPage = new
	 * LoginPage(driver);
	 * loginPage.loginToApplication(ObjectReader.reader.getUserName(),
	 * ObjectReader.reader.getPassword());
	 * 
	 * manageClaim = new ClaimAdjusterManageClaim(driver);
	 * manageClaim.verifyManageClaimView(); String url =
	 * "https://prodigyservicesdev.powerappsportals.com/user-details/";
	 * 
	 * String keyValue = manageClaim.getHTMLResponse(url); JSONObject jsonObject =
	 * new JSONObject(keyValue); JSONArray tsmresponse = (JSONArray)
	 * jsonObject.get("groups"); for(int i=0; i<tsmresponse.length(); i++){ String
	 * getGroupId = tsmresponse.get(i).toString(); System.out.println(getGroupId); }
	 * 
	 * 
	 * 
	 * }
	 */

	@Test(priority = 0, description = "Verify list of claim records in manage claim page")
	public void verifyListOfClaimRecord() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver);

		manageClaim.verifyManageClaimView();

		getUserName = manageClaim.getLoggedInUserName();

		String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";

		String keyValue = manageClaim.getHTMLResponse(url);
		JSONObject jsonObject = new JSONObject(keyValue);

		// String claimAdjSourcOrgValue = jsonObject.get("sourceOrg").toString();
		userRole = jsonObject.get("webRole").toString();
		JSONArray tsmresponse = (JSONArray) jsonObject.get("groups");
		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < tsmresponse.length(); i++) {
				claimAdjGroupValue = tsmresponse.get(i).toString();
				System.out.println(claimAdjGroupValue);

				List<WebElement> list = manageClaim.getListOfClaim();

				for (int j = 0; j < list.size(); j++) {
					String patientGroup = manageClaim.getGroupName();
					Pattern p = Pattern.compile("\"Id\":\"(.*?)\"\\,");
					Matcher m = p.matcher(patientGroup);

					if (m.find()) {
						patientGroupValue = m.group(1).trim();
						System.out.println(patientGroupValue);
					}
					// String patientSourceOrgValue = manageClaim.getSourceOrganization();
					if (patientGroupValue != null || patientGroupValue != " " || claimAdjGroupValue != null
							|| claimAdjGroupValue != "") {
						if (patientGroupValue.equals(claimAdjGroupValue)) {
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
