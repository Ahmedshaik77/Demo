package com.demo.ipagerepo;

import org.openqa.selenium.By;

public interface IProductDetailPage {

	
	 By ADD_TO_CART_BTN = By.name("add");
	 By BUY_NOW = By.xpath("//button[contains(text(),'Buy it now')]");
	 By COLOR_DD = By.id("SingleOptionSelector-0");
	 By SIZE_DD = By.id("SingleOptionSelector-1");
	 By PRODUCT_TITLE = By.className("product-single__title");
	 By PRODUCT_PRICE = By.className("price");
	 By CART_CLOSE = By.className("cart-popup__close");
	 By CART = By.xpath("//a[contains(@class,'site-header__icon')]");
}
