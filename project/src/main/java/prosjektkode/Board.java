package prosjektkode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javafx.scene.layout.GridPane;

public class Board {
	
	private Tile[][] board; // to-dimensjonalt array

	private final List<Integer> levels = List.of(10,25,35); // str på brett utifra vanskelighetsgrad
	private final List<Integer> numberOfMines = Arrays.asList(25,40,100); // antall miner utifra vanskelighetsgrad

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
		addNeighborMines();
		addGridPane(gridPane);

	}
	
	private void addTile(int x, int y) {
		Tile t = new Tile(x, y);
		t.setOnAction(event -> {
			openEmptyTiles(x, y);
		});
		board[y][x] = t;
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
	
	private void addNeighborMines() {
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
				Tile tile = getTileAt(x, y);
				tile.setPrefSize(40, 40);
				gridPane.add(tile, x, y);
			}
		}
		
		gridPane.setPrefSize(400, 400);
	}
	private void openEmptyTiles(int x, int y) {
		board[y][x].setOpen(true);
		int x0;
		int y0;
		if(board[y][x].isMine()) {
			gameOver();
		}
		for (double i = 0; i < 2*Math.PI; i+= Math.PI/2) {
			x0 = x + (int) Math.cos(i);
			y0 = y + (int) Math.sin(i);
			if (isPositionWithinBoard(x0, y0) && (!board[y0][x0].isMine()) && (!board[y0][x0].isOpen())) {
				openEmptyTiles(x0,y0);
			
			}
			System.out.println("" + y0 + "," + x0 + "," + i);
		}
		
	}
	
	
	private void setMine(int x, int y) {
		board[y][x].setMine();
	}
	
	private void setEmpty(int x, int y) {
		board[y][x].setEmpty();
	}
		
	private Vector<Integer> makeVector(int x, int y) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(x);
		v.add(y);
		return v;
	}
	
	private void setNeighborMines(int x, int y, int neighborMines) {
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
	
	public boolean isPositionWithinBoard(int x, int y) {
		return (0 <= x && x < getSize() && 0 <= y && y < getSize() );
	}

//	public static void main(String[] args) {
//		Board b1 = new Board(1);
//		System.out.println(b1);
//	}

	
}