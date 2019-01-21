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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private CountryViewModel countryViewModel;
    private CountryDetailsAdapter countryDetailsAdapter;
    private Context context;

    public CountryDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_country_details, container,
                false);
        initialiseView(view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadCountryDetails();
    }

    private void initialiseView(View view) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        rvCountryDetails = view.findViewById(R.id.rv_country_details_list);

        context = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvCountryDetails.setLayoutManager(linearLayoutManager);

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        setSwipeRefreshLayoutListener();

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

        if (null != context) {
            if (Utils.isNetworkAvailable(context)) {
                swipeRefreshLayout.setRefreshing(true);

                countryViewModel.getCountryData().observe(this,
                        new Observer<Country>() {
                            @Override
                            public void onChanged(@Nullable Country country) {
                                if(null != country && null != country.getCountryDetails()
                                        && !country.getCountryDetails().isEmpty())
                                {
                                        setDataToView(country);
                                        swipeRefreshLayout.setRefreshing(false);
                                        //TODO : data cache logic add here
                                }
                                else {
                                    Toast.makeText(context, getString(R.string.no_data_available),Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }else {
                //TODO : add logic to get data from cache
            }
        }


    }


    private void setDataToView(Country country){

        if(null == countryDetailsAdapter){
            countryDetailsAdapter = new CountryDetailsAdapter(context, country.getCountryDetails());
        }else {
            countryDetailsAdapter.updateView(country.getCountryDetails());
        }

        rvCountryDetails.setAdapter(countryDetailsAdapter);

        if(null != getActivity() && null != country.getCountryName()){
           getActivity().setTitle(country.getCountryName());
        }



    }
}



