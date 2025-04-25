package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.TimerObserver;

public class TimeModel {
	private int seconds;
	private TimerObserver observer;
	private Timer timer;
	
	public TimeModel(int seconds) {
		this.seconds = seconds;
	}
	
	public void increment() {
		seconds++;
		notifyObserver();
	}
	
	private void notifyObserver() {
		this.observer.newTime(seconds);
	}

	public int getSeconds() {
		return seconds;
	}
	
	public void setObserver(TimerObserver observer) {
		this.observer = observer;
	}
	
    // code taken from -> https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html
    public void startTimer() {
    	  int delay = 1000;
    	  ActionListener taskPerformer = new ActionListener() {
    	      public void actionPerformed(ActionEvent evt) {
    	          increment();
    	      }
    	  };
    	  timer = new Timer(delay, taskPerformer);
    	  timer.start();
    }
    
    public void stopTimer() {
    	timer.stop();
    }
	
}
