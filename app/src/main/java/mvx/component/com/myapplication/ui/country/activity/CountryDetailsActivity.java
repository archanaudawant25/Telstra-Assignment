package mvx.component.com.myapplication.ui.country.activity;

/*
 * It is Country details Activity Class.
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mvx.component.com.myapplication.R;
import mvx.component.com.myapplication.ui.country.CountryInterface;
import mvx.component.com.myapplication.ui.country.fragment.CountryDetailsActivityFragment;

public class CountryDetailsActivity extends AppCompatActivity implements CountryInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null == savedInstanceState) {
            CountryDetailsActivityFragment countryDetailsActivityFragment =
                    new CountryDetailsActivityFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_country_frag_container, countryDetailsActivityFragment
                            , "Country").commit();
        }
    }


    @Override
    public void finishActivity() {
        finish();
    }
}
