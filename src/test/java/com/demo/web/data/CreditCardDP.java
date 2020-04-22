package com.demo.web.data;

import org.testng.annotations.DataProvider;

public class CreditCardDP {
	
	
	@DataProvider
	public static Object[][] creditCardDP(){
		return new Object[][] {
			 {"4811111111111114","02/21","123","112233"},
			 {"4911111111111113","02/21","123","112233"}};
	}

}
