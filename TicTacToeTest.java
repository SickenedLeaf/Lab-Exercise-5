/*
 * Class: TicTacToeTest
 * -> This class contains JUnit 5 tests for the TicTacToe game logic.
 *    It initializes a fresh TicTacToe instance before each test and verifies core behaviors:
 *    initial turn, move application and turn switching, win detection, invalid move rejection, and reset behavior.
 * 
 * Exclusive Functions:
 * setUp() - JUnit @BeforeEach method that initializes a new TicTacToe game before each test.
 * @param - none, @return - void
 * 
 * testInitialTurnIsX() - Verifies that a new game starts with player 'X'.
 * @param - none, @return - void
 * 
 * testAddMoveAndSwitchTurn() - Confirms addMove places a mark, switches the turn to 'O', and that the board contains 'X' where placed.
 * @param - none, @return - void
 * 
 * testWinnerRow() - Plays a sequence of moves that gives X a winning row and asserts winner detection.
 * @param - none, @return - void
 * 
 * testInvalidMoveOnFilledCell() - Ensures attempting to move on an occupied cell is rejected.
 * @param - none, @return - void
 * 
 * testResetGame() - Verifies resetGame clears the board and resets the current player to 'X'.
 * @param - none, @return - void
 * 
 * Notes:
 * - Tests use JUnit 5 annotations and assertions.
 * - displayBoard() string output is used in some assertions for simplicity.
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {
	
	private TicTacToe game;
	
	@BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    @Test
    void testInitialTurnIsX() {
        assertEquals('X', game.getTurn());
    }

    @Test
    void testAddMoveAndSwitchTurn() {
        assertTrue(game.addMove(0, 0));
        assertEquals('O', game.getTurn()); // turn switches
        assertEquals('X', game.displayBoard().charAt(0)); // X placed
    }

    @Test
    void testWinnerRow() {
        game.addMove(0, 0); // X
        game.addMove(1, 0); // O
        game.addMove(0, 1); // X
        game.addMove(1, 1); // O
        game.addMove(0, 2); // X wins
        assertTrue(game.hasWinner());
        assertEquals('X', game.getWinner());
    }

    @Test
    void testInvalidMoveOnFilledCell() {
        game.addMove(0, 0);
        assertFalse(game.addMove(0, 0)); // cannot place again
    }

    @Test
    void testResetGame() {
        game.addMove(0, 0);
        game.resetGame();
        assertEquals('X', game.getTurn());
        assertEquals(" | | \n | | \n | | \n", game.displayBoard().replaceAll("[^XO|\\n ]", ""));
    }

}
