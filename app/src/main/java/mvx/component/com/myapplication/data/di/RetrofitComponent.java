package mvx.component.com.myapplication.data.di;

import dagger.Component;
import mvx.component.com.myapplication.data.di.CountryRetrofitModule;
import mvx.component.com.myapplication.viewmodel.CountryViewModel;

@Component(modules = {CountryRetrofitModule.class})
public interface RetrofitComponent {

     void injectRetrofit(CountryViewModel countryViewModel);
}
