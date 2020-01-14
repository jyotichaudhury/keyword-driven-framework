package com.mynrma.configurations;

import static com.mynrma.executionEngine.DriverScript.OR;
import static com.mynrma.executionEngine.DriverScript.logger;
import static com.mynrma.executionEngine.DriverScript.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.mynrma.executionEngine.DriverScript;
import com.mynrma.utilities.CryptPasswordUtility;
import com.mynrma.utilities.ErrorHandlingUtility;
import com.mynrma.utilities.ScreenShotUtility;
import com.relevantcodes.extentreports.LogStatus;

public class ActionKeywords {

	public static WebDriver driver;
	
	public static String sError;
	
	public static String sTruncatedError;
	
	public static int iErrorTruncatedLimit;
	
	public static String sScreenshotPath;
	
	public static String sImage;

	public static void OpenBrowser(String object, String data, String testCaseID) {

		try {

			System.setProperty("webdriver.chrome.driver", GlobalVariables.Path_ChromeDriver);

			driver = new ChromeDriver();

			driver.manage().window().maximize();
			
			test.log(LogStatus.PASS, "Open "+data+" Browser", data+" Browser instance created successfully");

		} catch(Exception e) {

			logger.info("Unable to open the browser ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError =  "Unable to open the browser ---- "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
			sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
			test.log(LogStatus.FAIL, "Open "+data+" Browser", sTruncatedError+sImage);
						
		}
	}

	public static void Navigate(String object, String data, String testCaseID) {

		try {

			driver.get(data);
			
			test.log(LogStatus.PASS, "Navigate to "+data, "Navigated to URL "+"'"+data+"'");

		} catch(Exception e) {

			logger.info("Unable to navigate to the url ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError =  "Unable to navigate to the url ---- "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
            sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
			test.log(LogStatus.FAIL, "Navigate to "+data, sTruncatedError+sImage);
		}

	}

	public static void Input(String object, String data, String testCaseID) {
		
		String encryptedPass;
	
		try {

			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			
			if(object.contains("Password")) {
				
				encryptedPass = CryptPasswordUtility.cryptPassword(data);
				
				test.log(LogStatus.PASS, "Enter text "+encryptedPass+" in '"+object+"'", object+" = "+encryptedPass);
			}
			
			else
			
			test.log(LogStatus.PASS, "Enter text '"+data+"' in '"+object+"'", object+" = "+data);

		} catch(Exception e) {

			logger.info("Unable to enter text in "+object+" ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError =  "Unable to enter text in "+object+" ---- "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
            sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
            if(object.contains("Password")) {
				
				encryptedPass = CryptPasswordUtility.cryptPassword(data);
				
				test.log(LogStatus.FAIL, "Enter text "+encryptedPass+" in '"+object+"'", sTruncatedError+sImage);
			}
			
			else
			
			test.log(LogStatus.FAIL, "Enter text '"+data+"' in '"+object+"'", sTruncatedError+sImage);
		}
	}

	public static void Wait(String object, String data, String testCaseID) throws Exception {

		try {

			long waitTime = Integer.parseInt(data) * 1000;

			Thread.sleep(waitTime);
			
			test.log(LogStatus.PASS, "Wait for "+data+" seconds", data+" seconds Wait completed");

		} catch(Exception e) {

			logger.info("Wait action failed ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError =  "Wait action failed ----  "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
            sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
			test.log(LogStatus.FAIL, "Wait for "+data+" seconds", sTruncatedError+sImage);
		}
	}

	public static void Click(String object, String data, String testCaseID) {

		try {

			driver.findElement(By.xpath(OR.getProperty(object))).click();
			
			test.log(LogStatus.PASS, "Click on '"+object+"'", "Clicked on '"+object+"'");

		} catch(Exception e) {

			logger.info("Unable to click on "+object+" ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError =  "Unable to click on "+object+" ---- "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
            sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
			test.log(LogStatus.FAIL, "Click on '"+object+"'", sTruncatedError+sImage);
		}
	}

	public static void Select(String object, String data, String testCaseID) {

		try {

			Select select = new Select(driver.findElement(By.xpath(OR.getProperty(object))));

			select.selectByVisibleText(data);

		} catch(Exception e) {

			logger.info("Unable to select "+object+" ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			//errorCause = "Unable to click on "+object+" ---- "+e.getMessage();
			
		}
	}

	public static void CloseBrowser(String object, String data, String testCaseID) {

		try {

			driver.quit();
			
			test.log(LogStatus.PASS, "Close "+data+" Browser instance", data+" Browser instance closed successfully");

		} catch(Exception e) {

			logger.info("Unable to close the browser ---- "+e.getMessage());

			DriverScript.bResult = false;
			
			sError = "Unable to click on "+object+" ---- "+e.getMessage();
			
			sTruncatedError = (new ErrorHandlingUtility(sError)).truncateErrorMessage();
			
            sScreenshotPath = ScreenShotUtility.captureScreenshot(driver, testCaseID, "fail");
			
			sImage = test.addScreenCapture(sScreenshotPath);
			
			test.log(LogStatus.FAIL, "Close "+data+" Browser instance", sTruncatedError+sImage);
		}

	}
	
}
