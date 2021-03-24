package prosjektkode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MsApp extends Application {
	
	private Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Minesweeper");
		Scene menu = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI1.fxml"))); // her bør vi loade chooselevel
		stage.setScene(menu);
		stage.show();
	}
	
	public static void main(String[] args) {
		MsApp.launch(args);
	}
}

