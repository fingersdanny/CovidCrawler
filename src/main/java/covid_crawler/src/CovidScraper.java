package covid_crawler.src;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CovidScraper {
    private static String[] cityIds = {"V11", "V21", "V22", "V23", "V24"
                                    , "V25", "V26", "V29", "V31", "V32"
                                    , "V33", "V34", "V35", "V36", "V37"
                                    , "V38", "V39"};
    private static final String URL = "https://kosis.kr/covid/covid_index.do";

    public static void main(String[] args) throws FileNotFoundException {
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        List<CovidStatus> covidStatusList = new ArrayList<>();
        String date = driver.findElement(By.id("dashboardDate")).getText();
        System.out.println(date);
        String[] dates = date.split(" ");
        String fileName = "covid_status_" + dates[0].replace("(", "") + "_" + dates[1] + "_" + dates[2] + "_00시_기준";

        covidStatusList.add(getCovidStatus(driver));
        for (String cityIndex : cityIds) {
            try {
                driver.findElement(By.id(cityIndex)).click();
                CovidStatus curCovidStatus = getCovidStatus(driver);
                if (curCovidStatus != null) {
                    covidStatusList.add(curCovidStatus);
                }
            } catch (ElementClickInterceptedException e) {
                e.printStackTrace();
            }
        }
        for (CovidStatus cs : covidStatusList) {
            System.out.println(cs.toString());
        }
        driver.close();
        ExcelExporter.generateCovidListExcel(covidStatusList, fileName + ".xlsx");
        PdfExporter.generateCovidListPdf(covidStatusList, fileName + ".pdf", date);
    }

    private static CovidStatus getCovidStatus(WebDriver driver) {
        try {
            String city = driver.findElement(By.id("regionname")).getText();

            Thread.sleep(3000);

            driver.findElement(By.id("byDateTab")).click();
            String dt = driver.findElement(By.id("commaconfirmval1"))
                    .findElement(By.className("Color1")).getText().replaceAll(",", "");
            String dD = driver.findElement(By.id("commadeadval1"))
                    .findElement(By.className("Color3")).getText().replaceAll(",", "");
            int dailyTotal = Integer.parseInt(dt);
            int dailyDeceased = Integer.parseInt(dD);

            Thread.sleep(3000);

            driver.findElement(By.id("byPathTab")).click();
            String dO = driver.findElement(By.id("commadomesticval"))
                    .findElement(By.className("Color1")).getText().replaceAll(",", "");
            String fO = driver.findElement(By.id("commaoverseasval"))
                    .findElement(By.className("Color3")).getText().replaceAll(",", "");
            int domesticOriented = Integer.parseInt(dO);
            int foreignOriented = Integer.parseInt(fO);

            Thread.sleep(3000);

            driver.findElement(By.id("allTab")).click();
            String tC = driver.findElement(By.id("commaconfirmval"))
                    .findElement(By.className("Color1")).getText().replaceAll(",", "");
            String tD = driver.findElement(By.id("commadeadval"))
                    .findElement(By.className("Color3")).getText().replaceAll(",", "");
            int totalConfirmed = Integer.parseInt(tC);
            int totalDeceased = Integer.parseInt(tD);

        return new CovidStatus(city, dailyTotal, dailyDeceased, domesticOriented, foreignOriented, totalConfirmed, totalDeceased);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
