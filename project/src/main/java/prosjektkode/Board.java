package prosjektkode;


import java.util.Arrays;
import java.util.List;

public class Board {
	
	private Tile[][] board;
	private int neighborMines;
	private final List<Integer> levels = List.of(10,25,35);
	private int size;
	
	
	public Board(int level) {
		size = levels.get(level-1);
		this.board = new Tile[size][size];
		int numberOfMines = 0;
		
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
