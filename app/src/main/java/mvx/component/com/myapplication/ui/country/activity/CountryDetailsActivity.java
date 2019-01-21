package mvx.component.com.myapplication.ui.country.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mvx.component.com.myapplication.R;
import mvx.component.com.myapplication.ui.country.fragment.CountryDetailsActivityFragment;

public class CountryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(null == savedInstanceState){
            CountryDetailsActivityFragment countryDetailsActivityFragment =
                    new CountryDetailsActivityFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_country_details , countryDetailsActivityFragment
                            ,"Country").commit();
        }
        //This is to check git working

    }

}
