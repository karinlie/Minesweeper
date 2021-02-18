package prosjektkode;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	
	private ArrayList<Tile> board;
	private int neighborMines;
	private final Array<Integer> levels = new ArrayList<Integer>(Arrays.asList(10,25,35));
	
	
	public Board(int level) {
		
	}
	
	public void setNeighborMines() {
		
	}
	
	public int getNeighborMines() {
		return this.neighborMines;
	}
}
