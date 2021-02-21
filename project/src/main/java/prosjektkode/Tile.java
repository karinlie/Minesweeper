package prosjektkode;

public class Tile {

	private String tile;

	
	public void setEmpty() { // setter tom boks
		tile = " ";
	}
	
	public void setMine() { // setter mine
		tile = "*";
	}
	public boolean isMine() {
		return tile.equals("*");
	}
	
	public boolean isEmpty() {
		return tile.equals(" ");
	}
	
	public void setNumber(int neighborMines) { // setter antall nabominer
		tile = String.valueOf(neighborMines);
	}
	
	public String getTile() { // henter ut verdi på mine
		return this.tile;
	}
	
}
