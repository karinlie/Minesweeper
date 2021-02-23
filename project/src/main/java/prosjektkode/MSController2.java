package prosjektkode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MSController2 {
	@FXML
	GridPane gridPane;
	
	@FXML
	private void initialize() {
		new Board(gridPane, 1);
	}
}
