package prosjektkode;



import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MsApp extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper");
		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("msUI2.fxml")));
		primaryStage.setScene(game);
		primaryStage.show();
	}
	
//	@FXML
//	public void switchScene(ActionEvent event) throws IOException {
//		Scene game = new Scene(FXMLLoader.load(MsApp.class.getResource("ChooseLevel.fxml")));
//		Stage inputWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//		
//		
//		inputWindow.setScene(game);
//		inputWindow.show();
//	}
//	
	public static void main(String[] args) {
		MsApp.launch(args);
	}
}

