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
import com.example.shafi.databaseimport.model.City;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shafi on 7/5/2017.
 */

public class CityListAdapter extends ArrayAdapter<City> {

    private int layoutResouce;
    private Activity activity;
    private ArrayList<City> cityList = new ArrayList<>();


    public CityListAdapter(Activity act, int resource, ArrayList<City> data){

        super (act, resource, data);
        this.layoutResouce = resource;
        this.activity = act;
        this.cityList = data;

    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Nullable
    @Override
    public City getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public int getPosition(@Nullable City item) {
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
        CityViewHolder holder = null;

        if (row == null || row.getTag() == null){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResouce, null);

            holder = new CityViewHolder();

            holder.cityName = (TextView) row.findViewById(R.id.cityRowName);
            holder.cityNo = (TextView) row.findViewById(R.id.cityRowid);

            row.setTag(holder);
        }
        else{
            holder = (CityViewHolder) row.getTag();
        }

        holder.city = getItem(position);

        holder.cityName.setText(holder.city.getName());
        holder.cityNo.setText(String.valueOf(holder.city.getId()));

        return row;
    }

    public class CityViewHolder{

        City city;
        TextView cityName;
        TextView cityNo;

    }
}
