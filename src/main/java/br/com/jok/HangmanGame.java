package br.com.jok;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HangmanGame {
	private static final String LETTER = "Try a letter";
	private static final String RIGHT = "You're right";
	private static final String WRONG = "You're wrong";
	protected static final String LOST = "You've lost";
	protected static final String WON = "You've won";
	private static final int MAX_OF_MISSES = 6;
	
	private String pass;
	private String board;
	private int misses;
	private String message;
	private List<Character> attempts = new LinkedList<>();

	public HangmanData start(String pass) {
		this.pass = pass;
		attempts.clear();
		board = repeat("*", pass.length());
		misses = 0;
		message = LETTER;
		return HangmanData.builder().board(board).message(message).missesCounter(misses).pass(pass).attempts(new LinkedList<>()).build();
	}

	public HangmanData play(Character... attempts) {
		HangmanData result = null;
		for (Character attempt : attempts) {
			result = play(attempt);
		}
		return result;
	}

	public HangmanData play(Character attempt) {
		attempts = updateAttempts(attempts, attempt);
		board = updateBoard(pass, board, attempt);
		misses = updateMisses(pass, board, attempt, misses);
		message = updateMessage(pass, board, attempt, misses);
		return HangmanData.builder().board(board).message(message).missesCounter(misses).pass(pass).attempts(attempts).build();
	}

	private static String repeat(String text, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(text);
		}
		return sb.toString();
	}

	private static List<Character> updateAttempts(final List<Character> attempts, Character attempt) {
		final List<Character> result = new LinkedList<>();
		result.addAll(attempts);
		result.add(attempt);
		return result;
	}

	private static String updateMessage(String pass, String board, Character attempt, int misses) {
		boolean found = pass.indexOf(attempt) > -1;
		boolean gotcha = pass.equals(board);
		boolean overMisses = misses >= 6;
		String result = WRONG;
		if (found)
			result = RIGHT;
		if (gotcha)
			result = WON;
		if (overMisses)
			result = LOST;

		return result;
	}

	private static int updateMisses(String pass, String board, Character attempt, int misses) {
		int result = misses;
		boolean notFound = pass.indexOf(attempt) == -1;
		if (notFound)
			result++;
		return result;
	}

	private static String updateBoard(String pass, String board, Character attempt) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pass.length(); i++) {
			if (attempt.equals(pass.charAt(i))) {
				sb.append(pass.charAt(i));
			} else {
				sb.append(board.charAt(i));
			}
		}
		return sb.toString();
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class HangmanData {
		private long id;
		private String board;
		private String message;
		private int missesCounter;
		@JsonIgnore
		private String pass;
		private List<Character> attempts;

		public int getMissesLimit() {
			return MAX_OF_MISSES;
		}

		public void update(Character attempt) {
			attempts.add(attempt);
		}

	}
}