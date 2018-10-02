package com.rbc.assignment.helper.wait;

import com.rbc.assignment.helper.logger.LoggerHelper;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * this class is a helper to handle wait objects
 * @author paras
 *
 */
public class WaitHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WaitHelper Object created");
	}
	
	/**
	 * set implicit wait
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit Wait is set to: "+ timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	/**
	 * get fluent wait object with timeOut and polling time
	 * @param TimeOutInSeconds
	 * @param PollingEveryInMilliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int TimeOutInSeconds, int PollingEveryInMilliSec) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(TimeOutInSeconds))
		.pollingEvery(Duration.ofMillis(PollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
		return fwait;
	}
	
	/**
	 * wait for element to be visible using fluent wait
	 * @param element
	 * @param TimeOutInSeconds
	 * @param PollingEveryInMilliSec
	 * @return
	 */
	public WebElement waitForElement(WebElement element,int TimeOutInSeconds, int PollingEveryInMilliSec) {
		Wait<WebDriver> fwait = getfluentWait(TimeOutInSeconds, PollingEveryInMilliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/**
	 * set page load time out
	 * @param TimeOut
	 * @param unit
	 */
	public void pageLoadTime(long TimeOut, TimeUnit unit) {
		driver.manage().timeouts().pageLoadTimeout(TimeOut, unit);
		log.info("Page load Timeout set "+ TimeOut +" Seconds");
	}
	
}
