package com.demo.pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;

import com.demo.ipagerepo.IProductDetailPage;
import com.demo.ipagerepo.IProductListPage;
import com.demo.web.utils.WebActionHelperUtils;

public class ProductDetailPage extends WebActionHelperUtils implements IProductListPage{
    WebDriver driver;
    
	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	public void addProductToCart(JSONObject data,String color,String size) {
		
		   JSONObject cartData = new JSONObject();
		   if(color.length()>1)
			    selectItemFromDD(IProductDetailPage.COLOR_DD,color);
		   if(color.length() > 1)
		        selectItemFromDD(IProductDetailPage.SIZE_DD, size);
		   
		   cartData.put("Title",getText(IProductDetailPage.PRODUCT_TITLE));
		   cartData.put("Price",getText(IProductDetailPage.PRODUCT_PRICE));
		   
		   data.put("Cart",cartData);
		   
		   safeClick(IProductDetailPage.ADD_TO_CART_BTN);
		   safeClick(IProductDetailPage.CART_CLOSE);
		
	}
	
	
	public void clickCart() {
		   sleepTime(3000);
		   safeClick(IProductDetailPage.CART);
	}
	

}
