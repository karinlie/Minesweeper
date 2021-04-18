package prosjektkode;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class BoardGUI {
	private GridPane gridPane;
	private Board board;
	private Popup popup = new Popup();
	private Label bombLabel; //label for antall miner som er igjen, oppdateres hver gang man åpner en mine
	
	
	public BoardGUI(GridPane gridPane, Board board, Label bombLabel) {
		this.gridPane = gridPane;
		this.board = board;
		this.bombLabel = bombLabel;
		updateBombLabel();
		buildBoard();
	}
	
	public Button addButton(Tile tile) { // legger til button på tile, med funksjon for høyre og venstreklikk
		Button button = new Button();
		int x = tile.getX();
		int y = tile.getY();
		button.setOnMousePressed(event -> { // her kobler vi hver tile som extender button opp mot
			if (event.isPrimaryButtonDown()) {        // venstreklikk (som vil tilsi at vi åpner feltet)
				board.checkGameOverOrWon(x, y);
				switch(board.getStatus()) {
				case WIN:
					gameWonPopup();
					break;
				case LOSE:
					gameOverPopup();
					break;
				case CONTINUE:
					break;
				}
			}
			else if (event.isSecondaryButtonDown()){  // og høyreklikk (som tilsier at vi markerer knappen)
				tile.setFlagged(!tile.getFlagged());
			}
			buildBoard(); // når man klikker på en button må brettet bygges på nytt
			updateBombLabel();
		});
		return button;
	}
	
	public void gameWonPopup() { // popup som sier game won
		Label label = new Label("Game won! Choose a level for new game"); // setter label
		label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
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
	
	public void gameOverPopup() { // popup som sier game over
		Label label = new Label("Game over! Choose a level for new game"); // setter label
		label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
		getPopup().getContent().add(label); // legger til label på pop-upen vår
		label.setTextFill(Color.RED); // setter fargen på teksten i label til rød
		label.setMinHeight(50); // setter høyde på label
		label.setMinWidth(80); // setter bredde på label
		if(!getPopup().isShowing()) { // hvis pop-upen ikke er synlig
			getPopup().show(gridPane,550,300); // sender popup
		} else {
			getPopup().hide(); 
		}
	}
	
	private void updateBombLabel() { // oppdaterer hvor mange miner som ikke er flagget
		bombLabel.setText("Mines left: " + (board.getNumOfMines() - board.getNumOfFlags())); // oppdaterer teksten
	}
	
	private void buildBoard() {
		gridPane.getChildren().clear(); // fjerner det gamle brettet
		for(int y = 0; y < board.getSize(); y++) {
			for(int x = 0; x < board.getSize(); x++) {
				Button button = addButton(board.getTileAt(x, y)); // henter tile på posisjon i brettet
				button.setPrefSize(30, 30); // setter preferert størrelse på button/tile
				gridPane.add(button, x, y); // legger til tilpå posisjon i gridpane
				showButton(board.getTileAt(x, y), button);
			}
		}
		gridPane.setPrefSize(800, 800); // setter størrelse på gridpane
	}

	public void showButton(Tile tile, Button button) { // åpner tekst på button 
		if( tile == null || button == null) { // sjekker at inputargument er lovlig
			throw new IllegalArgumentException("tile/button kan ikke være null");
		}
		if(tile.getFlagged()) {
			button.setText("F"); // setter tekst på tilen hvis den er flagget
		}
		else if (button.getText() == "F") { // fjerner tekst på tilen
			button.setText("");
		}
		if(tile.isOpen()) { // setter tekst på knappen om den tilen er åpen
			button.setText(tile.getTile());
			button.setDisable(true);
		}
	}
	
	public Popup getPopup() {
		return popup;
	}
}
