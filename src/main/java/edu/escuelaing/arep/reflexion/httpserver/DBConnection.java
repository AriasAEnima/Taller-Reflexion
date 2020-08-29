/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpserver;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author J. Eduardo Arias
 */
public class DBConnection {
    public static void testConection() {
         MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://admin:<password>@testquery.h2f1e.mongodb.net/<dbname>?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
        DBCollection collection = (DBCollection) database.getCollection("users");

        BasicDBObject document = new BasicDBObject();
        document.put("name", "lokesh");
        document.put("website", "howtodoinjava.com");

        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("addressLine1", "Sweet Home");
        documentDetail.put("addressLine2", "Karol Bagh");
        documentDetail.put("addressLine3", "New Delhi, India");

        document.put("address", documentDetail);
        collection.insert(document);
    
    }
    
    private static DBObject createDBObject() {
            BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

            docBuilder.append("_id","" );
            docBuilder.append("name", "");
            docBuilder.append("role", "");
            docBuilder.append("isEmployee", "");
            return docBuilder.get();
    }
     
}
