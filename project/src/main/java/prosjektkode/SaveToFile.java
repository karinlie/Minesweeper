package prosjektkode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveToFile implements FileSaver {

	public static final String SAVE_FOLDER = "src/main/java/savedGames/";
	
	@Override
	public void save(String filename, Board board) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(SAVE_FOLDER + filename);
		// henter størrelse på brettet og skriver level på board til fil
		int size = board.getSize();
		writer.println(board.getLevel(size));
		// itererer over posisjonene i brettet og skriver nødvendig info om hver tile til fil
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				writer.print(board.getTileAt(x, y).toString());
			}
			writer.println();
		}
		System.out.println("Saved");
		writer.close();
	}
	
	@Override
	public Board load(String filename) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(SAVE_FOLDER + filename));
		Board board;
		String line0 = scanner.nextLine();
		int level = Integer.valueOf(line0);
		board = new Board(level);
		for (int y = 0; y < board.getSize(); y++) {
			String line = scanner.nextLine();
			String[] eachTile = line.split(":");
			for (int x = 0; x < board.getSize(); x++) {
				String[] eachValue = eachTile[x].split(",");
				board.getTileAt(x, y).setTile(eachValue[0]);
				board.getTileAt(x, y).setOpen(intToBool(Integer.valueOf(eachValue[1])));
				board.getTileAt(x,y).setFlagged(intToBool(Integer.valueOf(eachValue[2])));
			}
		}
		scanner.close();
		return board;
	}
	
	public boolean intToBool(int i) {
		if(i == 1) {
			return true;
		} else {
			return false;
		}
	}
		
}
