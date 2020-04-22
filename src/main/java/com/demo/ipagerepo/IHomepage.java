package com.demo.ipagerepo;

import org.openqa.selenium.By;

public interface IHomepage {
	
	
	By PASSWORD_LINK = By.partialLinkText("ENTER USING");
	By PASSWORD_TXT_BOX = By.id("Password");
	By PASSWORD_ENTER = By.xpath("//button[contains(text(),'Enter')]");
	By SEARCH_ELES = By.xpath("//div[@class='site-header__icons-wrapper']/button");
	By SEARCH_TXT_BOX = By.name("q");
	By SEARCH_ENTER = By.xpath("//button[@type='submit']");
	By SEARCH_RESULT_TEXT =By.xpath("//h1[@class='h2']");

}
