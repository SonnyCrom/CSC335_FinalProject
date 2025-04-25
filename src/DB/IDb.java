package DB;

import model.GameBoard;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDb {
    public void saveGame(GameBoard gameBoard);

    public void deleteGame();

    public boolean isGameExist();

    public GameBoard getSaveGame() throws IOException;
}
