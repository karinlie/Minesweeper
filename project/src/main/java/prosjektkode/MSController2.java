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
	
	@FXML GridPane gridPane;

	@FXML private Label bombLabel;
	
	@FXML private Button button1, button2, button3;
	
	@FXML private Button Button1, Button2, Button3;
	
	@FXML
	private void run() {
		new Board(gridPane, level, bombLabel);
	}
	
	@FXML
	public void setOnActionButton1() {
		this.level = 1;
//		initialize();
		run();
	}
	
	@FXML
	public void setOnActionButton2() {
		this.level = 2;
//		initialize();
		run();
	}
	
	@FXML
	public void setOnActionButton3() {
		this.level = 3;
//		initialize();
		run();
	}
	
//	@FXML
//	public void setOnActionRestart(final Stage primaryStage) throws Exception{
//		primaryStage.setTitle("Minesweeper");
//		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI2.fxml")));
//		primaryStage.setScene(game);
//	}
}
