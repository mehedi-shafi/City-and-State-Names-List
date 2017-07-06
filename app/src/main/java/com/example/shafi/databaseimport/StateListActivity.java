package com.example.shafi.databaseimport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shafi.databaseimport.data.StateListAdapter;
import com.example.shafi.databaseimport.model.Country;
import com.example.shafi.databaseimport.model.State;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StateListActivity extends AppCompatActivity {

    private TextView countryTitle, noOfStates, phoneCode;
    private ListView stateList;
    private ArrayList<State> dbStates = new ArrayList<>();
    private StateListAdapter stateListAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);

        countryTitle = (TextView) findViewById(R.id.stateCountryName);
        noOfStates = (TextView) findViewById(R.id.stateNoStates);
        phoneCode = (TextView) findViewById(R.id.stateCountryPhoneCode);

        Country country = (Country) getIntent().getSerializableExtra("data");

        countryTitle.setText(country.getName());

        //getting number of states in the country
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int stateCount = databaseAccess.getStateCount(country.getId());
        noOfStates.setText(String.valueOf(stateCount));
        phoneCode.setText(String.valueOf(country.getPhoneCode()));
        databaseAccess.close();

        stateList = (ListView) findViewById(R.id.stateList);

        refreshData(country.getId());
    }

    public void refreshData(int id){
        dbStates.clear();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        dbStates = databaseAccess.getStates(String.valueOf(id));
        databaseAccess.close();

        stateListAdapter = new StateListAdapter(StateListActivity.this, R.layout.row_list_state, dbStates);
        stateList.setAdapter(stateListAdapter);

        stateListAdapter.notifyDataSetChanged();

    }
}
