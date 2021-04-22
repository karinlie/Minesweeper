package prosjektkode;

import java.io.FileNotFoundException;

public interface FileSaver {
	
	void save(String fileName, Board board) throws FileNotFoundException;
	
	Board load(String fileName) throws FileNotFoundException;
	
}
