package mvx.component.com.myapplication.data.model;

/*
 * This is Model Class for Country Details.
 * It defines the information about various properties to country like Regions, Flag, Public etc.
 */

import com.google.gson.annotations.SerializedName;

public class CountryDetails {

    @SerializedName("title")
    private String countryPropertyTitle;

    @SerializedName("description")
    private String countryPropertyDescription;

    @SerializedName("imageHref")
    private String countryPropertyImageUrl;

    public String getCountryPropertyTitle() {
        return countryPropertyTitle;
    }

    public void setCountryPropertyTitle(String countryPropertyTitle) {
        this.countryPropertyTitle = countryPropertyTitle;
    }

    public String getCountryPropertyDescription() {
        return countryPropertyDescription;
    }

    public void setCountryPropertyDescription(String countryPropertyDescription) {
        this.countryPropertyDescription = countryPropertyDescription;
    }

    public String getCountryPropertyImageUrl() {
        return countryPropertyImageUrl;
    }

    public void setCountryPropertyImageUrl(String countryPropertyImageUrl) {
        this.countryPropertyImageUrl = countryPropertyImageUrl;
    }
}
