package mvx.component.com.myapplication.data.di;

/*
 *This class used to provide the retrofit client to call api.
 */

import dagger.Module;
import dagger.Provides;
import mvx.component.com.myapplication.data.remote.api.CountryDetailsApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mvx.component.com.myapplication.data.remote.ApiConstants.BASE_URL;

//consider it is retrofit module

@Module
public class CountryRetrofitModule {


    @Provides
    CountryDetailsApiInterface getApiInterface(Retrofit retrofit){
        return retrofit.create(CountryDetailsApiInterface.class);
    }

    /**
     * @return retrofit object
     */
    @Provides
    public  Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
