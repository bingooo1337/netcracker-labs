package lab1.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ExcelWriter {
    private Workbook workbook;
    private String filename;
    private Map<String, ArrayList<ArrayList<String>>> sheets;

    public ExcelWriter(String filename, Map<String, ArrayList<ArrayList<String>>> sheetsAndData) {
        this.workbook = new HSSFWorkbook();
        this.filename = filename + ".xls";
        this.sheets = sheetsAndData;
    }

    public void write() {
        sheets.forEach(this::createSheetAndFill);
        writeToFile();
    }

    private void createSheetAndFill(String sheetTitle, ArrayList<ArrayList<String>> data) {
        Sheet sheet = workbook.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(20);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> list = data.get(i);
            Row row = sheet.createRow(i);
            for (int j = 0; j < list.size(); j++) {
                String value = list.get(j);
                try {
                    row.createCell(j).setCellValue(Double.parseDouble(value));
                } catch (NumberFormatException ex) {
                    row.createCell(j).setCellValue(value);
                }
            }
        }
    }

    private void writeToFile() {
        try {
            workbook.write(new FileOutputStream(filename));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
