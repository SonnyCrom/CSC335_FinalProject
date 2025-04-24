package model;

import java.util.ArrayList;

import view.Observer;

public class Model {

	private int number;
	private ArrayList<Observer> observers;
	
	public Model() {
		this.observers = new ArrayList<Observer>();
	}
	

	public void changeNum(int i) {
		this.number = i;
		notifyObservers();
		
	}
	// Change
	public void increment() {
		this.number++;
		notifyObservers();
	}
	//
	
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	public void deregisterObserver(Observer observer) {
		this.observers.remove(observer);
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


	public void quitGame(Observer observer) {
	}
}
