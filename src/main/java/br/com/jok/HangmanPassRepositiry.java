package br.com.jok;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangmanPassRepositiry {

	private static final List<String> PASS_LIST = Arrays.asList("Function", "Predicate", "Supplier", "Consumer",	"Stream");
	
	public String draw() {
		int index = new Random().nextInt(PASS_LIST.size());
		return PASS_LIST.get(index);
	}


}
