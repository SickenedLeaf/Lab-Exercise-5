/*
 * Class: Hangman
 * -> This class implements the core logic for a simple Hangman game.
 *    It selects a random word from a predefined list, maintains a masked display of the word
 *    (using '*' for hidden letters), tracks missed guesses and previously guessed letters,
 *    and provides methods to start a new game, make guesses, and query game state.
 * 
 * Exclusive Functions:
 * Hangman() - Default constructor which initializes and starts a new game.
 * @param - none, @return - none
 * 
 * startNewGame() - Selects a random word, initializes the masked display array, resets misses, and clears guessed letters.
 * @param - none, @return - void
 * 
 * getDisplayWord() - Returns the current masked word as a String (hidden letters shown as '*').
 * @param - none, @return - String
 * 
 * getMisses() - Returns the number of incorrect guesses (misses).
 * @param - none, @return - int
 * 
 * isWordComplete() - Checks whether the current word has been fully guessed.
 * @param - none, @return - boolean
 * 
 * guessLetter() - Processes a guessed character (case-insensitive). Records duplicates, reveals matching letters, increments misses on incorrect guesses, and returns a feedback message.
 * @param - char c, @return - String
 * 
 * getWord() - Returns the current (true) word for the game.
 * @param - none, @return - String
 * 
 * Notes:
 * - Guesses are case-insensitive and repeated guesses are detected.
 * - The words list is fixed; adapt the words array to change the pool.
 */
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	
	private String[] words = {"program", "java", "arrays", "object", "school", "hangman", "university", "copilot", "engineering", "tictactoe"};
	private String currentWord;
	private char[] displayWord;
	private int misses;
	private ArrayList<Character> guessedLetters;
	
	public Hangman() {
		startNewGame();
	}
	
	public void startNewGame() {
		Random rand = new Random();
		currentWord = words[rand.nextInt(words.length)];
		displayWord = new char[currentWord.length()];
		for (int i = 0; i < displayWord.length; i++) displayWord[i] = '*';
		misses = 0;
		guessedLetters = new ArrayList<>();
	}
	
	public String getDisplayWord() {
		return new String(displayWord);
	}
	
	public int getMisses() {
		return misses;
	}
	
	public boolean isWordComplete() {
		return currentWord.equals(getDisplayWord());
	}
	
	public String guessLetter(char c) {
		c = Character.toLowerCase(c);
		if (guessedLetters.contains(c)) {
			return c + " is already guessed.";
		}
		guessedLetters.add(c);
		
		boolean found = false;
		for (int i = 0; i < currentWord.length(); i++) {
			if (currentWord.charAt(i) == c) {
				displayWord[i] = c;
				found = true;
			}
		}
		
		if (!found) {
			misses++;
			return c + " is not in the word.";
		}
		return "Good guess!";
	}
	
	public String getWord() {
		return currentWord;
	}
	
}
