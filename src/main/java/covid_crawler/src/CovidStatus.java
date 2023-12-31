package covid_crawler.src;

public class CovidStatus {
    private String city;
    private int dailyTotal;
    private int dailyDeceased;
    private int domesticOriented;
    private int foreignOriented;
    private int totalConfirmed;
    private int totalDeceased;

    public CovidStatus() {

    }

    public CovidStatus(String city, int dailyTotal, int dailyDeceased, int domesticOriented, int foreignOriented, int totalConfirmed, int totalDeceased) {
        this.city = city;
        this.dailyTotal = dailyTotal;
        this.dailyDeceased = dailyDeceased;
        this.domesticOriented = domesticOriented;
        this.foreignOriented = foreignOriented;
        this.totalConfirmed = totalConfirmed;
        this.totalDeceased = totalDeceased;
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

    public int getDailyDeceased() {
        return dailyDeceased;
    }

    public void setDailyDeceased(int dailyDeceased) {
        this.dailyDeceased = dailyDeceased;
    }

    @Override
    public String toString() {
        return "CovidStatus{" +
                "city='" + city + '\'' +
                ", dailyTotal=" + dailyTotal +
                ", dailyDeceased=" + dailyDeceased +
                ", domesticOriented=" + domesticOriented +
                ", foreignOriented=" + foreignOriented +
                ", totalConfirmed=" + totalConfirmed +
                ", totalDeceased=" + totalDeceased +
                '}';
    }
}
