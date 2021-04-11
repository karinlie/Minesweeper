package prosjektkode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class saveToFile {

	Board newBoard;
	public final static String SAVE_FOLDER = "src/main/java/savedGames/";
	
	public void save(String filename, Board board) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(SAVE_FOLDER + filename)) {
			int size = board.getSize();
			writer.println(board.getLevel(size));
			
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					writer.print(board.getTileAt(x, y).toString());
				}
				writer.println();
			}
		}
	}
	
	public Board load(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(SAVE_FOLDER + filename));
		if(scanner.hasNext()) {
			int level = Integer.valueOf(scanner.nextLine());
			newBoard = new Board(level);
		}
		for (int y = 0; y < newBoard.getSize(); y++) {
			while(scanner.hasNext()) {
				String line = scanner.next();
				String[] eachTile = line.split(":");
				for (int x = 0; x < newBoard.getSize(); x++) {
					String[] eachValue = eachTile[x].split("\\s+");
					newBoard.getTileAt(x, y).setTile(eachValue[0]);
					newBoard.getTileAt(x, y).setOpen(intToBool(Integer.valueOf(eachValue[1])));
					newBoard.getTileAt(x,y).setFlagged(intToBool(Integer.valueOf(eachValue[2])));
				}
			}
		}
		return newBoard;
	}
	
	public boolean intToBool(int i) {
		if(i == 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
