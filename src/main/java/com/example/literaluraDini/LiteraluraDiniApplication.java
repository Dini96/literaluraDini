package com.example.literaluraDini;

import com.example.literaluraDini.repository.AutorRepository;
import com.example.literaluraDini.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.literaluraDini.principal.Principal;

@SpringBootApplication
public class LiteraluraDiniApplication  implements CommandLineRunner {


	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraDiniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository, libroRepository);
		principal.mostrarElMenu();

	}
}
