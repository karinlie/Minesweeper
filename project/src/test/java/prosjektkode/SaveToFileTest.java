package prosjektkode;

import org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileNotFoundException;


public class SaveToFileTest {

	private Board board2;
	private SaveToFile saveToFile = new SaveToFile();
	
	private void createBoard() {
		board2 = new Board(1);
		// gjør alle tilene tomme
		board2.addEmptyTiles();
		// legger til miner på alle tallene
		board2.getTileAt(0,0).setMine();
		board2.getTileAt(1, 1).setMine();
		board2.getTileAt(6, 1).setMine();
		board2.getTileAt(3, 3).setMine();
		board2.getTileAt(5, 4).setMine();
		board2.getTileAt(2, 6).setMine();
		board2.getTileAt(3, 6).setMine();
		board2.getTileAt(1, 7).setMine();
		board2.getTileAt(5, 7).setMine();
		board2.getTileAt(3, 8).setMine();
		
		board2.getTileAt(1, 0).setNumber(2);
		board2.getTileAt(5, 0).setNumber(1);
		board2.getTileAt(6, 0).setNumber(1);
		board2.getTileAt(7, 0).setNumber(1);
		board2.getTileAt(0, 1).setNumber(2);
		board2.getTileAt(5, 1).setNumber(1);
		board2.getTileAt(7, 1).setNumber(1);
		board2.getTileAt(2, 2).setNumber(1);
		board2.getTileAt(3, 2).setNumber(1);
		board2.getTileAt(4, 2).setNumber(1);
		board2.getTileAt(5, 2).setNumber(1);
		board2.getTileAt(6, 2).setNumber(1);
		board2.getTileAt(7, 2).setNumber(1);
		board2.getTileAt(2, 3).setNumber(1);
		board2.getTileAt(4, 3).setNumber(2);
		board2.getTileAt(5, 3).setNumber(1);
		board2.getTileAt(6, 3).setNumber(1);
		board2.getTileAt(2, 4).setNumber(1);
		board2.getTileAt(2, 4).setNumber(1);
		board2.getTileAt(3, 4).setNumber(1);
		board2.getTileAt(4, 4).setNumber(2);
		board2.getTileAt(6, 4).setNumber(1);
		board2.getTileAt(4, 5).setNumber(2);
		board2.getTileAt(5, 5).setNumber(1);
		board2.getTileAt(6, 5).setNumber(1);
		board2.getTileAt(0, 6).setNumber(1);
		board2.getTileAt(1, 6).setNumber(2);
		board2.getTileAt(4, 6).setNumber(2);
		board2.getTileAt(5, 6).setNumber(1);
		board2.getTileAt(6, 6).setNumber(1);
		board2.getTileAt(0, 7).setNumber(1);
		board2.getTileAt(2, 7).setNumber(4);
		board2.getTileAt(3, 7).setNumber(3);
		board2.getTileAt(4, 7).setNumber(3);
		board2.getTileAt(6, 7).setNumber(1);
		board2.getTileAt(0, 8).setNumber(1);
		board2.getTileAt(1, 8).setNumber(1);
		board2.getTileAt(2, 8).setNumber(2);
		board2.getTileAt(4, 8).setNumber(2);
		board2.getTileAt(5, 8).setNumber(1);
		board2.getTileAt(6, 8).setNumber(1);
		board2.getTileAt(2, 9).setNumber(1);
		board2.getTileAt(3, 9).setNumber(1);
		board2.getTileAt(4, 9).setNumber(1);
	}
	
	@BeforeEach
	public void setup() {
		createBoard();
	}
	@Test
	public void testLoad() {
		Board savedBoard = board2;
		try {
			savedBoard = saveToFile.load("saved-board-test");
		} catch(FileNotFoundException e) {
			fail("Could not load saved file");
			return;
		}
		assertEquals(board2, savedBoard);
		
	}
	
	@Test
	public void testSave() {
		
	}
}
