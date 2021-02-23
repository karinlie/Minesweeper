package prosjektkode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MSController2 {
	
	private int level=1;
	
	@FXML
	GridPane gridPane;
	
	@FXML
	private void initialize() {
		new Board(gridPane, level);
	}
	
	@FXML
	public void handleLevel1() {
		this.level = 1;
	}
	
	@FXML
	public void handleLevel2() {
		this.level = 2;
	}
	
	@FXML
	public void handleLevel3() {
		this.level = 3;
	}
	
}
