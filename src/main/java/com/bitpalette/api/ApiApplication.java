package com.bitpalette.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bitpalette.api.services.ArtworkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ArtworkService artworkService) {
		return args -> {
			System.out.println("\n--- INICIO DE PRUEBA DE BITPALETTE ---");
			artworkService.publishArtwork("Retrato_Digital_01.png", "David Ramirez");
			System.out.println("--- FIN DE PRUEBA ---\n");
		};
	}
}
