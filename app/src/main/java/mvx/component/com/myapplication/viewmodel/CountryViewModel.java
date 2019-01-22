package mvx.component.com.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import mvx.component.com.myapplication.data.model.Country;
import mvx.component.com.myapplication.data.remote.CountryDetailsApiInterface;
import mvx.component.com.myapplication.data.remote.apiservices.CountryDetailsApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {

    //This will hold the updated/observable country data
    private MutableLiveData<Country> countryDetails;

    public LiveData<Country> getCountryData() {
        if (countryDetails == null) {
            countryDetails = new MutableLiveData<>();
        }

        loadCountryDetail();
        return countryDetails;
    }

    private void loadCountryDetail() {
        final CountryDetailsApiInterface countryDetailsApiInterface =
                CountryDetailsApiClient.getClient().create(CountryDetailsApiInterface.class);

        Call<Country> call = countryDetailsApiInterface.getCountryData();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                countryDetails.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });


    }
}
