package domains;

/**
 * Created by Manthan_personal on 10/12/17.
 */
public class CountryCityVisit {
    private int cid;
    private String cityName;
    private int dayOfVisit;
    private String countryName;
    private int countryid;
    private int visitid;

    public CountryCityVisit(int cid, String cityName, int dayOfVisit, String countryName, int countryid, int visitid) {
        this.cid = cid;
        this.cityName = cityName;
        this.dayOfVisit = dayOfVisit;
        this.countryName = countryName;
        this.countryid = countryid;
        this.visitid = visitid;
    }

    @Override
    public String toString() {
        return "CountryCityVisit{" + "cid=" + cid + ", cityName=" + cityName + ", dayOfVisit=" + dayOfVisit + ", countryName=" + countryName + ", countryid=" + countryid + ", visitid=" + visitid + '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getDayOfVisit() {
        return dayOfVisit;
    }

    public void setDayOfVisit(int dayOfVisit) {
        this.dayOfVisit = dayOfVisit;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public int getVisitid() {
        return visitid;
    }

    public void setVisitid(int visitid) {
        this.visitid = visitid;
    }
}

