package prosjektkode;

import javafx.scene.control.Button;

public class Tile extends Button {

	private String tile;
	private boolean open = false;
	private int x;
	private int y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
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
		if(value ) {
			setText(getTile());
			setDisable(true);
		}
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
	public boolean isOpen() {
		return this.open;
	}
	
	public String toString() {
		return this.tile;
	}
	
}
