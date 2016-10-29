package org.kita.utils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelLib_JXL {

	public Sheet wrksheet;
	public Workbook wrkbook = null;
	public Hashtable<String, Integer> dict = new Hashtable<String, Integer>();
	public Hashtable<Integer, String> flaggedMethod = new Hashtable<Integer, String>();

	// Create a Constructor
	public ExcelLib_JXL(String ExcelSheetPath) throws BiffException,
			IOException {
		// Initialize
		wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
		// For Demo purpose the excel sheet path is hardcoded, but not
		// recommended :)
		wrksheet = wrkbook.getSheet("Sheet1");
	}

	// Returns the Number of Rows
	public int RowCount() {
		return wrksheet.getRows();
	}

	// Returns the Cell value by taking row and Column values as argument
	public String ReadCell(int column, int row) {
		return wrksheet.getCell(column, row).getContents();
	}

	public String ReadCell(String columnName, int rowNumber) {
		return ReadCell(GetCell(columnName), rowNumber);
	}

	// Create Column Dictionary to hold all the Column Names
	public void ColumnDictionary() {
		// Iterate through all the columns in the Excel sheet and store the
		// value in Hashtable
		for (int col = 0; col < wrksheet.getColumns(); col++) {
			dict.put(ReadCell(col, 0), col);
		}
	}

	// Read Column Names
	public int GetCell(String colName) {
		try {
			int value;
			value = ((Integer) dict.get(colName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}

	// Get all the Flagged Methods from Excel sheet
	public Hashtable<Integer, String> GetFlaggedMethods(String ColumnName) {
		try {
			int methodcount = 1; // Keycount flag will track of method count
			for (int row = 0; row < RowCount(); row++) {
				if (ReadCell(ColumnName, row).equals("Y")) {
					flaggedMethod.put(methodcount, ReadCell("Function", row)
							+ ";" + ReadCell("ExcelName", row));
					methodcount++;
					System.out.println("6");
				}
			}
		} catch (Exception e) {

		}
		return flaggedMethod;
	}
}
