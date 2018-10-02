package com.rbc.assignment.utils;

/**
 * use this class to get resource from src/main/resources
 * @author paras
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String path)
	{
		String basePath = System.getProperty("user.dir");
		return basePath+"/"+path;
	}	
}
