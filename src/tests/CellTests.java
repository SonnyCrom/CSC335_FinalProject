package tests;


import model.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTests {

	GameBoardCell testCell01 = new GameBoardCell(Numbers.One, false, 1, 1);
	GameBoardCell testCell02 = new GameBoardCell(Numbers.One, false, 1, 1);
	GameBoardCell testCell03 = new GameBoardCell(Numbers.One, false, 2, 1);
	GameBoardCell testCell04 = new GameBoardCell(Numbers.Empty, true, 2, 1);
	GameBoardCell testCell05 = new GameBoardCell(Numbers.Two, true, 2, 2);
	
	
	
	
	@Test
	void testGetCol() {
		assertEquals(1, testCell01.getCol());
	}
	
	@Test
	void testGetRow() {
		assertEquals(1, testCell01.getRow());
	}
	
	@Test
	void testCanChange() {
		assertEquals(false, testCell01.canChange());
	}
	
	@Test
	void testCanChange12() {
		assertEquals(true, testCell05.canChange());
	}
	
	@Test
	void testGetVal() {
		assertEquals(Numbers.One, testCell01.getVal());
	}
	
	@Test
	void testSetVal() {
		testCell01.setVal(Numbers.Two);
		assertEquals(Numbers.One, testCell01.getVal());
	}
	
	@Test
	void testSetCanChange() {
		testCell01.setCanChange(true);
		testCell01.setVal(Numbers.Two);
		assertEquals(Numbers.Two, testCell01.getVal());
	}
	
	@Test
	void testCopy() {
		GameBoardCell testCell01Copy = testCell01.copy();
		assertEquals(testCell01, testCell01Copy);
	}
	
	@Test
	void testHash() {
		GameBoardCell testCell01Copy = testCell01.copy();
		int testCell01Hash = testCell01.hashCode();
		int testCell01CopyHash = testCell01Copy.hashCode();
		assertEquals(testCell01Hash, testCell01CopyHash);
	}

	@Test
	void testEquals() {
		assertTrue(testCell01.equals(testCell01));
		assertFalse(testCell01.equals(null));
		assertFalse(testCell01.equals(new GameBoard(Difficulty.EASY)));
		assertTrue(testCell01.equals(testCell02));
		assertFalse(testCell01.equals(testCell03));
	}
	
	
}
