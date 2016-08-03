package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**Classe para ferificar como funciona o Get com Spark
 * 
 * Como o Spark rodando dentro de um servidor ou Dentro de jetty
 * ele contem como qualquer server o Get/post etc.
 * 
 * Semelhante o que já vimos a unica diferença são os demais getters
 * 
 * @author daniloJava
 *
 */
public class SparkRoutes {
    public static void main(String[] args) {
    	//Get normal, com acesso ao barra e imprime o Retorno
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "Hello World\n";
            }
        });
      //Get normal, porem passando o Test na URL com acesso ao barra e imprime o Retorno
        Spark.get(new Route("/test") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "This is a test page\n";
            }
        });
        
        /**Esse :thing, é onde pode ser agregado qualquer valor
         * onde será passado para o Request ou seja
         * chamando no navegador /echo/MongoDb - vai funcionar e imprimir MongoDb ou
         * /echo/JAVA - vai imprimir JAVA..
         * 
         */
        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(final Request request, final Response response) {
                return request.params(":thing");
            }
        });
    }
}