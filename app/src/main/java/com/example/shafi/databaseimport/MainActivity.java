package com.example.shafi.databaseimport;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shafi.databaseimport.data.CountryListAdapter;
import com.example.shafi.databaseimport.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<Country> dbCountries  = new ArrayList<>();
    private CountryListAdapter countryAdapter;
    private Country tempCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.mainList);
        refreshData();

    }


    private void refreshData(){

        dbCountries.clear();

        DatabaseAccess  databaseAccess =  DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<Country> countriesFromDb = databaseAccess.getCountires();

        countryAdapter = new CountryListAdapter(MainActivity.this, R.layout.row_list_country, countriesFromDb);
        listView.setAdapter(countryAdapter);

        countryAdapter.notifyDataSetChanged();
        databaseAccess.close();
    }
}
