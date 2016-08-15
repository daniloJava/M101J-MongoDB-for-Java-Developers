package com.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.JsonHelp.printJson;

public class Delete {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("course");
		MongoCollection<Document> collection = database.getCollection("teste");

		collection.drop();

		// insert 8 documents, with _id set to the value of the loop variable
		for (int i = 0; i < 8; i++) {
			collection.insertOne(new Document().append("_id", i));
		}
		//especificando o filtro da condição e o comando delite.
		collection.deleteOne(eq("_id", 4));

		for (Document cur : collection.find().into(new ArrayList<Document>())) {
			printJson(cur);
		}
	}
}