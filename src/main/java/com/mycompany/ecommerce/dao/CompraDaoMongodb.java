package com.mycompany.ecommerce.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.ecommerce.entidades.Compra;
import org.bson.Document;

public class CompraDaoMongodb {
    
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    
    public CompraDaoMongodb(){
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("testes");
        collection = database.getCollection("Compra");
    }
    
    public boolean create(Compra c){
        collection.insertOne(c.toDocument());
        return true;
    }
    
    public Compra read(int id){
        MongoCursor<Document> cursor = collection.find(eq("_id",id)).iterator();
        Compra compra = null;
        if(cursor.hasNext()){
            compra = new Compra().fromDocument(cursor.next());
        }
        return compra;
    }
    
}
