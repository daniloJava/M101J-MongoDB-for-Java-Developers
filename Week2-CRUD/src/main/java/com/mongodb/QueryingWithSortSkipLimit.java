package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.JsonHelp.printJson;

/**Ordenando as querys de qualquer forma.
 * e utilizando com classes do Bson e os 
 * 
 * @author Danilo Silva
 *
 */
public class QueryingWithSortSkipLimit {
	public static void main(String[] args) {
		// Criação do Monfo Cliente
		MongoClient client = new MongoClient();
		// criacão da data Base
		MongoDatabase db = client.getDatabase("course");
		// criação da colection
		MongoCollection<Document> collection = db.getCollection("QueryingWithSortSkipLimit");

		collection.drop();

		// Inserir 10 documentos aleatorios
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				collection.insertOne(new Document().append("i", i).append("j", j));
			}
		}

		// criando uma projesão de I e J sem o ID.
		Bson projection = fields(include("i", "j"), excludeId());
		// ordenando crescente os registros pelo atributo i
		Bson sort1 = new Document("i", 1);
		// ordenando decrecente os registros pelo atributo j
		Bson sort2 = new Document("j", -1);
		
		//mesmo principio assima, mas com o metodo Bilder 
		Bson sort = descending("j", "i");

		List<Document> all = collection.find().projection(projection)
				.sort(sort)// aplicando a ordenação
				.skip(20)//registros de forma inversa
				.limit(50)//limite de registros
				.into(new ArrayList<Document>());

		for (Document cur : all) {
			printJson(cur);
		}
	}
}
