package mvx.component.com.myapplication.data.model;



/*
 * This is model class for Country.
 * It gives detail information about country.
 *
 */

public class Country {



    private String countryName;
    private CountryDetails countryDetails;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public CountryDetails getCountryDetails() {
        return countryDetails;
    }

    public void setCountryDetails(CountryDetails countryDetails) {
        this.countryDetails = countryDetails;
    }
}
