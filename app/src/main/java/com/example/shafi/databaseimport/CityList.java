package com.example.shafi.databaseimport;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shafi.databaseimport.data.CityListAdapter;
import com.example.shafi.databaseimport.model.City;
import com.example.shafi.databaseimport.model.State;

import java.util.ArrayList;

public class CityList extends AppCompatActivity {

    private TextView stateName, countryName, cityCount;
    private ListView cityList;
    private ArrayList<City> dbCities = new ArrayList<>();
    private State state;
    private CityListAdapter cityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        stateName = (TextView) findViewById(R.id.cityListStateName);
        countryName = (TextView) findViewById(R.id.cityListCountryName);
        cityCount = (TextView) findViewById(R.id.cityListCityCunt);
        cityList = (ListView) findViewById(R.id.cityListList);

        state = (State) getIntent().getSerializableExtra("data");

        stateName.setText(state.getName());

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        countryName.setText(databaseAccess.getCountryName(String.valueOf(state.getCountryId())));
        cityCount.setText(String.valueOf(databaseAccess.getCityCount(state.getId())));
        databaseAccess.close();

        refreshData();
    }

    private  void refreshData(){

        dbCities.clear();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        dbCities = databaseAccess.getCities(String.valueOf(state.getId()));

        databaseAccess.close();

        cityListAdapter = new  CityListAdapter(CityList.this, R.layout.row_list_city, dbCities);
        cityList.setAdapter(cityListAdapter);

        cityListAdapter.notifyDataSetChanged();

    }
}
