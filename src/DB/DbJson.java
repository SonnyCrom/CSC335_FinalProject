package DB;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.GameBoard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class DbJson implements IDb {
    private final Gson gson;
    private final String file_path;

    public DbJson(String filepath) {
        gson = new Gson();
        file_path = filepath;
    }

    public void saveGame(GameBoard gameBoard) {
        try (FileWriter writer = new FileWriter(file_path)) {
            gson.toJson(gameBoard, writer);
        } catch (IOException e) {
            System.out.println("Failed saving db");
        }
    }

    public void deleteGame() {
        File dataFile = new File(file_path);
        if (dataFile.exists()) {
            dataFile.delete();
        }
    }

    public boolean isGameExist() {
        File dataFile = new File(file_path);
        return dataFile.exists();
    }

    @Override
    public GameBoard getSaveGame() throws IOException {
        Reader reader = new FileReader(file_path);
        GameBoard gameBoard = gson.fromJson(reader, GameBoard.class);
        reader.close();
        return gameBoard;
    }
}
