package com.demo.generic.utils;

public class DriverProvider {
	
	 public IDriver getDriver(String typeOfDriver) {


	        if (typeOfDriver != null) {
	            switch (typeOfDriver) {
	                case "local":
	                    return new LocalDriver();
	                case "remote":
	                    return new RemoteDriver();
	                 
	                default:
	                    return new LocalDriver();
	            }
	        } else {
	            return null;
	        }
	    }

}
