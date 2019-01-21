package mvx.component.com.myapplication.data.remote.apiservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mvx.component.com.myapplication.data.remote.ApiConstants.BASE_URL;

/*
 *This class used to provide the retrofit client to call api.
 */


public class CountryDetailsApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
