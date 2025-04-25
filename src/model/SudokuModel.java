package model;

import view.BoardObserver;
import view.MsgObserver;
import view.NumberBtnObserver;
import view.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SudokuModel {
    private GameBoard board;
    private Numbers selectedNumber;
    private DbConnector db;
    private HashMap<Integer, HashMap<Integer, NumberBtnObserver>> numberObservers;
    private MsgObserver msgObserver;
    private boolean isChosingHint;

    public SudokuModel(Difficulty difficulty) {
        this.board = new GameBoard(difficulty);
        setUpModel();
    }

    public SudokuModel() {
        try {
            this.board = db.getSaveGame();
            setUpModel();
        } catch (IOException e) {
            System.err.println("Error: Could not load saved game");
        }
    }

    private void setUpModel() {
//        this.observers = new ArrayList<Observer>();
//        this.bObservers = new ArrayList<BoardObserver>();
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector();
        this.selectedNumber = Numbers.Empty;
    }

    public void loadBoard() {
        for (int row : this.numberObservers.keySet()) {
            for (int col : this.numberObservers.get(row).keySet()) {
                this.numberObservers.get(row).get(col).setText(board.getValueAt(row, col));
                if (!board.getChangeAt(row, col)) {
                    this.numberObservers.get(row).get(col).setDisable();
                }
            }
        }
    }

    public void setMsgObserver(MsgObserver observer) {
        msgObserver = observer;
    }

    public void registerNumberObserver(NumberBtnObserver observer, int row, int col) {
        if (!this.numberObservers.containsKey(row)) {
            this.numberObservers.put(row, new HashMap<>());
        }
        this.numberObservers.get(row).put(col, observer);
    }

    public void choseNumber(Numbers num) {
        selectedNumber = num;
        isChosingHint = false;
        msgObserver.newNumber(num.toInteger());
    }

    public void choseHint() {
        isChosingHint = true;
        msgObserver.hint(true);
    }
}
