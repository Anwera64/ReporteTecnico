package com.example.anton.trabajodatos.Data.Model;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by anton on 11/20/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleModel implements Comparable<PeopleModel>, Serializable {

    @JsonProperty("Nombre")
    private String name;
    @JsonProperty("Apellido")
    private String lastname;

    private String id;

    @SuppressWarnings("unused")
    public PeopleModel() {
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname == null ? "" : lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName(){
        return getLastname() + " " + getName();
    }

    @Override
    public int compareTo(@NonNull PeopleModel another) {
        return this.getFullName().compareToIgnoreCase(another.getFullName());
    }
}
