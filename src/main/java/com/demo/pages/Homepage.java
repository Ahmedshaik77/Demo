package com.demo.pages;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.demo.generic.utils.PropertiesDataUtils;
import com.demo.ipagerepo.ICheckoutPage;
import com.demo.ipagerepo.IHomepage;
import com.demo.web.utils.WebActionHelperUtils;

public class Homepage extends WebActionHelperUtils implements IHomepage{
	
	WebDriver driver;
	public Homepage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	public void loginInWebSite(){
		safeClick(IHomepage.PASSWORD_LINK);
		safeType(IHomepage.PASSWORD_TXT_BOX,propertyDataUtils.configDataStore.get("PASSWORD"));
		safeClick(IHomepage.PASSWORD_ENTER);
		
	}
	
	public void searchAddProduct(SoftAssert softAssert,String productName) {
		List<WebElement> btns = getWebElementList(driver,IHomepage.SEARCH_ELES);
		btns.get(0).click();
		safeType(IHomepage.SEARCH_TXT_BOX, productName);
		safeClick(IHomepage.SEARCH_ENTER);
		softAssert.assertTrue(getText(IHomepage.SEARCH_RESULT_TEXT).contains(productName.toUpperCase()));	
	}
	
	
	

	
    
	
	

}
