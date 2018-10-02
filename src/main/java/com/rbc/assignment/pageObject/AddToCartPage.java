package com.rbc.assignment.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.helper.browserConfigurations.config.ObjectReader;
import com.rbc.assignment.helper.wait.WaitHelper;
import com.rbc.assignment.testbase.TestBase;

/**
 * page objects for add to cart page
 * @author paras
 *
 */
public class AddToCartPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(AddToCartPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(id="add-to-cart-button")
	private static WebElement addToCartButton;
	
	@FindBy(id="quantity")
	private static WebElement quantityDropDown;
	
	@FindBy(id="productTitle")
	private static WebElement productTitle;
	
	public AddToCartPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	public static WebElement getAddToCartButton() {
		return addToCartButton;
	}

	public static WebElement getQuantityDropDown() {
		return quantityDropDown;
	}


	public static WebElement getProductTitle() {
		return productTitle;
	}

	/**
	 * click on Web Element
	 */
	public void click(WebElement element) {
		element.click();
		log.info("Clicked on: "+ element.getText());
		TestBase.logExtentReport("Clicked on: "+ element.getText());
	}
	
	/**
	 * @return 
	 * 
	 */
	public String getText(WebElement element) {
		return element.getText();
	}
	
	/**
	 * select value from dropdown
	 * @param element
	 * @param value
	 */
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("Select using value: "+value);
		select.selectByValue(value);
	}
	
	/**
	 * read selected value from dropdown
	 * @param element
	 * @return
	 */
	public String readSelectedValue(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText().trim();
	}
	
}

