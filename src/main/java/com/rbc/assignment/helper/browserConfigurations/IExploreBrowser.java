package com.rbc.assignment.helper.browserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.rbc.assignment.utils.ResourceHelper;

/**
 * this class is used to get instance of Internet Explorer Browser with specified properties
 * @author paras
 *
 */
public class IExploreBrowser {
	
	/**
	 * this is used to get Internet Explorer capabilities
	 * @return
	 */
	public InternetExplorerOptions getIExplorerCapabilities() {
		
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setJavascriptEnabled(true);
		
		InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions(cap);
		
		return internetExplorerOptions;
	}
	
	/**
	 * get Internet Explorer Driver
	 * @param cap
	 * @return
	 */
	public WebDriver getIExplorerDriver(InternetExplorerOptions cap){
		System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/IEDriver.exe"));
		return new InternetExplorerDriver(cap);
	}

}