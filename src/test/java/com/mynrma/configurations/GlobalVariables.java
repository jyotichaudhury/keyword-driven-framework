package com.mynrma.configurations;

public class GlobalVariables {

public static final String Path_ChromeDriver = ".\\src\\test\\resources\\Drivers\\ChromeDriver\\chromedriver.exe";
	
	public static final String Path_TestData = ".\\src\\test\\java\\com\\mynrma\\dataEngine\\DataEngine.xls";
	public static final String File_TestData = "DataEngine.xls";
	public static final String Path_OR = ".\\src\\test\\java\\com\\mynrma\\configurations\\OR.txt";
    public static final String Path_ExtentReport = ".\\AutomationTestReport.html";
    public static final String Path_ExtentConfig = ".\\extent-config.xml";
    
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_PageObject =4 ;
	public static final int Col_ActionKeyword =5 ;
	public static final int Col_RunMode =1 ;
	public static final int Col_DataSet =6;
	public static final int Col_TestCaseResult =2 ;
	public static final int Col_TestStepResult =7 ;
	 
	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
}
