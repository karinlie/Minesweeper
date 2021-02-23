package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Board {
	
	private Tile[][] board; // to-dimensjonalt array

	private final List<Integer> levels = List.of(10,25,35); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(10,40,100); // antall miner utifra vanskelighetsgrad

	private int size; //faktisk brettstørrelse
	private int numOfMines; //faktisk antall miner
	private List<Double> mines; // plasseringen av de forskjellige minene
	private ArrayList<Vector<Integer>> everyPosition = new ArrayList<Vector<Integer>>(); // arraylist med alle posisjonene --> blir til alle posisjonene uten miner
	
	public Board(int level) {
		size = levels.get(level-1); // finner str. på brettet utifra vanskelighetsgrad
		this.numOfMines = numberOfMines.get(level-1); // antall miner brettet skal ha
		board = new Tile[size][size];// oppretter brett av todimensjonale arrays
		
		for (int y = 0; y < size; y++) {      
			for (int x = 0; x < size; x++) {
				addTile(x,y);
				setEmpty(x, y);
				everyPosition.add(makeVector(x, y)); //fyller arraylist med posisjonen nå lagt til
			}
		}
		
		for(int i = 0 ; i < numOfMines; i++) {
			int randomIndex = (int) (Math.random() * everyPosition.size());
			Vector<Integer> position = everyPosition.get(randomIndex);
			System.out.println(position);
 			setMine(position.get(0), position.get(1));
			everyPosition.remove(randomIndex);
		}
		
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (!getTileAt(x,y).isMine()) {
					int neighborMines = getNeighborMines(x,y);
					if (!(neighborMines == 0)) {
						setNeighborMines(x,y,neighborMines);
					}
				}
			}
		}
	}
	
	public void setMine(int x, int y) {
		board[y][x].setMine();
	}
	
	public void setEmpty(int x, int y) {
		board[y][x].setEmpty();
	}
	
	public void addTile(int x, int y) {
		Tile t = new Tile();
		board[y][x] = t;
	}
	
	public Vector<Integer> makeVector(int x, int y) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(x);
		v.add(y);
		return v;
	}
	
	public void setNeighborMines(int x, int y, int neighborMines) {
		board[y][x].setNumber(neighborMines);
	}
	
	public int getNeighborMines(int x, int y) {
		int num = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i==x && j==y) && isPositionWithinBoard(i, j) && board[i][j].isMine()) {
					num += 1;
				}
			}
		}
		return num;
	}
	
	public boolean isPositionWithinBoard(int x, int y) {
		return (0 <= x && x < size && 0 <= y && y < size );
	}
	
//	private boolean isNum(Tile tile) {
//		return (!tile.isMine()|| !tile.isEmpty());
//	}
	
	public Tile getTileAt(int x, int y) {
		return board[y][x];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumOfMines() {
		return this.numOfMines;
	}
	
	public static void main(String[] args) {
		Board b1 = new Board(1);
		System.out.println(b1);
	}

	
}