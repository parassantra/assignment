package com.rbc.assignment.helper.browserConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.rbc.assignment.helper.browserConfigurations.BrowserType;
import com.rbc.assignment.utils.ResourceHelper;

/**
 * this class is used to read configuration from properties file
 * @author paras
 *
 */
public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties or;
	public PropertyReader() {
		String filePath=ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
		try {
			file = new FileInputStream(new File(filePath));
			or = new Properties();
			or.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(or.getProperty("implicitwait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(or.getProperty("explicitwait"));
	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(or.getProperty("pageloadtime"));
	}

	public String getUrl() {
		if(System.getProperty("url")!=null) {
			return System.getProperty("url");
		}
		return or.getProperty("applicationUrl");
	}
}
