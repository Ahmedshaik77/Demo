package com.demo.reportutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.demo.generic.utils.PropertiesDataUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ExtentReportUtils implements ITestListener {

	String screenShotPath = "";

	static ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	protected ExtentTest extentTest;

	static String pathOfFile = "./configurator.properties";
	PropertiesDataUtils propertiesDataUtils = PropertiesDataUtils.getInstance(pathOfFile);
	
	public void setup() {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date now = new Date();
			String currentTime = simpleDateFormat.format(now);
			extentHtmlReporter = new ExtentHtmlReporter(
					new File(System.getProperty("user.dir") + "_Reports_" + currentTime + ".html"));
			extentHtmlReporter.loadXMLConfig(
					new File(System.getProperty("user.dir") + "/src/test/resources/config/extent-config.xml"));
			extentReports = new ExtentReports();
			extentReports.setSystemInfo("Environment", PropertiesDataUtils.configDataStore.get("ENVIRONMENT"));
			extentReports.setSystemInfo("ModeOfExecution", PropertiesDataUtils.configDataStore.get("EXECUTION_MODE"));

			extentReports.attachReporter(extentHtmlReporter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setup(String reportName) {
		extentReports = getExtent(reportName);
	}

	public ExtentReports getExtent(String reportName) {
		if (extentReports != null)
			return extentReports; // avoid creating new instance of html file
		extentReports = new ExtentReports();

		extentReports.attachReporter(getHtmlReporter(reportName));
		return extentReports;
	}

	private ExtentHtmlReporter getHtmlReporter(String reportName) {

		extentHtmlReporter = new ExtentHtmlReporter("./reports/" + reportName + ".html");
		extentHtmlReporter.loadXMLConfig("./src/test/resources/config/extent-config.xml");

		// make the charts visible on report open
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setDocumentTitle(PropertiesDataUtils.configDataStore.get("APP_NAME"));
		extentHtmlReporter.config().setReportName("Regression Cycle");

		// Append the existing report
		extentHtmlReporter.setAppendExisting(false);
		Locale.setDefault(Locale.ENGLISH);
		return extentHtmlReporter;
	}

	public void registerTestMethod(Method method) {
		String testName = method.getName();
		extentTest = extentReports.createTest(testName);

	}
	
	
	public void sequenceScreenShot(WebDriver driver, String application, String step) {
		try {
			extentTest.addScreenCaptureFromPath(screenshotStepWise(driver, application, step));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String screenshotStepWise(WebDriver driver, String application, String step) {

		File file = new File(".");
		String filename = step + ".png";
		String filepath = null;
		try {
			filepath = file.getCanonicalPath() + "/ScreenShots/" + application + "/" + putLogDateWithoutmm() + filename;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
			screenShotPath = System.getProperty("user.dir") + "/ScreenShots/" + application + "/"
					+ putLogDateWithoutmm() + filename;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshotFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filepath));
		} catch (Exception e) {
			Reporter.log("Unable to get the screenshot");
		}
		return screenShotPath;
	}


	public void screenshotAnyCase(ITestResult result, WebDriver driver, String application) {

		String testName = result.getName();
		File file = new File(".");
		String filename = testName + ".png";
		String filepath = null;
		try {
			filepath = file.getCanonicalPath() + "/ScreenShots/" + application + "/" + putLogDate() + filename;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

			screenShotPath = System.getProperty("user.dir") + "/ScreenShots/" + application + "/" + putLogDate()
					+ filename;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshotFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filepath));
			File reportFile = new File(filepath);
			reportLogScreenshot(reportFile, filename, application);
		} catch (Exception e) {
			Reporter.log("Unable to get the screenshot");
		}
	}

	protected void reportLogScreenshot(File file, String fileName, String application) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String absolute = file.getAbsolutePath();
			absolute = System.getProperty("user.dir") + "/ScreenShots/" + application + "/" + putLogDate() + fileName;
		screenShotPath = absolute;

	}

	public void captureStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "The test method Named as :" + result.getName() + " is PASSED");
			try {
				extentTest.addScreenCaptureFromPath(screenShotPath);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "The test method Named as :" + result.getName() + " is FAILED");
			extentTest.log(Status.FAIL, "The failure : " + result.getThrowable());
			extentTest.log(Status.FAIL, "StackTrace: " + result.getThrowable());
			try {
				extentTest.addScreenCaptureFromPath(screenShotPath);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "The test method Named as :" + result.getName() + " is SKIPPED");

		}

	}

	public String putLogDate() {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
		return dateString;
	}


	public void cleanup() {
		extentReports.flush();
	}

	
	public String putLogDateWithoutmm() {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
		return dateString;
	}
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
