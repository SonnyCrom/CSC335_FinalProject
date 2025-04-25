package tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import model.*;
import view.BtnObserver;
import view.StartScreen;
import view.SudokuGUI;
import DB.*;

public class ModelTests {

	GameBoard game = new GameBoard(Difficulty.EASY);
	
	@Test
	public void testStartModel() {
		StartModel start = new StartModel();
	}
	
	@Test
	public void testSudokuModel() {
		SudokuModel game = new SudokuModel(Difficulty.EASY);
		
		SudokuModel game2 = new SudokuModel();
		
		game.handleIfGameOver();
	}
	
	/* JUnit Test cannot handle GUI's so any observer related
	 * lines cannot be tested within this file
	 * */

}
