package prosjektkode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MsApp extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper");
//		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI2.fxml")));
		Scene menu = new Scene(FXMLLoader.load(MsApp.class.getResource("ChooseLevel.fxml")));
		primaryStage.setScene(menu);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		MsApp.launch(args);
	}
}

