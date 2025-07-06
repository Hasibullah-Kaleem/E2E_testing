package com.techproed.utilities;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for interacting with Excel files using the Apache POI library.
 */
public class ExcelUtils {
    private Workbook workBook;
    private Sheet sheet;
    private String path;

    /**
     * Constructor to open and access an Excel file.
     *
     * @param path      Path to the Excel file
     * @param sheetName Name of the sheet to access
     *
     * Usage:
     * ExcelUtils excelUtils = new ExcelUtils("filePath.xlsx", "Sheet1");
     */
    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        try {
            // Open the Excel file
            FileInputStream fileInputStream = new FileInputStream(path);
            // Access the workbook
            workBook = WorkbookFactory.create(fileInputStream);
            // Get the worksheet
            sheet = workBook.getSheet(sheetName);
            // Verify if the sheet exists
            Assert.assertNotNull(sheet.toString(), "Worksheet: \"" + sheetName + "\" not found\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves data from the Excel file as a list.
     * This method fetches data as Strings and returns it in a Map format.
     *
     * @return A list of data from the Excel file (List<Map<String, String>>)
     *
     * Usage:
     * List<Map<String, String>> data = excelUtils.getDataList();
     */
    public List<Map<String, String>> getDataList() {
        // Retrieve all column names
        List<String> columns = getColumnsNames();
        // Data to be returned
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            // Retrieve each row
            Row row = sheet.getRow(i);
            // Create a map for each row using column names as keys and cell values as values
            Map<String, String> rowMap = new HashMap<>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    /**
     * Returns the number of columns in a specific row.
     *
     * @return Number of columns in the given row
     *
     * Usage:
     * int columnCount = excelUtils.columnCount();
     */
    public int columnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    /**
     * Returns the last row number. Indexing starts from 0.
     *
     * @return Last row number
     *
     * Usage:
     * int rowCount = excelUtils.rowCount();
     */
    public int rowCount() {
        return sheet.getLastRowNum() + 1; // Adding 1 to get the actual row count
    }

    /**
     * Writes data to a specific cell by providing row and column indices.
     *
     * @param value  Value to be written
     * @param rowNum Row index
     * @param colNum Column index
     *
     * Usage:
     * excelUtils.setCellData("New Value", 2, 3);
     */
    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            // Get or create the row
            row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            // Get or create the cell
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);

            // Write changes to the file
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the value of a specific cell.
     *
     * @param rowNum Row index
     * @param colNum Column index
     * @return Value of the cell
     *
     * Usage:
     * String cellData = excelUtils.getCellData(1, 2);
     */
    public String getCellData(int rowNum, int colNum) {
        try {
            // Get the row
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return ""; // Return an empty string if the row is null
            }

            // Get the cell
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return ""; // Return an empty string if the cell is null
            }

            // Return the value of the cell
            return cell.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all data as a two-dimensional array.
     *
     * @return Data as a two-dimensional array
     *
     * Usage:
     * String[][] dataArray = excelUtils.getDataArray();
     */
    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                data[i][j] = getCellData(i, j);
            }
        }
        return data;
    }

    /**
     * Reads the first row and retrieves all column names.
     *
     * @return List of column names
     *
     * Usage:
     * List<String> columns = excelUtils.getColumnsNames();
     */
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();
        for (Cell cell : sheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    /**
     * Returns all data as a two-dimensional array, excluding the first row.
     * This is useful for data provider formats.
     *
     * @return Two-dimensional array of data excluding the first row
     *
     * Usage:
     * String[][] dataWithoutFirstRow = excelUtils.getDataArrayWithoutFirstRow();
     */
    public String[][] getDataArrayWithoutFirstRow() {
        String[][] data = new String[rowCount() - 1][columnCount()];
        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }
}