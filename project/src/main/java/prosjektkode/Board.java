package prosjektkode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	
	private ArrayList<Tile> board;
	private int neighborMines;
	private final Array<Integer> levels = new ArrayList<Integer>(Arrays.asList(10,25,35));
	private int size;
	
	
	public Board(int level) {
		size = levels.get(level-1);
	}
	
	public void setNeighborMines() {
		
	}
	
	public int getNeighborMines() {
		return this.neighborMines;
	}
}
