package model;

import view.EndGameObserver;
import view.MsgObserver;
import view.TimerObserver;
import view.BtnObserver;

import java.io.IOException;
import java.util.HashMap;

public class SudokuModel {
    private GameBoard board;
    private Numbers selectedNumber;
    private final DbConnector db;
    private final HashMap<Integer, HashMap<Integer, BtnObserver>> numberObservers;
    private MsgObserver msgObserver;
    private BtnObserver hintObserver;
    private EndGameObserver endGameObserver;
    private boolean isChoosingHint;
    private TimerObserver timerObserver;

    public SudokuModel(Difficulty difficulty) {
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector();
        this.selectedNumber = Numbers.Empty;
        this.board = new GameBoard(difficulty);
        this.db.saveNewGameSave(this.board);
    }

    public SudokuModel() {
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector();
        this.selectedNumber = Numbers.Empty;
        try {
            this.board = db.getSaveGame();
        } catch (IOException e) {
            System.err.println("Error: Could not load saved game");
        }
    }

    public SudokuModel(Difficulty difficulty, String filePath) {
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector(filePath);
        this.selectedNumber = Numbers.Empty;
        this.board = new GameBoard(difficulty);
        this.db.saveNewGameSave(this.board);
    }

    public SudokuModel(String filePath) {
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector(filePath);
        this.selectedNumber = Numbers.Empty;
        try {
            this.board = db.getSaveGame();
        } catch (IOException e) {
            System.err.println("Error: Could not load saved game");
        }
    }

    public void loadBoard() {
        for (int row : this.numberObservers.keySet()) {
            for (int col : this.numberObservers.get(row).keySet()) {
                this.numberObservers.get(row).get(col).setText(board.getValueAt(row, col).toInteger());
                if (!board.getChangeAt(row, col)) {
                    this.numberObservers.get(row).get(col).setDisable();
                }
            }
        }
    }

    public void setMsgObserver(MsgObserver observer) {
        msgObserver = observer;
    }

    public void setHintObserver(BtnObserver observer) {
        hintObserver = observer;
        updateHintsBtn();
    }

    public void updateHintsBtn() {
        int hints = board.getHints();
        hintObserver.setText(hints);
        if (hints <= 0) {
            hintObserver.setDisable();
        }
    }

    public void registerNumberObserver(BtnObserver observer, int row, int col) {
        if (!this.numberObservers.containsKey(row)) {
            this.numberObservers.put(row, new HashMap<>());
        }
        this.numberObservers.get(row).put(col, observer);
    }

    public void choseNumber(Numbers num) {
        selectedNumber = num;
        isChoosingHint = false;
        msgObserver.newNumber(num.toInteger());
    }

    public void choseHint() {
        isChoosingHint = true;
        msgObserver.hint(true);
    }

    public void updateCell(int row, int col) {
        if (!isChoosingHint) {
            boolean fillResult = this.board.fillPlace(selectedNumber, row, col);
            // update the button if the user input of the cell is correct
            if (fillResult) {
                numberObservers.get(row).get(col).setText(selectedNumber.toInteger());
                msgObserver.newNumber(selectedNumber.toInteger());
                db.updateGameSave(board);
                handleIfGameOver();
            } else {
                msgObserver.incorrect(selectedNumber.toInteger());
            }
        } else {
            Numbers correctNum = board.useHintAt(row, col);
            numberObservers.get(row).get(col).setText(correctNum.toInteger());
            db.updateGameSave(board);
            updateHintsBtn();
            handleIfGameOver();
            // If we used the last hint then we need to change the selection
            if (board.getHints() <= 0) {
                isChoosingHint = false;
                selectedNumber = Numbers.Empty;
                msgObserver.newNumber(selectedNumber.toInteger());
            }
        }
    }

    public void setEndGameObserver(EndGameObserver observer) {
        endGameObserver = observer;
        handleIfGameOver();
    }

    public void handleIfGameOver() {
        if (board.gameOver()) {
            db.deleteSaveGame();
            endGameObserver.handleEndGame();
        }
    }

    public void incrementTimer() {
        if (!board.gameOver()) {
            this.board.incSecs();
            db.updateGameSave(board);
            notifyTimer();
        }
    }

    private void notifyTimer() {
        this.timerObserver.newTime(this.board.getSeconds());
    }

    public void registerTimer(TimerObserver observer) {
        this.timerObserver = observer;
    }

    public int getInitSeconds() {
        return board.getSeconds();
    }

}
