package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.*;

class BoardTests {

	Numbers number = Numbers.Empty;
	GameBoard board01 = new GameBoard(Difficulty.EASY);
	GameBoard board02 = new GameBoard(Difficulty.HARD);

	@Test
	void allCellsInit() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(number.getClass(), board01.getValueAt(i, j).getClass());
			}
		}
	}

	@Test
	void testDifficulty() {
		int countEasy = 0;
		int countHard = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board01.getValueAt(i, j) == Numbers.Empty)
					countEasy++;
				if (board02.getValueAt(i, j) == Numbers.Empty)
					countHard++;
			}
		}
		assertTrue(countEasy <= 40);
		assertTrue(countEasy <= 81);

	}

	@Test
	void testFillPlace() {
		GameBoard solvedBoard = board01.solve();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board01.getValueAt(i, j) == Numbers.Empty)
					assertTrue(board01.isValidPlacement(solvedBoard.getValueAt(i, j), i, j));
			}
		}
	}

	@Test
	void testRemoveNumber() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board01.getValueAt(i, j) != Numbers.Empty) {
					board01.removeNumber(i, j);
					assertTrue(board01.getValueAt(i, j) == Numbers.Empty);
				}
			}
		}
	}

	@Test
	void testGiveHint() {
		GameBoard solution = board01.solve();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board01.getValueAt(i, j) == Numbers.Empty) {
					Numbers correctValue = solution.getValueAt(i, j);
					board01.giveHint(i, j);
					assertEquals(board01.getValueAt(i, j), correctValue);

				}
			}
		}
	}

	@Test
	void testGameOver() {
		GameBoard solution = board02.solve();
		assertTrue(solution.gameOver());
	}

}
