package br.com.jok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JwpHangmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwpHangmanApplication.class, args);
	}
	
	@GetMapping("/api/info")
	public String getInfo() {
		return "Hangman";
	}
}
