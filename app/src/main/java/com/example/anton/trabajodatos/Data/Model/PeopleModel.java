package com.example.anton.trabajodatos.Data.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by anton on 11/20/17.
 */

public class PeopleModel implements Comparable<PeopleModel>, Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("cellphone")
    @Expose
    private String cellphone;
    @SerializedName("_id")
    @Expose
    private ID id;

    public PeopleModel(String name, String lastname, String cellphone) {
        this.name = name;
        this.lastname = lastname;
        this.cellphone = cellphone;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getLastname() {
        return lastname == null ? "" : lastname;
    }

    public String getId() {
        return id.getId();
    }

    public String getFullName() {
        return getLastname() + " " + getName();
    }

    public String getCellphone() {
        return cellphone;
    }

    @Override
    public int compareTo(@NonNull PeopleModel another) {
        return this.getFullName().compareToIgnoreCase(another.getFullName());
    }

    public static class ID {
        @SerializedName("$oid")
        private String id;

        public String getId() {
            return id;
        }
    }
}
