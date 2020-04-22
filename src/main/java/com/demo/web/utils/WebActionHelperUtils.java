package com.demo.web.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.generic.utils.PropertiesDataUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebActionHelperUtils extends DriverSetup {

    public WebDriver driver;
    private final Logger BASE_LOGGER= LoggerFactory.getLogger(WebActionHelperUtils.class);
    public PropertiesDataUtils propertyDataUtils;
    public WebActionHelperUtils(WebDriver driver) {

        this.driver = driver;
        propertyDataUtils = PropertiesDataUtils.getInstance(System.getProperty("user.dir")+"//configurator.properties");
        
    }


    public void sleepTime(int milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {

		}
	}
	public void safeClick(By element) {

		try {
			waitForElementToBeClickAble(element, 20);
			driver.findElement(element).click();
			BASE_LOGGER.info("Safeclick operation has been performed for the locator : " + String.valueOf(element));

		} catch (Exception e) {

			 e.printStackTrace();
		}
	}

	public List<WebElement> getElements(By elements) {

		try {
			List<WebElement> allElements = driver.findElements(elements);
			BASE_LOGGER.info("GetElements operation has been performed for the locator : " + String.valueOf(elements));
			return allElements;
		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("GetElements operation has been failed for the locator : " + String.valueOf(elements)
					+ " with the exception i.e : " + exceptionData);
			return null;

		}

	}
	
	public void mouseHover(By element) {
		 Actions action = new Actions(driver);
		 WebElement textBox = driver.findElement(element);
	     action.moveToElement(textBox).perform();
		 
	}




	public void waitForWebElementsToBeDisplayed(By elements, int timeOuts) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOuts);
			wait.until(ExpectedConditions.visibilityOfAllElements(getElements(elements)));
			BASE_LOGGER.info("waitForWebElementsToBeDisplayed operation has been performed for the locator : "
					+ String.valueOf(elements));
		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("waitForWebElementsToBeDisplayed operation has been failed for the locator : "
					+ String.valueOf(elements) + " with the exception i.e : " + exceptionData);
		}
	}

	public void waitForElementToBeClickAble(By element, int timeOutSeconds) {

		try {
			WebDriverWait waitForElement = new WebDriverWait(driver, timeOutSeconds);
			waitForElement.until(ExpectedConditions.elementToBeClickable(element));
			BASE_LOGGER.info("waitForElementToBeClickAble operation has been performed for the locator : "
					+ String.valueOf(element));
		} catch (Exception e) {
			String exceptionData = e.getCause().getMessage();
			BASE_LOGGER.error("waitForElementToBeClickAble operation has been failed for the locator : "
					+ String.valueOf(element) + " with the exception i.e : " + exceptionData);

		}
	}
	
	public void selectItemFromDD(By element,String name) {
	    waitForElement(driver,10,element);
	    Select dd = new Select(driver.findElement(element));
	    dd.selectByVisibleText(name);
		
	}

	public void waitForElementToBeDisplayed(By element, int timeOuts) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOuts);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			BASE_LOGGER.info("waitForElementToBeDisplayed operation has been performed for the locator : "
					+ String.valueOf(element));

		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("waitForElementToBeDisplayed operation has been failed for the locator : "
					+ String.valueOf(element) + " with the exception i.e : " + exceptionData);

		}
	}

	public void safeType(By element, String textToBeEntered) {
		try {
			waitForWebElementsToBeDisplayed(element,10);
			driver.findElement(element).sendKeys(textToBeEntered);
			BASE_LOGGER.info(
					"enterTextIntoElement operation has been performed for the locator : " + String.valueOf(element));

		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("enterTextIntoElement operation has been failed for the locator : "
					+ String.valueOf(element) + " with the exception i.e : " + exceptionData);

		}
	}

	public String getText(By element) {

		try {

			String elementText = driver.findElement(element).getText();
			BASE_LOGGER.info("getText operation has been performed for the locator : " + String.valueOf(element));
			return elementText;
		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("EnterTextIntoElement operation has been failed for the locator : "
					+ String.valueOf(element) + " with the exception i.e : " + exceptionData);
			return null;
		}
	}
	
	public List<WebElement> getWebElementList(WebDriver driver,By by) {
 		boolean elementPresentFlag = false;
 		if (elementPresentFlag = elementPresent(driver, by, 5)) {
 			return driver.findElements(by);
 		}		
 		return null;
 	}

	public String getAttribute(By element, String attribute) {
		try {
			String attributeData = driver.findElement(element).getAttribute(attribute);
			BASE_LOGGER.info("GetAttribute operation has been performed for the locator : " + String.valueOf(element));
			return attributeData;
		} catch (Exception e) {
			String exceptionData = e.getCause().getMessage();
			BASE_LOGGER.error("GetAttribute operation has been failed for the locator : " + String.valueOf(element)
					+ " with the exception i.e : " + exceptionData);
			return null;
		}
	}


	public void implicitlyWait(int timeOuts) {

		try {
			driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
			BASE_LOGGER.info("implicitlyWait operation has been performed upto : " + timeOuts + " Seconds");
		} catch (Exception e) {
			String exceptionData = e.getCause().getMessage();
			BASE_LOGGER.error("implicitlyWait operation has been failed for a timeout upto" + timeOuts
					+ " with the exception i.e : " + exceptionData);

		}
	}

	public boolean isElementPresent(By element) {
		try {
			driver.findElement(element);
			BASE_LOGGER
					.info("IsElementPresent operation has been performed for the locator : " + String.valueOf(element));
			return true;
		} catch (Exception e) {
			String exceptionData = e.getCause().getMessage();
			BASE_LOGGER.info("Element with the locator : " + String.valueOf(element) + " does not exists ");
			return false;
		}
	}

	

	public void getUrl() {

		try {

			driver.get(url);
			BASE_LOGGER.info("Successfully navigated to the URL as :  " + url);
		} catch (Exception ex) {
			String exceptionData = ex.getCause().getMessage();
			BASE_LOGGER.error("Unable to navigate to URL  : " + url + " with the error as : " + exceptionData);

		}

	}

	public void switchToFrame(int frameNo) {
		try {
			 driver.switchTo().frame(frameNo);
		}catch (StaleElementReferenceException e) {
			BASE_LOGGER.error("Frame is not attached to the page document " + e.getCause().getMessage());
		} catch (NoSuchElementException e) {
			BASE_LOGGER.error("Frame was not found in DOM " + e.getCause().getMessage());
		} catch (Exception e) {
			BASE_LOGGER.error("Element was not found " + e.getCause().getMessage());
		}
	}

	

	public void maximizeWindow() {
		try {
			driver.manage().window().maximize();
			BASE_LOGGER.info("Successfully Maximized the Window");

		} catch (Exception e) {
			BASE_LOGGER.info("Exception Occured while Maximizing the Window As : " + e.getCause().getMessage());
		}
	}
	
	public boolean elementPresent(WebDriver driver,By by, int time)  {

 		boolean elementPresentFlag = false;
 		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
 		for (int i = 0; i < time; i++) {
 			try {
 				WebElement we = null;
 				if ((we = driver.findElement(by)) != null) {
 					elementPresentFlag = true;
 					break;
 				}

 			} catch (Exception e) {
 				}
 			}
 		return elementPresentFlag;
 	}
	
	public boolean waitForElement(WebDriver driver, int time, By by) {

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int i = 0;
		boolean elementActiveFlag = false;
		try {
			long timerNow = new Date().getTime();
			for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {
				if (elementPresent(driver, by, 1)) {
					elementActiveFlag = true;
					break;
				}
				Thread.sleep(1000);
			}
			if (!elementActiveFlag) {
			
			}

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {

		}
		return elementActiveFlag;
	}




}
