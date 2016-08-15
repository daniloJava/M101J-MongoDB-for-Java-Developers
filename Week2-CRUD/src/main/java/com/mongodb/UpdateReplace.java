package com.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.JsonHelp.printJson;

/**Forma diferentes de atualizar os registros
 * 
 * @author Danilo Silva
 *
 */
public class UpdateReplace {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("course");
		MongoCollection<Document> collection = database.getCollection("teste");

		collection.drop();

		// inseri 8 documentos, com  _id e x com o mesmo valor, e todo mundo true
		for (int i = 0; i < 8; i++) {
			collection.insertOne(new Document().append("_id", i).append("x", i).append("y", true));
		}
		//atualizando apenas um documento que x for igual a 5, caso não tenha ele add um novo registro
		 collection.replaceOne(eq("x", 5), new Document()
		 .append("x", 20)
		 .append("updated", true));
		
		 //ele adiciona mais um atributo em com as condições por causa do operador set
		 collection.updateOne(eq("x", 5), new Document("$set",
		 new Document("x", 20)
		 .append("updated", true)));
		
		 //igual a instrução acima, porem uma forma mais curta de escrever com a classe Comine.
		 collection.updateOne(eq("x", 5), combine(set("x", 20), set("updated",
		 true)));
		
		//se não tiver a condição de atualizaçõa, ele cria um novo registro.
		 collection.updateOne(eq("x", 9), combine(set("x", 20), set("updated",
		 true)),
		 new UpdateOptions().upsert(true));

		//incrementando 1 nos documentos da condição 
		collection.updateMany(gte("x", 5), inc("x", 1));
		
		
		for (Document cur : collection.find().into(new ArrayList<Document>())) {
			printJson(cur);
		}
	}
}
