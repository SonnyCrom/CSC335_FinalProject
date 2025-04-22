package model;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.Observer;

public class Numbers extends JLabel implements Observer {
	
	public Numbers() {
		super("Current number " + Integer.toString(0),JLabel.LEFT);
		this.setSize(250,100);
	}
	
	public void newNumber(int num) {
		this.setText("Current number: " + Integer.toString(num));
	}
}