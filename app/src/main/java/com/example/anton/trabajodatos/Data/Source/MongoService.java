package com.example.anton.trabajodatos.Data.Source;

import android.util.Log;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anton on 11/21/17.
 */

public class MongoService {

    private Retrofit retrofit;

    public MongoService() {

        Gson gson = new GsonBuilder().create();

        String URL_ROOT = "https://api.mlab.com";
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_ROOT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getContacts(final ResponseListener listener) {
        RestClient restClient = retrofit.create(RestClient.class);

        Call<ArrayList<PeopleModel>> call = restClient.getContacts();

        call.enqueue(new Callback<ArrayList<PeopleModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PeopleModel>> call, Response<ArrayList<PeopleModel>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PeopleModel>> call, Throwable t) {
                listener.onError(t.toString());
            }
        });
    }

    public void loadContact(PeopleModel contact, final FormPostListener listener) {
        RestClient restClient = retrofit.create(RestClient.class);

        Call<Void> call = restClient.addContact(contact);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("POST", "Funciona");
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("POST", "No Funciona");
                listener.onError(t.toString());
            }
        });
    }

}
