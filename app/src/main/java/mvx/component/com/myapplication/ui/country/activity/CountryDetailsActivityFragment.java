package mvx.component.com.myapplication.ui.country.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvx.component.com.myapplication.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CountryDetailsActivityFragment extends Fragment {

    public CountryDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_details, container, false);
    }
}
