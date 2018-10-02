package com.rbc.assignment.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.rbc.assignment.helper.logger.LoggerHelper;

/**
 * this class implements ITestListener and is used as a custom listener
 * @author paras
 *
 */
public class TestListener implements ITestListener {
	private Logger log = LoggerHelper.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName()+" test Started..");
		log.info(result.getMethod().getMethodName()+" test Started..");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName()+" test Passed..");
		log.info(result.getMethod().getMethodName()+" test Passed..");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName()+" test Failed.."+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" test Failed.."+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName()+" test Skipped.."+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" test Skipped.."+result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log(context.getName()+" test Started..");
		log.info(context.getName()+" test Started..");
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log(context.getName()+" test Finished..");
		log.info(context.getName()+" test Finished..");
	}

}
