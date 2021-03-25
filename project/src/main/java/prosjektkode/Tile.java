package prosjektkode;

public class Tile {

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private String tile; // string med tekst til tile
	private boolean open = false; // boolean med hvorvidt teksten skal vises (åpen) eller ikke
	private int x; // x-posisjon til tile
	private int y; // y-posisjon til tile
	private boolean flagged = false; // om tile er flagget eller ikke
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setTile(String value) {
		tile = value;
	}
	
	public void setEmpty() { // setter tom tile
		tile = " ";
	}
	
	public void setMine() { // setter mine
		tile = "*";
	}
	
	public void setNumber(int neighborMines) { // setter antall nabominer
		tile = String.valueOf(neighborMines);
	}
	
	public void setOpen(boolean value) {
		this.open = value;	
	}
	
	public boolean isMine() { // sjekker om det er en mine
		return tile.equals("*");
	}
	
	public boolean isEmpty() { // sjekker om det er blank
		return tile.equals(" ");
	}
	
	public boolean isNum() { // sjekker om det er et tall
		return (!isMine()|| !isEmpty());
	}
	
	public String getTile() { // henter ut verdi på mine
		return this.tile;
	}
	public boolean isOpen() { // sjekker om tile er åpen/viser teksten
		return this.open;
	}
	
	public boolean getFlagged() { // henter ut verdi for om den er flagget eller ikke
		return this.flagged;
	}
	
	public void setFlagged(boolean flagged) { // setter flagged
		this.flagged = flagged;
	}
	
	@Override
	public String toString() {
		return getTile() + " " + (isOpen() ? "1" : "0") + " " + (getFlagged() ? "1" : "0") + ":";
	}
	
}
