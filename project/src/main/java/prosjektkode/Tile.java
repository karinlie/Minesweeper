package prosjektkode;

import javafx.scene.control.Button;

public class Tile extends Button {

	private String tile; // string med tekst til tile
	private boolean open = false; // boolean med hvorvidt teksten skal vises (åpen) eller ikke
	private int x; // x-posisjon til tile
	private int y; // y-posisjon til tile
	private boolean flagged = false; // om tile er flagget eller ikke
	
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
		if(value) {
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
	public boolean isOpen() { // sjekker om tile er åpen/viser teksten
		return this.open;
	}
	
	public String toString() { // to-string for å vise teksten
		return this.tile;
	}
	public boolean getFlagged() { // henter ut verdi for om den er flagget eller ikke
		return this.flagged;
	}
	public void setFlagged(boolean flagged) { // setter flagged
		this.flagged = flagged;
		if(flagged) {
			setText("F"); // setter tekst på tilen hvis den er flagget
		}
		else if (getText() == "F") { // fjerner tekst på tilen
			setText("");
		}
	}
}
