package org.F1Soft.Helpers;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Roshan Shah
 * @date 05/16/2023, Tuesday
 * @version 1.0
 * @description :
 **/
public class Excel {
    public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;

    /**
     * @param xlFilePath path of Excel File to read.
     * @throws Exception file not found exception.
     */
    public Excel(String xlFilePath) throws Exception {
        if (xlFilePath.isEmpty()) {
            xlFilePath = getClass().getResource("/TestData/TestData.xlsx").toURI().getPath();
        }
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    /**
     * @param sheetName name of sheet in Excel File
     * @param colName column Header in Sheet
     * @param rowNum row number of Excel
     * @return String
     * @author Roshan Shah
     * @date 05/25/2023
     * @description finds the data of cell under column header of given row no. in given sheet name.
     */
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            int col_Num = -1;
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }

            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(col_Num);

            if (cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            } else if (cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("row " + rowNum + " or column Name" + colName + " does not exist  in Excel");
            return "";
        }
    }

    /**
     * @param sheet sheet data of Excel file
     * @param colName column Header in Sheet
     * @param rowNum row number of Excel
     * @return String
     * @author Roshan Shah
     * @date 05/25/2023
     * @description finds the data of cell under column header of given row no. in given sheet data.
     */
    public String getCellData(XSSFSheet sheet, String colName, int rowNum) {
        try {
            int col_Num = -1;
            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }

            row = sheet.getRow(rowNum - 1);
            XSSFCell cell = row.getCell(col_Num);
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.STRING)
                    return cell.getStringCellValue();
                else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                    String cellValue = String.valueOf(cell.getNumericCellValue());
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yy");
                        Date date = cell.getDateCellValue();
                        cellValue = df.format(date);
                    }
                    return cellValue;
                } else if (cell.getCellTypeEnum() == CellType.BLANK)
                    return "";
                else
                    return String.valueOf(cell.getBooleanCellValue());
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("row " + rowNum + " or column Name" + colName + " does not exist  in Excel");
            return "";
        }
    }

    /**
     * @param row row data from Excel sheet.
     * @param col_Num column Header in Sheet
     * @return String
     * @author Roshan Shah
     * @date 05/25/2023
     * @description finds the data of cell under column header of given row data
     */
    public String getCellData(XSSFRow row, int col_Num) {
        try {
            cell = row.getCell(col_Num);

            if (cell == null) return "";
            if (cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if (cellValue.contains("E+") || cellValue.contains("E-")) {
                    cellValue = String.valueOf(Double.valueOf(cellValue).longValue());
                }
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            } else if (cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {
            e.printStackTrace();
//            Log.error("row " + rowNum + " or column Name" + colName + " does not exist  in Excel");
            return "";
        }
    }


    /**
     * @param XLFilePath file path for Excel
     * @throws IOException IOException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description creates Excel file on given path.
     */
    public static void createExcel(String XLFilePath) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(XLFilePath, true);

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * @param XLFilePath file path for Excel
     * @param SheetName sheet Name of Excel file
     * @throws IOException IOException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description creates new sheet with Name in Excel file.
     */
    public void createSheet(String XLFilePath, String SheetName) throws IOException {

        // Creating Workbook instances
        XSSFWorkbook wb = new XSSFWorkbook();
        OutputStream fileOut = new FileOutputStream(XLFilePath);
        // An output stream accepts output bytes and sends them to sink.
        //   FileOutputStream fileOut = new FileOutputStream(XLFilePath,true);

        // Creating Sheets using sheet object
        XSSFSheet spreadsheet = workbook.getSheet(SheetName);
        // Check if the workbook is empty or not
        if (workbook.getNumberOfSheets() != 0) {
            boolean found = false;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                String name = workbook.getSheetName(i);
                if (name.equals(SheetName)) {
                    found = true;
                }
            }
            if (!found) {
                workbook.createSheet(SheetName);
            }
        } else {
            // Create new sheet to the workbook if empty
            workbook.createSheet(SheetName);
        }
        System.out.println("Sheets Has been Created successfully");
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * @param filePath file path for Excel
     * @param sheetName sheet Name of Excel file
     * @param colNum column number
     * @param rowNum row number
     * @param value Value to write into cell.
     * @param rowHeight height of row to maintain
     * @throws IOException  IOException
     * @throws InvalidFormatException InvalidFormatException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description writes value in the cell of Sheet Name of provided Excel.
     */
    public void writeToXL(String filePath, String sheetName, int colNum, int rowNum, String value, int rowHeight) throws IOException, InvalidFormatException {
        //   String newPath = filePath.substring(0, filePath.length() - 5) + "1" + filePath.substring(filePath.length() - 5, filePath.length());

        FileInputStream fis = new FileInputStream(new File(filePath));
        // Obtain a workbook from the Excel file
        workbook = new XSSFWorkbook(fis);

        // Get Sheet at index 0
        sheet = workbook.getSheet(sheetName);

        // Get Row at index
        try {
            row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
        } catch (Exception e) {
            row = sheet.createRow(rowNum);
        }


        // Get the Cell at index  from the above row
        try {
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }
        } catch (Exception e) {
            cell = row.createCell(colNum);
        }

        // Update the cell's value
//        cell.setCellType(CellType.STRING);
        cell.setCellValue(value);

        //to enable newlines you need set a cell styles with wrap=true
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);

        //increase row height to accomodate two lines of text
        row.setHeightInPoints((rowHeight * sheet.getDefaultRowHeightInPoints()));

        //adjust column width to fit the content
        sheet.autoSizeColumn(colNum);

        fis.close();
        // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        //   workbook.close();
    }

    /**
     * @param filePath file path of Excel
     * @param sheetName name of Sheet
     * @return XSSFSheet
     * @throws IOException IOException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description gets sheet data of sheet name in excel file
     */
    public XSSFSheet getSheet(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        // Obtain a workbook from the excel file
        workbook = new XSSFWorkbook(fis);

        // Get Sheet at index 0
        return workbook.getSheet(sheetName);
    }

    /**
     * @param filePath file path of Excel
     * @param position position of sheet in excel
     * @return XSSFSheet
     * @throws IOException IOException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description gets sheet data at position in Excel file
     */
    public XSSFSheet getSheet(String filePath, Integer position) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        // Obtain a workbook from the excel file
        workbook = new XSSFWorkbook(fis);

        // Get Sheet at index 0
        return workbook.getSheetAt(position);
    }

    /**
     * @param filePath file path of Excel
     * @return Integer
     * @throws IOException IOException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description gets no of sheets available in Excel file
     */
    public Integer getNoOfSheets(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        // Obtain a workbook from the excel file
        workbook = new XSSFWorkbook(fis);
        return workbook.getNumberOfSheets();
    }


    /**
     * @param sheetName name of sheet in Excel file
     * @return List of HasMap
     * @throws IOException IOException
     * @throws URISyntaxException URISyntaxException
     * @author Roshan Shah
     * @date 05/25/2023
     * @description gets all data in sheet of Excel;
     * list contains row data as HasMap mapped with column header
     */
    public  List<HashMap> getExcelValue( String sheetName) throws IOException, URISyntaxException {
        List<HashMap> excelData = new ArrayList<HashMap>();
        try {
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > 1) {
                int totalColumns = sheet.getRow(0).getLastCellNum();
                HashMap<Integer, String> columns = new HashMap<Integer, String>();
                XSSFRow row = sheet.getRow(0);
                for (int i = 0; i < totalColumns; i++) {
                    columns.put(i, getCellData(row, i));
                }
                for (int rowIndex = 1; rowIndex <= totalRows - 1; rowIndex++) {
                    HashMap<String, String> rowData = new HashMap<>();
                    for (Map.Entry<Integer, String> entry : columns.entrySet()) {
                        Integer key = entry.getKey();
                        String value = entry.getValue();
                        rowData.put(value.toUpperCase(), getCellData(sheet.getRow(rowIndex), key));
                    }
                    excelData.add(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (excelData.isEmpty()) {
            Log.error("Could not find Data in sheet:'" + sheetName + "' in excel file");
        }
        return excelData;
    }
}
