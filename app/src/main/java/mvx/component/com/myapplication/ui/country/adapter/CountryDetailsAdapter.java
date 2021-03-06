package mvx.component.com.myapplication.ui.country.adapter;

/*
 * It is CountryDetailsAdapter class.
 * This class is used to set up the UI for the Country details data.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mvx.component.com.myapplication.R;
import mvx.component.com.myapplication.data.model.CountryDetails;
import mvx.component.com.myapplication.ui.GlideApp;

public class CountryDetailsAdapter extends RecyclerView.Adapter
        <CountryDetailsAdapter.CountryDetailsViewHolder> {
    private ArrayList<CountryDetails> countryDetailsList;
    private Context context;

    //Constructor to initialise variables
    public CountryDetailsAdapter(Context context, ArrayList<CountryDetails> countryDetailsList) {
        this.context = context;
        this.countryDetailsList = countryDetailsList;
    }

    @NonNull
    @Override
    public CountryDetailsAdapter.CountryDetailsViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row_item, parent, false);
        return new CountryDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryDetailsAdapter.CountryDetailsViewHolder holder,
                                 int position) {
        CountryDetails countryDetails = countryDetailsList.get(position);

        if (!TextUtils.isEmpty(countryDetails.getCountryPropertyTitle())) {
            holder.tvTitle.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(countryDetails.getCountryPropertyTitle());
        } else {
            holder.tvTitle.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(countryDetails.getCountryPropertyDescription())) {
            holder.tvDesc.setVisibility(View.VISIBLE);
            holder.tvDesc.setText(countryDetails.getCountryPropertyDescription());
        } else {
            holder.tvDesc.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(countryDetails.getCountryPropertyImageUrl())) {
            holder.ivFrameLayout.setVisibility(View.VISIBLE);
            GlideApp.with(context)
                    .load(countryDetails.getCountryPropertyImageUrl())
                    .override(300, 200)
                    .fitCenter()
                    .centerCrop()
                    .into(holder.ivCountryPropertyIcon);
        } else {
            holder.ivFrameLayout.setVisibility(View.GONE);
        }
    }

    /*
     * @return size of list.
     */
    @Override
    public int getItemCount() {
        return countryDetailsList.size();
    }

    /*
     * Method to update the UI on data change
     */
    public void updateView(ArrayList<CountryDetails> countryDetailsList) {
        this.countryDetailsList = countryDetailsList;
        notifyDataSetChanged();
    }

    /*
     * A ViewHolder describe item view components.
     */
    class CountryDetailsViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDesc;
        private final ImageView ivCountryPropertyIcon;
        private final FrameLayout ivFrameLayout;

        CountryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.tvDesc = itemView.findViewById(R.id.tv_desc);
            this.ivCountryPropertyIcon = itemView.findViewById(R.id.iv_icon);
            this.ivFrameLayout = itemView.findViewById(R.id.frame_layout);
        }
    }
}
