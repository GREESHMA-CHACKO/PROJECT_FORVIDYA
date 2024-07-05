package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMCategory;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.Assert;

public class CategoryTC extends BasePage {
	POMLogin objPomLogin;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	
	POMCategory objpomCategory;

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
	public void add_category(String categoryName, String categoryCode) throws IOException, InterruptedException {

		objPomLogin.product_click();
		objpomCategory = new POMCategory(driver.get());
		objpomCategory.category_click();
		objpomCategory.add_Category(categoryName, categoryCode);
		String actual_message = PropertyReadUtility.readConfigFile("category_added_successfully");
		String message = objpomCategory.getMessage();
		Assert.assertTrue(actual_message.contains(message));
		
		
	}

	@Test(priority = 3, enabled = true)
	public void SearchCategory() throws InterruptedException {
		objpomCategory.Search_category(PropertyReadUtility.readConfigFile("category_test_data"));
		String tableName=objpomCategory.isDisplayed();
		String actualTableName=PropertyReadUtility.readConfigFile("category_test_data");
			SoftAssert objassert = new SoftAssert();
			objassert.assertTrue(actualTableName.contains(tableName));
			objassert.assertAll();

	}

	@Test(priority = 4, enabled = true)
	public void deleteCategory() throws InterruptedException {
		objpomCategory.delete_category(PropertyReadUtility.readConfigFile("category_test_data"));

		String actual_message = PropertyReadUtility.readConfigFile("category_deleted_message");
		String message = objpomCategory.getMessage();
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(actual_message, message);
		objassert.assertAll();
	}

	@DataProvider(name = "testData")
	public Object[][] TestDataFeed() {
		Object[][] categoryData = new Object[1][2];
		categoryData[0][0] = "Test_category";
		categoryData[0][1] = "t_code";
		return categoryData;
	}

}
