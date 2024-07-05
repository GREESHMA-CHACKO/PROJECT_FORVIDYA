package automationCore;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseExtent {
	
	  public static ExtentSparkReporter extentSparkReporter;
	  public static ExtentReports extent;
	  public static ExtentTest test;

	
	public static void setExtent() {
		
		extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/extentReport_Latest.html");
		extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		// configuration items to change the look and feel
		// add content, manage tests etc
		extentSparkReporter.config().setDocumentTitle("PRJ_OBSACCOUNT Automation");
		extentSparkReporter.config().setReportName("Test Report");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	  }

	  public static void endReport() {
		  extent.flush();
	  }
	  
	
}
