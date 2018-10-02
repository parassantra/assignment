package com.rbc.assignment;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.rbc.assignment.helper.browserConfigurations.config.ObjectReader;
import com.rbc.assignment.steps.TestSteps;
import com.rbc.assignment.testbase.TestBase;


public class ProcessOrderTest extends TestBase{
	
	@Test(dataProvider = "kindlePurchase", description="Process Order (Kindle Paper White)")
	public void processOrderForProduct(String productCategory, String productName, String quantity){
		TestSteps steps = new TestSteps(driver);
		steps.getApplication();
		steps.gotoShopByDepartment();
		steps.selectProductByCategory(productCategory, productName);
		steps.increaseProductQuantity(quantity);
		steps.addToCart(productName, quantity);
		steps.proceedToOrder();
	}
	
	@DataProvider(name = "kindlePurchase")
	public static Object[][] credentials() {
	        return new Object[][] { { "Kindle", "Kindle Paperwhite", "2" }};
	}

}
