package com.example.shafi.databaseimport.model;

import java.io.Serializable;

/**
 * Created by shafi on 7/4/2017.
 */

public class Country implements Serializable{

    private String name;
    private int id;
    private String shortName;
    private int phoneCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode = phoneCode;
    }
}
