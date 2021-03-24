package prosjektkode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class saveToFile {
	/* kan bruke json, som gjør at man kan ha listestrukturer i en fil og så går det bra
	 * 
	*/
	
	public final static String SAVE_FOLDER = "src/main/java/savedGames/";
	
	
	public void save(String filename, Board board) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(filename)) {
			writer.println(board.getSize());
			
			
			for (int y = 0; y < board.getSize(); y++) {
				for (int x = 0; x < board.getSize(); x++) {
					writer.print(board.getTileAt(x, y).toString());
				}
			}
			writer.println();
		}
	}
	
}
