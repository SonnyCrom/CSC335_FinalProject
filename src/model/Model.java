package model;

import java.io.IOException;
import java.util.ArrayList;

import controller.SudokuController;
import view.BoardObserver;
import view.Observer;
import view.SudokuGUI;

public class Model {

	private int x;
	private int y;
	private int number;
	private GameBoard board;
	private ArrayList<Observer> observers;
	private ArrayList<BoardObserver> bObservers;
	private SudokuGUI gui;
	private SudokuController controller;
	private DbConnector db;
	
	public Model() {
		this.observers = new ArrayList<Observer>();
		this.bObservers = new ArrayList<BoardObserver>();
		this.board = new GameBoard(Difficulty.EASY);
		this.gui = new SudokuGUI(this);
		this.controller = new SudokuController(this);
		this.db = new DbConnector();
	}
	
	public Model(boolean isSaved, Difficulty difficulty ) {
		this.observers = new ArrayList<Observer>();
		this.bObservers = new ArrayList<BoardObserver>();
		this.db = new DbConnector();
		if(isSaved) {
			try {
				this.board = db.getSaveGame();
			} catch (IOException e) {
				this.board = new GameBoard(difficulty);
				System.err.println("Error: Could not load saved game");
				e.printStackTrace();
			}
		}
		else {
			this.board = new GameBoard(difficulty);	
		}
		this.gui = new SudokuGUI(this);
		this.controller = new SudokuController(this);
	}
	
	public void selectBoard(int i, int j) {
		this.x = i;
		this.y = j;
		Numbers num = Numbers.Empty;

		board.fillPlace(num.fromInteger(this.number), this.x, this.y);
		//board.addNumber(num.fromInteger(this.number), this.x, this.y);
		gui.updateBoard();
		if (board.getValueAt(this.x, this.y) == Numbers.Empty) {
			System.out.println("Invlaid Move");
		} else {
			System.out.println("Added: " + board.getValueAt(this.x, this.y).toInteger());
		}
		notifyBObservers();
	}

	public void changeNum(int i) {
		this.number = i;
		notifyObservers();	
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
		for(Observer o : observers) {
			o.newNumber(this.number);
		}
	}
	private void notifyBObservers() {
		for(BoardObserver o : bObservers) {
			o.newCord(this.x, this.y);
		}
	}


	public void quitGame(Observer observer) {
	}
}
