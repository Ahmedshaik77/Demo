package com.demo.web.specs;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.demo.generic.utils.PropertiesDataUtils;
import com.demo.pages.CartPage;
import com.demo.pages.Homepage;
import com.demo.pages.ProductDetailPage;
import com.demo.pages.ProductListPage;
import com.demo.web.utils.DriverSetup;
import com.demo.web.utils.WebActionHelperUtils;

public class BaseTest extends DriverSetup {



	public WebDriver driver;
	WebActionHelperUtils utils = new WebActionHelperUtils(driver);
	String url = PropertiesDataUtils.configDataStore.get("BASE_URL");
	String environment = PropertiesDataUtils.configDataStore.get("ENVIRONMENT");
    public Homepage homepage;
    public ProductListPage productListPage;
    public ProductDetailPage productDetailPage;
    public CartPage cartPage;


	@BeforeClass
	public void setUpTest(ITestContext testContext) {
		try {
			String browserNameCheck = checkForParameterizedBrowserName(testContext);
			if (browserNameCheck.equalsIgnoreCase("DEFAULT")) {
				setDriver("DEFAULT");
			} else {
				setDriver(browserNameCheck);
			}
			this.driver = getDriver();
			homepage = new Homepage(driver);
			productListPage = new ProductListPage(driver);
			productDetailPage = new ProductDetailPage(driver);
			cartPage = new CartPage(driver);
			

		} catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeSuite
    public void setupSuite() {
        extentReportUtils.setup("Web_Report");
    }

    @AfterSuite
    public void tearDownSuite() {
        extentReportUtils.cleanup();
    }

    
    public void baseUrl() {
    	 if("QA".equalsIgnoreCase(environment))
    		 driver.get(url);
    	 else if("SOME_OTHER_ENV".equalsIgnoreCase(environment))
    		 driver.get(url);
    	 else
    		 driver.get(url);
    }


    protected String checkForParameterizedBrowserName(ITestContext testContext) {

        try {
            String browserName = runnerXmlHooks.getXmlParamValueThroughReporter("browser");
            if (null == browserName) {
                return "DEFAULT";
            } else {
                return browserName;
            }
        } catch (Exception ex) {
            return "DEFAULT";
        }
    }
    
    public void dataComparsion(SoftAssert softAssert,JSONObject data) {
    	
    	 //Only Email values are compared here, Other data can also be compared
    	  Map<String,String> homepageData = (HashMap) data.get("Homepage");
    	  Map<String,String> checkoutData = (HashMap) data.get("Checkout");
    	  Map<String,String> paymentData = (HashMap) data.get("Payment");
    	  
    	  softAssert.assertEquals(homepageData.get("Email"), checkoutData.get("Email"),"Email id's are different on home and checkout pages");
          
   
    	  
    	  System.out.println(homepageData+" "+checkoutData+" "+paymentData);
    
    }
}
