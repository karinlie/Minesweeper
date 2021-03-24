package prosjektkode;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class BoardGUI {
	private GridPane gridPane;
	private Board board;
	private int flagg;
	private Popup popup = new Popup();
	private Label bombLabel; //label for antall miner som er igjen, oppdateres hver gang man åpner en mine
	
	
	public BoardGUI(GridPane gridPane, Board board, Label bombLabel) {
		this.gridPane = gridPane;
		this.board = board;
		this.bombLabel = bombLabel;
		updateBombLabel();
		buildBoard();
	}
	
	public Button addButton(Tile tile) {
		Button button = new Button();
		int x = tile.getX();
		int y = tile.getY();
		button.setOnMousePressed(event -> { // her kobler vi hver tile som extender button opp mot
			if (event.isPrimaryButtonDown()) {        // venstreklikk (som vil tilsi at vi åpner feltet)
				
				if(!tile.getFlagged()) {                 // men dersom knappen er flagget skal man ikke kunne åpne den
					if(board.getTileAt(x, y).isMine()) { // dersom vi åpner et felt som er mine --> game over
						board.gameOver();
						gameOverPopup();
					} else {
						board.openTile(x, y);
						if(board.GameWon()) {
							gameWonPopup();
						}
					}
					
				}
			}
			else if (event.isSecondaryButtonDown()){  // og høyreklikk (som tilsier at vi markerer knappen)
				
				tile.setFlagged(!tile.getFlagged()); // gjør at vi setter flagged til motsatt av det den er
				if(tile.getFlagged()) { // hvis den er flagget skal flagg oppdateres og settes til 1
					flagg += 1;      
				}
				else {               // hvis den ikke er flagget skal flagg oppdateres og settes til 0
					flagg -= 1;
				}
				if(board.GameWon()) {
					gameWonPopup();
				}
			}
			buildBoard();
			updateBombLabel();
		});
		return button;
	}
	
	public void gameWonPopup() {
		Label label = new Label("Game won! Choose a level for new game"); // setter label
		label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
		System.out.println("won");
		getPopup().getContent().add(label); // legger til label på pop-upen vår
		label.setTextFill(Color.BLUE); // setter fargen på teksten i label til blå
		label.setMinHeight(50); // setter høyde på label
		label.setMinWidth(80); // setter bredde på label
		if(!getPopup().isShowing()) {// hvis pop-upen ikke er synlig
			getPopup().show(gridPane,550,300); // sender popup
		} else {
			getPopup().hide();
		}
	}
	
	public void gameOverPopup() {
		Label label = new Label("Game over! Choose a level for new game"); // setter label
		label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
		System.out.println("looser");
		getPopup().getContent().add(label); // legger til label på pop-upen vår
		label.setTextFill(Color.RED); // setter fargen på teksten i label til rød
		label.setMinHeight(50); // setter høyde på label
		label.setMinWidth(80); // setter bredde på label
		System.out.println(getPopup().isShowing());
		if(!getPopup().isShowing()) { // hvis pop-upen ikke er synlig
			getPopup().show(gridPane,550,300); // sender popup
		} else {
			getPopup().hide(); 
		}
	}
	
	
	public void updateBombLabel() {
		bombLabel.setText("Mines left: " + (board.getNumOfMines() - flagg)); // oppdaterer teksten
	}
	
	private void buildBoard() {
		gridPane.getChildren().clear();
		for(int y = 0; y < board.getSize(); y++) {
			for(int x = 0; x < board.getSize(); x++) {
				Button button = addButton(board.getTileAt(x, y)); // henter tile på posisjon i brettet
				button.setPrefSize(30, 30); // setter preferert størrelse på button/tile
				gridPane.add(button, x, y); // legger til tilpå posisjon i gridpane
				showButton(board.getTileAt(x, y), button);
			}
		}
		gridPane.setPrefSize(800, 800);
	}

	public Popup getPopup() {
		return popup;
	}

	public void showButton(Tile tile, Button button) {
		if(tile.getFlagged()) {
			button.setText("F"); // setter tekst på tilen hvis den er flagget
		}
		else if (button.getText() == "F") { // fjerner tekst på tilen
			button.setText("");
		}
		if(tile.isOpen()) {
			button.setText(tile.getTile());
			button.setDisable(true);
		}
		
	}
}
