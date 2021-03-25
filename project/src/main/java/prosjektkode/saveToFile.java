package prosjektkode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class saveToFile {
	/* kan bruke json, som gjør at man kan ha listestrukturer i en fil og så går det bra
	 * 
	*/
	Board newBoard;
	public final static String SAVE_FOLDER = "src/main/java/savedGames/";
	
	public void save(String filename, Board board) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(SAVE_FOLDER + filename)) {
			writer.println(board.getSize());
			
			for (int y = 0; y < board.getSize(); y++) {
				for (int x = 0; x < board.getSize(); x++) {
					writer.print(board.getTileAt(x, y).toString());
				}
				writer.println();
			}
		}
	}
	
	public Board load(String filename, Board board) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(SAVE_FOLDER + filename)){
			
			if(scanner.hasNext()) {
				int size = Integer.valueOf(scanner.nextLine());
				int level = board.getLevels().get(size);
				this.newBoard = new Board(level);
			}
			
		}
		return newBoard;
	}
	
}
