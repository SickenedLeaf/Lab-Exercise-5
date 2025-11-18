/*
 * Class: LE51View
 * -> This class builds the JavaFX user interface for a TicTacToe exercise.
 *    It creates the board UI, connects the UI to the TicTacToe game logic, displays turn and result status,
 *    handles player moves and resets, and highlights the winning line when the game ends.
 * 
 * Exclusive Functions:
 * getContent() - Builds and returns the main VBox layout containing the title, status label, game grid, and reset button.
 * @param - none, @return - VBox
 * 
 * handleMove() - Processes a user's click on a cell: validates the move, updates the game model and UI, sets status text, detects a win or draw, and prevents further moves when the game is over.
 * @param - Button cell, int row, int col, @return - void
 * 
 * highlightWinner() - Scans the board for the winning line and applies winner styling to the three winning cells.
 * @param - none, @return - void
 * 
 * styleWinner() - Applies green "winner" styling to the provided cell buttons.
 * @param - Button... buttons, @return - void
 * 
 * Notes:
 * - This view relies on a TicTacToe model class that exposes getTurn(), addMove(row,col), hasWinner(), getWinner(), isBoardFull(), and resetGame().
 * - Styling and button sizing are applied inline to keep the view self-contained.
 */
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LE51View {
	
	private TicTacToe game;
	private Label statusLabel;
	private boolean gameOver = false; // prevent moves after winner
	private Button[][] cells;
	
	public VBox getContent() {
	
		game = new TicTacToe();
		
		
		// VBox for LE51 Content
		VBox le51Content = new VBox(10);
		le51Content.setAlignment(Pos.CENTER);
					
		Label title = new Label("TicTacToe");
		title.setFont(Font.font("Times New Roman", 32));
		title.setTextFill(Color.WHITE);
		
		statusLabel = new Label("Turn: X");
		statusLabel.setTextFill(Color.web("#A78BFA"));
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		
		cells = new Button[3][3];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Button cell = new Button(" ");
				cell.setPrefSize(80, 80);
				cell.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; "
                        + "-fx-background-color: #3E3E4E; -fx-text-fill: white;");
				final int row = i, col = j;
				cell.setOnAction(e -> handleMove(cell, row, col));
				cells[i][j] = cell;
				grid.add(cell, j, i);
			}
		}
		
		Button resetBtn = new Button("Reset");
		ButtonStyler.styleActionButton(resetBtn);
		resetBtn.setOnAction(e -> {
			game.resetGame();
			statusLabel.setText("Turn: X");
			gameOver = false;
			for (Button[] row : cells) {
				for (Button b: row) {
					b.setText(" ");
					b.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; "
                            + "-fx-background-color: #3E3E4E; -fx-text-fill: white;");
				}
			}
		});
		
		le51Content.getChildren().addAll(title, statusLabel, grid, resetBtn);
		return le51Content;
	}
	
	private void handleMove(Button cell, int row, int col) {
		
		if (gameOver) return; // stop moves if game ended
		if(!cell.getText().equals(" ")) return; // already filled
		
		char turn = game.getTurn();
		if (game.addMove(row, col)) {
			cell.setText(String.valueOf(turn));
			if (game.hasWinner()) {
				statusLabel.setText("Winner: " + game.getWinner());
				gameOver = true;
				highlightWinner();
			} else if (game.isBoardFull()) {
				statusLabel.setText("Draw!");
				gameOver = true;
			} else {
				statusLabel.setText("Turn: " + game.getTurn());
			}
		}
	}
	
	/** Highlights the winning line in green */
	private void highlightWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!cells[i][0].getText().equals(" ") &&
                cells[i][0].getText().equals(cells[i][1].getText()) &&
                cells[i][1].getText().equals(cells[i][2].getText())) {
                styleWinner(cells[i][0], cells[i][1], cells[i][2]);
                return;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (!cells[0][j].getText().equals(" ") &&
                cells[0][j].getText().equals(cells[1][j].getText()) &&
                cells[1][j].getText().equals(cells[2][j].getText())) {
                styleWinner(cells[0][j], cells[1][j], cells[2][j]);
                return;
            }
        }
        // Check diagonals
        if (!cells[0][0].getText().equals(" ") &&
            cells[0][0].getText().equals(cells[1][1].getText()) &&
            cells[1][1].getText().equals(cells[2][2].getText())) {
            styleWinner(cells[0][0], cells[1][1], cells[2][2]);
            return;
        }
        if (!cells[0][2].getText().equals(" ") &&
            cells[0][2].getText().equals(cells[1][1].getText()) &&
            cells[1][1].getText().equals(cells[2][0].getText())) {
            styleWinner(cells[0][2], cells[1][1], cells[2][0]);
        }
    }

    /** Applies green styling to winning tiles */
    private void styleWinner(Button... buttons) {
        for (Button b : buttons) {
            b.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; "
                     + "-fx-background-color: #2E7D32; -fx-text-fill: white;");
        }
    }

}
