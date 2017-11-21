package com.example.anton.trabajodatos.Data.Source;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Created by anton on 11/21/17.
 */

public class MyMongoDB {
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase db;

    public MyMongoDB() {
        uri = new MongoClientURI("mongodb://Anwera64:Anwera97@ds113746.mlab.com:13746/reporte_tecnico");
        client = new MongoClient(uri);
        db = client.getDatabase("reporte_tecnico");
    }

    public MongoDatabase getDb() {
        return db;
    }
}
