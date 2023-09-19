package com.bk.testScripts.loginPage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bk.testbase.TestBase;
import com.bk_helper.browserconfiguration.config.ObjectReader;
import com.bk_pageObject.ClaimAdjusterManageClaim;
import com.bk_pageObject.LoginPage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import android.util.Log;

public class ClaimAdjusterManageClaimTest extends TestBase {

	String userRole = null;
	String getUserName = null;

	@Test
	public void json() throws JSONException, IOException {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver);
		manageClaim.verifyManageClaimView();
		String url = "https://prodigyservicesdev.powerappsportals.com/user-details/";

		String keyValue = manageClaim.getHTMLResponse(url);
		JSONObject jsonObject = new JSONObject(keyValue);

		String getValue = jsonObject.get("sourceOrg").toString();
		String getValue2 = jsonObject.get("webRole").toString();
		System.out.println(getValue + " " + getValue2);
	}

	@Test(priority = 0, description = "Verify list of claim records in manage claim page")
	public void verifyListOfClaimRecord() {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		manageClaim = new ClaimAdjusterManageClaim(driver);

		manageClaim.verifyManageClaimView();

		getUserName = manageClaim.getLoggedInUserName();

		userRole = manageClaim.verifyLoggedInUserRole();

		String claimAdjGroupValue = "Prodigy Demo Group";
		String claimAdjSourcOrgValue = "deadbeef-0020-0000-0000-000000000024";
		List<WebElement> list = manageClaim.getListOfClaim();

		if (userRole.contains("Claim Adjuster")) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
				String patientGroupValue = manageClaim.getGroupName();
				String patientSourceOrgValue = manageClaim.getSourceOrganization();
				if (claimAdjSourcOrgValue.equals(patientSourceOrgValue)) {
					if (patientGroupValue != null || patientGroupValue != " " || claimAdjGroupValue != null
							|| claimAdjGroupValue != "") {
						if (patientGroupValue.equals(claimAdjGroupValue)) {
							Assert.assertTrue(true);
						} else {
							Assert.assertFalse(false);
						}
					}
				} else {
					System.out.println(userRole + " or patient are not in the same source organization");
				}
				driver.navigate().back();
			}
		}
	}

}
