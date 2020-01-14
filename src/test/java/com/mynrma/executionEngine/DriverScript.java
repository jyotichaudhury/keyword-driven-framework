package com.mynrma.executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.mynrma.configurations.ActionKeywords;
import com.mynrma.configurations.GlobalVariables;
import com.mynrma.utilities.ExcelUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class DriverScript {

	public static Logger logger = null;

	public static ExtentReports extent;

	public static ExtentTest test;

	public static Properties OR;

	public static ActionKeywords actionKeywords;

	public static Method methods[];

	public static String testCaseID;

	public static String runMode;

	public static int testStep;

	public static int testLastStep;

	public static String sActionKeyword;

	public static String sPageObject;

	public static String sDataSet;

	public static boolean bResult;
	
	public DriverScript() {

		actionKeywords = new ActionKeywords();

		methods = actionKeywords.getClass().getMethods();
	}

	@Test
	public void scriptEngine() throws Exception{

		logger = Logger.getLogger("rootLogger");

		extent = new ExtentReports(GlobalVariables.Path_ExtentReport,true);
		
		extent.loadConfig(new File(GlobalVariables.Path_ExtentConfig));
		
		ExcelUtility.setExcelFile(GlobalVariables.Path_TestData);

		String Path_OR = GlobalVariables.Path_OR;

		FileInputStream fs = new FileInputStream(Path_OR);

		OR = new Properties();

		OR.load(fs);

		DriverScript startEngine = new DriverScript();

		startEngine.executeTestCase();

		extent.flush();
	}

	public void executeTestCase() throws Exception {

		int totalTestCases = ExcelUtility.getRowCount(GlobalVariables.Sheet_TestCases);

		for(int testCaseNum = 1; testCaseNum <totalTestCases; testCaseNum++ ) {

			bResult = true;

			testCaseID = ExcelUtility.getCellData(testCaseNum, GlobalVariables.Col_TestCaseID, GlobalVariables.Sheet_TestCases);

			runMode = ExcelUtility.getCellData(testCaseNum, GlobalVariables.Col_RunMode, GlobalVariables.Sheet_TestCases);

			if(runMode.equalsIgnoreCase("Yes")) {

				test = extent.startTest(testCaseID);
				
				testStep = ExcelUtility.getRowContains(testCaseID, GlobalVariables.Col_TestCaseID, GlobalVariables.Sheet_TestSteps);

				testLastStep = ExcelUtility.getTestStepsCount(GlobalVariables.Sheet_TestSteps, testCaseID, testStep);

				bResult = true;

				for(; testStep < testLastStep; testStep++) {

					sActionKeyword = ExcelUtility.getCellData(testStep, GlobalVariables.Col_ActionKeyword, GlobalVariables.Sheet_TestSteps);

					sPageObject = ExcelUtility.getCellData(testStep, GlobalVariables.Col_PageObject, GlobalVariables.Sheet_TestSteps);

					sDataSet = ExcelUtility.getCellData(testStep, GlobalVariables.Col_DataSet, GlobalVariables.Sheet_TestSteps);

					executeAction(testCaseID);

					if(bResult==false){

						ExcelUtility.setCellData(GlobalVariables.KEYWORD_FAIL,testCaseNum,GlobalVariables.Col_TestCaseResult,GlobalVariables.Sheet_TestCases);

						ActionKeywords.CloseBrowser("", "", "");
						
						extent.endTest(test);

						break;

					}
				}

				if(bResult==true){

					ExcelUtility.setCellData(GlobalVariables.KEYWORD_PASS,testCaseNum,GlobalVariables.Col_TestCaseResult,GlobalVariables.Sheet_TestCases);

					ActionKeywords.CloseBrowser("", "", "");
					
					extent.endTest(test);

				}
			}
		}

	}

	public void executeAction(String testCaseID) throws Exception{
			
		for(int i=0; i<=methods.length; i++) {

			if(methods[i].getName().equals(sActionKeyword)){

				methods[i].invoke(actionKeywords, sPageObject, sDataSet, testCaseID);

				if(bResult==true){

					ExcelUtility.setCellData(GlobalVariables.KEYWORD_PASS, testStep, GlobalVariables.Col_TestStepResult, GlobalVariables.Sheet_TestSteps);
					
					break;

				} else{

					ExcelUtility.setCellData(GlobalVariables.KEYWORD_FAIL, testStep, GlobalVariables.Col_TestStepResult, GlobalVariables.Sheet_TestSteps);
			        
					break;
				}
			}
		}
		
	}

}
