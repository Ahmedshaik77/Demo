package com.demo.generic.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver implements IDriver {

    DesiredCapabilities caps;
    String remoteHuburl=PropertiesDataUtils.configDataStore.get("WEB_GRID_IP");

    @Override
    public WebDriver init(String browserName) {

        if (browserName != null) {

            switch (browserName) {
                case "firefox":
                    try {
                        return new RemoteWebDriver(new URL(remoteHuburl), caps.firefox());
                    } catch (MalformedURLException malformedUrlEx) {

                        malformedUrlEx.getCause().getMessage();
                        malformedUrlEx.printStackTrace();
                    }
                case "chrome":
                    try {
                        return new RemoteWebDriver(new URL(remoteHuburl), caps.chrome());
                    } catch (MalformedURLException malformedUrlEx) {

                        malformedUrlEx.getCause().getMessage();
                        malformedUrlEx.printStackTrace();
                    }
                case "ie":
                    try {
                        return new RemoteWebDriver(new URL(remoteHuburl), caps.internetExplorer());
                    } catch (MalformedURLException malformedUrlEx) {

                        malformedUrlEx.getCause().getMessage();
                        malformedUrlEx.printStackTrace();

                    }
                default:
                    try {
                        return new RemoteWebDriver(new URL(remoteHuburl), caps.chrome());
                    } catch (MalformedURLException malformedUrlEx) {

                        malformedUrlEx.getCause().getMessage();
                        malformedUrlEx.printStackTrace();
                    }
            }
            return null;
        } else {
            return null;
        }
    }

}
