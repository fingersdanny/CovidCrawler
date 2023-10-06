package covid_crawler.src;

/**
* totalOccurrence는 10만명당 발생률입니다.
 */
public class CovidStatus {
    private String city;
    private int dailyTotal;
    private int domesticOriented;
    private int foreignOriented;
    private int totalConfirmed;
    private int totalDeceased;
    private int totalOccurrence;

    public CovidStatus() {

    }

    public CovidStatus(String city, int dailyTotal, int domesticOriented, int foreignOriented, int totalConfirmed, int totalDeceased, int totalOccurrence) {
        this.city = city;
        this.dailyTotal = dailyTotal;
        this.domesticOriented = domesticOriented;
        this.foreignOriented = foreignOriented;
        this.totalConfirmed = totalConfirmed;
        this.totalDeceased = totalDeceased;
        this.totalOccurrence = totalOccurrence;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDailyTotal() {
        return dailyTotal;
    }

    public void setDailyTotal(int dailyTotal) {
        this.dailyTotal = dailyTotal;
    }

    public int getDomesticOriented() {
        return domesticOriented;
    }

    public void setDomesticOriented(int domesticOriented) {
        this.domesticOriented = domesticOriented;
    }

    public int getForeignOriented() {
        return foreignOriented;
    }

    public void setForeignOriented(int foreignOriented) {
        this.foreignOriented = foreignOriented;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalDeceased() {
        return totalDeceased;
    }

    public void setTotalDeceased(int totalDeceased) {
        this.totalDeceased = totalDeceased;
    }

    public int getTotalOccurrence() {
        return totalOccurrence;
    }

    public void setTotalOccurrence(int totalOccurrence) {
        this.totalOccurrence = totalOccurrence;
    }
}
