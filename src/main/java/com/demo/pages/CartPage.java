package com.demo.pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.demo.ipagerepo.ICartPage;
import com.demo.web.utils.WebActionHelperUtils;

public class CartPage  extends WebActionHelperUtils implements ICartPage{
	
	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void changeCartQTY(SoftAssert softAssert,int count) {
		 
		 mouseHover(ICartPage.PRODUCT_QTY);
		 safeType(ICartPage.PRODUCT_QTY,String.valueOf(count));
		 softAssert.assertEquals(getText(ICartPage.CART_COUNT),String.valueOf(count));

		 
	}

}
