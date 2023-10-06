package covid_crawler.src;

import java.util.List;

public class CovidStatusUtility {
    public static CovidStatus getTotalCovidStatus(List<CovidStatus> covidStatusList) {
        CovidStatus totalCovidStatus = new CovidStatus();
        totalCovidStatus.setCity("합계");
        totalCovidStatus.setDailyTotal(covidStatusList.stream().map(CovidStatus::getDailyTotal).reduce(0, Integer::sum));
        totalCovidStatus.setDomesticOriented(covidStatusList.stream().map(CovidStatus::getDomesticOriented).reduce(0, Integer::sum));
        totalCovidStatus.setForeignOriented(covidStatusList.stream().map(CovidStatus::getForeignOriented).reduce(0, Integer::sum));
        totalCovidStatus.setTotalConfirmed(covidStatusList.stream().map(CovidStatus::getTotalConfirmed).reduce(0, Integer::sum));
        totalCovidStatus.setTotalDeceased(covidStatusList.stream().map(CovidStatus::getTotalDeceased).reduce(0, Integer::sum));
        totalCovidStatus.setTotalOccurrence(covidStatusList.stream().map(CovidStatus::getTotalOccurrence).reduce(0, Integer::sum));

        return totalCovidStatus;
    }
}
