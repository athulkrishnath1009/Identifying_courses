package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
    
    // DataProvider method to supply form data for tests
    @DataProvider(name = "formData")
    public Object[][] getGiftCardData() throws Exception {
        // Path to the Excel file containing test data
        String excelFilePath = "testData/inputFile.xlsx";
        // Name of the sheet in the Excel file
        String sheetName = "Sheet1";
        // Creating an instance of ExcelUtil to read data from the Excel file
        ExcelUtil excelUtil = new ExcelUtil(excelFilePath);
        
        // Number of rows of data to read (excluding header row)
        int rowCount = 1;
        // Initializing a 2D array to store the data
        Object[][] data = new Object[1][8];
        
        // Looping through the rows and columns to read data from the Excel file
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < 8; j++) {
                // Storing the data in the array
                data[i - 1][j] = excelUtil.getCellData(sheetName, i, j);
            }
        }
        
        // Closing the Excel file
        excelUtil.close();
        // Returning the data to the test method
        return data;
    }
}
