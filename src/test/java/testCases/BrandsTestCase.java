package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMBrands;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.Assert;

public class BrandsTestCase extends BasePage{
	POMLogin objPomLogin;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	POMBrands objPomBrands;

	@Test(priority = 1, enabled = true)
	public void logIn() throws IOException {
		objPomLogin = new POMLogin(driver.get());
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);

		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(objPomLogin.isElementDisplayed(), true);	
		objassert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "testData")
	public void addBrands(String brandName, String brandDescription) throws IOException, InterruptedException {
		objPomBrands = new POMBrands(driver.get());
		objPomLogin.product_click();
		objPomBrands.brands_click();
		objPomBrands.add_brands(brandName, brandDescription);
		String actual_message = PropertyReadUtility.readConfigFile("brand_added_message");
		String message = objPomBrands.getMessage();
		Assert.assertTrue(actual_message.contains(message));
		
}

	@Test(priority = 3, enabled = true)
	public void SearchCategory() throws InterruptedException {
		objPomBrands.search_brand(PropertyReadUtility.readConfigFile("brand_test_data"));
		 String tableName=objPomBrands.isDisplayed();
		  String actualTableName=PropertyReadUtility.readConfigFile("brand_test_data");
			SoftAssert objassert = new SoftAssert();
			objassert.assertTrue(actualTableName.contains(tableName));
			objassert.assertAll();
	}

	@DataProvider(name = "testData")
	public Object[][] TestDataFeed() {
		Object[][] brandData = new Object[1][2];
		brandData[0][0] = "Sree_brand";
		brandData[0][1] = "Sree_brand";
		return brandData;
	}

}
