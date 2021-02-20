package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Board {
	
	private Tile[][] board; // to-dimensjonalt array
	private int neighborMines; // felt for antall nabominer

	private final List<Integer> levels = List.of(10,25,35); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(10,40,100); // antall miner utifra vanskelighetsgrad

	private int size; //faktisk brettstørrelse
	private int numOfMines; //faktisk antall miner
	private List<Double> mines; // plasseringen av de forskjellige minene
	
	
	public Board(int level) {
		size = levels.get(level-1); // finner str. på brettet utifra vanskelighetsgrad
		this.numOfMines = this.numberOfMines.get(level-1); // antall miner brettet skal ha
		this.board = new Tile[size][size];// oppretter brett av todimensjonale arrays
		ArrayList<Vector<Integer>> everyPosition = new ArrayList<Vector<Integer>>();
		/* TO-DO

		 * brettet skal fylles. 
		 * vi må først sjekke om det skal være en mine der, ved å sjekke om pos ligger i mines lista
		 * deretter kan vi etterfylle resten med blanks (setEmpty)
		 * 
		 * så må vi på et eller annet vis få fylt inn de tallene, men det kan kanskje
		 * gjøres i en ny for-løkke der vi kaller funksjonen setNeighborMines()
		 */
		
		for (int y = 0; y < size; y++) {      
			for (int x = 0; x < size; x++) {
				setEmpty(x, y);
				everyPosition.add(new Vector<Integer>(x,y));
			}
		}
		for(int i = 0 ; i < numOfMines; i++) {
			int randomIndex = (int) (Math.random() * everyPosition.size());
			Vector<Integer> position = everyPosition.get(randomIndex);
			everyPosition.remove(randomIndex);
			setMine(position.get(0), position.get(1));
		}
	}
	
	public void setMine(int x, int y) {
		board[x][y].setMine();
	}
	
	public void setEmpty(int x, int y) {
		board[x][y].setEmpty();
	}
	
	
	public void setNeighborMines() {
		/*
		 * TO-DO
		 * Vi må lage en mekanisme som iteterer over brettet, og teller
		 * antall nabominer ved å sjekke om det er miner på følgende posisjoner:
		 * (x-1,y),(x-1,y-1),(x-1,y+1),(x,y+1),(x,y-1),(x+1,y),(x+1,y-1),(x+1,y+1)
		 * Før man begynner telling må man sjekke om feltet man står på er en mine
		 */
	}
	
	public int getNeighborMines(int x, int y) {
		int num = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i==x && j==y) && isPositionWithinBoard(i, j) && board[i][j].isBomb()) {
					num += 1;
				}
			}
		}
		return num;
	}
	
	public boolean isPositionWithinBoard(int x, int y) {
		return (0 <= x && x < size && 0 <= y && y < size );
	}
	private boolean checkIfMine(Tile tile) {
		return tile.equals("*");
	}
	
	private boolean checkIfEmpty(Tile tile) {
		return tile.equals(" ");
	}
	
	private boolean checkIfNum(Tile tile) {
		return (!checkIfMine(tile)|| !checkIfEmpty(tile));
	}
	
	public Tile getTileAt(int x, int y) {
		return board[y][x];
	}
	
	private void setMines(){
		while(mines.size() < numOfMines) {
			//TO-DO
			// random generere to tall, x og y, som skal være mindre enn size
			// deretter sjekke om de ligger i lista mines
			// gjør de ikke det legger vi dem til som flyttall (evt string delt med .)
		}
	}
	
	
}