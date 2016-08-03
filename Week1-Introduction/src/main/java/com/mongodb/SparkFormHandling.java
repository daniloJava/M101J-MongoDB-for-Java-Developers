package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**Basicamente a mesma estrutura do HelloWorldSparkFreemarkerStyle
 * para o acesso ao formulário..
 * 
 * a Mudança é o Post, onde é a recuperação do dado enviado.
 * 
 * @author Danilo Silva
 *
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        // Configuração padrão Freemarker
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        // Configuração do GET padrão de acesso
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                try {
                	//colocando as frutas em um mapa
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits",
                            Arrays.asList("Maça", "Laranja", "Banana", "Pêssego"));
                    
                    //Configuração normal do Freemarker com a "pagina" fruitPicker
                    Template fruitPickerTemplate =
                            configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPickerTemplate.process(fruitsMap, writer);
                    return writer;

                } catch (Exception e) {
                    halt(500);
                    return null;
                }
            }
        });
        
        //Mapeando a submissão do form
        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(final Request request, final Response response) {
            	//recupera o teste selecionado peo request.
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Porque você não escolheu nenhuma pruta?";
                }
                else {
                    return "Sua Fruta favorita é " + fruit;
                }
            }
        });
    }
}