package com.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.JsonHelp.printJson;

public class QueryingWithFilter {
    public static void main(String[] args) {
    	// Criação do Monfo Cliente
		MongoClient client = new MongoClient();
		// criacão da data Base
		MongoDatabase db = client.getDatabase("course");
		// criação da colection
		MongoCollection<Document> collection = db.getCollection("QueryingWithFilter");

        collection.drop();

        // Inserir 10 documentos aleatorios
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                                 .append("x", new Random().nextInt(2))
                                 .append("y", new Random().nextInt(100)));
        }
        
        //modo Classico de criação de uma query
        Bson filter = new Document("x", 0)
        .append("y", new Document("$gt", 10).append("$lt", 90));
        
        //Ablicando filtro com a classe
        Bson filter2 = and(eq("x", 0), gt("y", 10), lt("y", 90));
        //procurando os registros com o filter
        List<Document> all = collection.find(filter).into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }

        long count = collection.count(filter);
        System.out.println();
        System.out.println(count);
    }
}