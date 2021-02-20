package prosjektkode;

public class Tile {

	private String tile;

	
	public void setEmpty() { // setter tom boks
		tile = " ";
	}
	
	public void setMine() { // setter mine
		tile = "*";
	}
	public boolean isBomb() {
		return tile == "*";
	}
	
	public void setNumber(int getNeighborMines()) { // setter antall nabominer
		/* her må vi finne på noe lurt
		 * og det trengs kalkulasjoner i Board 
		 * for å finne ut hvor mange neighborMines det er
		 */
		tile = String.valueOf(getNeighborMines());
	}
	
	public String getTile() { // henter ut verdi på mine
		return this.tile;
	}
	
}
