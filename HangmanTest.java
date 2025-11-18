/*
 * Class: HangmanTest
 * -> This class contains JUnit 5 tests for the Hangman game logic.
 *    It prepares a fresh Hangman instance for each test and verifies core behaviors:
 *    that the word display starts masked, correct guesses reveal letters, incorrect guesses increment misses,
 *    repeated guesses are detected, and a word can be completed by guessing all letters.
 * 
 * Exclusive Functions:
 * setUp() - JUnit @BeforeEach method that initializes a new Hangman game before each test.
 * @param - none, @return - void
 * 
 * testWordStartsHidden() - Verifies the initial display contains only '*' characters.
 * @param - none, @return - void
 * 
 * testCorrectGuessRevealsLetter() - Ensures a correct guess reveals at least the guessed letter in the display.
 * @param - none, @return - void
 * 
 * testIncorrectGuessIncreasesMisses() - Confirms that an incorrect guess increments the miss counter.
 * @param - none, @return - void
 * 
 * testAlreadyGuessedLetterMessage() - Verifies that guessing the same letter twice returns an "already" message.
 * @param - none, @return - void
 * 
 * testWordCompletion() - Guesses all letters of the word and asserts isWordComplete() returns true.
 * @param - none, @return - void
 * 
 * Notes:
 * - Tests use JUnit 5 annotations and assertions.
 * - The Hangman implementation selects a random word; tests assume at least one predictable behavior (e.g., first-letter guesses).
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanTest {
	
	private Hangman game;

    @BeforeEach
    void setUp() {
        game = new Hangman();
        game.startNewGame(); // ensure fresh word
    }

    @Test
    void testWordStartsHidden() {
        String display = game.getDisplayWord();
        assertTrue(display.chars().allMatch(ch -> ch == '*'));
    }

    @Test
    void testCorrectGuessRevealsLetter() {
        String word = game.getWord();
        char firstLetter = word.charAt(0);
        game.guessLetter(firstLetter);
        assertTrue(game.getDisplayWord().contains(String.valueOf(firstLetter)));
    }

    @Test
    void testIncorrectGuessIncreasesMisses() {
        int missesBefore = game.getMisses();
        game.guessLetter('z'); // unlikely to be in word
        assertEquals(missesBefore + 1, game.getMisses());
    }

    @Test
    void testAlreadyGuessedLetterMessage() {
        char c = game.getWord().charAt(0);
        game.guessLetter(c);
        String result = game.guessLetter(c);
        assertTrue(result.contains("already"));
    }

    @Test
    void testWordCompletion() {
        String word = game.getWord();
        for (char c : word.toCharArray()) {
            game.guessLetter(c);
        }
        assertTrue(game.isWordComplete());
    }

}
