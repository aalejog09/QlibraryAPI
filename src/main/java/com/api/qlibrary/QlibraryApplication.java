package com.api.qlibrary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Aalejo
 *
 */ 
@SpringBootApplication(exclude = {
                BatchAutoConfiguration.class,
                JmxAutoConfiguration.class
        },
        excludeName = {
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        })
@EnableTransactionManagement
public class QlibraryApplication {
	
	@Bean 
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer () {

			
			@Value("#{'${cors.origin.main}'.split(',')}") 
			 public String[] myList;
			
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/**")
				.allowedMethods("GET","POST")
				.allowedOrigins(myList);
				
				
			}
		};
	}
	

	public static void main(String[] args) {
		SpringApplication.run(QlibraryApplication.class, args);
	}
	
}
