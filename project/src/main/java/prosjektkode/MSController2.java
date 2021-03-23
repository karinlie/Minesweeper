package prosjektkode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MSController2 {
	
	private int level=1;
	private Board board;
	
	@FXML GridPane gridPane;

	@FXML private Label bombLabel;
	
	@FXML private Button Button1, Button2, Button3;
	
	@FXML
	private void run() {
		board = new Board(gridPane, level, bombLabel);
	}
	
	@FXML
	private void clearGridPane() {
		if(board != null) {
			gridPane.getChildren().clear();
			if(board.getPopup().isShowing())
				board.getPopup().hide();
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
}
