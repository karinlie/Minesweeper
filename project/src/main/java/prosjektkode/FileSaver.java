package prosjektkode;

import java.io.FileNotFoundException;

public interface FileSaver {

	public static final String SAVE_FOLDER = "";
	
	void save(String fileName, Board board) throws FileNotFoundException;
	
	Board load(String fileName) throws FileNotFoundException;
	
}
