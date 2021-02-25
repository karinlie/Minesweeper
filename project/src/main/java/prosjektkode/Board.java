package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javafx.scene.layout.GridPane;

public class Board {
	
	private Tile[][] board; // to-dimensjonalt array

	private final List<Integer> levels = List.of(10,25,35); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(10,40,100); // antall miner utifra vanskelighetsgrad

	private int size; //faktisk brettstørrelse
	private int numOfMines; //faktisk antall miner
	private ArrayList<Vector<Integer>> mines; // plasseringen av de forskjellige minene
	private ArrayList<Vector<Integer>> everyPosition = new ArrayList<Vector<Integer>>(); // arraylist med alle posisjonene --> blir til alle posisjonene uten miner
	
	public Board(GridPane gridPane, int level) {
		this.size = levels.get(level-1); // finner str. på brettet utifra vanskelighetsgrad
		this.numOfMines = numberOfMines.get(level-1); // antall miner brettet skal ha
		board = new Tile[size][size];// oppretter brett av todimensjonale arrays
		
		addEmptyTiles();
		
		addMines();
		
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (!(getNeighborMines2(x,y) == 0) && !(getTileAt(x,y).isMine())) {
					setNeighborMines(x,y,getNeighborMines(x,y));
				}
			}
		}
		

		for(int y = 0; y < getSize(); y++) {
			for(int x = 0; x < getSize(); x++) {
				Tile tile = getTileAt(x, y);
				tile.setPrefSize(40, 40);
				tile.setText(tile.getTile());
				tile.setOnAction(event -> {
					System.out.println(tile);
				});
				gridPane.add(tile, x, y);
			}
		}
		
		gridPane.setPrefSize(400, 400);
	}
	
	private void addEmptyTiles() {
		for (int y = 0; y < getSize(); y++) {      
			for (int x = 0; x < getSize(); x++) {
				addTile(x,y);
				setEmpty(x, y);
				everyPosition.add(makeVector(x, y)); //fyller arraylist med posisjonen nå lagt til
			}
		}
	}
	
	private void addMines() { // vet at denne funker
		for(int i = 0 ; i < getNumOfMines(); i++) {
			int randomIndex = (int) (Math.random() * everyPosition.size());
			Vector<Integer> position = everyPosition.get(randomIndex);
 			setMine(position.get(0), position.get(1));
			everyPosition.remove(randomIndex);
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
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (!(j==x && i==y) && isPositionWithinBoard(j, i) && board[i][j].isMine()) {
					num += 1;
				}
			}
		}
		return num;
	}
	
	public int getNeighborMines2(int x, int y) {
		int num2 = 0;
		if (!(getTileAt(x,y).isMine())) {
			for (int i = y - 1; i < y + 2; i++) {
				for(int j = x - 1; j < x + 2; j++) {
					if (isPositionWithinBoard(j,i) && (!(j==x && i==y)) && board[i][j].isMine()) {
						num2 += 1;
					}
				}
			}
		}
		return num2;
	}
	
	public boolean isPositionWithinBoard(int x, int y) {
		return (0 <= x && x < getSize() && 0 <= y && y < getSize() );
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
	
	@Override
	public String toString() {
		String boardString = "";
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				boardString += getTileAt(x,y);
			}
			boardString += '\n';
		}
		return boardString;
	}
	
//	public static void main(String[] args) {
//		Board b1 = new Board(1);
//		System.out.println(b1);
//	}

	
}