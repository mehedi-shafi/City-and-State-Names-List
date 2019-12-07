package com.example.shafi.databaseimport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.shafi.databaseimport.data.CountryListAdapter;
import com.example.shafi.databaseimport.model.Country;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {

    private ListView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String searchString = getIntent().getStringExtra("searchString");

        searchList = (ListView) findViewById(R.id.searchList);

        DatabaseAccess  databaseAccess =  DatabaseAccess.getInstance(this);
        databaseAccess.open();

        ArrayList<Country> searchResult = databaseAccess.searchCountry(searchString);

        CountryListAdapter adapter = new CountryListAdapter(SearchResult.this, R.layout.row_list_country, searchResult);
        searchList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        databaseAccess.close();
    }
}
