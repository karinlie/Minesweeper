package prosjektkode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class saveToFile {

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
