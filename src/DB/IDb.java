package DB;

import model.GameBoard;

public interface IDb {
    public void saveGame(GameBoard gameBoard);
    public void deleteGame();
    public boolean isGameExist();
}
