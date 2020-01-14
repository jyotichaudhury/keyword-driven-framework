package com.mynrma.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import static com.mynrma.executionEngine.DriverScript.logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mynrma.configurations.GlobalVariables;

public class ExcelUtility {

	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFCell Cell;
	private static HSSFRow Row;


	public static void setExcelFile(String Path) throws Exception {

		try {

			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new HSSFWorkbook(ExcelFile);

		} catch(Exception e) {

			logger.info("Class ExcelUtility -> Method setExcelFile -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;
		}
	}


	public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{
		try {

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		}catch (Exception e){

			logger.info("Class ExcelUtility -> Method getCellData -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;

			return"";

		}
	}


	public static int getRowCount(String SheetName){

		int number = 0;
		try {

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			number=ExcelWSheet.getLastRowNum()+1;

		} catch(Exception e) {

			logger.info("Class ExcelUtility -> Method getRowCount -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;
		}

		return number;

	}


	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{

		int iRowNum = 0;

		try {

			//ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int rowCount = ExcelUtility.getRowCount(SheetName);

			for (; iRowNum<rowCount; iRowNum++){

				if  (ExcelUtility.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){

					break;
				}

			}

		} catch(Exception e) {

			logger.info("Class ExcelUtility -> Method getRowContains -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;
		}
		return iRowNum;

	}


	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{

		try {

			for(int i=iTestCaseStart;i<=ExcelUtility.getRowCount(SheetName);i++){

				if(!sTestCaseID.equals(ExcelUtility.getCellData(i, GlobalVariables.Col_TestCaseID, SheetName))){
					int number = i;
					return number;
				}
			}

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int number=ExcelWSheet.getLastRowNum()+1;

			return number;

		} catch(Exception e) {

			logger.info("Class ExcelUtility -> Method getTestStepsCount -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;

			return 0;
		}
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception{

		try{

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			Row  = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

			FileOutputStream fileOut = new FileOutputStream(GlobalVariables.Path_TestData);

			ExcelWBook.write(fileOut);

			fileOut.close();

			ExcelWBook = new HSSFWorkbook(new FileInputStream(GlobalVariables.Path_TestData));

		}catch(Exception e){

			logger.info("Class ExcelUtility -> Method setCellData -> Exception Desc: "+e.getMessage());

			//DriverScript.bResult = false;

		}
	}
}
