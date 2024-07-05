package listeners;

import org.testng.ITestListener;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import automationCore.BaseExtent;
import automationCore.BasePage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class ExtentReportListener extends BaseExtent implements ITestListener {

	
	 public void onTestStart(ITestResult result) {
		    test = extent.createTest(result.getName());
	
		  }

		  public void onTestSuccess(ITestResult result) {
		    if (result.getStatus() == ITestResult.SUCCESS) {
		      test.log(Status.PASS, "Pass Test case is: " + result.getName());
		    }
		  }

		  public void onTestFailure(ITestResult result) {
		    if (result.getStatus() == ITestResult.FAILURE) {
		      try {
				test.addScreenCaptureFromPath(BasePage.savedScreenshot());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      test.log(Status.FAIL,
		          MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		      test.log(Status.FAIL,
		          MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		    }
		  }

		  public void onTestSkipped(ITestResult result) {
		    if (result.getStatus() == ITestResult.SKIP) {
		      test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		    }
		  }

		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    test.log(Status.INFO, "Test case failed but within success percentage: " + result.getName());
		  }

		  public void onStart(ITestContext context) {
		    //test.log(Status.INFO, "Test Execution Started");
		  }

		  public void onFinish(ITestContext context) {

		  }

}
