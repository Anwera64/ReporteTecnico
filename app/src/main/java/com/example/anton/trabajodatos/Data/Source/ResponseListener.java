package com.example.anton.trabajodatos.Data.Source;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;

import java.util.ArrayList;

/**
 * Created by anton on 11/21/17.
 */

public interface ResponseListener {
    void onResponseSuccess(ArrayList<PeopleModel> contacts);

    void onResponseError(String error);

    void onDeleteSuccess();
}
