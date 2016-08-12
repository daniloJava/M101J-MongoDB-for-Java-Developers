package com.mongodb;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.JsonHelp.printJson;

public class FindFindOneAndCount {

	public static void main(String[] args) {
		// Criação do Monfo Cliente
		MongoClient client = new MongoClient();
		// criacão da data Base
		MongoDatabase db = client.getDatabase("course");
		// criação da colection
		MongoCollection<Document> collection = db.getCollection("insertTest");
		

		// apagar tudo do banco
		collection.drop();
		
		//inserindo 10 documentos
		for (int i = 0; i < 10; i++) {
			collection.insertOne(new Document("x", i));
		}
		
		
		System.out.println("Procurando um: ");
		Document doc = collection.find().first();
		printJson(doc);
		
		System.out.println("Procuranto todos com into:");
		List<Document> list = collection.find().into(new ArrayList<Document>());
		for (Document document : list) {
			printJson(document);
			
		}
		
		/**Um adento, é que seria a mesma coisa, 
		 * a unica vantagem que você poderia parar a qualquer momneto
		 * a interação
		 * 
		 */
		System.out.println("Procuranto todos com Iteration: ");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document cur = cursor.next();
				printJson(cur);
			}
			
		} finally {
			cursor.close();
		}
	
		System.out.println("Contando os registros");
		long cont = collection.count();
		System.out.println(cont);
		

	}

}
