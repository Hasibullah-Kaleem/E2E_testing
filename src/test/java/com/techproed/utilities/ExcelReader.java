package com.techproed.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    Workbook workbook;
    Sheet sheet;

    // Constructor that loads the specified Excel file and sheet
    public ExcelReader(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet = workbook.getSheet(sheetName);
    }

    // Method to get data from a specific cell
    public String getCellData(int row, int column) {
        Cell cell = sheet.getRow(row).getCell(column);
        return cell.toString();
    }

    // Method to get the total number of rows in the sheet
    public int rowCount() {
        return sheet.getLastRowNum();
    }
}