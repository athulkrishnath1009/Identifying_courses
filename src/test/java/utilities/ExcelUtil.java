package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    private Workbook workbook;

    // Constructor to initialize the workbook with the given Excel file path
    public ExcelUtil(String excelFilePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
        this.workbook = new XSSFWorkbook(fileInputStream);
    }

    // Method to get cell data from a specific sheet, row, and column
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        
        if (sheet == null) {
            return "";
        }
        
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }

        // Returning cell data based on its type
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    // Method to get the row count of a specific sheet
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return (sheet != null) ? sheet.getPhysicalNumberOfRows() : 0;
    }

    // Static method to write data to an Excel file
    public static void writeToExcel(String filePath, String sheetName, String[] headers, String[][] data) throws IOException {
        Workbook workbook;
        Sheet sheet;

        // Check if the file exists
        if (Files.exists(Paths.get(filePath))) {
            workbook = new XSSFWorkbook(filePath);
        } else {
            workbook = new XSSFWorkbook();
        }

        // Create a new sheet or overwrite if it exists
        if (workbook.getSheet(sheetName) != null) {
            sheet = workbook.getSheet(sheetName);
            workbook.removeSheetAt(workbook.getSheetIndex(sheet));
        }
        sheet = workbook.createSheet(sheetName);

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Write data rows
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j]);
            }
        }

        // Write the output to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    // Method to close the workbook
    public void close() throws IOException {
        workbook.close();
    }
}
