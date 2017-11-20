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
    @JsonProperty("Grado")
    private String grade;
    @JsonProperty("Correo")
    private String email;
    @JsonProperty("Cargo")
    private String cargo;

    private String id;
    private String school;
    private int icon;
    private boolean checked;

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

    /*public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }*/

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @JsonIgnore
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @JsonIgnore
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getFullName(){
        return getLastname() + " " + getName();
    }

    @Override
    public int compareTo(@NonNull PeopleModel another) {
        return this.getFullName().compareToIgnoreCase(another.getFullName());
    }
}
