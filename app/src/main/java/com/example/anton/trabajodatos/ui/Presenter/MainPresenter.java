package com.example.anton.trabajodatos.ui.Presenter;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.example.anton.trabajodatos.Data.Source.MyMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 11/21/17.
 */

public class MainPresenter {

    private MongoDatabase db;

    public MainPresenter() {
        db = new MyMongoDB().getDb();
    }

    public ArrayList<PeopleModel> getContacts() {
        MongoCollection collection = db.getCollection("Contactos");

        List<Document> docs = (List<Document>) collection.find().into(new ArrayList());
        ArrayList<PeopleModel> contacts = new ArrayList<>();

        for (Document doc : docs) {
            PeopleModel contact = new PeopleModel();
            contact.setName((String) doc.get("name"));
            //contact.setLastname((String) doc.get("lastname"));
            contact.setAge((Integer) doc.get("age"));

            contacts.add(contact);
        }
        return contacts;
    }
}
