package com.example.literaluraDini;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.literaluraDini.principal.Principal;

@SpringBootApplication
public class LiteraluraDiniApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraDiniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarElMenu();

	}
}
