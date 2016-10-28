package org.kita.utils;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelLib_JXL {

	Workbook workbook = null;
	Sheet sheet = null;

	public ExcelLib_JXL(String path) {
		try {
			workbook = Workbook.getWorkbook(new File(path));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String GetCellValue(int colNumber, int rowNumber) {
		return workbook.getSheet(0).getCell(colNumber, rowNumber).getContents();
	}
}
