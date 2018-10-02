package com.rbc.assignment.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.rbc.assignment.utils.ResourceHelper;

/**
 * this class is used to Log messages from test
 * @author paras
 *
 */
public class LoggerHelper {

	private static boolean root=false;
	
	/**
	 * get Logger object for Class
	 * @param cls
	 * @return
	 */
	public static Logger getLogger(Class <?> cls){
		if(root){
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/resources/configfile/log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}
}