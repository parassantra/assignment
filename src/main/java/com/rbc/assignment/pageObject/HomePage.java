package com.rbc.assignment.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.testbase.TestBase;

/**
 * page objects for Home Page
 * @author paras
 *
 */
public class HomePage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	
	@FindBy(id = "nav-link-shopall")
	private static WebElement shopByDepatment;
	
	@FindBy(id = "nav-logo")
	private static WebElement logo;
	
	@FindBy(id = "shopAllLinks")
	private static WebElement shopByDepartmentTable;
	
	public static WebElement getShopByDepatment() {
		return shopByDepatment;
	}

	public static WebElement getLogo() {
		return logo;
	}

	public static WebElement getShopByDepartmentTable() {
		return shopByDepartmentTable;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * click on Web Element
	 */
	public void click(WebElement element) {
		log.info("Clicking on: "+ element.getText());
		TestBase.logExtentReport("Clicking on: "+ element.getText());
		element.click();
	}
	
	/**
	 * move to Web Element
	 */
	public void move(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		log.info("moved to " + element.getText());
		TestBase.logExtentReport("Moved to: "+ element.getText());
	}
	
	/**
	 * get product Category webelment
	 * @param productCategory
	 * @return
	 */
	public WebElement getProductCategory(String productCategory) {
		return driver.findElement(By.xpath("//h2[contains(text(),'"+productCategory+"')]"));
	}
	
	/**
	 * get product webelment
	 * @param productCategory
	 * @return
	 */
	public WebElement getProduct(String productName) {
		return driver.findElement(By.linkText(productName));
	}
}
