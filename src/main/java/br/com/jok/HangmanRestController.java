package br.com.jok;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.Data;

@RestController
public class HangmanRestController {

	@GetMapping("/api/games")
	public HangmanResponse start(String action) {
		if ("start".equals(action)) {
			HangmanResponse response = HangmanResponse.builder().
										board("******").
										message("Try a letter").
										misses("0/6").build();
			return response;
		}
		
		return null;
	}
	
	@GetMapping("/api/games/{id}/attempts")
	public HangmanResponse play(@PathVariable Long id, String action, Character attempt) {
		HangmanResponse response = null;
		if ("play".equals(action)) {
			if (Arrays.asList('F', 'u', 'n', 'c', 't').contains(attempt)) {
				response = HangmanResponse.builder().
							board("F*****").
							message("You are right!").
							misses("0/6").build();
			} else {
				response = HangmanResponse.builder().
						board("******").
						message("You are wrong!").
						misses("1/6").build();
				
			}
		}
		return response;
	}
	
	@Data
	@Builder
	static class HangmanResponse {
		private String board;
		private String message;
		private String misses;
	}
	
}

	
