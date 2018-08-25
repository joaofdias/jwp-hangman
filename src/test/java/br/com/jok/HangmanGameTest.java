package br.com.jok;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.jok.HangmanGame.HangmanData;

public class HangmanGameTest {
	
	@Test
	public void should_announce_a_game_lost() {
		HangmanGame game = new HangmanGame();
		game.start("Object");
		game.play('O','w','j','x','h','i','a','c','a');
		HangmanData response = game.play('t');
		assertEquals(HangmanGame.LOST, response.getMessage());
	}
	
	@Test
	public void should_announce_a_game_announce() {
		HangmanGame game = new HangmanGame();
		game.start("Object");
		game.play('O','b','j','e','c');
		HangmanData response = game.play('t');
		assertEquals(HangmanGame.WON, response.getMessage());
	}

}
