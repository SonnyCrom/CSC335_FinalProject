package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.SudokuController;
import view.*;

public class Model {

    private int x;
    private int y;
    private int number;
    private GameBoard board;
    private ArrayList<Observer> observers;
    private ArrayList<BoardObserver> bObservers;
    public SudokuGUI gui;
    private SudokuController controller;
    private ValidGUI valid;
    private DbConnector db;
    private HashMap<Integer, HashMap<Integer, NumberBtnObserver>> numberObservers;

    public Model() {
        this.observers = new ArrayList<Observer>();
        this.bObservers = new ArrayList<BoardObserver>();
        this.board = new GameBoard(Difficulty.EASY);
        this.gui = new SudokuGUI(this);
        this.controller = new SudokuController(this);
        this.db = new DbConnector();
        this.numberObservers = new HashMap<>();
    }

    public Model(boolean isSaved, Difficulty difficulty) {
        this.observers = new ArrayList<Observer>();
        this.bObservers = new ArrayList<BoardObserver>();
        this.numberObservers = new HashMap<>();
        this.db = new DbConnector();
        if (isSaved) {
            try {
                this.board = db.getSaveGame();
            } catch (IOException e) {
                this.board = new GameBoard(difficulty);
                System.err.println("Error: Could not load saved game");
                e.printStackTrace();
            }
        } else {
            this.board = new GameBoard(difficulty);
        }
        this.gui = new SudokuGUI(this);
        this.controller = new SudokuController(this);
    }

    public void selectBoard(int i, int j) {
        this.x = i;
        this.y = j;
        Numbers num = Numbers.Empty;
        if (!board.getChangeAt(this.x, this.y)) {
            this.valid.isValidMove(2); // Static Cell
            return;
        }

        board.fillPlace(num.fromInteger(this.number), this.x, this.y);
        //board.addNumber(num.fromInteger(this.number), this.x, this.y);
        notifyBObservers();
        if (board.getValueAt(this.x, this.y) == Numbers.Empty) {
            this.valid.isValidMove(1); //
        } else {
            this.valid.isValidMove(0); // Is valid
            db.updateGameSave(board);
        }
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

    public void changeNum(int i) {
        this.number = i;
        notifyObservers();
    }

    public void registerNumberObserver(NumberBtnObserver observer, int row, int col) {
        if (!this.numberObservers.containsKey(row)) {
            this.numberObservers.put(row, new HashMap<>());
        }
        this.numberObservers.get(row).put(col, observer);
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void registerBObserver(BoardObserver observer) {
        this.bObservers.add(observer);
    }

    public void deregisterBObserver(BoardObserver observer) {
        this.bObservers.remove(observer);
    }

    public GameBoard getBoard() {
        return this.board.copy();
    }

    public void startGame(Observer observer) {

    }

    public void exitGame() {
        System.exit(0);
    }

    /* PRIVATE METHODS */
    private void notifyObservers() {
        for (Observer o : observers) {
            o.newNumber(this.number);
        }
    }

    private void notifyBObservers() {
        for (BoardObserver o : bObservers) {
            o.newCord(this.x, this.y);
        }
    }


    public void quitGame(Observer observer) {
    }

    public void setValid(ValidGUI validGui) {
        this.valid = validGui;
    }

}
