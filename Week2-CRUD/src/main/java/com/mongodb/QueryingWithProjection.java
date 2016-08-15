package com.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.JsonHelp.printJson;

/** Classe para projetar todos os resultados ddos registros
 * 
 * @author Danilo silva
 *
 */
public class QueryingWithProjection {

	public static void main(String[] args) {

		// Criação do Monfo Cliente
		MongoClient client = new MongoClient();
		// criacão da data Base
		MongoDatabase db = client.getDatabase("course");
		// criação da colection
		MongoCollection<Document> collection = db.getCollection("QueryingWithProjection");

		collection.drop();

		//  Inserir 10 documentos aleatorios
		for (int i = 0; i < 10; i++) {
			collection.insertOne(new Document().append("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)).append("i", i));
		}
		
		
		Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

		// a ideia é projetar as informações conforme informado no documento.
		Bson projection = new Document("y", 1).append("i", 1).append("_id", 0);
		
		//mesma forma, mas inclui a reprodução do atributo Y e I tambem excluindo o ID  
		Bson projection2 = fields(include("y", "i"), excludeId());
		
		List<Document> all = collection.find(filter).projection(projection).into(new ArrayList<Document>());

		for (Document cur : all) {
			printJson(cur);
		}
	}
}
