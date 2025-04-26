package model;

import DB.DbJson;
import DB.IDb;

import java.io.IOException;

public class DbConnector {
    private static final String DEFAULT_FILE_PATH = "gameSave.json";

    private final IDb db;

    public DbConnector() {
        this(DEFAULT_FILE_PATH);
    }

    public DbConnector(String filepath) {
        this.db = new DbJson(filepath);
    }

    public boolean isSaveExist() {
        return db.isGameExist();
    }

    public void saveNewGameSave(GameBoard gameBoard) {
        db.saveGame(gameBoard);
    }

    public void updateGameSave(GameBoard gameBoard) {
        // This does the same as saveNewGameSave, but we have both function because the user doesn't
        // need to know the behaviour of save and update are the same
        db.saveGame(gameBoard);
    }

    public void deleteSaveGame() {
        db.deleteGame();
    }

    public GameBoard getSaveGame() throws IOException {
        return db.getSaveGame();
    }

}
