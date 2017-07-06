package com.example.shafi.databaseimport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.shafi.databaseimport.model.City;
import com.example.shafi.databaseimport.model.Country;
import com.example.shafi.databaseimport.model.State;

/**
 * Created by shafi on 7/4/2017.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance ;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null){
            this.database.close();
        }
    }

    public ArrayList<State> getStates(String country){
        ArrayList<State> states = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM states WHERE country_id = " + country, null);

        cursor.moveToFirst();
        do {
            State state = new State();

            state.setName(cursor.getString(cursor.getColumnIndex("name")));
            state.setId(cursor.getInt(cursor.getColumnIndex("id")));
            state.setCountryId(Integer.parseInt(country));

            states.add(state);
        }while (cursor.moveToNext());

        return states;
    }

    public ArrayList<Country> getCountires(){
        ArrayList<Country> countries = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM countries", null);

        cursor.moveToFirst();
        do{
            Country country = new Country();
            country.setPhoneCode(cursor.getInt(cursor.getColumnIndex("phonecode")));
            country.setId(cursor.getInt(cursor.getColumnIndex("id")));
            country.setName(cursor.getString(cursor.getColumnIndex("name")));
            country.setShortName(cursor.getString(cursor.getColumnIndex("shortname")));

            countries.add(country);
        }while (cursor.moveToNext());

        return countries;
    }

    public ArrayList<City> getCities(String state){
        ArrayList<City> cities = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM  cities WHERe state_id =  " + state, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            City city = new City();
            city.setName(cursor.getString(cursor.getColumnIndex("name")));
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            city.setStateId(cursor.getInt(cursor.getColumnIndex("state_id")));
            cities.add(city);
            cursor.moveToNext();
        }
        cursor.close();
        return cities;
    }


    public int getStateCount (int country_id){
        int numState = 0;

        Cursor cursor = database.rawQuery("SELECT * FROM states WHERE country_id = " + String.valueOf(country_id), null);

        numState = cursor.getCount();
        cursor.close();
        return numState;
    }

    public int getCityCount (int state_id){
        int numCity = 0;

        Cursor cursor = database.rawQuery("SELECT * FROM cities WHERE state_id = " + String.valueOf(state_id), null);

        numCity = cursor.getCount();
        cursor.close();
        return numCity;
    }

    public String getCountryName (String countryID){

        String cn = "";
        Cursor cursor = database.rawQuery("SELECT * FROM countries WHERE id  = " + countryID, null);
        cursor.moveToFirst();

        cn = cursor.getString(cursor.getColumnIndex("name"));
        cursor.close();

        return cn;
    }

    public String getStateName (String stateID){
        String state = "";

        Cursor cursor = database.rawQuery("SELECT * FROM states WHERE id = " + stateID, null);
        state = cursor.getString(cursor.getColumnIndex("name"));
        cursor.close();

        return state;
    }
}
