package prosjektkode;


import java.util.Arrays;
import java.util.List;

public class Board {
	
	private Tile[][] board;
	private int neighborMines;
	private final List<Integer> levels = Arrays.asList(10,25,35);
	private int size;
	
	
	
	public Board(int level) {
		size = levels.get(level-1);
		this.board = new Tile[size][size];
	}
	
	public void setNeighborMines() {
		
	}
	
	public int getNeighborMines() {
		return this.neighborMines;
	}
}
