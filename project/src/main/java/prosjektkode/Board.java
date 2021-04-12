package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class Board {
	
	private Tile[][] board; // to-dimensjonalt array

	private final List<Integer> levels = List.of(10,20,25); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(10,30,50); // antall miner utifra vanskelighetsgrad

	private int size; //faktisk brettstørrelse
	private int numOfMines; //faktisk antall miner
	private ArrayList<Vector<Integer>> everyPosition = new ArrayList<Vector<Integer>>(); // arraylist med alle posisjonene --> blir til alle posisjonene uten miner
	
	public Board(int level) {
		if(level > 3 || level < 1) { // sjekker om input er lovlig
			throw new IllegalArgumentException("Dette er ikke et gyldig level");
		}
		
		this.size = levels.get(level-1);// finner str. på brettet utifra vanskelighetsgrad
		this.numOfMines = numberOfMines.get(level-1); // antall miner brettet skal ha
		board = new Tile[size][size];// oppretter brett av todimensjonale arrays
		
		addEmptyTiles(); // legger til tomme tiles
		addMines();      // legger til miner random på tiles
		addNeighborMines();  // fyller inn logikk for hvor mange nabominer et felt har
	}
	
	private void addTile(int x, int y) {
		checkPosition(x, y);
		Tile t = new Tile(x, y);
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
		for (int y = 0; y < size; y++) { // her sjekker vi sjekker om det allerede er lagt til miner
			for (int x = 0; x < size; x++) {
				if(getTileAt(x,y).isMine()) {
					throw new IllegalStateException("Miner allerede lagt til");
				}
			}
		}
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
		
	public void openTile(int x, int y) {
		checkPosition(x,y);
		if(board[y][x].isEmpty()) { // dersom vi åpner et felt som er tomt --> åpne alle rundt
			board[y][x].setOpen(true); //åpner
			for (int i = y-1; i <= y+1; i++) { //itererer over brettet
				for (int j = x-1; j <= x+1; j++) {
					if(isPositionWithinBoard(j, i) && board[i][j].isEmpty() && !(board[i][j].isOpen())) { // sjekker at posisjon er innafor brettet, at feltet er tomt og at det ikke er åpnet fra før
						board[i][j].setOpen(true); // åpner
						openTile(j,i); // åpner felter rundt som også er empty/tall ved å kalle den rekursivt
					} else if (isPositionWithinBoard(j,i) && board[i][j].isNum()) {
						board[i][j].setOpen(true);
					}
				}
			}
		} else {
			board[y][x].setOpen(true);
		}
	}
	
	public void gameOver() {
		 for( int y = 0; y < getSize(); y++) { // åpner alle feltene
			 for(int x = 0; x < getSize(); x++) {
				 board[y][x].setOpen(true);
			 }
		 }

	}
	
	public boolean GameWon() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				Tile t = getTileAt(x,y);
				if(!t.isMine() && !t.isOpen()) {
					return false;
				} 
			}
		}
		return true;
	}
	
	private Vector<Integer> makeVector(int x, int y) { // oppretter vektor
		checkPosition(x, y);
		Vector<Integer> v = new Vector<Integer>();
		v.add(x);
		v.add(y);
		return v;
	}
	
	public boolean isPositionWithinBoard(int x, int y) { // validering for å sjekke at posisjon er innad i brettet
		return (0 <= x && x < getSize() && 0 <= y && y < getSize() );
	}
	
	private void setMine(int x, int y) {
		checkPosition(x, y);
		board[y][x].setMine();
	}
	
	private void setEmpty(int x, int y) {
		checkPosition(x, y);
		board[y][x].setEmpty();
	}
		
	private void setNeighborMines(int x, int y, int neighborMines) {
		checkPosition(x, y);
		board[y][x].setNumber(neighborMines); //valideringen for om tallet er rett kommer i tile
	}
	
	public int getNeighborMines(int x, int y) { // teller antall nabominer til et felt
		checkPosition(x, y);
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
		
	public int getLevel(int size) {
		return levels.indexOf(size) + 1;
	}
	
	public Tile getTileAt(int x, int y) {
		checkPosition(x, y);
		return board[y][x];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumOfMines() {
		return this.numOfMines;
	}
	
	public int getNumOfFlags() {
		int numOfFlags = 0;
		for(int y = 0; y < getSize(); y++) {
			for (int x = 0; x < getSize(); x++){
				if(this.getTileAt(x,y).getFlagged()) {
					numOfFlags ++;
				}
			}
		}
		return numOfFlags;
	}
	
	public void checkPosition(int x,int y) throws IllegalArgumentException {
		if(!isPositionWithinBoard(x,y)) {
			throw new IllegalArgumentException("Posisjonen er ikke innenfor brettet");
		}
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