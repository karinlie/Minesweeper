package prosjektkode;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SaveToFileTest {

	private Board board1;
	private Board board2;
	private SaveToFile saveToFile = new SaveToFile();
	
	private Board createBoard() {
		Board board2 = new Board(1);
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
		
		return board2;
	}
	
	
	@BeforeEach
	public void setup() {
		board2 = createBoard();
		board1 = createBoard();
		board1.openTile(1, 0);
		try {
			saveToFile.save("test-with-opened", board1);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't save board3");
		}
	}
	
	@Test
	@DisplayName("Sjekker load metoden")
	public void testLoad() {
		
		Board savedBoard;
		try {
			savedBoard = saveToFile.load("saved-board-test");
		} catch(FileNotFoundException e) {
			fail("Could not load saved file");
			return;
		}
		assertEquals(board2.toString(), savedBoard.toString());
		
		
		//sjekker at det ikke gå å laste ikke-eksisterende fil
		assertThrows(FileNotFoundException.class, () -> 
					 board2 = saveToFile.load("eksisterer-ikke")
					 );
	}
	
	@Test
	@DisplayName("Kan lagre påbegynte spill")
	public void testLoadStartedGame() {
		Board savedBoard2;
		try {
			savedBoard2 = saveToFile.load("test-with-opened");
		} catch(FileNotFoundException e) {
			fail("Could not load saved file");
			return;
		}
		assertEquals(board1.toString(), savedBoard2.toString());
	}
 
	@Test
	@DisplayName("Skal ikke kunne laste fil som ikke er korrekt")
	public void testInvalidFile() {
		assertThrows(Exception.class, () ->
					 board2 = saveToFile.load("invalid-test") // opprettet en save-fil som manglet en linje
					 );
	}
	
	@Test
	@DisplayName("Sjekker save metoden")
	public void testSave() {
		try {
			saveToFile.save("saved-board-new", board2);
		} catch (FileNotFoundException e) {
			fail("Could not save file");
		}
		
		byte[] testFile = null, newFile = null;
		
		try {
			testFile = Files.readAllBytes(Path.of(SaveToFile.getFilePath("saved-board-test")));
		} catch (IOException e) {
			fail("Could not load test file");
		}

		try {
			newFile = Files.readAllBytes(Path.of(SaveToFile.getFilePath("saved-board-new")));
		} catch (IOException e) {
			fail("Could not load saved file");
		}
		assertNotNull(testFile);
		assertNotNull(newFile);
		assertTrue(Arrays.equals(testFile, newFile));
	}
	
	@AfterAll
	static void teardown() {
		File newTestSaveFile = new File(SaveToFile.getFilePath("saved-board-new"));
		newTestSaveFile.delete();
	}
	
}
