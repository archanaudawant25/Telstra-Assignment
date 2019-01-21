package mvx.component.com.myapplication.data.remote;

/*
* This Interface for country details api.
* */

import mvx.component.com.myapplication.data.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryDetailsApiInterface {


    @GET("s/2iodh4vg0eortkl/CountryI")
    Call<Country> getCountryData();


}
