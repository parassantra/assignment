package com.rbc.assignment.helper.browserConfigurations.config;

import com.rbc.assignment.helper.browserConfigurations.BrowserType;

/**
 * this interface contains methods to implement if reading configurations file
 * @author paras
 *
 */
public interface ConfigReader {
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public String getUrl();
}
