package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMAddProduct;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;

public class AddProductTC extends BasePage {
	POMLogin objPomLogin;
	POMAddProduct objPomAddProduct;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	

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

	@Test(priority = 2, enabled = true)
	public void addProduct() throws Exception {
		objPomAddProduct = new POMAddProduct(driver.get());
		objPomLogin.product_click();
		objPomAddProduct.add_product();
		String actual_message = PropertyReadUtility.readConfigFile("product_added_message");
		String message = objPomAddProduct.get_message();
		Assert.assertTrue(actual_message.contains(message));
	}
}
