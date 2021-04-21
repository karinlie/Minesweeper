package prosjektkode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prosjektkode.Board.WinOrLose;

import org.junit.jupiter.api.DisplayName;

public class BoardTest {
	
	private Board board;
	private Board board2;
	
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
		board = new Board(1);
		createBoard();
	}
	
	
	@Test
	@DisplayName("Tester konstruktøren")
	public void testConstructor() {
		//sjekker at level 1 henter ut rett size fra lista med str
		assertEquals(10, board.getSize());
		//sjekker at level settes rett, og at getLevel stemmer
		assertEquals(1, board.getLevel(board.getSize()));
		
		//sjekker at antall miner er korrekt (ved å iterere over brettet)
		int expectedNumOfMines = 10;
		int actualNumOfMines = 0;
		for (int y = 0; y < board.getSize(); y++) {
			for (int x = 0; x < board.getSize(); x++){
				if (board.getTileAt(x, y).isMine()) {
					actualNumOfMines++;
				}
			}
		}
		
		assertEquals(expectedNumOfMines, actualNumOfMines);
		
		//sjekker at validering for konstruktøren stemmer
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(4);
		});
	}
	
	@Test
	@DisplayName("Sjekk at nabominer telles rett")
	public void testGetNeighborMines() {
		//sjekker at getNeighborMines teller rett (vi har konstruert et brett i setup())
		assertEquals(2, board2.getNeighborMines(1, 0)); 
		assertEquals(4, board2.getNeighborMines(2, 7));
		
		//sjekker at ugyldig posisjon kaster exception
		assertThrows(IllegalArgumentException.class, () -> {
			board2.getNeighborMines(-1, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			board2.getNeighborMines(10,10);
		});
		
		//sjekker at setNeighborMines fungerer som den skal, og at tallet settes riktig på tile
		board2.setNeighborMines(1,0,board2.getNeighborMines(1, 0));
		assertTrue(board2.getTileAt(1,0).isNum());
		assertEquals("2", board2.getTileAt(1, 0).getTile());
	}
	
	@Test
	@DisplayName("Sjekk at tiles åpnes korrekt")
	public void testOpenTile() {
		//åpner felt med tall rundt. Bare det feltet skal åpnes, og ikke de rundt
		board2.openTile(1, 0);
		assertTrue(board2.getTileAt(1, 0).isOpen());
		assertFalse(board2.getTileAt(0, 0).isOpen());
		assertFalse(board2.getTileAt(2, 0).isOpen());
		assertFalse(board2.getTileAt(3, 0).isOpen());
		assertFalse(board2.getTileAt(0, 1).isOpen());
		assertFalse(board2.getTileAt(1, 1).isOpen());
		assertFalse(board2.getTileAt(2, 1).isOpen());

		//åpner tomt felt og sjekker at den rekursive rundt fungerer korrekt
		board2.openTile(0, 9);
		assertTrue(board2.getTileAt(0, 9).isOpen());
		assertTrue(board2.getTileAt(1, 9).isOpen());
		assertTrue(board2.getTileAt(2, 9).isOpen());
		assertTrue(board2.getTileAt(0, 8).isOpen());
		assertTrue(board2.getTileAt(1, 8).isOpen());
		assertTrue(board2.getTileAt(2, 8).isOpen());
		assertFalse(board2.getTileAt(3, 9).isOpen());
		assertFalse(board2.getTileAt(3, 8).isOpen());
		assertFalse(board2.getTileAt(0, 7).isOpen());
		assertFalse(board2.getTileAt(1, 7).isOpen());
		assertFalse(board2.getTileAt(2, 7).isOpen());
		assertFalse(board2.getTileAt(3, 7).isOpen());
	}
	
	@Test
	@DisplayName("Tester at alle Tiles åpnes")
	public void testOpenAll() {
		board2.openAll();
		for (int y = 0; y < board2.getSize(); y++) {
			for (int x = 0; x < board2.getSize(); x++) {
				assertTrue(board2.getTileAt(x, y).isOpen());
			}
		}
	}
	
	@Test
	@DisplayName("Tester game over")
	public void testGameOver() {
		board2.openTile(0, 0);
		board2.checkGameOverOrWon(0, 0);
		assertEquals(WinOrLose.LOSE.name(), board2.getStatus().name());
	}
	
	@Test
	@DisplayName("Tester game won")
	public void testGameWon() {
		for (int y = 0; y < board2.getSize(); y++) {
			for (int x = 0; x < board2.getSize(); x++) {
				if (!board2.getTileAt(x, y).isMine()) {
					if (y == 9 && x == 9) {
						break;
					}
					board2.getTileAt(x, y).setOpen(true);
				}
			}
		}
		board2.checkGameOverOrWon(9, 9);
		assertEquals(WinOrLose.WIN.name(), board2.getStatus().name());
	}	
	
	
	@Test
	@DisplayName("Tester game continue")
	public void testGameContinue() {
		board2.checkGameOverOrWon(2, 1);
		assertEquals(WinOrLose.CONTINUE.name(), board2.getStatus().name());
		board2.checkGameOverOrWon(9, 9);
		assertEquals(WinOrLose.CONTINUE.name(), board2.getStatus().name());
	}
}
