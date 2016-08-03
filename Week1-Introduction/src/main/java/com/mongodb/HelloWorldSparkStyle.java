package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**Primeiro contato com o Spark
 * 
 * @author Danilo Silva
 *
 */
public class HelloWorldSparkStyle {

	public static void main(String[] args) {
		//Quando for estartar um Webapp e quando for reduzir a URL ira chamar esse metodo.
		Spark.get( new Route("/") {
			
			public Object handle(final Request arg0, final Response arg1) {
				return "Hello World from Spark";
			}
		});
	}
}
