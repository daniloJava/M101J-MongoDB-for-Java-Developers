package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


/**Unificando o o conceito de Spark e Freemarker
 * 
 * Uma estrutura basica dos dois framework de e sua estrutura de Site Funcionando.
 * 
 * @author DaniloJava
 *
 */
public class HelloWorldSparkFreemarkerStyle {

	public static void main(String[] args) {
		//Configuração de todo Freemarker. 
		//Alterando o HelloWorldFreemarkerStyle para HelloWorldSparkFreemarkerStyle.class 
		//a variavel configuration precisa ser Final para não dar problemas nas Heranças.
		final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                HelloWorldSparkFreemarkerStyle.class, "/");

        //
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request,
                                 final Response response) {
            	//Fora do blodo do Try para que seja possivel retornar
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Freemarker");

                    helloTemplate.process(helloMap, writer);
                } catch (Exception e) {
                    halt(500); //Pequeno "gato" porque o Spark retorna Error 500
                    e.printStackTrace();
                }
                return writer;
            }
        });
		
	}

}
