package view;

import javax.swing.JLabel;

import model.Numbers;

public class NumbersGUI extends JLabel implements Observer{
	
	public NumbersGUI() {
		super("Current number: " + Integer.toString(0),JLabel.LEFT);
		this.setSize(250,100);
	}
	
	public void newNumber(int num) {
		this.setText("Current number: " + Integer.toString(num));
	}
	
	public void newNumber(Numbers num) {
		this.setText("Current number: " + Integer.toString(num.toInteger()));
	}
}
