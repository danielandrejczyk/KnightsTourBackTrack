import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ChessBoardTest {
	
	ChessBoard myBoard = null;
	
	@BeforeEach
	void setUp() throws Exception {
		myBoard = new ChessBoard();
	}
	
	@Test
	void testConstructor() {
		assertEquals(0,myBoard.getSquare(1,1));
	}
	
	void testGetSquare() {
		try {
			int x = myBoard.getSquare(-5,-5);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		}
	}
	
	@Test
	void testSetSquare() {
		myBoard.markSquare(2,2,1);
		assertEquals(1,myBoard.getSquare(2,2));
	}
}

