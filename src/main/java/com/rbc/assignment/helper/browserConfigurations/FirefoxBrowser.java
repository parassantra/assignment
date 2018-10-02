package com.rbc.assignment.helper.browserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.rbc.assignment.utils.ResourceHelper;

/**
 * this class is used to get instance of Firefox Browser with specified properties
 * @author paras
 *
 */
public class FirefoxBrowser {

	/**
	 * this is used to get Firefox Options
	 * @return
	 */
	public FirefoxOptions getFirefoxOptions() {
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
		FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
		// Linux
		if (System.getProperty("os.name").contains("Linux")) {
			firefoxOptions.addArguments("window-size=1024,768", "--no-sandbox");
		}
		return firefoxOptions;
	}
	
	/**
	 * get Firefox Driver according to OS
	 * @param cap
	 * @return
	 */
	public WebDriver getFirefoxDriver(FirefoxOptions cap){

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver",ResourceHelper.getResourcePath("src/main/resources/drivers/geckodriver"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.gecko.driver",ResourceHelper.getResourcePath("src/main/resources/drivers/geckodriver.exe"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriverlinux");
			return new FirefoxDriver(cap);
		}
		return null;
	}
	
}