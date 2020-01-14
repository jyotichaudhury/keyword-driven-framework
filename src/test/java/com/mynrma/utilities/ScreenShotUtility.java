package com.mynrma.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtility {

	public static String captureScreenshot(WebDriver driver, String testCaseID, String status) {

		String destDir = "";

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		if(status.equalsIgnoreCase("fail")){

			destDir = "test-output/AutomationReports/Screenshots/Failures";
		}

		else if (status.equalsIgnoreCase("pass")){

			destDir = "test-output/AutomationReports/Screenshots/Success";
		}

		new File(destDir).mkdirs();

		String destFile = testCaseID+" - "+dateFormat.format(new Date()) + ".png";

		try {

			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));

		}catch (IOException e) {

			e.printStackTrace();
		}

		return destDir+"/"+destFile;

	}

}
