package covid_crawler.src;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CovidScraper {
    private static String[] cities = {"I11", "I21", "I22", "I23", "I24"
                                    , "I25", "I26", "I29", "I31", "I32"
                                    , "I33", "I34", "I35", "I36", "I37"
                                    , "I38", "I39"};

    private static int[][] coordinates = {{150, 70}, {522, 486}, {656, 491}};
    private static String[] cityIds = {"V11", "V21", "V22", "V23", "V24"
                                    , "V25", "V26", "V29", "V31", "V32"
                                    , "V33", "V34", "V35", "V36", "V37"
                                    , "V38", "V39"};
    private static final String URL = "https://kosis.kr/covid/covid_index.do";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        int now = 0;
        for (String cityIndex : cityIds) {
            System.out.println("cityIndex = " + cityIndex);
            try {
                driver.findElement(By.id(cityIndex)).click();
                String city = driver.findElement(By.id("regionname")).getText();
                System.out.println(city);
                driver.findElement(By.id("byDateTab")).click();
            } catch (ElementClickInterceptedException e) {
//                e.printStackTrace();
                try {
                    Point origin = driver.findElement(By.className("Map")).getLocation();
                    System.out.println("x = " + origin.x);
                    System.out.println("y = " + origin.y);
                    Actions actions = new Actions(driver);
                    Point location = driver.findElement(By.id(cityIndex)).getLocation();
                    int x = location.x;
                    int y = location.y;
                    boolean find = false;
                    for (int i = 0; i < coordinates[now][0]; i++) {
                        for (int j = 0; j < coordinates[now][1]; j++) {
                            y++;
                            actions.moveToLocation(x, y).click();
                            String city = driver.findElement(By.id("regionname")).getText();
                            System.out.println("x = " + x);
                            System.out.println("y = " + y);
                            if (city.equals("인천")) {
                                find = true;
                                break;
                            }
                        }
                        x++;
                        if (find) {
                            break;
                        }
                        y = location.y;
                    }
                    System.out.println("loc x = " + x);
                    System.out.println("loc y = " + y);
                    actions.moveToLocation(x, y).click();
                    String city = driver.findElement(By.id("regionname")).getText();
                    System.out.println(city);
                    now++;
                } catch (ElementClickInterceptedException e1) {
                    e.printStackTrace();
                }
            }
        }
        driver.close();
    }
}
