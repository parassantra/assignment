package com.rbc.assignment.steps;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.rbc.assignment.helper.browserConfigurations.config.ObjectReader;
import com.rbc.assignment.helper.browserConfigurations.config.PropertyReader;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.pageObject.AddToCartPage;
import com.rbc.assignment.pageObject.HomePage;
import com.rbc.assignment.pageObject.LoginPage;
import com.rbc.assignment.pageObject.ProceedToCheckOutPage;
import com.rbc.assignment.pageObject.SuggestionPopUpPage;
import com.rbc.assignment.testbase.TestBase;

public class TestSteps {

	private WebDriver driver;
	private String url;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	
	public TestSteps(WebDriver driver) {
		this.driver = driver;
		ObjectReader.reader = new PropertyReader();
		this.url = ObjectReader.reader.getUrl();
	}
	
	/**
	 * get application using url
	 * @param url
	 */
	public void getApplication() {
		driver.get(url);
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(HomePage.getLogo().isDisplayed());
		TestBase.logExtentReport("Navigating to ..."+ url);
		new TestBase().getNavigationScreen(driver);
	}
	
	public void gotoShopByDepartment() {
		HomePage homePage = new HomePage(driver);
		WebElement shopByDepartment = HomePage.getShopByDepatment();
		homePage.move(shopByDepartment);
		new TestBase().getNavigationScreen(driver);
		homePage.click(shopByDepartment);
		new TestBase().getNavigationScreen(driver);
		WebElement shopByDepartmentTable = HomePage.getShopByDepartmentTable();
		Assert.assertTrue(shopByDepartmentTable.isDisplayed());
		log.info("Shop By Department Table is Displayed");
		TestBase.logExtentReport("Shop By Department Table is Displayed");
	}
	
	public void selectProductByCategory(String productCategory, String productName) {
		HomePage homePage = new HomePage(driver);
		WebElement category = homePage.getProductCategory(productCategory);
		WebElement product = homePage.getProduct(productName);
		Assert.assertTrue(category.isDisplayed());
		Assert.assertTrue(product.isDisplayed());
		log.info("Product Category: "+productCategory+" Product :" + productName + " is Displayed");
		TestBase.logExtentReport("Product Category: "+productCategory+" Product :" + productName + " is Displayed");
		homePage.click(product);
		new TestBase().getNavigationScreen(driver);
		AddToCartPage addToCartPage = new AddToCartPage(driver);
		WebElement productTitle = AddToCartPage.getProductTitle();
		Assert.assertTrue(addToCartPage.getText(productTitle).contains(productName));
		log.info("Correct product Added to cart: " + productName);
		TestBase.logExtentReport("Correct product Added to cart: " + productName);
	}
	
	public void increaseProductQuantity(String quantity) {
		AddToCartPage addToCartPage = new AddToCartPage(driver);
		WebElement quantityDropDown = AddToCartPage.getQuantityDropDown();
		Assert.assertTrue(quantityDropDown.isDisplayed());
		addToCartPage.selectUsingValue(quantityDropDown, quantity);
		Assert.assertEquals(addToCartPage.readSelectedValue(quantityDropDown), quantity);
		new TestBase().getNavigationScreen(driver);
		log.info("Product quantity:" + quantity);
		TestBase.logExtentReport("Product quantity:" + quantity);
	}
	
	public void addToCart(String productName, String quantity) throws InterruptedException {
		AddToCartPage addToCartPage = new AddToCartPage(driver);
		WebElement addToCartButton = AddToCartPage.getAddToCartButton();
		Assert.assertTrue(addToCartButton.isDisplayed());
		addToCartPage.click(addToCartButton);
		new TestBase().getNavigationScreen(driver);
		log.info("Product added to cart: " + productName + " quantity "+quantity);
		TestBase.logExtentReport("Product added to cart: " + productName + " quantity "+quantity);
		SuggestionPopUpPage suggestionPopUpPage = new SuggestionPopUpPage(driver);
		suggestionPopUpPage.handleSuggestionPopUp();
		new TestBase().getNavigationScreen(driver);
		ProceedToCheckOutPage proceedToCheckOutPage = new ProceedToCheckOutPage(driver);
		WebElement proceeCheckOutButton = ProceedToCheckOutPage.getProceedCheckOutButton();
		WebElement productTitle = ProceedToCheckOutPage.getProductTitle();
		WebElement productQuantity = ProceedToCheckOutPage.getProductQuantity();
		Assert.assertTrue(proceeCheckOutButton.isDisplayed());
		Assert.assertTrue(proceedToCheckOutPage.getText(productTitle).contains(productName));
		Assert.assertEquals(proceedToCheckOutPage.getText(productQuantity),quantity);
		log.info("Checked Out  " + productName + " quantity "+quantity);
		TestBase.logExtentReport("Checked Out  " + productName + " quantity "+quantity);
	}
	
	public void proceedToOrder() throws InterruptedException {
		ProceedToCheckOutPage proceedToCheckOutPage = new ProceedToCheckOutPage(driver);
		WebElement proceedToCheckOutButton = ProceedToCheckOutPage.getProceedCheckOutButton();
		new TestBase().getNavigationScreen(driver);
		proceedToCheckOutButton.click();
		Thread.sleep(5000);
		LoginPage loginPage = new LoginPage(driver);
		WebElement emailAddressInput = LoginPage.getEmailAddressPhoneNo();
		Assert.assertTrue(emailAddressInput.isDisplayed());
		log.info("Login Page displayed");
		TestBase.logExtentReport("Login Page displayed");
	}
}

