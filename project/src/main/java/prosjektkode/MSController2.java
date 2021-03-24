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
	
	@FXML GridPane gridPane;

	@FXML private Label bombLabel;
	
	@FXML private Button Button1, Button2, Button3, saveButton;
	
	@FXML
	private void run() {
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
		clearGridPane();
		run();
	}
	
	@FXML
	public void setOnActionButton2() {
		this.level = 2;
		clearGridPane();
		run();
	}
	
	@FXML
	public void setOnActionButton3() {
		this.level = 3;
		clearGridPane();
		run();
	}
	
	@FXML
	public void setOnActionSaveButton() throws FileNotFoundException {
		if(board != null) {
			saveToFile saveFile = new saveToFile();
			saveFile.save("game.txt", board);
		}
	}
}
