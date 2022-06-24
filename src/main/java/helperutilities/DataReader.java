package main.java.helperutilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

    private static File file;
    private static FileInputStream fis;
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static XSSFCell Cell;

    public static String[][] getOrderDetails(String filePath) {
        String[][] loadData = null;
        try {
            file = new File(filePath);   //creating a new file instance
            fis = new FileInputStream(file);   //obtaining bytes from the file
            wb = new XSSFWorkbook(fis); // to create an object of XSSFWorkbook and pass the file inputstream
            sheet = wb.getSheet("order");     //creating a Sheet object to retrieve object

            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            loadData = new String[rowCount][columnCount];
            int c1 = 0, c2;
            for (int i = 1; i <= rowCount; i++, c1++) {
                c2 = 0;
                for (int j = 0; j < columnCount; j++, c2++) {
                    loadData[c1][c2] = getCellData(i, j);
                }
            }
            LogUtilities.logMessagesAndAddThemToReport("Data has been fetched from the excel file", "pass");
            return loadData;
        } catch (Exception exception) {
            LogUtilities.logMessagesAndAddThemToReport("Unable to fetch the data from excel file", "fail");
            Logger.error(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static String getCellData(int RowNum, int ColNum) {

        try {

            Cell = sheet.getRow(RowNum).getCell(ColNum);

            String CellData = Cell.getStringCellValue();

            return CellData;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}

