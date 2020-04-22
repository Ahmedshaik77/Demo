package com.demo.web.tests;



import org.json.simple.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.demo.web.data.CreditCardDP;
import com.demo.web.specs.BaseTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PurchaseTest1 extends BaseTest {
	
	JSONObject data = new JSONObject();
	
	
    @BeforeMethod
    public void set(Method method, ITestResult results) {
        extentReportUtils.registerTestMethod(method);
        extentReportUtils.onTestStart(results);
    }

    @AfterMethod
    public void tear(ITestResult results) {
        extentReportUtils.screenshotAnyCase(results, driver, "Web");
        extentReportUtils.captureStatus(results);

    }

    @AfterClass
    public void teardown() {
       driver.close();
    }

    @Test(description = "Payment Success")
    public void purchaseCase() {
         SoftAssert softAssert = new SoftAssert();
         baseUrl();
         extentReportUtils.sequenceScreenShot(driver,"Web","WebSite Loaded");
         homepage.loginInWebSite();
         extentReportUtils.sequenceScreenShot(driver,"Web","Logged Into website");
         homepage.searchAddProduct(softAssert,"T-SHIRTS");
         extentReportUtils.sequenceScreenShot(driver,"Web","Searched Items");
         productListPage.selectItemsFromList();
         extentReportUtils.sequenceScreenShot(driver,"Web","Navigate To Cart Page");
         productDetailPage.addProductToCart(data,"Silver","M");
         productDetailPage.clickCart();
         extentReportUtils.sequenceScreenShot(driver,"Web","On Cart Page");
         softAssert.assertAll();   
    }



}
