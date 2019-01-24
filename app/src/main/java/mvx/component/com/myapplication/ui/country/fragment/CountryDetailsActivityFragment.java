package mvx.component.com.myapplication.ui.country.fragment;

/**
 * It is CountryDetailsActivityFragment class.
 * It shows details about various properties of country like flag, geographical areas etc.
 */

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

import mvx.component.com.myapplication.R;
import mvx.component.com.myapplication.data.model.Country;
import mvx.component.com.myapplication.data.model.CountryDetails;
import mvx.component.com.myapplication.ui.Utils;
import mvx.component.com.myapplication.ui.country.CountryInterface;
import mvx.component.com.myapplication.ui.country.activity.CountryDetailsActivity;
import mvx.component.com.myapplication.ui.country.adapter.CountryDetailsAdapter;
import mvx.component.com.myapplication.viewmodel.CountryViewModel;


public class CountryDetailsActivityFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvCountryDetails;
    private CountryViewModel countryViewModel;
    private CountryDetailsAdapter countryDetailsAdapter;
    private Context context;
    private CountryInterface countryInterface;

    public static final String TAG = "country_details_fragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_details, container,
                false);
        initialiseView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        loadCountryDetails();
    }

    /*
     * Method to initialise the view elements
     */
    private void initialiseView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        rvCountryDetails = view.findViewById(R.id.rv_country_details_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvCountryDetails.setLayoutManager(linearLayoutManager);
        setSwipeRefreshLayoutListener();
    }

    /*
     * Method to initialise the activity reference variables
     */
    private void init() {
        context = getActivity();
        countryInterface = (CountryDetailsActivity) context;
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
    }

    private void setSwipeRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (!Utils.isNetworkAvailable(context) && null != countryViewModel.getCountryData().getValue()) {
                            showErrorDialog(context, getString(R.string.no_internet_connection));
                        } else {
                            loadCountryDetails();
                        }
                    }
                });
    }


    /*
     * Method to load the country details.
     * If data is not available in ViewModel then it will fetch from api
     * Network check is also handle here.
     */
    private void loadCountryDetails() {
        if (null != countryViewModel && null != countryViewModel.getCountryData().getValue()) {
            Country countryLiveData = countryViewModel.getCountryData().getValue();
            setDataToView(countryLiveData);

        } else if (Utils.isNetworkAvailable(context)) {
            countryViewModel.getCountryData().observe(this,
                    new Observer<Country>() {
                        @Override
                        public void onChanged(@Nullable Country country) {
                            if (null != country && null != country.getCountryDetails()
                                    && !country.getCountryDetails().isEmpty()) {
                                setDataToView(country);

                            } else {
                                Toast.makeText(context, getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            showErrorDialog(context, getString(R.string.no_internet_connection));
        }
    }

    /*
     * Method to set list data using adapter
     */
    private void setDataToView(Country country) {
        swipeRefreshLayout.setRefreshing(true);
        ArrayList<CountryDetails> countryDetails = Utils.reformatData(country.getCountryDetails());
        if (null == countryDetailsAdapter) {
            countryDetailsAdapter = new CountryDetailsAdapter(context, countryDetails);
        } else {
            countryDetailsAdapter.updateView(country.getCountryDetails());
        }
        rvCountryDetails.setAdapter(countryDetailsAdapter);
        swipeRefreshLayout.setRefreshing(false);

        if (null != getActivity() && null != country.getCountryName()) {
            getActivity().setTitle(country.getCountryName());
        }
    }

    /*
     * Error message for no internet connection
     */
    private void showErrorDialog(final Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_alert))
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (null == countryViewModel.getCountryData().getValue()) {
                            countryInterface.finishActivity();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        dialog.cancel();

                    }
                });
        builder.create().show();
    }
}



