package com.demo.ipagerepo;

import org.openqa.selenium.By;

public interface ICheckoutPage {
	
	By SHIPPING_DETAILS = By.xpath("//span[text()='shipping details']");
	By AMOUNT= By.className("text-amount-amount");
	By EMAIL_TEXT = By.xpath("//div[text()='Email']/../div[2]");
	By CHECKOUT_BTN = By.className("button-main-content");
	By CREDIT_CARD_LINK =By.xpath("//div[contains(text(),'Pay with Visa')]");
    By COUPON_TEXT =By.xpath("//span[contains(@class,'pull-right')]");
    By EMAIL = By.xpath("//input[@type='email']");
    By CARD = By.name("cardnumber");
    By DATE = By.xpath("//input[@placeholder='MM / YY']");
    By CVV = By.xpath("//input[@placeholder='123']");
    By OTP = By.xpath("//input[@type='password']");
    By OK = By.name("ok");
    By THANK_YOU_MESSAGE = By.xpath("//span[text()='Thank you for your purchase.']");
    By FAILED_MESSAGE = By.xpath("//span[text()='Transaction failed']");
}
