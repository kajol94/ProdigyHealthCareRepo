package com.bk_helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.bk_helper.logger.LoggerHelper;
import com.bk_helper.resource.ResourceHelper;

public class ExcelHelper {
	String excelFilePath = "\\src\\test\\resources\\dataSheet\\testData.xlsx";

	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);

	@DataProvider(name = "emailloginData")
	public Object[][] emailloginData() {
		Object[][] obj = getExcelData(ResourceHelper.getResourcePath(excelFilePath), "InvalidEmailLoginData");
		return obj;
	}

	public String getData(String filepath, int rowNum, String colName, String sheetName) {

		String data = null;
		FileInputStream file;

		try {
			file = new FileInputStream(new File(filepath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum();

			int totalCol = sheet.getRow(0).getLastCellNum();

			for (int i = 0; i < totalCol; i++) {
				String excelColName = sheet.getRow(0).getCell(i).getStringCellValue().trim();
				if (excelColName.equals(colName.trim())) {

					Cell cell = sheet.getRow(rowNum).getCell(i);

					DataFormatter dataFormatter = new DataFormatter();
					String formatterValue = dataFormatter.formatCellValue(cell);
					data = formatterValue;
					data = data.trim();
					break;
				}

			}

			file.close();
			return data;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Object[][] getExcelSheetData(String fileLocation, String sheetName) {

		Object[][] dataSet = null;
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum();

			int totalColumn = sheet.getRow(0).getLastCellNum();

			dataSet = new Object[totalRow][totalColumn];

			for (int i = 1; i <= totalRow; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < totalColumn; j++) {
					dataSet[i - 1][j] = row.getCell(j).getStringCellValue();
				}
			}

			file.close();
			return dataSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object[][] getExcelData(String excelLocation, String sheetName) {

		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum();
			System.out.println(totalRow);
			// count active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();

			dataSets = new Object[totalRow][totalColumn];

			// Iterate Through each Rows one by one.
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				// for Every row , we need to iterator over columns
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					if (cell.getStringCellValue().contains("Start")) {
						i = 0;
						break;
					}

					switch (cell.getCellType()) {
					case STRING:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i - 1][j++] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i - 1][j++] = cell.getBooleanCellValue();
					case FORMULA:
						dataSets[i - 1][j++] = cell.getCellFormula();
						break;

					default:
						System.out.println("no matching enum data  type found");
						break;
					}
				}
			}
			file.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus) {
		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum() + 1;
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if (ce.contains(testCaseName)) {
					r.createCell(1).setCellValue(testStatus);
					file.close();
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
