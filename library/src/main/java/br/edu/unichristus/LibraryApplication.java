package br.edu.unichristus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Public Library API",
				version = "1.0",
				description = "API para gerenciamento de uma biblioteca pública.",
				contact = @Contact(
						name = "Ana Paula Santos, Lívia Braga Sydrião, Marilce Lopes",
						email = "ana.paraujosanto@gmail.com",
						url = "https://github.com/anapaulasanto/public-library"
				)
		)
)
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
