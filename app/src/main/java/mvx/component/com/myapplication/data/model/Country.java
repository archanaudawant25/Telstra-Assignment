package mvx.component.com.myapplication.data.model;



/*
 * This is model class for Country.
 * It gives detail information about country.
 *
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Country {


    @SerializedName("title")
    private String countryName;

    @SerializedName("rows")
    private ArrayList<CountryDetails> countryDetails;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ArrayList<CountryDetails> getCountryDetails() {
        return countryDetails;
    }

    public void setCountryDetails(ArrayList<CountryDetails> countryDetails) {
        this.countryDetails = countryDetails;
    }


}
