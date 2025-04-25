package DB;

import model.GameBoard;

import java.io.IOException;

public interface IDb {
    void saveGame(GameBoard gameBoard);

    void deleteGame();

    boolean isGameExist();

    GameBoard getSaveGame() throws IOException;
}
