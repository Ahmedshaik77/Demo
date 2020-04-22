package com.demo.generic.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements IDriver {
    public WebDriver init(String browserName) {

        String pathToDriver = getDriverPath(browserName);

        if (null != browserName) {

            switch (browserName) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",
                            pathToDriver);
                    return new ChromeDriver();
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", pathToDriver);
                    return new FirefoxDriver();

                default:
                    System.setProperty("webdriver.chrome.driver", pathToDriver);
                    return new ChromeDriver();
            }
        } else {
            System.setProperty("webdriver.chrome.driver",
                    pathToDriver);
            return new ChromeDriver();
        }
    }

    
    private String getDriverPath(String browserName) {

        String osData = System.getProperty("os.name").toLowerCase().split("\\s")[0];
        if (null != osData) {
            if ("mac".equalsIgnoreCase(osData)) {
                return "./drivers/" + osData + "_" + browserName;

            } else if ("nux".contains(osData) || "nix".contains(osData)) {
                return "./drivers/linux_" + browserName;
            } else if (osData.contains("win")) {
                return "./drivers/" + osData + "_" + browserName + ".exe";
            }
        }
        return null;
    }
}


