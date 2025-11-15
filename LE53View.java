package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LE53View {
	
	/* ============================================================
	   LE33: Grade Distribution Exercise
	   ============================================================
	*/
	
	/**
	 * Constructs and returns the full layout for the Grade Distribution Exercise.
	 * This includes input fields for grade categories (A–F), a button to trigger
	 * graph generation, and labels/containers to display the results.
	 *
	 * <p>When the "Show Graph" button is clicked, the input is validated and passed
	 * to the {@link GradeDistribution} class. The resulting star graph is shown as text,
	 * and the bar graph is rendered as a JavaFX node.</p>
	 *
	 * @return a {@link VBox} containing all UI components for LE33.
	 */
	public VBox getContent() {
		
		VBox le53Content = new VBox(10);
		le53Content.setAlignment(Pos.CENTER);
		
		Label label53 = new Label("Grade Distribution Exercise");
		label53.setFont(Font.font("Times New Roman", 32));
		label53.setTextFill(Color.WHITE);
		
		Label labelGrades = new Label("Enter The Grades");
		labelGrades.setFont(Font.font("Arial", 16));
		labelGrades.setTextFill(Color.WHITE);
		
		Label promptA = new Label("A: ");
		promptA.setTextFill(Color.WHITE);			
		TextField inputFieldA = new TextField();
		inputFieldA.setMaxWidth(100);
		
		Label promptB = new Label("B: ");
		promptB.setTextFill(Color.WHITE);			
		TextField inputFieldB = new TextField();
		inputFieldB.setMaxWidth(100);
		
		Label promptC = new Label("C: ");
		promptC.setTextFill(Color.WHITE);			
		TextField inputFieldC = new TextField();
		inputFieldC.setMaxWidth(100);
		
		Label promptD = new Label("D: ");
		promptD.setTextFill(Color.WHITE);			
		TextField inputFieldD = new TextField();
		inputFieldD.setMaxWidth(100);
		
		Label promptF = new Label("F: ");
		promptF.setTextFill(Color.WHITE);			
		TextField inputFieldF = new TextField();
		inputFieldF.setMaxWidth(100);
		
		Label resultStarGraph = new Label();
		resultStarGraph.setTextFill(Color.web("#A78BFA"));
		
		VBox barGraphContainer = new VBox();
		barGraphContainer.setAlignment(Pos.CENTER);
		
		Label errorLabel = new Label();
		errorLabel.setTextFill(Color.RED);
		
		Button calcBtn = new Button("Show Graph");
		ButtonStyler.styleActionButton(calcBtn);
		
		
		HBox abcHori = new HBox(10, promptA, inputFieldA, promptB, inputFieldB, promptC, inputFieldC);
		abcHori.setAlignment(Pos.CENTER);
		HBox dfHori = new HBox(10, promptD, inputFieldD, promptF, inputFieldF);
		dfHori.setAlignment(Pos.CENTER);
		
		le53Content.getChildren().addAll(label53, labelGrades, abcHori, dfHori, calcBtn,
										errorLabel, resultStarGraph, barGraphContainer);
		return le53Content;
	}

}
