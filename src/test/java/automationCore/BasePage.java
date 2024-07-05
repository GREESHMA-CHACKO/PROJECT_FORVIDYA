package automationCore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.DriverUtility;
import utility.PropertyReadUtility;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BasePage {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	// public WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	//static String browser = PropertyReadUtility.readConfigFile("browser");

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		BaseExtent.setExtent();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		BaseExtent.endReport();
	}

	@BeforeTest(alwaysRun = true)
	@Parameters ({"browser"})
	public void beforeTest(String browser) {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver.set(objDriverManager.driver);

	}


	@AfterTest(alwaysRun = true)
	public void afterTest() {
            driver.get().close();
	}
	
	  public static String savedScreenshot() throws Exception {

			Calendar cal = Calendar.getInstance();
			Date time = (Date) cal.getTime();
			String timestamp = time.toString().replace(":", "").replace(" ", "");

			System.out.println(time);
			System.out.println(timestamp);

			// Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) driver.get());

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(
					System.getProperty("user.dir") + "\\src\\main\\resources\\Screenshots\\test_" + timestamp + ".png");

	        //Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
			return DestFile.getAbsolutePath();
		}

}
