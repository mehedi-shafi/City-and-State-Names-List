package com.example.shafi.databaseimport.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shafi.databaseimport.R;
import com.example.shafi.databaseimport.StateListActivity;
import com.example.shafi.databaseimport.model.Country;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shafi on 7/5/2017.
 */

public class CountryListAdapter extends ArrayAdapter<Country> {

    private int layoutResource;
    private Activity activity;
    private ArrayList<Country> countries = new ArrayList<>();


    public CountryListAdapter(Activity act, int resource, ArrayList<Country> data){

        super(act, resource, data);
        layoutResource = resource;
        activity = act;
        countries = data;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Nullable
    @Override
    public Country getItem(int position) {
        return countries.get(position);
    }

    @Override
    public int getPosition(@Nullable Country item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        CountryViewHolder  holder = null;

        if (row == null || row.getTag() == null){
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new CountryViewHolder();
            holder.countryName = (TextView) row.findViewById(R.id.listCountryName);
            holder.countryId = (TextView) row.findViewById(R.id.listCountryNo);
            holder.countryShortname = (TextView) row.findViewById(R.id.countryShortName);
            holder.countryPhoneCode = (TextView) row.findViewById(R.id.countryPhoneCode);

            row.setTag(holder);
        }else{
            holder = (CountryViewHolder) row.getTag();
        }

        holder.country = getItem(position);

        holder.countryName.setText(holder.country.getName());
        holder.countryId.setText(String.valueOf(holder.country.getId()));
        holder.countryPhoneCode.setText(String.valueOf(holder.country.getPhoneCode()));
        holder.countryShortname.setText(holder.country.getShortName());

        holder.countryIdInt = holder.country.getId();

        final CountryViewHolder finalHolder = holder;

        row.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, StateListActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable("data", finalHolder.country);

                i.putExtras(mBundle);

                activity.startActivity(i);

            }
        });

        return row;
    }

    public class CountryViewHolder{

        Country country;
        TextView countryName;
        TextView countryId;
        TextView countryShortname;
        TextView countryPhoneCode;
        int countryIdInt;

    }
}
