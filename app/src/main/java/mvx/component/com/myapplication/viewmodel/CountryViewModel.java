package mvx.component.com.myapplication.viewmodel;

/*
 * It is CountryViewModel class.
 * It is used to fetch data from server and observes the changes on data .
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import mvx.component.com.myapplication.data.model.Country;
import mvx.component.com.myapplication.data.remote.CountryDetailsApiInterface;
import mvx.component.com.myapplication.data.remote.apiservices.CountryDetailsApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {

    private MutableLiveData<Country> countryDetails;

    public LiveData<Country> getCountryData() {
        if (countryDetails == null) {
            countryDetails = new MutableLiveData<>();
        }
        loadCountryDetail();
        return countryDetails;
    }

    /*
     * Method to load country details from server.
     */
    private void loadCountryDetail() {
        final CountryDetailsApiInterface countryDetailsApiInterface =
                CountryDetailsApiClient.getClient().create(CountryDetailsApiInterface.class);

        Call<Country> call = countryDetailsApiInterface.getCountryData();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(@NonNull Call<Country> call, @NonNull Response<Country> response) {
                countryDetails.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<Country> call, @NonNull Throwable t) {
                countryDetails.setValue(null);
            }
        });
    }
}
