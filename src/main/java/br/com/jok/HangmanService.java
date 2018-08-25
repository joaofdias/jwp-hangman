package br.com.jok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jok.HangmanGame.HangmanData;

@Service
public class HangmanService {
	
	@Autowired
	HangmanGameRepository gameRepository;
	
	@Autowired
	HangmanPassRepositiry passRepository;
	
	public HangmanData start() {
		HangmanGame game = new HangmanGame();
		HangmanData data = game.start(passRepository.draw());
		Long id = gameRepository.create(data);
		data.setId(id);
		return data;
	}
	
	public HangmanData play(Long id, Character attempt) {
		HangmanGame game = new HangmanGame();		
		HangmanData data = gameRepository.update(id, attempt);
		game.start(data.getPass());
		return game.play(data.getAttempts().toArray(new Character[data.getAttempts().size()]));
	}

}
