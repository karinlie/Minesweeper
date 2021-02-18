package prosjektkode;


import java.util.Arrays;
import java.util.List;

public class Board {
	
	private Tile[][] board;
	private int neighborMines;
	private final List<Integer> levels = Arrays.asList(10,25,35);
	private final List<Integer> numberOfMines = Arrays.asList(10,40,100);
	private int size;
	
	
	public Board(int level) {
		size = levels.get(level-1); // finner str. på brettet utifra vanskelighetsgrad
		int numOfMines = this.numberOfMines.get(level-1); // antall miner brettet skal ha
		this.board = new Tile[size][size];    // oppretter brett av todimensjonale arrays
		
		
		
		for (int y = 0; y < size; y++) {      
			for (int x = 0; x < size; x++) {
				
			}
		}
		
	}
	
	public void setNeighborMines() {
		
	}
	
	public int getNeighborMines() {
		return this.neighborMines;
	}
	
	private boolean checkIfMine(Tile tile) {
		return tile.equals("*");
	}
	
	
}
