package prosjektkode;

public class Tile {

	private String tile;
	private int neighborMines;
	
	public void setEmpty() {
		tile = " ";
	}
	
	public void setMine() {
		tile = "*";
	}
	
	public void setNumber(int neighborMines) {
		/* her må vi finne på noe lurt
		 * og det trengs kalkulasjoner i Board 
		 * for å finne ut hvor mange neighborMines det er
		 */
		tile = String.valueOf(neighborMines);
	}
}
