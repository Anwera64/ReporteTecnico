package com.example.anton.trabajodatos.Data.Model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by anton on 11/20/17.
 */

public class PeopleModel implements Comparable<PeopleModel>, Serializable {

    private String name;

    private String lastname;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return getLastname() + " " + getName();
    }

    @Override
    public int compareTo(@NonNull PeopleModel another) {
        return this.getFullName().compareToIgnoreCase(another.getFullName());
    }
}
