package com.rbc.assignment.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rbc.assignment.helper.browserConfigurations.BrowserType;
import com.rbc.assignment.helper.browserConfigurations.ChromeBrowser;
import com.rbc.assignment.helper.browserConfigurations.FirefoxBrowser;
import com.rbc.assignment.helper.browserConfigurations.IExploreBrowser;
import com.rbc.assignment.helper.browserConfigurations.config.ObjectReader;
import com.rbc.assignment.helper.browserConfigurations.config.PropertyReader;
import com.rbc.assignment.helper.logger.LoggerHelper;
import com.rbc.assignment.helper.wait.WaitHelper;
import com.rbc.assignment.utils.ExtentManager;
import com.rbc.assignment.utils.ResourceHelper;
import org.apache.commons.io.FileUtils;

/**
 * base class for all test
 * @author paras
 *
 */
public class TestBase {
	
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);

	public static ExtentReports extent;
	public static File reportDirectory;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@AfterSuite
	public void afterSuite() {
		extent.close();
	}
	
    @BeforeClass
    public synchronized void beforeClass() {
        ExtentTest parent = extent.createTest(getClass().getSimpleName());
        parentTest.set(parent);
    }
    
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(@Optional("Chrome") BrowserType browser) {
		ObjectReader.reader = new PropertyReader();
		reportDirectory=new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
		setupDriver(browser);
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
		
	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
        logExtentReport("**************"+method.getName()+" Test started***************");
		log.info("************** "+method.getName()+" Test Started***************");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE){
			 test.get().log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(),driver);
			 test.get().addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			 test.get().log(Status.PASS, result.getName()+" is Passed");
			String imagePath = captureScreen(result.getName(),driver);
			 test.get().addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			 test.get().log(Status.SKIP, result.getThrowable());
		}
		
		log.info("************** "+result.getName()+" Finished***************");
		logExtentReport("************** "+result.getName()+" Finished***************");
		extent.flush();
	}
	
	/**
	 * get browser object using browser type
	 * @param btype
	 * @return
	 * @throws Exception
	 */
	public WebDriver getBrowserObject(BrowserType btype) throws Exception {
		try {
			switch(btype){
			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions chromeOptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeOptions);
			case FireFox:
				FirefoxBrowser ff = FirefoxBrowser.class.newInstance();
				FirefoxOptions ffOptions = ff.getFirefoxOptions();
				return ff.getFirefoxDriver(ffOptions);
				
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions ieoptions = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(ieoptions);
			
			default:
				throw new Exception("Driver not found "+btype.name());
			}
		}
		catch(Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * setup driver for test
	 * @param btype
	 */
	public void setupDriver(BrowserType btype) {
		try {
			driver = getBrowserObject(btype);
			log.info("Initialized WebDriver "+driver.hashCode());
			WaitHelper wait = new WaitHelper(driver);
			wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
			wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * capture screenshot
	 * @param fileName
	 * @param driver
	 * @return
	 */
	public String captureScreen(String fileName, WebDriver driver) {
		if(driver == null) {
			log.info("driver is null..");
			return null;
		}
		if(fileName=="") {
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(screFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	/**
	 * capture navigation screen
	 * @param driver
	 */
	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing navigation Screen");
		String screen = captureScreen("",driver);
		try {
			 test.get().addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * log message to extent report
	 * @param s1
	 */
	public static void logExtentReport(String s1){
		 test.get().log(Status.INFO, s1);
	}
	
}
