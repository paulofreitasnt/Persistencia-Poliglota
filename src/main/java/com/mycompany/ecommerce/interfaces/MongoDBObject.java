package com.mycompany.ecommerce.interfaces;

import org.bson.Document;

public interface MongoDBObject <T>{

    Document toDocument();
    T fromDocument(Document doc);
    
    
}
