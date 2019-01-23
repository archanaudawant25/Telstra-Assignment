package mvx.component.com.myapplication.ui;

/*
 * It is a Utils class.
 * It is used to define helper methods for application
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

import mvx.component.com.myapplication.data.model.CountryDetails;

public class Utils {

    /*
     * Method to check network connectivity
     * @parameter : context
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (null != connectivityManager) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /*
     * Method to remove the null objects from the list.
     */
    public static ArrayList<CountryDetails> reformatData(ArrayList<CountryDetails> countryDetails) {
        for (int i = 0; i < countryDetails.size(); i++) {
            CountryDetails countryDetailsData = countryDetails.get(i);
            if (null == countryDetailsData.getCountryPropertyTitle() &&
                    null == countryDetailsData.getCountryPropertyDescription()) {
                countryDetails.remove(i);
            }
        }
        return countryDetails;
    }
}
