package mvx.component.com.myapplication.data.remote.apiservices;

/*
 *This class used to provide the retrofit client to call api.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mvx.component.com.myapplication.data.remote.ApiConstants.BASE_URL;

public class CountryDetailsApiClient {
    /**
     * @return retrofit object
     */
    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
