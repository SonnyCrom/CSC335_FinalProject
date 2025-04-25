package tests;


import model.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTests {

	GameBoardCell testCell01 = new GameBoardCell(Numbers.One, false);
	GameBoardCell testCell02 = new GameBoardCell(Numbers.One, true);
	
	@Test
	void testSetCorrectValue() {
		assertEquals(testCell01.getCorrectVal(), Numbers.Empty);
		testCell01.setCorrectVal(Numbers.Two);
		assertEquals(testCell01.getCorrectVal(), Numbers.Two);
	}
	
	@Test
	void testCanChange() {
		assertEquals(false, testCell01.canChange());
	}
	
	@Test
	void testCanChange12() {
		assertEquals(true, testCell02.canChange());
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
	
}