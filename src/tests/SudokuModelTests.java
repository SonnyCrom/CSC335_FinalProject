package tests;

import com.google.gson.Gson;
import model.Difficulty;
import model.GameBoard;
import model.Numbers;
import model.SudokuModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.EndGameObserver;
import view.HintBtn;
import view.MsgLabel;
import view.NumberBtn;

import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuModelTests {
    private static final String jsonTest = "{\"board\":[[{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false}],[{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true}],[{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Empty\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true}],[{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false}],[{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true}],[{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false}],[{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false}],[{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true}],[{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Empty\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false}]],\"difficulty\":\"EASY\",\"hints\":17}";
    private static final String jsonTestNoHints = "{\"board\":[[{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false}],[{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true}],[{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Empty\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true}],[{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false}],[{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true}],[{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false}],[{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false}],[{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true}],[{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Empty\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false}]],\"difficulty\":\"EASY\",\"hints\":0}";
    private static final String jsonTestEnd = "{\"board\":[[{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false}],[{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true}],[{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true}],[{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false}],[{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":true},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true}],[{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false}],[{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false}],[{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":false},{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":false},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":true}],[{\"val\":\"Four\",\"correctVal\":\"Four\",\"canChange\":true},{\"val\":\"Three\",\"correctVal\":\"Three\",\"canChange\":false},{\"val\":\"Two\",\"correctVal\":\"Two\",\"canChange\":false},{\"val\":\"Five\",\"correctVal\":\"Five\",\"canChange\":false},{\"val\":\"One\",\"correctVal\":\"One\",\"canChange\":true},{\"val\":\"Six\",\"correctVal\":\"Six\",\"canChange\":true},{\"val\":\"Eight\",\"correctVal\":\"Eight\",\"canChange\":false},{\"val\":\"Seven\",\"correctVal\":\"Seven\",\"canChange\":false},{\"val\":\"Nine\",\"correctVal\":\"Nine\",\"canChange\":false}]],\"difficulty\":\"EASY\",\"hints\":0}";
    private static File dbFile;
    private static File dbFileNoHints;
    private static File dbFileEnd;
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

            dbFileNoHints = new File("testFiles/saveGame2.json");
            Assertions.assertTrue(dbFileNoHints.createNewFile());
            writer = new BufferedWriter(new FileWriter(dbFileNoHints, true));
            writer.write(jsonTestNoHints);
            writer.close();

            dbFileEnd = new File("testFiles/saveGame3.json");
            Assertions.assertTrue(dbFileEnd.createNewFile());
            writer = new BufferedWriter(new FileWriter(dbFileEnd, true));
            writer.write(jsonTestEnd);
            writer.close();

        } catch (IOException e) {
            fail();
        }
    }

    @AfterEach
    void afterEach() {
        if (dbFile.exists()) {
            Assertions.assertTrue(dbFile.delete());
        }
        if (dbFileNoHints.exists()) {
            Assertions.assertTrue(dbFileNoHints.delete());
        }
        if (dbFileEnd.exists()) {
            Assertions.assertTrue(dbFileEnd.delete());
        }
        if (testDir.exists()) {
            assertTrue(testDir.delete());
        }
    }

    @Test
    void testCtorWithDifficulty() {
        SudokuModel model = new SudokuModel(Difficulty.EASY, dbFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertNotEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testCtorWithoutDifficulty() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testLoadBoard() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        NumberBtn lock = new NumberBtn(Color.green);
        model.registerNumberObserver(lock, 0, 3);
        NumberBtn withVal = new NumberBtn(Color.green);
        model.registerNumberObserver(withVal, 0, 0);
        NumberBtn empty = new NumberBtn(Color.green);
        model.registerNumberObserver(empty, 2, 3);
        model.loadBoard();

        assertFalse(lock.isEnabled());
        assertTrue(withVal.isEnabled());
        assertTrue(empty.isEnabled());
    }

    @Test
    void testChoseNumberNonEmpty() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseNumber(Numbers.Eight);
        assertTrue(msgLabel.getText().contains(Numbers.Eight.toString()));
    }

    @Test
    void testChoseNumberEmpty() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseNumber(Numbers.Empty);
        assertTrue(msgLabel.getText().contains(Numbers.Empty.toString()));
    }

    @Test
    void testChoseHint() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseHint();

        assertTrue(msgLabel.getText().contains("hint"));
    }

    @Test
    void testUpdateHintsBtnHintsExist() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        HintBtn hints = new HintBtn();
        model.setHintObserver(hints);
        model.updateHintsBtn();
        Gson gson = new Gson();
        GameBoard g = gson.fromJson(jsonTest, GameBoard.class);

        assertTrue(hints.getText().contains(String.valueOf(g.getHints())));
        assertTrue(hints.isEnabled());
    }

    @Test
    void testUpdateHintsBtnHintsZero() {
        SudokuModel model = new SudokuModel(dbFileNoHints.getAbsolutePath());
        HintBtn hints = new HintBtn();
        model.setHintObserver(hints);
        model.updateHintsBtn();

        assertTrue(hints.getText().contains("0"));
        assertFalse(hints.isEnabled());
    }

    @Test
    void testHandleIfGameOverFalse() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();
        model.setEndGameObserver(observerTest);
        model.handleIfGameOver();
        assertFalse(observerTest.flag);
    }

    @Test
    void testHandleIfGameOverTrue() {
        SudokuModel model = new SudokuModel(dbFileEnd.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();
        model.setEndGameObserver(observerTest);
        model.handleIfGameOver();

        assertTrue(observerTest.flag);
    }

    @Test
    void testUpdateCellIncorrect() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();
        model.setEndGameObserver(observerTest);

        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseNumber(Numbers.Five);

        NumberBtn empty = new NumberBtn(Color.green);
        model.registerNumberObserver(empty, 2, 3);

        model.updateCell(2, 3);
        assertTrue(msgLabel.getText().toLowerCase().contains("incorrect"));
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testUpdateCellCorrect() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();

        model.setEndGameObserver(observerTest);

        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseNumber(Numbers.Three);

        NumberBtn empty = new NumberBtn(Color.green);
        model.registerNumberObserver(empty, 2, 3);

        model.updateCell(2, 3);
        assertFalse(msgLabel.getText().toLowerCase().contains("incorrect"));
        assertTrue(empty.getText().contains(Numbers.Three.toString()));
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertNotEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testUpdateCellUseHint() {
        SudokuModel model = new SudokuModel(dbFile.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();
        model.setEndGameObserver(observerTest);

        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseHint();

        NumberBtn empty = new NumberBtn(Color.green);
        model.registerNumberObserver(empty, 2, 3);

        HintBtn hints = new HintBtn();
        model.setHintObserver(hints);

        model.updateCell(2, 3);
        assertTrue(msgLabel.getText().toLowerCase().contains("hint"));
        assertTrue(empty.getText().contains(Numbers.Three.toString()));
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertNotEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }

        assertTrue(hints.isEnabled());
    }

    @Test
    void testUpdateCellUseLastHint() {
        SudokuModel model = new SudokuModel(dbFileNoHints.getAbsolutePath());
        EndGameObserverTest observerTest = new EndGameObserverTest();
        model.setEndGameObserver(observerTest);

        MsgLabel msgLabel = new MsgLabel();
        model.setMsgObserver(msgLabel);
        model.choseHint();

        NumberBtn empty = new NumberBtn(Color.green);
        model.registerNumberObserver(empty, 2, 3);

        HintBtn hints = new HintBtn();
        model.setHintObserver(hints);

        model.updateCell(2, 3);
        assertFalse(msgLabel.getText().toLowerCase().contains("hint"));
        assertTrue(empty.getText().contains(Numbers.Three.toString()));
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFileNoHints.getAbsolutePath()))) {
            String newJson = reader.readLine();
            assertNotEquals(jsonTest, newJson);
        } catch (IOException e) {
            fail();
        }

        assertFalse(hints.isEnabled());
    }


}

class EndGameObserverTest implements EndGameObserver {
    boolean flag = false;

    @Override
    public void handleEndGame() {
        flag = true;
    }
}



