package com.EventoApp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = { "com.eventoapp.app.models" })
@SpringBootApplication
public class AppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
