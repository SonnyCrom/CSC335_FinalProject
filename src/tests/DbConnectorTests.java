package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import model.*;

public class DbConnectorTests {

	
	@Test
	public void DbCreationTest() {
		DbConnector db = new DbConnector();
	}
	
	@Test
	public void DbSaveTest() {
		DbConnector db = new DbConnector();
		db.isSaveExist();
		
		GameBoard board = new GameBoard(Difficulty.HARD);
		db.saveNewGameSave(board);
		
		GameBoard updateboard = new GameBoard(Difficulty.HARD);
		db.updateGameSave(updateboard);
		
		try {
			GameBoard getBoard = db.getSaveGame();
		} catch (IOException e) {
			System.out.println("No save found");
		}
		
		db.deleteSaveGame();
		assertFalse(db.isSaveExist());
	}
	
}
