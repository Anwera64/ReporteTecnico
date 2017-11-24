package com.example.anton.trabajodatos.Data.Source;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by anton on 11/21/17.
 */

public interface RestClient {

    @GET("/api/1/databases/reporte_tecnico/collections/Contactos/?apiKey=7s0IYtwz7COkLlq2PoLhm8a98qJVT1PL")
    Call<ArrayList<PeopleModel>> getContacts();

    @POST("/api/1/databases/reporte_tecnico/collections/Contactos/?apiKey=7s0IYtwz7COkLlq2PoLhm8a98qJVT1PL")
    Call<Void> addContact(@Body PeopleModel contact);

    @DELETE("/api/1/databases/reporte_tecnico/collections/Contactos/{id}?apiKey=7s0IYtwz7COkLlq2PoLhm8a98qJVT1PL")
    Call<Void> deleteContact(@Path("id") String id);
}
