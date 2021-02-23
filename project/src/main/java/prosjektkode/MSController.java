package prosjektkode;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MSController {
	
	private Board board;
	private int level = 1;
	
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button button7;
	@FXML
	private Button button8;
	@FXML
	private Button button9;
	@FXML
	private Button button10;
	@FXML
	private Button button11;
	@FXML
	private Button button12;
	@FXML
	private Button button13;
	@FXML
	private Button button14;
	@FXML
	private Button button15;
	@FXML
	private Button button16;
	@FXML
	private Button button17;
	@FXML
	private Button button18;
	@FXML
	private Button button19;
	@FXML
	private Button button20;
	@FXML
	private Button button21;
	@FXML
	private Button button22;
	@FXML
	private Button button23;
	@FXML
	private Button button24;
	@FXML
	private Button button25;
	@FXML
	private Button button26;
	@FXML
	private Button button27;
	@FXML
	private Button button28;
	@FXML
	private Button button29;
	@FXML
	private Button button30;
	@FXML
	private Button button31;
	@FXML
	private Button button32;
	@FXML
	private Button button33;
	@FXML
	private Button button34;
	@FXML
	private Button button35;
	@FXML
	private Button button36;
	@FXML
	private Button button37;
	@FXML
	private Button button38;
	@FXML
	private Button button39;
	@FXML
	private Button button40;
	@FXML
	private Button button41;
	@FXML
	private Button button42;
	@FXML
	private Button button43;
	@FXML
	private Button button44;
	@FXML
	private Button button45;
	@FXML
	private Button button46;
	@FXML
	private Button button47;
	@FXML
	private Button button48;
	@FXML
	private Button button49;
	@FXML
	private Button button50;
	@FXML
	private Button button51;
	@FXML
	private Button button52;
	@FXML
	private Button button53;
	@FXML
	private Button button54;
	@FXML
	private Button button55;
	@FXML
	private Button button56;
	@FXML
	private Button button57;
	@FXML
	private Button button58;
	@FXML
	private Button button59;
	@FXML
	private Button button60;
	@FXML
	private Button button61;
	@FXML
	private Button button62;
	@FXML
	private Button button63;
	@FXML
	private Button button64;
	@FXML
	private Button button65;
	@FXML
	private Button button66;
	@FXML
	private Button button67;
	@FXML
	private Button button68;
	@FXML
	private Button button69;
	@FXML
	private Button button70;
	@FXML
	private Button button71;
	@FXML
	private Button button72;
	@FXML
	private Button button73;
	@FXML
	private Button button74;
	@FXML
	private Button button75;
	@FXML
	private Button button76;
	@FXML
	private Button button77;
	@FXML
	private Button button78;
	@FXML
	private Button button79;
	@FXML
	private Button button80;
	@FXML
	private Button button81;
	@FXML
	private Button button82;
	@FXML
	private Button button83;
	@FXML
	private Button button84;
	@FXML
	private Button button85;
	@FXML
	private Button button86;
	@FXML
	private Button button87;
	@FXML
	private Button button88;
	@FXML
	private Button button89;
	@FXML
	private Button button90;
	@FXML
	private Button button91;
	@FXML
	private Button button92;
	@FXML
	private Button button93;
	@FXML
	private Button button94;
	@FXML
	private Button button95;
	@FXML
	private Button button96;
	@FXML
	private Button button97;
	@FXML
	private Button button98;
	@FXML
	private Button button99;
	@FXML
	private Button button100;
	
	ArrayList<String> s = new ArrayList<String>(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16,button17,button18,button19,button20,button21,button22,button23,button24,button25,button26,button27,button28,button29,button30,button31,button32,button33,button34,button35,button36,button37,button38,button39,button40,button41,button42,button43,button44,button45,button46,button47,button48,button49,button50,button51,button52,button53,button54,button55,button56,button57,button58,button59,button60,button61,button62,button63,button64,button65,button66,button67,button68,button69,button70,button71,button72,button73,button74,button75,button76,button77,button78,button79,button80,button81,button82,button83,button84,button85,button86,button87,button88,button89,button90,button91,button92,button93,button94,button95,button96,button97,button98,button99,button100);

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