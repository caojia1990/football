package com.eastng.football.web.service;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {

	public static void main(String[] args) throws BiffException, IOException {
		
		String fileName = "C:/Users/lenovo/Desktop/match.xls";
		File file = new File(fileName);
		
		Workbook workbook = Workbook.getWorkbook(file);
		
		Sheet sheet = workbook.getSheet(1);
		
		for(int i = 0;i<sheet.getRows();i++){
			for(int j = 0;j<sheet.getColumns();j++){
				Cell cell = sheet.getCell(j, i);
				System.out.println(cell.getContents());
			}
		}
	}
}
