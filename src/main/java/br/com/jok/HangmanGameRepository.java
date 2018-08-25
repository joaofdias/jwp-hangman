package br.com.jok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.jok.HangmanGame.HangmanData;

@Repository
public class HangmanGameRepository {

	private Map<Long, HangmanData> map = new HashMap<>();
	
	public long create(HangmanData 	data) {
		long currentTimeAsId = System.currentTimeMillis();
		map.put(currentTimeAsId, data);
		return currentTimeAsId;
	}
	
	public HangmanData update(Long id, Character attempt) {
		HangmanData data = map.get(id);
		data.update(attempt);
		return data;
	}
}