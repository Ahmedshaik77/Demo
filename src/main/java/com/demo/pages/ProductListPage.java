package com.demo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demo.ipagerepo.IProductListPage;
import com.demo.web.utils.WebActionHelperUtils;

public class ProductListPage extends WebActionHelperUtils implements IProductListPage {
    WebDriver driver;
	public ProductListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void selectItemsFromList() {
		List<WebElement> items = getWebElementList(driver,IProductListPage.PRODUCT_LIST);
		items.get(0).click();
		
	}
	
	

}
