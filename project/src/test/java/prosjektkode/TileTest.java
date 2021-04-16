package prosjektkode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {
	
	
	private Tile tile;
	
	
	@BeforeEach
	public void setUp() {
		tile = new Tile(1,1);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(tile.getX(), 1);
		assertEquals(tile.getY(),1);
		assertEquals(tile.getTile(), null);
		assertFalse(tile.isOpen());
		assertFalse(tile.getFlagged());
		
	}
	
	@Test
	public void testSetInvalidTile() {
		assertThrows(IllegalArgumentException.class,() -> {
			tile.setTile("9");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			tile.setTile("0");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			tile.setTile("h");
		});
	}
	
	@Test
	public void testSetMine() {
		tile.setMine();
		assertEquals(tile.getTile(), "*");
		assertTrue(tile.isMine());
		assertFalse(tile.isEmpty());
		assertFalse(tile.isNum());
	}
	
	@Test
	public void testSetEmpty() {
		tile.setEmpty();
		assertEquals(tile.getTile(), " ");
		assertTrue(tile.isEmpty());
		assertFalse(tile.isMine());
		assertFalse(tile.isNum());
	}
	
	@Test
	public void testSetNumber() {
		tile.setNumber(1);
		assertEquals(tile.getTile(), "1");
		assertThrows(IllegalArgumentException.class, () -> {
			tile.setNumber(-1);
		});
		assertTrue(tile.isNum());
		assertFalse(tile.isMine());
		assertFalse(tile.isEmpty());
	}
	
	@Test
	public void testOpen() {
		tile.setOpen(true);
		assertTrue(		tile.isOpen());
		tile.setOpen(false);
		assertFalse(tile.isOpen());
	}
	
	
	
}
