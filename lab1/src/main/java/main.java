import analyzer.Analyzer;
import excel.ExcelWriter;

public class main {
    public static void main(String[] args) {
        Object[][] arguments = {
                {1000, 0, 1000},
                {1500, 0, 1000},
                {2000, 0, 1000},
                {2500, 0, 1000}
        };

        Analyzer analyzer = new Analyzer("sorters");

        ExcelWriter excelWriter = new ExcelWriter("results", analyzer.generateStatistics(arguments));
        excelWriter.write();
    }
}
