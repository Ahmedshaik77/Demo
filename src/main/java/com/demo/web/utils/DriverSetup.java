package com.demo.web.utils;


import org.openqa.selenium.WebDriver;
import com.demo.generic.utils.DriverProvider;
import com.demo.generic.utils.PropertiesDataUtils;
import com.demo.reportutils.ExtentReportUtils;

public class DriverSetup {

	public WebDriver driver;
	DriverProvider browserProvider = new DriverProvider();
	public RunnerXmlHooks runnerXmlHooks = new RunnerXmlHooks();
    public ExtentReportUtils extentReportUtils = new ExtentReportUtils();
    String modeOfExecution = PropertiesDataUtils.configDataStore.get("EXECUTION_MODE");
	String defaultBrowserName = PropertiesDataUtils.configDataStore.get("BROWSER_NAME");
	String url = PropertiesDataUtils.configDataStore.get("url");

	public WebDriver getDriver() {
		return this.driver;
	}

	protected void setDriver(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("DEFAULT")) {
				driver = browserProvider.getDriver(modeOfExecution).init(defaultBrowserName);
			} else {
				driver = browserProvider.getDriver(modeOfExecution).init(browserName);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
