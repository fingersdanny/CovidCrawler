package covid_crawler.src;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void generateCovidListExcel(List<CovidStatus> covidStatusList, String destination){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("코로나 현황");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("시도");
            headerRow.createCell(1).setCellValue("합계");
            headerRow.createCell(2).setCellValue("국내발생");
            headerRow.createCell(3).setCellValue("해외유입");
            headerRow.createCell(4).setCellValue("확진환자");
            headerRow.createCell(5).setCellValue("사망자");
            headerRow.createCell(6).setCellValue("발생률");

            CovidStatus totalCovidStatus = CovidStatusUtility.getTotalCovidStatus(covidStatusList);
            covidStatusList.add(0, totalCovidStatus);

            for (int i = 0; i < covidStatusList.size(); i++) {
                CovidStatus covidStatus = covidStatusList.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(covidStatus.getCity());
                row.createCell(0).setCellValue(covidStatus.getDailyTotal());
                row.createCell(0).setCellValue(covidStatus.getDomesticOriented());
                row.createCell(0).setCellValue(covidStatus.getForeignOriented());
                row.createCell(0).setCellValue(covidStatus.getTotalConfirmed());
                row.createCell(0).setCellValue(covidStatus.getTotalDeceased());
                row.createCell(0).setCellValue(covidStatus.getTotalOccurrence());
            }

            FileOutputStream outputStream = new FileOutputStream(new File(destination));
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
