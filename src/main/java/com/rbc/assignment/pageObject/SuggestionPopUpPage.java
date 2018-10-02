package com.rbc.assignment.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rbc.assignment.helper.browserConfigurations.config.ObjectReader;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.helper.wait.WaitHelper;


public class SuggestionPopUpPage {

	private WebDriver driver;
	WaitHelper waitHelper;
	private final Logger log = LoggerHelper.getLogger(SuggestionPopUpPage.class);
	
	@FindBy(xpath="//div[@class='a-popover-wrapper']")
	public static WebElement suggestionPopup;
	
	@FindBy(xpath="//div[@class='a-popover-wrapper']//button[@aria-label='Close']")
	public static WebElement closeButton;
	
	public SuggestionPopUpPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	/**
	 * close popup related to suggestion of additional items
	 * @throws InterruptedException
	 */
	public void closeSuggestionPopover(){
		log.info("Closing Suggestion PopUP");
		WebElement popup = waitHelper.waitForElement(closeButton, ObjectReader.reader.getExplicitWait(),200);
		popup.click();
		log.info("Closed Suggestion PopUP");
		
	}
	
	/**
	 * check if popup related to suggestion of additonal items present
	 * @return
	 */
	public boolean isSuggestionPopupPresent() {
		WebElement popup = waitHelper.waitForElement(suggestionPopup, ObjectReader.reader.getExplicitWait(), 2000);
		if(popup == null) {
			log.info("Suggestion Popup Not visible");
			return false;
		}
		log.info("Suggestion Popup visible");
		return true;
	}
	
	/**
	 * handle suggestion popup if present
	 */
	public void handleSuggestionPopUp() {
		if(isSuggestionPopupPresent()) {
				closeSuggestionPopover();
		}
	}
}
