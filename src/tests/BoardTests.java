package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Difficulty;
import model.GameBoard;
import model.Numbers;

class BoardTests {
	GameBoard hardBoard = new GameBoard(Difficulty.HARD);
	GameBoard easyBoard = new GameBoard(Difficulty.EASY);
	GameBoard hintsBoard = new GameBoard(Difficulty.HARD);
	
	
	@Test
	void testGetHints() {
		assertEquals(2, hardBoard.getHints());
		assertEquals(5, easyBoard.getHints());
	}
	


	@Test
	void testFillPlace() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(easyBoard.getValueAt(i, j).equals(Numbers.Empty) && easyBoard.getChangeAt(i, j)) {
					for(Numbers n : Numbers.values()) {
						if(easyBoard.fillPlace(n, i, j)) {
							assertEquals(n, easyBoard.getValueAt(i, j));
						}
					}

				}
			}
		}
	}
	
	
	@Test
	void testStartingBoard() {
		for(int i =0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(hardBoard.getValueAt(i, j).equals(Numbers.Empty)) {
					assertTrue(hardBoard.getChangeAt(i, j));
				}
				else {
					assertFalse(hardBoard.getChangeAt(i, j));
				}
			}
		}
	}
	
	@Test
	void testGameOver() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				hardBoard.useHintAt(i, j);
			}
		}
		assertTrue(hardBoard.gameOver());
	}
	

	@Test
	void testHints() {
		for(int i =0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(hintsBoard.getValueAt(i, j).equals(Numbers.Empty)) {
					Numbers hint = hintsBoard.useHintAt(i, j);
					assertEquals(hintsBoard.getValueAt(i, j), hint);
				}
				
			}
		}
	}
}
