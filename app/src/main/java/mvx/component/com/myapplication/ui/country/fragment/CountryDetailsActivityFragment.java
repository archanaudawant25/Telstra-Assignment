package mvx.component.com.myapplication.ui.country.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import mvx.component.com.myapplication.R;
import mvx.component.com.myapplication.data.model.Country;
import mvx.component.com.myapplication.ui.Utils;
import mvx.component.com.myapplication.ui.country.adapter.CountryDetailsAdapter;
import mvx.component.com.myapplication.viewmodel.CountryViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class CountryDetailsActivityFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvCountryDetails;
    // private TextView tvNoInternet;
    private ScrollView svNoInternet;

    private CountryViewModel countryViewModel;
    private CountryDetailsAdapter countryDetailsAdapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_details, container,
                false);
        Log.d("onCreateView", "called");
        initialiseView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onViewCreated", "called");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated", "called");
        init();
        loadCountryDetails();
    }

    //Method ti initialise the view elements
    private void initialiseView(View view) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        rvCountryDetails = view.findViewById(R.id.rv_country_details_list);
        //tvNoInternet = view.findViewById(R.id.tv_no_internet);
        //svNoInternet = view.findViewById(R.id.sv_no_internet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvCountryDetails.setLayoutManager(linearLayoutManager);
        setSwipeRefreshLayoutListener();

    }

    //Method to initialise the activity reference variables
    private void init() {
        context = getActivity();
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

    }

    private void setSwipeRefreshLayoutListener() {

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadCountryDetails();
                    }
                }
        );
    }


    private void loadCountryDetails() {

        if (Utils.isNetworkAvailable(context)) {
            //swipeRefreshLayout.setRefreshing(true);

            countryViewModel.getCountryData().observe(this,
                    new Observer<Country>() {
                        @Override
                        public void onChanged(@Nullable Country country) {
                            if (null != country && null != country.getCountryDetails()
                                    && !country.getCountryDetails().isEmpty()) {

                                rvCountryDetails.setVisibility(View.VISIBLE);
                                //tvNoInternet.setVisibility(View.GONE);
                                //swipeRefreshLayout.setRefreshing(true);
                                setDataToView(country);
                                // swipeRefreshLayout.setRefreshing(false);
                                //TODO : data cache logic add here
                                //persist data locally in shared pref
                                   /* String jsonCountryDetails = new Gson().toJson(country);
                                    Utils.saveDataToSharedPref(context, PREF_KEY_COUNTRY_DETAILS, jsonCountryDetails);*/


                            } else {
                                //Toast.makeText(context, getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                                rvCountryDetails.setVisibility(View.GONE);
                                // tvNoInternet.setVisibility(View.VISIBLE);
                                // tvNoInternet.setText(getString(R.string.no_data_available));
                            }
                        }
                    });


        } else {
            //TODO : add logic to get data from cache
            /*
             * First time view loaded.
             * No internet connectivity and device orientation changes.
             * Country details get from ViewModel to set the view.
             */
                /*if (null != countryViewModel) {
                    Country countryLiveData = countryViewModel.getCountryData().getValue();
                    setDataToView(countryLiveData);

                }*/
            swipeRefreshLayout.setRefreshing(false);
            //Toast.makeText(context, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            rvCountryDetails.setVisibility(View.GONE);
            // tvNoInternet.setVisibility(View.VISIBLE);
            //tvNoInternet.setText(getString(R.string.no_internet_connection));
            // swipeRefreshLayout.setRefreshing(true);

        }

    }


    private void setDataToView(Country country) {
        if (null == countryDetailsAdapter) {
            countryDetailsAdapter = new CountryDetailsAdapter(context, country.getCountryDetails());
        } else {
            countryDetailsAdapter.updateView(country.getCountryDetails());
        }
        rvCountryDetails.setAdapter(countryDetailsAdapter);

        if (null != getActivity() && null != country.getCountryName()) {
            getActivity().setTitle(country.getCountryName());
        }
    }
}



