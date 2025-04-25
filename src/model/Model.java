package model;

import java.util.ArrayList;

import view.BoardObserver;
import view.Observer;

public class Model {

	private int x;
	private int y;
	private int number;
	private GameBoard board;
	private ArrayList<Observer> observers;
	private ArrayList<BoardObserver> bObservers;
	
	public Model() {
		this.observers = new ArrayList<Observer>();
		this.bObservers = new ArrayList<BoardObserver>();
		this.board = new GameBoard(Difficulty.EASY);
	}
	
	public void selectBoard(int i, int j) {
		this.x = i;
		this.y = j;

		board.fillPlace(Numbers.One, this.x, this.y);
		notifyBObservers();
	}

	public void changeCurrent() {
		board.fillPlace(Numbers.One, this.x, this.y);
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
