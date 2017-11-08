package lab1.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ExcelWriter {
    private Workbook workbook;
    private String filename;
    private Map<String, ArrayList<ArrayList<String>>> sheets;

    public ExcelWriter(String filename, Map<String, ArrayList<ArrayList<String>>> sheetsAndData) {
        this.workbook = new XSSFWorkbook();
        this.filename = filename + ".xlsx";
        this.sheets = sheetsAndData;
    }

    public void write() {
        sheets.forEach(this::createSheetAndFill);
        writeToFile();
    }

    private void createSheetAndFill(String sheetTitle, ArrayList<ArrayList<String>> data) {
        Sheet sheet = workbook.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(20);

        Chart chart = createChartWithLegend(sheet, data.size() + 2, data.get(0).size());
        LineChartData chartData = createChartData(chart);

        ChartDataSource<Number> xAxis = DataSources.fromNumericCellRange(sheet,
                new CellRangeAddress(0, 0, 1, data.get(0).size() - 1));

        fillWithData(data, sheet, chartData, xAxis);

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis valueAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        chart.plot(chartData, bottomAxis, valueAxis);
    }

    private void fillWithData(ArrayList<ArrayList<String>> data, Sheet sheet, LineChartData chartData, ChartDataSource<Number> xAxis) {
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
            if (i != 0)
                chartData.addSeries(xAxis,
                        DataSources.fromNumericCellRange(sheet,
                                new CellRangeAddress(i, i, 1, list.size() - 1)))
                        .setTitle(list.get(0));
        }
    }

    private Chart createChartWithLegend(Sheet sheet, int startRow, int endColumn) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startRow, endColumn, startRow + 30);

        Chart chart = drawing.createChart(anchor);
        ChartLegend chartLegend = chart.getOrCreateLegend();
        chartLegend.setPosition(LegendPosition.BOTTOM);
        return chart;
    }

    private LineChartData createChartData(Chart chart) {
        return chart.getChartDataFactory().createLineChartData();
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
