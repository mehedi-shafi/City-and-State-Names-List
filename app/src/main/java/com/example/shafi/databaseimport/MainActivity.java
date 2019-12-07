package com.example.shafi.databaseimport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText searchBox;
    private Button runSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.mainList);
        searchBox = (EditText) findViewById(R.id.search_text);

        refreshData();

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private void search(){
        String searchString = searchBox.getText().toString().trim();
        if (null != searchString && searchString.compareToIgnoreCase("") != 0){
            Intent i = new Intent(MainActivity.this, SearchResult.class);
            i.putExtra("searchString", searchString);
            startActivity(i);
        }
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
