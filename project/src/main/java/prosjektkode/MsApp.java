package prosjektkode;



import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
	
//	public void switchScene(ActionEvent event) {
//		try {
//			stage.setTitle("Minesweeper");
//			Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI2.fxml"))); // her skal vi loade msUI2.fxml			
//			stage.setScene(game);
//			stage.show();
////			main.stage.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@FXML
//	public void switchScene(ActionEvent event) throws IOException {
//		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("ChooseLevel.fxml")));
//		Stage inputWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//		>
//		inputWindow.setScene(game);
//		inputWindow.show();
//	}
	
	public static void main(String[] args) {
		MsApp.launch(args);
	}
}

