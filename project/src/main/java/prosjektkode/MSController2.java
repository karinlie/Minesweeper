package prosjektkode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MSController2 {
	
	private int level=1;
	
	@FXML
	GridPane gridPane;

	
	@FXML
	private void initialize() {
		new Board(gridPane, level);
	}
	
	@FXML
	public void setOnActionButton1() {
		this.level = 1;
	}
	
	@FXML
	public void setOnActionButton2() {
		this.level = 2;
	}
	
	@FXML
	public void setOnActionButton3() {
		this.level = 3;
	}
	
//	@FXML
//	public void setOnActionRestart(final Stage primaryStage) throws Exception{
//		primaryStage.setTitle("Minesweeper");
//		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI2.fxml")));
//		primaryStage.setScene(game);
//	}
}
