package com.demo.ipagerepo;

import org.openqa.selenium.By;

public interface ICartPage {
	
	
	By PRODUCT_TITLE = By.className("cart__product-title");
	By PRODUCT_QTY = By.xpath("//input[@type='number']");
	By CART_COUNT = By.id("CartCount");
    By PRODUCT_PRICE = By.xpath("//div[contains(@class,'cart__price')]");
}
