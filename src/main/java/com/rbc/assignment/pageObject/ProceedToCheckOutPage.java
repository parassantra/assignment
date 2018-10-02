package com.rbc.assignment.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.testbase.TestBase;

/**
 * page objects for add to checkout page
 * @author paras
 *
 */
public class ProceedToCheckOutPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ProceedToCheckOutPage.class);
		
	@FindBy(xpath="//a[@class='hucSprite s_checkout hlb-checkout-button']")
	public static WebElement proceedCheckOutButton;
	
	@FindBy(css = "span.hlb-item-title")
	private static WebElement productTitle;
	
	@FindBy(id = "hlb-cart-itemcount")
	private static WebElement productQuantity;
	
	public ProceedToCheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static WebElement getProceedCheckOutButton() {
		return proceedCheckOutButton;
	}

	public static WebElement getProductTitle() {
		return productTitle;
	}

	public static WebElement getProductQuantity() {
		return productQuantity;
	}
	
	/**
	 * get text of element
	 * @return 
	 * 
	 */
	public String getText(WebElement element) {
		return element.getText();
	}
	
	/**
	 * click on Web Element
	 */
	public void click(WebElement element) {
		element.click();
		log.info("Clicked on: "+ element.getText());
		TestBase.logExtentReport("Clicked on: "+ element.getText());
	}
	
}


