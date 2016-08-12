package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Classe Java para conexão com o Banco mongo. e insersão dos dados, Lembrando
 * que se não tiver uma coleção o proprio drive criará um automaticamente.
 * 
 * @author Danilo Silva
 *
 */
public class InsertJava {

	public static void main(String[] args) {

		// Criação do Monfo Cliente
		MongoClient client = new MongoClient();
		// criacão da data Base
		MongoDatabase db = client.getDatabase("course");
		// criação da colection
		MongoCollection<Document> collection = db.getCollection("insertTest");

		// apagar tudo do banco
		collection.drop();

		// Criação de um documento atravez do BSON
		Document smit = new Document("name", "Smith").append("age", 30).append("profession", "Programmer");

		Document joao = new Document("name", "Joao").append("age", 14).append("profession", "Zica Memo");

		collection.insertOne(smit);
		List<Document> lista = new ArrayList<Document>();
		lista.add(smit);
		lista.add(joao);
		
		/**
		 * inserindo apenas um documento. Mesmo não passando um ID, se não
		 * estiver esplicito o Mongo cria com um Id do metodo ObjectId()
		 */
		collection.insertOne(smit);
		
		//inserindo uma lista
		collection.insertMany(lista);

	}

}
