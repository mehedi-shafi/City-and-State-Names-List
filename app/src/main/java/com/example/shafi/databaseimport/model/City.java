package com.example.shafi.databaseimport.model;

import java.io.Serializable;

/**
 * Created by shafi on 7/4/2017.
 */

public class City implements Serializable {

    private int id;
    private int stateId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
