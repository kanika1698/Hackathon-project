package testcase;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
import PageClasses.LandingPage;
import Utilities.TestDataProvider;

public class IncompleteData extends PageBaseClass{
	LandingPage landingpage;

	@Test(dataProvider = "getTestData", priority = 1,description="Test to check all fields are filled or not")
	public void emiCalculator(Hashtable<String, String> testData) {
		logger = report.createTest("Emi Calculator " + testData.get("Comments"));
		PageBaseClass pageBase = new PageBaseClass();
		logger.log(Status.INFO, "Opening Browser");
		invokeBrowser("chrome");
		landingpage = pageBase.OpenApplication();
		landingpage.clickCarloan();
		sleep();
		landingpage.enterDetails(testData.get("Loanamount"), testData.get("Loaninterest"), testData.get("Tenure"));
		if (testData.get("Loanamount") == "" || testData.get("Loaninterest") == "" || testData.get("Tenure") == "") {
			reportFail("Incomplete Data");
			
		} else {
			landingpage.result();
			reportPass("All fields are filled");
			
		}

	}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestDataProvider.getTestData("hackathon.xlsx", "Data", "emiCalculator");

	}

}
