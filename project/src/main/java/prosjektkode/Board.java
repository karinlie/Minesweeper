package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class Board {
	
	private Tile[][] board; // to-dimensjonalt array

	private final List<Integer> levels = List.of(10,20,25); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(10,30,50); // antall miner utifra vanskelighetsgrad

	private int size; //faktisk brettstørrelse
	private int numOfMines; //faktisk antall miner
	private ArrayList<Vector<Integer>> mines; // plasseringen av de forskjellige minene
	private ArrayList<Vector<Integer>> everyPosition = new ArrayList<Vector<Integer>>(); // arraylist med alle posisjonene --> blir til alle posisjonene uten miner
	private GridPane gridPane;

	private Label bombLabel;

	private int flagg;
	
	public Board(GridPane gridPane, int level, Label bombLabel) {
		this.gridPane = gridPane;
		this.bombLabel = bombLabel;
		this.size = levels.get(level-1);// finner str. på brettet utifra vanskelighetsgrad
		this.numOfMines = numberOfMines.get(level-1); // antall miner brettet skal ha
		board = new Tile[size][size];// oppretter brett av todimensjonale arrays
		
		addEmptyTiles(); // legger til tomme tiles
		addMines();      // legger til miner random på tiles
		addNeighborMines();  // fyller inn logikk for hvor mange nabominer et felt har
		addGridPane(gridPane); // oppretter gridPane med buttons
		bombLabel.setText("Mines left: " + numOfMines); // teller for antall miner igjen
	}
	
	private void addTile(int x, int y) {
		Tile t = new Tile(x, y);
		t.setOnMousePressed(event -> { // her kobler vi hver tile som extender button opp mot
			if (event.isPrimaryButtonDown()) {        // venstreklikk (som vil tilsi at vi åpner feltet)
				if(!t.getFlagged()) {                 // men dersom knappen er flagget skal man ikke kunne åpne den
					openEmptyTiles(x, y);
					GameWon();
				}
			}
			else if (event.isSecondaryButtonDown()){  // og høyreklikk (som tilsier at vi markerer knappen)
				
				t.setFlagged(!t.getFlagged()); // gjør at vi setter flagged til motsatt av det den er
				if(t.getFlagged()) { // hvis den er flagget skal flagg oppdateres og settes til 1
					flagg += 1;      
				}
				else {               // hvis den ikke er flagget skal flagg oppdateres og settes til 0
					flagg -= 1;
				}
				int minesLeft = numOfMines - flagg; // oppdaterer antall miner som ikke er markert
				bombLabel.setText("Mines left: " + minesLeft); // oppdaterer teksten
				GameWon();
			}
		});
		board[y][x] = t; // legger tilen til i posisjon i gridpane på brettet
	}
	
	private void addEmptyTiles() {
		for (int y = 0; y < getSize(); y++) {      
			for (int x = 0; x < getSize(); x++) {
				addTile(x,y); // legger til tom tile
				setEmpty(x, y); // setter teksten til blank
				everyPosition.add(makeVector(x, y)); //fyller arraylist med posisjonen nå lagt til
			}
		}
	}
	
	private void addMines() { 
		for(int i = 0 ; i < getNumOfMines(); i++) {
			int randomIndex = (int) (Math.random() * everyPosition.size()); // velger random index i lista over posisjoner
			Vector<Integer> position = everyPosition.get(randomIndex); // henter ut vektoren på den posisjonen i lista
 			setMine(position.get(0), position.get(1)); // setter en mine på denne posisjonen
			everyPosition.remove(randomIndex); // fjerner det punktet fra lista over felter uten miner
		}
	}
	
	private void addNeighborMines() { // legger til tall/neighbormines teller på hvert felt
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (!(getNeighborMines(x,y) == 0) && !(getTileAt(x,y).isMine())) {
					setNeighborMines(x,y,getNeighborMines(x,y));
				}
			}
		}
	}
	
	private void addGridPane(GridPane gridPane) {
		for(int y = 0; y < getSize(); y++) {
			for(int x = 0; x < getSize(); x++) {
				Tile tile = getTileAt(x, y); // henter tile på posisjon i brettet
				tile.setPrefSize(40, 40); // setter preferert størrelse på button/tile
				gridPane.add(tile, x, y); // legger til tilpå posisjon i gridpane
			}
		}
		gridPane.setPrefSize(800, 800);
	}
		
	public void openEmptyTiles(int x, int y) {
//		GameWon();
		if(board[y][x].isMine()) { // dersom vi åpner et felt som er mine --> game over
			gameOver();
		} else if(board[y][x].isEmpty()) { // dersom vi åpner et felt som er tomt --> åpne alle rundt
			board[y][x].setOpen(true); //åpner
//			GameWon();
			for (int i = y-1; i <= y+1; i++) { //itererer over brettet
				for (int j = x-1; j <= x+1; j++) {
					if(isPositionWithinBoard(j, i) && board[i][j].isEmpty() && !(board[i][j].isOpen())) { // sjekker at posisjon er innafor brettet, at feltet er tomt og at det ikke er åpnet fra før
						board[i][j].setOpen(true); // åpner
						openEmptyTiles(j,i); // åpner felter rundt som også er empty/tall ved å kalle den rekursivt
					} else if (isPositionWithinBoard(j,i) && board[i][j].isNum()) {
						board[i][j].setOpen(true);
					}
				}
			}
		} else {
			board[y][x].setOpen(true);
//			GameWon();
		}
	}
	
	
	public void gameOver() {
		 for( int y = 0; y < getSize(); y++) { // åpner alle feltene
			 for(int x = 0; x < getSize(); x++) {
				 board[y][x].setOpen(true);
			 }
		 }
		 Label label = new Label("Game over! Click restart for new game"); // setter label
		 Popup popup = new Popup(); // oppretter en pop-up
		 label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
		 popup.getContent().add(label); // legger til label på pop-upen vår
		 label.setTextFill(Color.RED); // setter fargen på teksten i label til rød
		 label.setMinHeight(50); // setter høyde på label
		 label.setMinWidth(80); // setter bredde på label
		 if(!popup.isShowing()) // hvis pop-upen ikke er synlig
			 popup.show(gridPane,550,300); // sender popup
		 else
			 popup.hide(); 
	}
	public void GameWon() {
		int i = 0;
		if(getFlagg()== getNumOfMines()) {
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if(getTileAt(x,y).getFlagged() && getTileAt(x,y).isMine()) {
						i++;
					}
				}
			}
			if(i==getNumOfMines()) {
				 Label label = new Label("Game won! Click restart for new game"); // setter label
				 Popup popup = new Popup(); // oppretter en pop-up
				 label.setStyle(" -fx-background-color: white;"); // setter bakgrunnsfarge
				 popup.getContent().add(label); // legger til label på pop-upen vår
				 label.setTextFill(Color.BLUE); // setter fargen på teksten i label til blå
				 label.setMinHeight(50); // setter høyde på label
				 label.setMinWidth(80); // setter bredde på label
				 if(!popup.isShowing()) // hvis pop-upen ikke er synlig
					 popup.show(gridPane,550,300); // sender popup
				 else
					 popup.hide();
			}
			
		}
	}
	
	private Vector<Integer> makeVector(int x, int y) { // oppretter vektor
		Vector<Integer> v = new Vector<Integer>();
		v.add(x);
		v.add(y);
		return v;
	}
	public boolean isPositionWithinBoard(int x, int y) { // validering for å sjekke at posisjon er innad i brettet
		return (0 <= x && x < getSize() && 0 <= y && y < getSize() );
	}
	
	private void setMine(int x, int y) {
		board[y][x].setMine();
	}
	
	private void setEmpty(int x, int y) {
		board[y][x].setEmpty();
	}
		
	
	private void setNeighborMines(int x, int y, int neighborMines) {
		board[y][x].setNumber(neighborMines);
	}
	
	public int getNeighborMines(int x, int y) { // teller antall nabominer til et felt
		int num = 0;
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (!(j==x && i==y) && isPositionWithinBoard(j, i) && board[i][j].isMine()) {
					num += 1;
				}
			}
		}
		return num;
	}
		
	public Tile getTileAt(int x, int y) {
		return board[y][x];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumOfMines() {
		return this.numOfMines;
	}
	public int getFlagg() {
		return this.flagg;
	}
	
	@Override
	public String toString() { // to-stringen vi brukte da vi printet brettet i starten
		String boardString = "";
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				boardString += getTileAt(x,y);
			}
			boardString += '\n';
		}
		return boardString;
	}
	
		
}