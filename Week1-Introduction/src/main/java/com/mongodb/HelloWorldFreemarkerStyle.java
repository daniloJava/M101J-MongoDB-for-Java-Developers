package com.mongodb;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**FreeMarker é um Framework que trabalha de uma forma 
 * generalizada do Html 
 * 
 * http://freemarker.org/
 * 
 * @author DaniloJava
 *
 */
public class HelloWorldFreemarkerStyle {

	public static void main(String[] args) {
		//configuração do Framewor Freemarker
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		
		try {
			//Busca do tamplate criado na pasta resources.
			Template helloTemplate = config.getTemplate("hello.ftl");
			
			StringWriter writer = new StringWriter();
			//Mapa para escrever FreeMarker onde está o ${name} do arquivo hello.ftl
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Freemarker");
			
			//chama o processamento
			helloTemplate.process(helloMap, writer);
			
			//escreve no console
			System.out.println(writer);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
