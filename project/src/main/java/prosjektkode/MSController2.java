package prosjektkode;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MSController2 {
	
	private int level=1;
	private Board board;
	private BoardGUI boardGUI;
	private FileSaver saveToFile = new SaveToFile();
	
	@FXML GridPane gridPane;

	@FXML private Label bombLabel;
	
	@FXML private Button Button1, Button2, Button3, saveButton;
	
	
	
	@FXML
	private void initialize() {
		try {
			board = saveToFile.load("game");
			boardGUI = new BoardGUI(gridPane, board, bombLabel);
		} catch (Exception e) {
			newBoard();
		}
	}
	
	@FXML
	private void newBoard() {
		clearGridPane();
		board = new Board(level);
		boardGUI = new BoardGUI(gridPane, board, bombLabel);
	}
	
	@FXML
	private void clearGridPane() {
		if(board != null) {
			gridPane.getChildren().clear();
			if(boardGUI.getPopup().isShowing())
				boardGUI.getPopup().hide();
		}

	}
	
	@FXML
	public void setOnActionButton1() {
		this.level = 1;
		newBoard();
	}
	
	@FXML
	public void setOnActionButton2() {
		this.level = 2;
		newBoard();
	}
	
	@FXML
	public void setOnActionButton3() {
		this.level = 3;
		newBoard();
	}
	
	@FXML
	public void setOnActionSaveButton() {
		try {
			saveToFile.save("game", board);
		} catch (FileNotFoundException e) {
			System.out.println("Could not save game");
		}
	}
}
