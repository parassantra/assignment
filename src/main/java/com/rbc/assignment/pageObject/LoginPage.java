package com.rbc.assignment.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.rbc.assignment.helper.logger.LoggerHelper;

/**
 * page objects for login page
 * @author paras
 *
 */
public class LoginPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	
	@FindBy(name = "email")
	private static WebElement emailAddressPhoneNo;
	
	public static WebElement getEmailAddressPhoneNo() {
		return emailAddressPhoneNo;
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
}


