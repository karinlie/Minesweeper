package prosjektkode;

public class Tile {

	private String tile;

	
	public void setEmpty() {
		tile = " ";
	}
	
	public void setMine() {
		tile = "*";
	}
	
	public void setNumber(int getNeighborMines()) {
		/* her må vi finne på noe lurt
		 * og det trengs kalkulasjoner i Board 
		 * for å finne ut hvor mange neighborMines det er
		 */
		tile = String.valueOf(getNeighborMines());
	}
}
