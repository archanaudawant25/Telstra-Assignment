package mvx.component.com.myapplication.viewmodel;

/*
 * It is CountryViewModel class.
 * It is used to fetch data from server and observes the changes on data .
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import mvx.component.com.myapplication.data.di.DaggerRetrofitComponent;
import mvx.component.com.myapplication.data.di.RetrofitComponent;
import mvx.component.com.myapplication.data.model.Country;
import mvx.component.com.myapplication.data.remote.api.CountryDetailsApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {
    @Inject
    CountryDetailsApiInterface countryDetailsApiInterface;

    RetrofitComponent retrofitComponent;
    private MutableLiveData<Country> countryDetails;

    public LiveData<Country> getCountryData() {
        retrofitComponent = DaggerRetrofitComponent.builder().build();
        retrofitComponent.injectRetrofit(this);
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
        Call<Country> call = countryDetailsApiInterface.getCountryData();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(@NonNull Call<Country> call, @NonNull Response<Country> response) {
                countryDetails.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<Country> call, @NonNull Throwable t) {
            }
        });
    }
}
