package com.rbc.assignment.helper.browserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.rbc.assignment.utils.ResourceHelper;

/**
 * this class is used to get instance of Chrome Browser with specified properties
 * @author paras
 *
 */
public class ChromeBrowser {
	
	/**
	 * this is used to get Chrome Options
	 * @return
	 */
	public ChromeOptions getChromeOptions() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		option.setCapability(ChromeOptions.CAPABILITY, chrome);
		//linux
		if(System.getProperty("os.name").contains("Linux")) {
			option.addArguments("window-size=1024,768", "--no-sandbox");
		}
		return option;
	}
	
	/**
	 * get Chrome Driver according to OS
	 * @param cap
	 * @return
	 */
	public WebDriver getChromeDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriverlinux");
			return new ChromeDriver(cap);
		}
		return null;
	}
}