package com.nutech.simsppob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SimsppobApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@SuppressWarnings("null")
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry
				.addMapping("/**")
				.allowedOrigins("http://localhost:5173/")
				.allowedOrigins("http://localhost:8000/")
				.allowedOrigins("http://localhost:80/")
				.allowedOrigins("http://localhost:8080/")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SimsppobApplication.class, args);
	}

}
