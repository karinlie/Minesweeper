package prosjektkode;


public class Tile {
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
		try {
			int num = Integer.parseInt(value);
			if(num < 1 || num > 8) {
				throw new IllegalArgumentException("Kan ikke ha mindre enn 1 eller flere enn 8 nabominer");
			}
		}
		catch (NumberFormatException e) {
			if(!value.equals(" ") && !value.equals("*")) {
				System.out.println("<" + value + ">");
				throw new IllegalArgumentException("Ulovlig verdi");
			}
		}
		tile = value;
	}
	
	public void setEmpty() { // setter tom tile
		tile = " ";
	}
	
	public void setMine() { // setter mine
		tile = "*";
	}
	
	public void setNumber(int neighborMines) { // setter antall nabominer
		if(neighborMines < 1 || neighborMines > 8) { //validerer at antall miner er lovlig
			throw new IllegalArgumentException("Kan ikke ha mindre enn 1 eller flere enn 8 nabominer");
		}
		tile = String.valueOf(neighborMines);
	}
	
	public void setOpen(boolean value) {
		this.open = value;	
	}
	
	public void setFlagged(boolean flagged) { // setter flagged
		this.flagged = flagged;
	}
	
	public String getTile() { // henter ut verdi på mine
		return this.tile;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getFlagged() { // henter ut verdi for om den er flagget eller ikke
		return this.flagged;
	}
	
	public boolean isMine() { // sjekker om det er en mine
		return tile.equals("*");
	}
	
	public boolean isEmpty() { // sjekker om det er blank
		return tile.equals(" ");
	}
	
	public boolean isOpen() { // sjekker om tile er åpen/viser teksten
		return this.open;
	}
	
	public boolean isNum() { // sjekker om det er et tall
		return (!isMine()|| !isEmpty());
	}
	
	@Override
	public String toString() {
		/* bruker toString for å skrive nødvendig info om hver tile til fil
		 * skiller hver tile med ":" og de tre "verdier" som er nødvendige skilles med ";"
		*/
		return getTile() + "," + (isOpen() ? "1" : "0") + "," + (getFlagged() ? "1" : "0") + ":";
	}
	
}
