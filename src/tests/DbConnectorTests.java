package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import model.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DbConnectorTests {
    private static final String jsonTest = "{\"seconds\":0,\"board\":[[{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false}],[{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true}],[{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Empty\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true}],[{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false}],[{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true}],[{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false}],[{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false}],[{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true}],[{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Empty\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false}]],\"difficulty\":\"EASY\",\"hints\":17}";
    private static final String jsonTestDifferent = "{\"seconds\":0,\"board\":[[{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false}],[{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true}],[{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Empty\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true}],[{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false}],[{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true}],[{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false}],[{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false}],[{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true}],[{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Empty\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false}]],\"difficulty\":\"EASY\",\"hints\":7}";
    private static File dbFile;
    private static DbConnector dbc;
    private static File testDir;

    @BeforeEach
    void beforeEach() {
        try {
            testDir = new File("testFiles");
            if (!testDir.exists()) {
                assertTrue(testDir.mkdir());
            }

            dbFile = new File("testFiles/saveGame.json");
            Assertions.assertTrue(dbFile.createNewFile());
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbFile, true));
            writer.write(jsonTest);
            writer.close();
            dbc = new DbConnector(dbFile.getAbsolutePath());
        } catch (IOException e) {
            fail();
        }
    }

    @AfterEach
    void afterEach() {
        if (dbFile.exists()) {
            assertTrue(dbFile.delete());
        }
        if (testDir.exists()) {
            assertTrue(testDir.delete());
        }
    }

    @Test
    public void testIsSaveExistTrue() {
        assertTrue(dbc.isSaveExist());
    }

    @Test
    public void testIsSaveExistFalse() {
        dbFile.delete();
        assertFalse(dbc.isSaveExist());
    }

    @Test
    public void testGetSaveGame() {
        try {
            GameBoard g = dbc.getSaveGame();
            Gson gson = new Gson();

            String gJson = gson.toJson(g);

            assertEquals(jsonTest, gJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testDeleteSaveGame() {
        dbc.deleteSaveGame();
        assertFalse(dbFile.exists());
        assertFalse(dbc.isSaveExist());
    }

    @Test
    public void testSaveNewGameSave() {
        dbFile.delete();

        assertFalse(dbc.isSaveExist());
        Gson gson = new Gson();
        GameBoard g = gson.fromJson(jsonTestDifferent, GameBoard.class);
        dbc.saveNewGameSave(g);

        assertTrue(dbc.isSaveExist());
        try {
            g = dbc.getSaveGame();
            String gJson = gson.toJson(g);

            assertEquals(jsonTestDifferent, gJson);
            assertNotEquals(jsonTest, gJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testUpdateNewGameSave() {
        Gson gson = new Gson();
        GameBoard g = gson.fromJson(jsonTestDifferent, GameBoard.class);
        dbc.updateGameSave(g);
        try {
            g = dbc.getSaveGame();
            String gJson = gson.toJson(g);

            assertEquals(jsonTestDifferent, gJson);
            assertNotEquals(jsonTest, gJson);
        } catch (IOException e) {
            fail();
        }
    }
}
