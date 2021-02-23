package prosjektkode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MSController {
	
	private Board board;
	private int level = 1;
	
	//@FXML
	
	@FXML
	private Button button1;
	
	
	
	@FXML
	private void testButton() {
		button1.setStyle("-fx-background-color: #b91212");
	}
	
	@FXML
	private void initialize() {
		this.board = new Board(level);
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
	
	
	
//	@FXML
//	public void changeScreenInputMinesweeperUI(ActionEvent event) throws IOException {
//		Parent InputMsUIParent = FXMLLoader.load(getClass().getResource("MsUI.fxml"));
//		Scene InputMsUIScene = new Scene(InputMsUIParent);
//		Stage InputVindu = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//		InputVindu.setScene(InputMsUIScene);
//		InputVindu.show();
//	}
}