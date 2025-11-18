/*
 * Class: TicTacToe
 * -> This class implements the core game logic for a 3x3 TicTacToe game.
 *    It manages the board state, current player turn, move validation, win/draw detection,
 *    and offers utility functions for resetting and displaying the board.
 * 
 * Exclusive Functions:
 * TicTacToe() - Default constructor that initializes the board and starts a new game.
 * @param - none, @return - none
 * 
 * addMove() - Attempts to place the current player's mark at the given row and column.
 *             Validates bounds and occupancy; on success stores the mark and switches turn.
 * @param - int row, int col, @return - boolean (true if move applied)
 * 
 * getTurn() - Returns whose turn it currently is ('X' or 'O').
 * @param - none, @return - char
 * 
 * hasWinner() - Returns whether a winner exists.
 * @param - none, @return - boolean
 * 
 * getWinner() - Returns the winning mark ('X' or 'O') or ' ' if there is no winner.
 * @param - none, @return - char
 * 
 * isBoardFull() - Checks whether the board is full (draw condition).
 * @param - none, @return - boolean
 * 
 * resetGame() - Resets the board to empty and sets the current player to 'X'.
 * @param - none, @return - void
 * 
 * displayBoard() - Returns a simple string representation of the board for debugging.
 * @param - none, @return - String
 * 
 * Notes:
 * - The board is a 3x3 char array, using ' ' (space) for empty cells.
 * - addMove switches the turn after a successful placement.
 * - getWinner checks rows, columns, and both diagonals.
 */
public class TicTacToe {
	
	private char[][] board;
	private char currentPlayer;
	
	/** Initializes a new game with an empty board and X starting. */
	public TicTacToe() {
		board = new char[3][3];
		resetGame();
	}
	
	public boolean addMove(int row, int col) {
		if (row < 0 || row >=3 || col <0 || col >=3) return false;
		if (board[row][col] != ' ') return false; // already filled
		board[row][col] = currentPlayer;
		switchTurn();
		return true;
	}
	
	/** Returns whose turn it is (X or O). */
	public char getTurn() {
		return currentPlayer;
	}
	
	/** Switches turn between X and O. */
	private void switchTurn() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}
	
	/** Checks if there is a winner. */
	public boolean hasWinner() {
		return getWinner() != ' ';
	}
	
	/** Returns the winner ('X' or 'O'), or ' ' if none yet. */
	public char getWinner() {
		// Rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != ' ' &&
				board[i][0] == board[i][1] &&
				board[i][1] == board[i][2]) {
				return board[i][0];
			}
		}
		
		// Columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != ' ' &&
				board[0][j] == board[1][j] &&
				board[1][j] == board[2][j]) {
				return board[0][j];
			}
		}
		
		// Diagonals
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2];
        }
        
        return ' ';
	}
	
	/** Checks if the board is full (draw). */
	public boolean isBoardFull() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				if (board[i][j] == ' ') return false;
		return true;
	}
	
	/** Resets the game to empty board and X starts. */
	public void resetGame() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';
		currentPlayer = 'X';
	}
	
	/** For debugging: returns board as string. */
    public String displayBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(board[i][0]).append("|")
              .append(board[i][1]).append("|")
              .append(board[i][2]).append("\n");
        }
        return sb.toString();
    }


}
